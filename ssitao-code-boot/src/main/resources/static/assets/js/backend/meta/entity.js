define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: 'meta/entity/index',
                    add_url: 'meta/entity/add',
                    edit_url: 'meta/entity/edit',
                    del_url: 'meta/entity/del',
                    table: 'meta_entity',
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
                        {field: 'entity_code', title: '实体编码', operate: 'LIKE'},
                        {field: 'entity_name', title: '实体名称', operate: 'LIKE'},
                        {field: 'table_name', title: '表名', operate: 'LIKE'},
                        {field: 'package_name', title: '包名', operate: 'LIKE'},
                        {field: 'module_name', title: '模块名', operate: 'LIKE'},
                        {
                            field: 'category',
                            title: '分类',
                            searchList: {"BUSINESS": "业务", "SYSTEM": "系统", "CONFIG": "配置"},
                            formatter: function (value, row, index) {
                                var map = {"BUSINESS": "success", "SYSTEM": "warning", "CONFIG": "info"};
                                return '<span class="label label-' + (map[value] || 'default') + '">' + (value === 'BUSINESS' ? '业务' : value === 'SYSTEM' ? '系统' : value === 'CONFIG' ? '配置' : value) + '</span>';
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

            // 为表格添加行操作
            table.on('load-success.bs.table', function (e, data) {
                // 可以在这里添加自定义操作
            });

            // 点击行选中checkbox
            table.on('click-row.bs.table', function (e, row, $element) {
                $('.bs-checkbox').attr('name', 'ids');
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
