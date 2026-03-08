define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/meta/list/page',
                    add_url: '/meta/list/add',
                    edit_url: '/meta/list/edit',
                    del_url: '/meta/list/delete',
                    table: 'meta_list',
                },
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return {rows: data, total: data.length};
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return {rows: data.rows, total: data.total};
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
                queryParamsType: 'undefined',
                queryParams: function (params) {
                    return {
                        page: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'entity_id', title: '实体ID', operate: 'LIKE'},
                        {field: 'list_code', title: '列表编码', operate: 'LIKE'},
                        {field: 'list_name', title: '列表名称', operate: 'LIKE'},
                        {
                            field: 'list_type',
                            title: '列表类型',
                            searchList: {"DEFAULT": "默认列表", "TREE": "树形列表", "TABLE": "表格列表", "GROUP": "分组列表"},
                            formatter: function (value, row, index) {
                                var map = {"DEFAULT": "info", "TREE": "success", "TABLE": "primary", "GROUP": "warning"};
                                var typeMap = {"DEFAULT": "默认列表", "TREE": "树形列表", "TABLE": "表格列表", "GROUP": "分组列表"};
                                return '<span class="label label-' + (map[value] || 'default') + '">' + (typeMap[value] || value) + '</span>';
                            }
                        },
                        {
                            field: 'show_row_number',
                            title: '显示行号',
                            formatter: function (value, row, index) {
                                return value === 1 || value === true
                                    ? '<span class="label label-success">是</span>'
                                    : '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'show_checkbox',
                            title: '复选框',
                            formatter: function (value, row, index) {
                                return value === 1 || value === true
                                    ? '<span class="label label-success">是</span>'
                                    : '<span class="label label-default">否</span>';
                            }
                        },
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
