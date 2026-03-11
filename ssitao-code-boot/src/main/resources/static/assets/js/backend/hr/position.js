define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            Controller.initTable();
        },

        initTable: function () {
            Table.api.init({
                extend: {
                    index_url: '/hr/org/position/list',
                    add_url: 'hr/position/add',
                    edit_url: 'hr/position/edit',
                    del_url: '/hr/org/position',
                    table: 'hr_position',
                },
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table");
            var tenantId = Utils.getQueryString('tenantId') || '1';

            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'positionSort',
                sidePagination: 'server',
                queryParamsType: 'undefined',
                queryParams: function (params) {
                    return {
                        page: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder,
                        tenantId: tenantId
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true, visible: false},
                        {field: 'positionCode', title: '岗位编码', operate: 'LIKE'},
                        {field: 'positionName', title: '岗位名称', operate: 'LIKE'},
                        {field: 'deptId', title: '所属部门ID', visible: false},
                        {field: 'positionLevel', title: '职级', operate: 'LIKE'},
                        {
                            field: 'positionType',
                            title: '岗位类型',
                            searchList: {
                                "MANAGEMENT": "管理岗",
                                "PROFESSIONAL": "专业岗",
                                "OPERATIONS": "操作岗"
                            },
                            formatter: function(value) {
                                var types = {
                                    "MANAGEMENT": "管理岗",
                                    "PROFESSIONAL": "专业岗",
                                    "OPERATIONS": "操作岗"
                                };
                                return types[value] || value;
                            }
                        },
                        {field: 'positionSummary', title: '岗位职责', operate: 'LIKE'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"NORMAL": "正常", "DISABLED": "停用"},
                            formatter: function(value) {
                                return value === 'NORMAL'
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">停用</span>';
                            }
                        },
                        {
                            field: 'positionSort',
                            title: '排序',
                            sortable: true
                        },
                        {
                            field: 'createTime',
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
                            formatter: Table.api.formatter.operate
                        }
                    ]
                ]
            });

            Table.api.bindevent(table);
        },

        add: function () {
            Controller.api.bindevent();
        },

        edit: function () {
            Controller.api.bindevent();
        },

        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"));
            }
        }
    };

    return Controller;
});
