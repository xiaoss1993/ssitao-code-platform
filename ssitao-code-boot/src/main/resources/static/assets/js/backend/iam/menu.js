define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/menu/list',
                    tree_url: '/iam/menu/tree',
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
                treeShowField: 'menuName',
                parentIdField: 'parent_id',
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {
                            field: 'menuName',
                            title: '菜单名称',
                            formatter: function(value, row, index) {
                                var icon = row.icon ? '<i class="fa ' + row.icon + '"></i> ' : '';
                                return icon + value;
                            }
                        },
                        {
                            field: 'menuType',
                            title: '菜单类型',
                            searchList: {"MENU": "菜单", "BUTTON": "按钮"},
                            formatter: function (value, row, index) {
                                return value === 'MENU'
                                    ? '<span class="label label-primary">菜单</span>'
                                    : '<span class="label label-success">按钮</span>';
                            }
                        },
                        {field: 'path', title: '路由地址', operate: 'LIKE'},
                        {field: 'perms', title: '权限标识', operate: 'LIKE'},
                        {field: 'icon', title: '图标', formatter: function(value, row, index) {
                            return value ? '<i class="fa ' + value + '"></i>' : '-';
                        }},
                        {field: 'sort_order', title: '排序', sortable: true},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function (value, row, index) {
                                return value === true || value === 1
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
                ],
                // 启用树形表格
                treeEnable: true,
                onLoadSuccess: function(data) {
                    // 树形表格初始化后展开所有
                    table.treegrid({
                        treeColumn: 1,
                        onChange: function() {
                            table.bootstrapTable('resetWidth');
                        }
                    });
                }
            });

            // 展开/折叠事件
            $(document).on('click', '.btn-expand', function() {
                table.treegrid('expandAll');
            });

            $(document).on('click', '.btn-collapse', function() {
                table.treegrid('collapseAll');
            });

            // 绑定添加按钮事件
            $(document).on('click', '.btn-add', function() {
                var parentId = Table.api.selectedids(table)[0];
                var url = $.fn.bootstrapTable.defaults.extend.add_url;
                if (parentId) {
                    url += '&parentId=' + parentId;
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

            // 为表格绑定事件
            Table.api.bindevent(table);
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
                    return value === true || value === 1
                        ? '<span class="label label-success">正常</span>'
                        : '<span class="label label-default">禁用</span>';
                },
                icon: function(value, row, index) {
                    return value ? '<i class="fa ' + value + '"></i>' : '-';
                },
                menuType: function(value, row, index) {
                    return value === 'MENU'
                        ? '<span class="label label-primary">菜单</span>'
                        : '<span class="label label-success">按钮</span>';
                }
            }
        }
    };
    return Controller;
});
