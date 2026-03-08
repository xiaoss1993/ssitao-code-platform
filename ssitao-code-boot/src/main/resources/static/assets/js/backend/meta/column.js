define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/meta/column/page',
                    add_url: '/meta/column/add',
                    edit_url: '/meta/column/edit',
                    del_url: '/meta/column/delete',
                    multi_url: '/meta/column/multi',
                    table: 'meta_column',
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
                        {field: 'id', title: '字段ID', sortable: true, visible: false},
                        {field: 'column_name', title: '字段名', operate: 'LIKE'},
                        {field: 'column_desc', title: '字段描述', operate: 'LIKE'},
                        {field: 'column_type', title: '字段类型', sortable: true},
                        {field: 'java_type', title: 'Java类型'},
                        {field: 'java_field', title: 'Java字段名'},
                        {
                            field: 'is_pk',
                            title: '主键',
                            formatter: function(value, row, index) {
                                return value === true || value === 1 ?
                                    '<span class="label label-success">是</span>' :
                                    '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'is_required',
                            title: '必填',
                            formatter: function(value, row, index) {
                                return value === true || value === 1 ?
                                    '<span class="label label-success">是</span>' :
                                    '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'is_list',
                            title: '列表显示',
                            formatter: function(value, row, index) {
                                return value === true || value === 1 ?
                                    '<span class="label label-success">是</span>' :
                                    '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'is_query',
                            title: '可查询',
                            formatter: function(value, row, index) {
                                return value === true || value === 1 ?
                                    '<span class="label label-success">是</span>' :
                                    '<span class="label label-default">否</span>';
                            }
                        },
                        {field: 'query_type', title: '查询方式'},
                        {
                            field: 'operate',
                            title: '操作',
                            width: '150px',
                            table: table,
                            events: Table.api.events.operate,
                            formatter: Table.api.formatter.operate
                        }
                    ]
                ]
            });

            // 为表格添加事件
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
