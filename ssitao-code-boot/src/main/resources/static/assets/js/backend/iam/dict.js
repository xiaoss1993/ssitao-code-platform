/**
 * 字典管理JS
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格
            Table.api.init({
                extend: {
                    index_url: 'iam/system/dict-type/index',
                    add_url: 'iam/system/dict-type/add',
                    edit_url: 'iam/system/dict-type/edit',
                    del_url: 'iam/system/dict-type/del',
                    multi_url: 'iam/system/dict-type/multi',
                    table: 'core_dictionary',
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'dict_id',
                sortName: 'dict_sort',
                sidePagination: 'server',
                pagination: true,
                pageSize: 10,
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'dict_id', title: 'ID', sortable: true},
                        {field: 'dict_code', title: '字典编码', operate: 'LIKE'},
                        {field: 'dict_name', title: '字典名称', operate: 'LIKE'},
                        {
                            field: 'dict_type',
                            title: '字典类型',
                            searchList: {"SYSTEM": "系统字典", "BUSINESS": "业务字典"}
                        },
                        {
                            field: 'dict_source',
                            title: '数据来源',
                            searchList: {"CUSTOM": "自定义", "SQL": "SQL查询", "API": "接口调用"}
                        },
                        {
                            field: 'dict_status',
                            title: '状态',
                            searchList: {"1": "启用", "0": "禁用"},
                            formatter: function (value) {
                                return value == 1 ? '<span class="label label-success">启用</span>' : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {field: 'dict_sort', title: '排序', sortable: true},
                        {
                            field: 'operate',
                            title: '操作',
                            table: table,
                            events: Table.api.events.operate,
                            formatter: Table.api.formatter.operate,
                            buttons: [
                                {
                                    name: 'items',
                                    text: '字典项',
                                    title: '字典项管理',
                                    classname: 'btn btn-xs btn-info btn-dialog',
                                    icon: 'fa fa-list',
                                    url: 'iam/dict/item'
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
