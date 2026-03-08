define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'utils'], function ($, undefined, Backend, Table, Form, Utils) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/permission/page',
                    list_url: '/iam/permission/list',
                    add_url: '/iam/permission/add',
                    edit_url: '/iam/permission/edit',
                    del_url: '/iam/permission',
                    enable_url: '/iam/permission/enable',
                    disable_url: '/iam/permission/disable',
                    tree_url: '/iam/permission/tree',
                    table: 'iam_permission',
                },
                // 配置响应数据处理
                responseHandler: function (res) {
                    // 处理后端返回的数据格式
                    if (res.code === 200 && res.data) {
                        // 如果返回的是数组（没有分页信息），转换为bootstrap-table格式
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        // 如果返回的是分页对象
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        if (data.content) {
                            return { rows: data.content, total: data.totalElements || data.content.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'sort_order',
                treeShowField: 'permission_name',
                parentIdField: 'parent_id',
                sidePagination: 'server',
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                queryParamsType: 'undefined',
                queryParams: function (params) {
                    return {
                        page: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'permission_name', title: '权限名称', operate: 'LIKE'},
                        {field: 'permission_code', title: '权限编码', operate: 'LIKE'},
                        {
                            field: 'permission_type',
                            title: '权限类型',
                            searchList: {"MENU": "菜单", "BUTTON": "按钮", "DATA": "数据"},
                            formatter: function (value, row, index) {
                                var colorMap = {
                                    'MENU': 'primary',
                                    'BUTTON': 'success',
                                    'DATA': 'warning'
                                };
                                var color = colorMap[value] || 'default';
                                var labelMap = {
                                    'MENU': '菜单',
                                    'BUTTON': '按钮',
                                    'DATA': '数据'
                                };
                                return '<span class="label label-' + color + '">' + (labelMap[value] || value) + '</span>';
                            }
                        },
                        {field: 'path', title: '路由路径', operate: 'LIKE'},
                        {field: 'icon', title: '图标', formatter: function (value, row, index) {
                            return value ? '<i class="' + value + '"></i>' : '-';
                        }},
                        {field: 'sort_order', title: '排序', sortable: true},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "启用", "false": "禁用"},
                            formatter: function (value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">启用</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'create_time',
                            title: '创建时间',
                            operate: 'RANGE',
                            addclass: 'datetimerange',
                            formatter: Table.api.formatter.datetime
                        },
                        {
                            field: 'operate',
                            title: '操作',
                            table: table,
                            events: Table.api.events.operate,
                            formatter: Table.api.formatter.operate,
                            buttons: [
                                {
                                    name: 'enable',
                                    text: '启用',
                                    title: '启用',
                                    classname: 'btn btn-xs btn-success btn-ajax',
                                    icon: 'fa fa-check',
                                    url: function (row) {
                                        return '/iam/permission/enable?id=' + row.id;
                                    },
                                    success: function (res, data) {
                                        $("#table").bootstrapTable('refresh');
                                    },
                                    visible: function (row) {
                                        return row.status === false;
                                    }
                                },
                                {
                                    name: 'disable',
                                    text: '禁用',
                                    title: '禁用',
                                    classname: 'btn btn-xs btn-warning btn-ajax',
                                    icon: 'fa fa-ban',
                                    url: function (row) {
                                        return '/iam/permission/disable?id=' + row.id;
                                    },
                                    success: function (res, data) {
                                        $("#table").bootstrapTable('refresh');
                                    },
                                    visible: function (row) {
                                        return row.status === true;
                                    }
                                }
                            ]
                        }
                    ]
                ]
            });

            // 启用/禁用按钮事件
            $(document).on("click", ".btn-enable,.btn-disable", function () {
                var url = $(this).attr("href");
                if (url) {
                    Layer.confirm('确定要执行此操作吗？', function (index) {
                        $.ajax({
                            url: url,
                            type: 'POST',
                            dataType: 'json',
                            success: function (res) {
                                if (res.code === 200) {
                                    $("#table").bootstrapTable('refresh');
                                    Layer.close(index);
                                    Toastr.success(res.msg || '操作成功');
                                } else {
                                    Toastr.error(res.msg || '操作失败');
                                }
                            }
                        });
                    });
                }
                return false;
            });

            // 树形按钮点击事件
            $(document).on("click", ".btn-tree", function () {
                Controller.showTree();
            });

            // 为表格绑定事件
            Table.api.bindevent(table);
        },

        // 显示树形结构
        showTree: function () {
            $.ajax({
                url: '/iam/permission/tree',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var treeHtml = Controller.renderTree(res.data);
                        Layer.open({
                            type: 1,
                            title: '权限树',
                            area: ['400px', '500px'],
                            content: '<div class="tree-content" style="padding: 20px; overflow: auto; max-height: 400px;">' + treeHtml + '</div>',
                            btn: ['关闭']
                        });
                    } else {
                        Toastr.error('加载权限树失败');
                    }
                }
            });
        },

        // 渲染树形结构
        renderTree: function (data) {
            if (!data || data.length === 0) return '<p class="text-center text-muted">暂无数据</p>';

            var html = '<ul class="tree-list">';
            for (var i = 0; i < data.length; i++) {
                var item = data[i];
                html += '<li>';
                html += '<div class="tree-item">';
                html += '<i class="' + (item.icon || 'fa fa-folder') + '"></i> ';
                html += '<span class="tree-name">' + item.permissionName + '</span>';
                html += '<span class="tree-code text-muted">(' + item.permissionCode + ')</span>';
                html += '<span class="tree-actions pull-right">';
                html += '<a href="/iam/permission/edit?id=' + item.id + '" class="btn btn-xs btn-primary btn-dialog" title="编辑"><i class="fa fa-edit"></i></a> ';
                html += '<a href="/iam/permission/' + item.id + '" class="btn btn-xs btn-info btn-dialog" title="查看"><i class="fa fa-eye"></i></a>';
                html += '</span>';
                html += '</div>';
                if (item.children && item.children.length > 0) {
                    html += '<ul>' + Controller.renderTree(item.children) + '</ul>';
                }
                html += '</li>';
            }
            html += '</ul>';
            return html;
        },

        add: function () {
            // 获取URL参数
            var getQueryString = function(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURIComponent(r[2]);
                return null;
            };

            // 加载权限树
            var loadPermissionTree = function(selectedId) {
                $.ajax({
                    url: '/iam/permission/tree',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var options = '<option value="0">顶级权限</option>';
                            renderTreeOptions(res.data, options, 0, selectedId);
                            $('#parentId').html(options);
                        }
                    }
                });
            };

            // 递归渲染树形下拉选项
            var renderTreeOptions = function(data, options, level, selectedId) {
                if (!data || data.length === 0) return options;
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    var prefix = '';
                    for (var j = 0; j < level; j++) {
                        prefix += '&nbsp;&nbsp;&nbsp;&nbsp;';
                    }
                    var selected = item.id == selectedId ? ' selected' : '';
                    options += '<option value="' + item.id + '"' + selected + '>' + prefix + (level > 0 ? '├─ ' : '') + item.permissionName + '</option>';
                    if (item.children && item.children.length > 0) {
                        options = renderTreeOptions(item.children, options, level + 1, selectedId);
                    }
                }
                return options;
            };

            // 加载权限树
            loadPermissionTree(0);

            // 添加模式
            $('#edit-form').attr('action', '/iam/permission/create');
            $('#edit-form').attr('method', 'POST');

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();

                var url = $(this).attr('action');
                var method = $(this).attr('method');
                var formData = $(this).serialize();

                $.ajax({
                    url: url,
                    type: method,
                    data: formData,
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('操作成功', {icon: 1});
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.$("#table").bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '操作失败', {icon: 2});
                        }
                    },
                    error: function() {
                        Layer.msg('网络错误', {icon: 2});
                    }
                });
            });

            Form.api.bindevent($("form[role=form]"));
        },
        edit: function () {
            // 获取URL参数
            var getQueryString = function(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURIComponent(r[2]);
                return null;
            };

            // 加载权限树
            var loadPermissionTree = function(selectedId) {
                $.ajax({
                    url: '/iam/permission/tree',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var options = '<option value="0">顶级权限</option>';
                            renderTreeOptions(res.data, options, 0, selectedId);
                            $('#parentId').html(options);
                        }
                    }
                });
            };

            // 递归渲染树形下拉选项
            var renderTreeOptions = function(data, options, level, selectedId) {
                if (!data || data.length === 0) return options;
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    var prefix = '';
                    for (var j = 0; j < level; j++) {
                        prefix += '&nbsp;&nbsp;&nbsp;&nbsp;';
                    }
                    var selected = item.id == selectedId ? ' selected' : '';
                    options += '<option value="' + item.id + '"' + selected + '>' + prefix + (level > 0 ? '├─ ' : '') + item.permissionName + '</option>';
                    if (item.children && item.children.length > 0) {
                        options = renderTreeOptions(item.children, options, level + 1, selectedId);
                    }
                }
                return options;
            };

            var id = Utils.getPkId('id');

            // 加载权限树
            loadPermissionTree(0);

            if (id) {
                // 编辑模式 - 获取数据
                $.ajax({
                    url: '/iam/permission/' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('#permissionCode').val(data.permissionCode);
                            $('#permissionCode').attr('readonly', true);
                            $('#permissionName').val(data.permissionName);
                            $('#permissionType').val(data.permissionType);
                            $('#parentId').val(data.parentId || 0);
                            $('#path').val(data.path);
                            $('#component').val(data.component);
                            $('#icon').val(data.icon);
                            $('#sortOrder').val(data.sortOrder || 0);
                            $('input[name="status"][value="' + data.status + '"]').prop('checked', true);
                            $('#remark').val(data.remark);
                        }
                    }
                });

                // 设置表单提交地址
                $('#edit-form').attr('action', '/iam/permission/update');
                $('#edit-form').attr('method', 'PUT');
            }

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();

                var url = $(this).attr('action');
                var method = $(this).attr('method');
                var formData = $(this).serialize();

                $.ajax({
                    url: url,
                    type: method,
                    data: formData,
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('操作成功', {icon: 1});
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.$("#table").bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '操作失败', {icon: 2});
                        }
                    },
                    error: function() {
                        Layer.msg('网络错误', {icon: 2});
                    }
                });
            });

            Form.api.bindevent($("form[role=form]"));
        },
        api: {
            formatter: {
                status: function (value, row, index) {
                    return value === true
                        ? '<span class="label label-success">启用</span>'
                        : '<span class="label label-default">禁用</span>';
                },
                permissionType: function (value, row, index) {
                    var colorMap = {
                        'MENU': 'primary',
                        'BUTTON': 'success',
                        'DATA': 'warning'
                    };
                    var labelMap = {
                        'MENU': '菜单',
                        'BUTTON': '按钮',
                        'DATA': '数据'
                    };
                    return '<span class="label label-' + (colorMap[value] || 'default') + '">' + (labelMap[value] || value) + '</span>';
                }
            }
        }
    };
    return Controller;
});
