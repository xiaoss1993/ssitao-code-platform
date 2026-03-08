define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: 'meta/form/index',
                    add_url: 'meta/form/add',
                    edit_url: 'meta/form/edit',
                    del_url: 'meta/form/del',
                    table: 'meta_form',
                },
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return {rows: data, total: data.length};
                        }
                        if (data.records) {
                            return {rows: data.records, total: data.totalRow || data.records.length};
                        }
                        return {rows: data, total: data.length};
                    }
                    return {rows: [], total: 0};
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'create_time',
                sidePagination: 'server',
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'entity_id', title: '实体ID', operate: 'LIKE'},
                        {field: 'form_code', title: '表单编码', operate: 'LIKE'},
                        {field: 'form_name', title: '表单名称', operate: 'LIKE'},
                        {
                            field: 'form_type',
                            title: '表单类型',
                            searchList: {"DEFAULT": "默认表单", "DIALOG": "弹窗表单", "DRAWER": "抽屉表单", "STEPS": "步骤表单"},
                            formatter: function (value, row, index) {
                                var map = {"DEFAULT": "info", "DIALOG": "primary", "DRAWER": "warning", "STEPS": "success"};
                                var typeMap = {"DEFAULT": "默认表单", "DIALOG": "弹窗表单", "DRAWER": "抽屉表单", "STEPS": "步骤表单"};
                                return '<span class="label label-' + (map[value] || 'default') + '">' + (typeMap[value] || value) + '</span>';
                            }
                        },
                        {field: 'layout', title: '布局'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"1": "启用", "0": "禁用"},
                            formatter: function (value, row, index) {
                                return value === 1
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
                Form.api.bindevent();
            }
        }
    };
    return Controller;
});
