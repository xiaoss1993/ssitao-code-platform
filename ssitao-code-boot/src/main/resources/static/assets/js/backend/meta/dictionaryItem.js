/**
 * 字典数据管理JS
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'layer'], function ($, undefined, Backend, Table, Form, Layer) {

    var Controller = {
        index: function () {
            // 初始化表格
            Table.api.init({
                extend: {
                    index_url: '/meta/dictionary-item/list',
                    add_url: '/meta/dictionary-item/add',
                    edit_url: '/meta/dictionary-item/edit',
                    del_url: '/meta/dictionary-item',
                    multi_url: '/meta/dictionary-item/multi',
                    table: 'meta_dictionary_item',
                },
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        if (data.content) {
                            return { rows: data.content, total: data.totalElements || data.content.length };
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
                pk: 'id',
                sortName: 'sort_order',
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
                        {field: 'dict_id', title: '字典类型ID', visible: false},
                        {field: 'dict_type_code', title: '字典类型编码', operate: 'LIKE'},
                        {field: 'dict_type_name', title: '字典类型名称'},
                        {field: 'item_text', title: '字典项文本', operate: 'LIKE'},
                        {field: 'item_value', title: '字典项值', operate: 'LIKE'},
                        {field: 'sort_order', title: '排序', sortable: true},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"1": "启用", "0": "禁用"},
                            formatter: function (value) {
                                return value == 1 ? '<span class="label label-success">启用</span>' : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {field: 'create_time', title: '创建时间', operate: 'RANGE', addclass: 'datetimerange'},
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

            // 为表格绑定事件
            Table.api.bindevent(table);
        },
        add: function () {
            // 加载字典类型列表
            $.ajax({
                url: '/meta/dictionary/all',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择字典类型</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.id + '">' + item.dict_type_name + '</option>';
                        });
                        $('#dictId').html(options);
                    }
                }
            });
            Controller.api.bindevent();
        },
        edit: function () {
            // 加载字典类型列表
            $.ajax({
                url: '/meta/dictionary/all',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择字典类型</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.id + '">' + item.dict_type_name + '</option>';
                        });
                        $('#dictId').html(options);
                    }
                }
            });
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
