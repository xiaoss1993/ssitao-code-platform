define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'bootstrap-treegrid', 'utils'], function ($, undefined, Backend, Table, Form, Utils) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/permission/list',
                    add_url: '/iam/permission',
                    edit_url: '/iam/permission/edit',
                    del_url: '/iam/permission',
                    enable_url: '/iam/permission/enable',
                    disable_url: '/iam/permission/disable',
                    table: 'iam_permission',
                }
            });

            var table = $("#table");

            // 初始化表格 - 使用 bootstrap-treegrid 树形表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'sortOrder',
                // 响应数据处理
                responseHandler: function (res) {
                    console.log('API Response:', res);
                    if (res.code === 200 && res.data && Array.isArray(res.data)) {
                        console.log('Processed data:', res.data);
                        return res.data; // 直接返回数组
                    }
                    return [];
                },
                // 树形表格配置
                treeView: true,
                treeId: 'id',
                treeField: 'permName',
                treeParentId: 'parentId',
                parentIdInit: 0,
                treeRootLevel: 1,
                uniqueId: 'id',
                // 分页配置 - 禁用分页，因为树形数据不适合分页
                sidePagination: 'client',
                pagination: false,
                // 搜索配置
                search: true,
                searchText: '',
                // 排序配置
                sortable: true,
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', visible: false},
                        {field: 'parentId', title: '父级ID', visible: false},
                        {
                            field: 'permName',
                            title: '权限名称',
                            operate: 'LIKE',
                            formatter: function (value, row, index) {
                                // 根据权限类型显示不同图标
                                var iconMap = {
                                    'MENU': 'fa fa-folder',
                                    'BUTTON': 'fa fa-square-o',
                                    'DATA': 'fa fa-database'
                                };
                                var icon = iconMap[row.permType] || 'fa fa-circle-o';
                                // 如果有自定义图标，使用自定义图标
                                if (row.icon) {
                                    icon = row.icon;
                                }
                                return '<i class="' + icon + '"></i> ' + value;
                            }
                        },
                        {
                            field: 'permCode',
                            title: '权限编码',
                            operate: 'LIKE',
                            formatter: function (value, row, index) {
                                return value ? '<code>' + value + '</code>' : '-';
                            }
                        },
                        {
                            field: 'permType',
                            title: '类型',
                            width: 80,
                            searchList: {"MENU": "菜单", "BUTTON": "按钮", "DATA": "数据"},
                            formatter: function (value, row, index) {
                                var colorMap = {
                                    'MENU': 'primary',
                                    'BUTTON': 'success',
                                    'DATA': 'warning'
                                };
                                var color = colorMap[value] || 'default';
                                return '<span class="label label-' + color + '">' + value + '</span>';
                            }
                        },
                        {
                            field: 'path',
                            title: '路由',
                            visible: false,
                            operate: 'LIKE'
                        },
                        {
                            field: 'sortOrder',
                            title: '排序',
                            width: 80,
                            sortable: true
                        },
                        {
                            field: 'status',
                            title: '状态',
                            width: 80,
                            searchList: {"true": "启用", "false": "禁用"},
                            formatter: function (value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">启用</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'operate',
                            title: '操作',
                            width: 150,
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

            var id = getQueryString('id');

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
