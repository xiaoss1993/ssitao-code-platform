/**
 * 代码生成JS
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格
            Table.api.init({
                extend: {
                    index_url: 'meta/table/index',
                    add_url: 'meta/table/add',
                    edit_url: 'meta/table/edit',
                    del_url: 'meta/table/del',
                    multi_url: 'meta/table/multi',
                    table: 'meta_table',
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
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'table_name', title: '表名', operate: 'LIKE'},
                        {field: 'table_desc', title: '表描述', operate: 'LIKE'},
                        {field: 'class_name', title: '实体类名', operate: 'LIKE'},
                        {field: 'author', title: '作者', operate: 'LIKE'},
                        {
                            field: 'enabled',
                            title: '状态',
                            searchList: {"1": "启用", "0": "禁用"},
                            formatter: function (value) {
                                return value == 1 ? '<span class="label label-success">启用</span>' : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'operate',
                            title: '操作',
                            table: table,
                            events: Table.api.events.operate,
                            formatter: Table.api.formatter.operate,
                            buttons: [
                                {
                                    name: 'generate',
                                    text: '生成代码',
                                    title: '生成代码',
                                    classname: 'btn btn-xs btn-primary btn-dialog',
                                    icon: 'fa fa-code',
                                    url: 'meta/code/generate'
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
