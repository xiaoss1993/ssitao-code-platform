define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'treegrid'], function ($, undefined, Backend, Table, Form, Treegrid) {

    var Controller = {
        index: function () {
            // 初始化表格
            Controller.initTable();
        },

        initTable: function () {
            Table.api.init({
                extend: {
                    index_url: '/hr/org/department/list',
                    tree_url: '/hr/org/department/tree',
                    add_url: 'hr/department/add',
                    edit_url: 'hr/department/edit',
                    del_url: '/hr/org/department',
                    multi_url: 'hr/department/multi',
                    table: 'hr_department',
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

            // 获取URL参数中的tenantId
            var tenantId = Utils.getQueryString('tenantId') || '1';

            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'deptSort',
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
                treeShowField: 'deptName',
                parentIdField: 'parentId',
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true, visible: false},
                        {field: 'deptCode', title: '部门编码', operate: 'LIKE'},
                        {field: 'deptName', title: '部门名称', operate: 'LIKE'},
                        {
                            field: 'deptLevel',
                            title: '层级',
                            formatter: function(value, row, index) {
                                return '第 ' + value + ' 级';
                            }
                        },
                        {field: 'deptLeader', title: '负责人', operate: 'LIKE'},
                        {field: 'phone', title: '联系电话', operate: 'LIKE'},
                        {field: 'email', title: '邮箱', operate: 'LIKE'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"NORMAL": "正常", "DISABLED": "停用"},
                            formatter: function(value, row, index) {
                                return value === 'NORMAL'
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">停用</span>';
                            }
                        },
                        {
                            field: 'deptSort',
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

            // 启用树形表格
            table.treegrid({
                treeColumn: 2,
                onChange: function() {
                    table.bootstrapTable('resetWidth');
                }
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
