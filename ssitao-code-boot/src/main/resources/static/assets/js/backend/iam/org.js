define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化Tab切换
            Controller.initTab();

            // 初始化各表格
            Controller.initGroupTable();
            Controller.initCompanyTable();
            Controller.initDepartmentTable();
        },

        initTab: function() {
            // Tab切换事件
            $('ul.nav-tabs a').on('shown.bs.tab', function(e) {
                var type = $(this).data('value');
                $("#table-" + type).bootstrapTable('refresh');
            });
        },

        initGroupTable: function() {
            Table.api.init({
                extend: {
                    index_url: '/iam/org/group/list',
                    add_url: 'org-edit.html?type=group',
                    edit_url: 'org-edit.html?type=group',
                    del_url: '/iam/org/group',
                }
            });

            var table = $("#table-group");

            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'createTime',
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (records: 数据列表, totalRow: 总数)
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'groupCode', title: '集团编码', operate: 'LIKE'},
                        {field: 'groupName', title: '集团名称', operate: 'LIKE'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function(value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
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

        initCompanyTable: function() {
            Table.api.init({
                extend: {
                    index_url: '/iam/org/company/list',
                    add_url: 'org-edit.html?type=company',
                    edit_url: 'org-edit.html?type=company',
                    del_url: '/iam/org/company',
                }
            });

            var table = $("#table-company");

            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'createTime',
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (records: 数据列表, totalRow: 总数)
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'companyCode', title: '公司编码', operate: 'LIKE'},
                        {field: 'companyName', title: '公司名称', operate: 'LIKE'},
                        {field: 'groupName', title: '所属集团', operate: false},
                        {field: 'legalPerson', title: '法定代表人'},
                        {field: 'unifiedSocialCreditCode', title: '统一社会信用代码'},
                        {field: 'contactPhone', title: '联系电话'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function(value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
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

        initDepartmentTable: function() {
            Table.api.init({
                extend: {
                    index_url: '/iam/org/department/list',
                    add_url: 'org-edit.html?type=department',
                    edit_url: 'org-edit.html?type=department',
                    del_url: '/iam/org/department',
                }
            });

            var table = $("#table-department");

            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'sort',
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (records: 数据列表, totalRow: 总数)
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'deptCode', title: '部门编码', operate: 'LIKE'},
                        {field: 'deptName', title: '部门名称', operate: 'LIKE'},
                        {field: 'companyName', title: '所属公司', operate: false},
                        {field: 'pid', title: '上级部门ID'},
                        {field: 'manager', title: '负责人'},
                        {field: 'contactPhone', title: '联系电话'},
                        {field: 'sort', title: '排序', sortable: true},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function(value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
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

        add: function() {
            Form.api.bindevent($("form[role=form]"));
        },

        edit: function() {
            Form.api.bindevent($("form[role=form]"));
        }
    };

    return Controller;
});
