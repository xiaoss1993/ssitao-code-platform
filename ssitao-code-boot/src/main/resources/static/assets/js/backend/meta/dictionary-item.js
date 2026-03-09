/**
 * 字典数据管理JS
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格
            Table.api.init({
                extend: {
                    index_url: '/meta/dictionary-item/list',
                    add_url: '/meta/dictionary-item',
                    edit_url: '/meta/dictionary-item',
                    del_url: '/meta/dictionary-item',
                    multi_url: '/meta/dictionary-item/multi',
                    table: 'core_dictionary_item',
                },
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'dictId',
                sortName: 'sortOrder',
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
                        {field: 'dictId', title: 'ID', sortable: true},
                        {field: 'dictTypeCode', title: '字典类型编码'},
                        {field: 'dictDataCode', title: '字典数据编码', operate: 'LIKE'},
                        {field: 'dictDataValue', title: '字典数据值', operate: 'LIKE'},
                        {field: 'dictDataLabel', title: '字典数据标签', operate: 'LIKE'},
                        {field: 'cssClass', title: '样式类'},
                        {field: 'listClass', title: '列表样式'},
                        {
                            field: 'isDefault',
                            title: '是否默认',
                            searchList: {"1": "是", "0": "否"},
                            formatter: function (value) {
                                return value == 1 ? '<span class="label label-success">是</span>' : '<span class="label label-default">否</span>';
                            }
                        },
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"1": "启用", "0": "禁用"},
                            formatter: function (value) {
                                return value == 1 ? '<span class="label label-success">启用</span>' : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {field: 'sortOrder', title: '排序', sortable: true},
                        {field: 'remark', title: '备注'},
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
                Form.api.bindevent($("form[role=form]"));
            }
        }
    };
    return Controller;
});
