/**
 * 字典项管理JS
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格 - 使用 client 模式因为后端没有分页接口
            Table.api.init({
                extend: {
                    index_url: '/iam/system/dict-data/all',
                    add_url: '/iam/dict/item/add',
                    edit_url: '/iam/dict/item/edit',
                    del_url: '/iam/system/dict-data',
                    table: 'core_dictionary_item',
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'item_id',
                sortName: 'item_sort',
                sidePagination: 'client',
                pagination: true,
                pageSize: 10,
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'item_id', title: 'ID', sortable: true},
                        {field: 'dict_id', title: '字典ID', visible: false},
                        {field: 'item_code', title: '字典项编码', operate: 'LIKE'},
                        {field: 'item_name', title: '字典项名称', operate: 'LIKE'},
                        {field: 'item_value', title: '字典项值'},
                        {field: 'item_parent_id', title: '父级ID', visible: false},
                        {field: 'item_level', title: '层级'},
                        {
                            field: 'item_status',
                            title: '状态',
                            searchList: {"1": "启用", "0": "禁用"},
                            formatter: function (value) {
                                return value == 1 ? '<span class="label label-success">启用</span>' : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {field: 'item_sort', title: '排序', sortable: true},
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
            // 加载字典列表
            $.ajax({
                url: '/iam/system/dict-types',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择字典</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.dictId + '">' + item.dictName + '</option>';
                        });
                        $('#dictId').html(options);
                    }
                }
            });
            Controller.api.bindevent();
        },
        edit: function () {
            // 加载字典列表
            $.ajax({
                url: '/iam/system/dict-types',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择字典</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.dictId + '">' + item.dictName + '</option>';
                        });
                        $('#dictId').html(options);
                    }
                }
            });
            Controller.api.bindevent();
        },
        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"), function (data, ret) {
                    Layer.msg(ret.msg || '保存成功');
                    setTimeout(function () {
                        location.href = Fast.api.fixurl('iam/dict/item');
                    }, 1000);
                }, function (data, ret) {
                    Layer.error(ret.msg || '保存失败');
                });
            }
        }
    };

    return Controller;
});
