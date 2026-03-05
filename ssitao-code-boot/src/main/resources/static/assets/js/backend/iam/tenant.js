define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/tenant/page',
                    add_url: 'tenant-edit.html',
                    edit_url: 'tenant-edit.html',
                    del_url: '/iam/tenant',
                    multi_url: 'tenant/multi',
                    table: 'iam_tenant',
                },
                // 配置响应数据处理
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        if (res.data.content) {
                            return { rows: res.data.content, total: res.data.totalElements || res.data.total };
                        }
                        return { rows: res.data, total: res.data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'createTime',
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'tenantCode', title: '租户编码', operate: 'LIKE'},
                        {field: 'tenantName', title: '租户名称', operate: 'LIKE'},
                        {field: 'contactName', title: '联系人'},
                        {field: 'contactPhone', title: '联系电话', operate: 'LIKE'},
                        {field: 'contactEmail', title: '联系邮箱', operate: 'LIKE'},
                        {field: 'expireTime', title: '过期时间', operate: 'RANGE', addclass: 'datetimerange'},
                        {field: 'maxUsers', title: '最大用户数'},
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
                            formatter: Table.api.formatter.operate,
                            buttons: [
                                {
                                    name: 'enable',
                                    text: '启用',
                                    title: '启用',
                                    classname: 'btn btn-xs btn-success btn-dialog',
                                    icon: 'fa fa-check',
                                    url: function (row) {
                                        return '/iam/tenant/' + row.id + '/enable';
                                    },
                                    callback: function (data) {
                                        $("#table").bootstrapTable('refresh');
                                    }
                                },
                                {
                                    name: 'disable',
                                    text: '禁用',
                                    title: '禁用',
                                    classname: 'btn btn-xs btn-warning btn-dialog',
                                    icon: 'fa fa-ban',
                                    url: function (row) {
                                        return '/iam/tenant/' + row.id + '/disable';
                                    },
                                    callback: function (data) {
                                        $("#table").bootstrapTable('refresh');
                                    }
                                }
                            ]
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
        }
    };
    return Controller;
});
