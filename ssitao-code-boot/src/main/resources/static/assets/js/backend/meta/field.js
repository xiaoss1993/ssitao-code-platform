define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: 'meta/field/index',
                    add_url: 'meta/field/add',
                    edit_url: 'meta/field/edit',
                    del_url: 'meta/field/del',
                    table: 'meta_field',
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
                sortName: 'sort',
                sidePagination: 'server',
                pagination: true,
                pageSize: 20,
                pageList: [10, 20, 50, 100],
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
                        {field: 'field_code', title: '字段编码', operate: 'LIKE'},
                        {field: 'field_name', title: '字段名称', operate: 'LIKE'},
                        {
                            field: 'field_type',
                            title: '字段类型',
                            searchList: {
                                "VARCHAR": "字符串",
                                "INTEGER": "整数",
                                "BIGINT": "长整数",
                                "DECIMAL": "小数",
                                "DATE": "日期",
                                "DATETIME": "日期时间",
                                "TEXT": "文本",
                                "LONGTEXT": "长文本",
                                "JSON": "JSON"
                            },
                            formatter: function (value, row, index) {
                                return value || '-';
                            }
                        },
                        {field: 'java_type', title: 'Java类型'},
                        {field: 'default_value', title: '默认值'},
                        {
                            field: 'primary_key',
                            title: '主键',
                            formatter: function (value, row, index) {
                                return value === 1 || value === true
                                    ? '<span class="label label-success">是</span>'
                                    : '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'required',
                            title: '必填',
                            formatter: function (value, row, index) {
                                return value === 1 || value === true
                                    ? '<span class="label label-success">是</span>'
                                    : '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'show_in_list',
                            title: '列表显示',
                            formatter: function (value, row, index) {
                                return value === 1 || value === true
                                    ? '<span class="label label-success">是</span>'
                                    : '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'show_in_form',
                            title: '表单显示',
                            formatter: function (value, row, index) {
                                return value === 1 || value === true
                                    ? '<span class="label label-success">是</span>'
                                    : '<span class="label label-default">否</span>';
                            }
                        },
                        {field: 'sort', title: '排序', sortable: true},
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
