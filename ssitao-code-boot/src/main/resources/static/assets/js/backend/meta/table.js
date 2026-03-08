define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/meta/table/page',
                    add_url: '/meta/table/add',
                    edit_url: '/meta/table/edit',
                    del_url: '/meta/table/delete',
                    multi_url: '/meta/table/multi',
                    table: 'meta_table',
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
                        {field: 'id', title: '表ID', sortable: true, visible: false},
                        {field: 'table_name', title: '表名称', operate: 'LIKE'},
                        {field: 'table_desc', title: '表描述', operate: 'LIKE'},
                        {field: 'table_type', title: '表类型', sortable: true,
                            formatter: function(value, row, index) {
                                var typeMap = {1: '业务表', 2: '字典表', 3: '树形表', 4: '关联表'};
                                return typeMap[value] || '未知';
                            }
                        },
                        {field: 'class_name', title: '类名', operate: 'LIKE'},
                        {field: 'class_desc', title: '类描述', operate: 'LIKE'},
                        {field: 'module_name', title: '模块名', operate: 'LIKE'},
                        {field: 'author', title: '作者', operate: 'LIKE'},
                        {
                            field: 'enabled',
                            title: '状态',
                            sortable: true,
                            formatter: function(value, row, index) {
                                return value === true || value === 1 ?
                                    '<span class="label label-success">启用</span>' :
                                    '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'generated',
                            title: '已生成',
                            sortable: true,
                            formatter: function(value, row, index) {
                                return value === true || value === 1 ?
                                    '<span class="label label-success">是</span>' :
                                    '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'create_time',
                            title: '创建时间',
                            sortable: true,
                            formatter: function(value, row, index) {
                                return value ? new Date(value).toLocaleString() : '-';
                            }
                        },
                        {
                            field: 'operate',
                            title: '操作',
                            width: '200px',
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
