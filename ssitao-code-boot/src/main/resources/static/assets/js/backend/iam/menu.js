define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'ztree'], function ($, undefined, Backend, Table, Form) {

    // 格式化树数据
    function formatTreeData(data) {
        return data.map(function(item) {
            var node = {
                id: item.menuId || item.id,
                name: item.menuName || item.name,
                pId: item.menuParentId || item.parentId || '0',
                isParent: item.menuType === 'DIRECTORY' || item.menuType === 'MENU',
                open: false
            };
            return node;
        });
    }

    var Controller = {
        index: function () {
            var currentMenuId = null; // 当前选中的菜单ID

            // 初始化zTree
            var zTreeObj;
            var zTreeSettings = {
                view: {
                    dblClickExpand: false,
                    showLine: true,
                    selectedMulti: false
                },
                async: {
                    enable: true,
                    url: '/iam/menu/tree',
                    autoParam: ["id"],
                    dataFilter: function (treeId, parentNode, responseData) {
                        if (responseData.code === 200 && responseData.data) {
                            return responseData.data;
                        }
                        return [];
                    }
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        currentMenuId = treeNode.id;
                        // 点击节点时刷新右侧表格，显示该节点下的子菜单
                        Table.api.reload(table, {parentId: currentMenuId});
                    },
                    onAsyncSuccess: function (event, treeId, treeNode, msg) {
                        zTreeObj.expandAll(true);
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
                            // 默认展开第一级
                            var nodes = zTreeObj.getNodes();
                            if (nodes.length > 0) {
                                zTreeObj.expandNode(nodes[0], true);
                            }
                        }
                    }
                });
            }

            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/menu/list',
                    add_url: '/menu-edit.html',
                    edit_url: '/menu-edit.html?id=',
                    del_url: '/iam/menu/',
                    multi_url: '/iam/menu/multi',
                    table: 'iam_menu',
                },
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
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

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'menuId',
                sortName: 'menuSort',
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'menuId', title: 'ID', sortable: true},
                        {
                            field: 'menuName',
                            title: '菜单名称',
                            formatter: function(value, row, index) {
                                var icon = row.menuIcon ? '<i class="fa ' + row.menuIcon + '"></i> ' : '';
                                return icon + value;
                            }
                        },
                        {
                            field: 'menuType',
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
                        {field: 'menuPath', title: '路由地址', operate: 'LIKE'},
                        {field: 'menuPermission', title: '权限标识', operate: 'LIKE'},
                        {field: 'menuIcon', title: '图标', formatter: function(value, row, index) {
                            return value ? '<i class="fa ' + value + '"></i>' : '-';
                        }},
                        {field: 'menuSort', title: '排序', sortable: true},
                        {
                            field: 'menuStatus',
                            title: '状态',
                            searchList: {"1": "正常", "0": "禁用"},
                            formatter: function (value, row, index) {
                                return value == 1
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {field: 'createTime', title: '创建时间', operate: 'RANGE', addclass: 'datetimerange', formatter: Table.api.formatter.datetime},
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
            Form.api.bindevent($("form[role=form]"));
        },
        edit: function () {
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
