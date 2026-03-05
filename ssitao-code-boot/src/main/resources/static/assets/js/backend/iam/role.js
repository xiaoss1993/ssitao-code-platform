define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/role/page',
                    add_url: '/admin/role/add',
                    edit_url: '/admin/role/edit',
                    del_url: '/iam/role/delete',
                    multi_url: '/iam/role/multi',
                    table: 'iam_role',
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
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'role_code', title: '角色编码', operate: 'LIKE'},
                        {field: 'role_name', title: '角色名称', operate: 'LIKE'},
                        {field: 'role_type', title: '角色类型', searchList: {"SYSTEM": "系统角色", "CUSTOM": "自定义角色"},
                            formatter: function (value, row, index) {
                                return value === 'SYSTEM'
                                    ? '<span class="label label-danger">系统角色</span>'
                                    : '<span class="label label-primary">自定义角色</span>';
                            }
                        },
                        {field: 'data_scope', title: '数据范围', searchList: {"ALL": "全部", "DEPT": "本部门", "DEPT_AND_CHILD": "本部门及子部门", "SELF": "本人"},
                            formatter: function (value, row, index) {
                                var scopeMap = {
                                    "ALL": "全部",
                                    "DEPT": "本部门",
                                    "DEPT_AND_CHILD": "本部门及子部门",
                                    "SELF": "本人"
                                };
                                return scopeMap[value] || value;
                            }
                        },
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
                        {field: 'sort_order', title: '排序', sortable: true},
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
                }
            }
        }
    };
    return Controller;
});
