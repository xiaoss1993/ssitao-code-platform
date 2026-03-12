define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'ztree', 'utils'], function ($, undefined, Backend, Table, Form, Ztree, Utils) {

    // 格式化树数据
    function formatTreeData(data) {
        if (!data || !Array.isArray(data)) {
            return [];
        }
        return data.map(function(item) {
            // 同时支持 snake_case 和 camelCase 两种格式
            var menuName = item.menu_name || item.menuName || '';
            var parentId = item.parent_id || item.parentId || '0';
            var menuType = item.menu_type || item.menuType || '';

            var node = {
                id: item.id,
                name: menuName,
                pId: parentId,
                isParent: menuType === 'DIRECTORY' || menuType === 'MENU' || (item.children && item.children.length > 0),
                open: false
            };
            // 递归处理子节点
            if (item.children && item.children.length > 0) {
                node.children = formatTreeData(item.children);
            }
            return node;
        });
    }

    var Controller = {
        index: function () {
            var currentMenuId = null; // 当前选中的菜单ID
            var table = $("#table"); // 表格实例

            // 初始化zTree
            var zTreeObj;
            var zTreeSettings = {
                view: {
                    dblClickExpand: false,
                    showLine: true,
                    selectedMulti: false,
                    nameIsHTML: true
                },
                async: {
                    enable: false
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        currentMenuId = treeNode.id;
                        // 点击节点时刷新右侧表格，显示该节点下的子菜单
                        table.bootstrapTable('refresh');
                    }
                }
            };

            // 加载树数据
            function loadTree() {
                $.ajax({
                    url: '/iam/menu/tree',
                    type: 'get',
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 200 && res.data) {
                            // 转换数据格式
                            var treeData = formatTreeData(res.data);
                            zTreeObj = $.fn.zTree.init($("#menuTree"), zTreeSettings, treeData);
                            // 默认不展开，需要用户手动点击展开
                        }
                    }
                });
            }

            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/menu/list',
                    add_url: '/iam/menu/add',
                    edit_url: '/iam/menu/edit',
                    del_url: '/iam/menu',
                    table: 'iam_menu',
                },
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
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

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'sort_order',
                sidePagination: 'server',
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                queryParamsType: 'undefined',
                // 查询参数函数
                queryParams: function (params) {
                    return {
                        page: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder,
                        parentId: currentMenuId
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {
                            field: 'menu_name',
                            title: '菜单名称',
                            formatter: function(value, row, index) {
                                var icon = row.icon ? '<i class="fa ' + row.icon + '"></i> ' : '';
                                return icon + value;
                            }
                        },
                        {
                            field: 'menu_type',
                            title: '菜单类型',
                            searchList: {"DIRECTORY": "目录", "MENU": "菜单", "BUTTON": "按钮"},
                            formatter: function (value, row, index) {
                                var typeMap = {
                                    'DIRECTORY': '<span class="label label-info">目录</span>',
                                    'MENU': '<span class="label label-primary">菜单</span>',
                                    'BUTTON': '<span class="label label-success">按钮</span>'
                                };
                                return typeMap[value] || value;
                            }
                        },
                        {field: 'path', title: '路由地址', operate: 'LIKE'},
                        {field: 'permission', title: '权限标识', operate: 'LIKE'},
                        {field: 'icon', title: '图标', formatter: function(value, row, index) {
                            return value ? '<i class="fa ' + value + '"></i>' : '-';
                        }},
                        {field: 'sort_order', title: '排序', sortable: true},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"1": "正常", "0": "禁用"},
                            formatter: function (value, row, index) {
                                return value == 1
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {field: 'create_time', title: '创建时间', operate: 'RANGE', addclass: 'datetimerange', formatter: Table.api.formatter.datetime},
                        {
                            field: 'operate',
                            title: '操作',
                            table: table,
                            events: Table.api.events.operate,
                            formatter: Table.api.formatter.operate
                        }
                    ]
                ]
            });

            // 刷新树
            $(document).on('click', '.btn-refresh-tree', function() {
                loadTree();
            });

            // 添加根菜单（点击左侧树上面的加号按钮）
            $(document).on('click', '.btn-add-root', function() {
                var url = '/menu-edit.html';
                Layer.open({
                    type: 2,
                    title: '添加根菜单',
                    shadeClose: true,
                    shade: 0.3,
                    area: ['600px', '80%'],
                    content: url
                });
            });

            // 添加子菜单（点击右侧表格的添加按钮）
            $(document).on('click', '.btn-add', function() {
                var url = '/menu-edit.html';
                // 如果有选中的节点，添加子菜单
                if (currentMenuId) {
                    url += '?parentId=' + currentMenuId;
                }
                Layer.open({
                    type: 2,
                    title: '添加菜单',
                    shadeClose: true,
                    shade: 0.3,
                    area: ['600px', '80%'],
                    content: url
                });
            });

            // 编辑按钮
            $(document).on('click', '.btn-edit', function() {
                var ids = Table.api.selectedids(table);
                if (ids.length === 0) {
                    Layer.msg('请至少选择一条记录');
                    return false;
                }
                if (ids.length > 1) {
                    Layer.msg('只能选择一条记录');
                    return false;
                }
                var url = '/menu-edit.html?id=' + ids[0];
                Layer.open({
                    type: 2,
                    title: '编辑菜单',
                    shadeClose: true,
                    shade: 0.3,
                    area: ['600px', '80%'],
                    content: url
                });
            });

            // 删除按钮
            $(document).on('click', '.btn-del', function() {
                var ids = Table.api.selectedids(table);
                if (ids.length === 0) {
                    Layer.msg('请至少选择一条记录');
                    return false;
                }
                Layer.confirm('确认删除选中的菜单吗？', function() {
                    var url = $.fn.bootstrapTable.defaults.extend.del_url;
                    $.ajax({
                        url: url,
                        type: 'post',
                        data: {ids: ids.join(',')},
                        dataType: 'json',
                        success: function(res) {
                            if (res.code === 200) {
                                Layer.msg('删除成功');
                                table.bootstrapTable('refresh');
                                loadTree(); // 刷新树
                            } else {
                                Layer.msg(res.msg || '删除失败');
                            }
                        }
                    });
                });
            });

            // 刷新按钮
            $(document).on('click', '.btn-refresh', function() {
                table.bootstrapTable('refresh');
            });

            // 为表格绑定事件
            Table.api.bindevent(table);

            // 初始加载树
            loadTree();
        },
        add: function () {
            // 获取URL参数
            var getQueryString = function(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURIComponent(r[2]);
                return null;
            };

            var parentId = getQueryString('parentId');
            loadMenuTree(parentId || 0);
            $('#edit-form').attr('action', '/iam/menu');
            $('#edit-form').attr('method', 'POST');

            // 菜单类型切换
            $('#menuType').change(function() {
                var type = $(this).val();
                if (type === 'BUTTON') {
                    $('.menu-field').hide();
                    $('.button-field').show();
                } else {
                    $('.menu-field').show();
                    $('.button-field').hide();
                }
            });

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();
                var url = $(this).attr('action');
                var method = $(this).attr('method');

                var formData = {
                    menuName: $('#menuName').val(),
                    parentId: parseInt($('#parentId').val()) || 0,
                    menuType: $('#menuType').val(),
                    path: $('#path').val(),
                    component: $('#component').val(),
                    icon: $('#icon').val(),
                    perms: $('#perms').val(),
                    sortOrder: parseInt($('#sortOrder').val()) || 0,
                    status: $('input[name="status"]:checked').val() === 'true',
                    remark: $('#remark').val()
                };

                $.ajax({
                    url: url,
                    type: method,
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
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
            // 使用通用方法获取主键ID
            var id = Utils.getPkId('id');

            // 加载菜单树
            var loadMenuTree = function(selectedId) {
                $.ajax({
                    url: '/iam/menu/tree',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var options = '<option value="0">顶级菜单</option>';
                            var processedIds = {}; // 防止循环引用
                            var renderTree = function(nodes, level) {
                                if (level > 10) return ''; // 防止无限递归
                                var result = '';
                                var indent = '';
                                for (var i = 0; i < level; i++) {
                                    indent += '　';
                                }
                                if (!nodes || !Array.isArray(nodes)) return result;
                                $.each(nodes, function(idx, node) {
                                    if (!node || !node.id || processedIds[node.id]) return;
                                    processedIds[node.id] = true;

                                    var selected = selectedId && String(node.id) === String(selectedId) ? 'selected' : '';
                                    // 统一获取菜单名称
                                    var menuName = node.name || node.menu_name || node.menuName || '';
                                    // 排除自身和子节点，防止选择自己作为父级
                                    if (!selectedId || (String(node.id) !== String(selectedId) && !isChildNode(node, selectedId))) {
                                        result += '<option value="' + node.id + '" ' + selected + '>' + indent + (level > 0 ? '├ ' : '') + menuName + '</option>';
                                    }
                                    if (node.children && node.children.length > 0) {
                                        result += renderTree(node.children, level + 1);
                                    }
                                });
                                return result;
                            };
                            options += renderTree(res.data, 0);
                            $('#parentId').html(options).selectpicker('refresh');
                        }
                    }
                });
            };

            // 判断是否为子节点（防止无限递归）
            var isChildNode = function(node, parentId) {
                if (!node || !node.children || node.children.length === 0) return false;
                var targetId = String(parentId);
                var checkedIds = {};
                var checkNode = function(n, depth) {
                    if (depth > 10 || !n || !n.children) return false;
                    for (var i = 0; i < n.children.length; i++) {
                        var child = n.children[i];
                        if (!child || !child.id) continue;
                        if (String(child.id) === targetId) return true;
                        if (!checkedIds[child.id] && child.children && child.children.length > 0) {
                            checkedIds[child.id] = true;
                            if (checkNode(child, depth + 1)) return true;
                        }
                    }
                    return false;
                };
                return checkNode(node, 0);
            };

            // 初始化父级菜单树
            loadMenuTree(id || 0);

            // 根据是否有ID判断是新增还是编辑模式
            if (id) {
                // 编辑模式
                // 编辑模式
                $.ajax({
                    url: '/iam/menu/' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('#menuName').val(data.menuName);
                            $('#menuType').val(data.menuType || 'MENU');
                            $('#icon').val(data.icon || '');
                            $('#path').val(data.path || '');
                            $('#component').val(data.component || '');
                            $('#perms').val(data.perms || '');
                            $('#sortOrder').val(data.sortOrder || 0);
                            $('input[name="status"][value="' + (data.status === true || data.status === 1 ? 'true' : 'false') + '"]').prop('checked', true);
                            $('#remark').val(data.remark || '');

                            // 菜单类型切换
                            $('#menuType').trigger('change');

                            // 加载父级菜单树（排除自身）
                            loadMenuTree(data.parentId);
                        }
                    }
                });
                $('#edit-form').attr('action', '/iam/menu');
                $('#edit-form').attr('method', 'PUT');
            } else {
                // 新增模式
                $('#edit-form').attr('action', '/iam/menu');
                $('#edit-form').attr('method', 'POST');
            }

            // 菜单类型切换
            $('#menuType').change(function() {
                var type = $(this).val();
                if (type === 'BUTTON') {
                    $('.menu-field').hide();
                    $('.button-field').show();
                } else {
                    $('.menu-field').show();
                    $('.button-field').hide();
                }
            });

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();
                var url = $(this).attr('action');
                var method = $(this).attr('method');

                var formData = {
                    menuName: $('#menuName').val(),
                    parentId: parseInt($('#parentId').val()) || 0,
                    menuType: $('#menuType').val(),
                    path: $('#path').val(),
                    component: $('#component').val(),
                    icon: $('#icon').val(),
                    perms: $('#perms').val(),
                    sortOrder: parseInt($('#sortOrder').val()) || 0,
                    status: $('input[name="status"]:checked').val() === 'true',
                    remark: $('#remark').val()
                };

                if (id) {
                    formData.id = id;
                }

                $.ajax({
                    url: url,
                    type: method,
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
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
                    return value == 1
                        ? '<span class="label label-success">正常</span>'
                        : '<span class="label label-default">禁用</span>';
                },
                icon: function(value, row, index) {
                    return value ? '<i class="fa ' + value + '"></i>' : '-';
                },
                menuType: function(value, row, index) {
                    var typeMap = {
                        'DIRECTORY': '<span class="label label-info">目录</span>',
                        'MENU': '<span class="label label-primary">菜单</span>',
                        'BUTTON': '<span class="label label-success">按钮</span>'
                    };
                    return typeMap[value] || value;
                }
            }
        }
    };
    return Controller;
});
