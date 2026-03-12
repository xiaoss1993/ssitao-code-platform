/**
 * 岗位管理JS
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'layer'], function ($, undefined, Backend, Table, Form, Layer) {

    var Controller = {
        index: function () {
            // 初始化表格
            Table.api.init({
                extend: {
                    index_url: '/hr/org/position/list',
                    add_url: '/hr/org/position/add',
                    edit_url: '/hr/org/position/edit',
                    del_url: '/hr/org/position',
                    table: 'hr_position',
                },
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
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
                sidePagination: 'client',
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                queryParams: function (params) {
                    return {
                        tenantId: '1'
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'position_code', title: '岗位编码', operate: 'LIKE'},
                        {field: 'position_name', title: '岗位名称', operate: 'LIKE'},
                        {field: 'dept_id', title: '所属部门ID', visible: false},
                        {field: 'dept_name', title: '所属部门'},
                        {
                            field: 'position_level',
                            title: '职级',
                            formatter: function (value) {
                                var levelMap = {
                                    'P1': 'P1-初级',
                                    'P2': 'P2-中级',
                                    'P3': 'P3-高级',
                                    'P4': 'P4-专家',
                                    'P5': 'P5-资深专家'
                                };
                                return levelMap[value] || value;
                            }
                        },
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
                            events: {
                                'click .btn-edit': function (e, value, row, index) {
                                    Controller.edit(row.id);
                                },
                                'click .btn-del': function (e, value, row, index) {
                                    Controller.delete(row.id);
                                },
                                'click .btn-enable': function (e, value, row, index) {
                                    Controller.enable(row.id);
                                },
                                'click .btn-disable': function (e, value, row, index) {
                                    Controller.disable(row.id);
                                }
                            },
                            formatter: function (value, row, index) {
                                var buttons = [];
                                buttons.push('<a href="javascript:;" class="btn btn-xs btn-primary btn-edit" data-id="' + row.id + '"><i class="fa fa-edit"></i> 编辑</a> ');
                                buttons.push('<a href="javascript:;" class="btn btn-xs btn-danger btn-del" data-id="' + row.id + '"><i class="fa fa-trash"></i> 删除</a> ');
                                if (row.status === 0) {
                                    buttons.push('<a href="javascript:;" class="btn btn-xs btn-success btn-enable" data-id="' + row.id + '"><i class="fa fa-check"></i> 启用</a> ');
                                } else {
                                    buttons.push('<a href="javascript:;" class="btn btn-xs btn-warning btn-disable" data-id="' + row.id + '"><i class="fa fa-ban"></i> 禁用</a> ');
                                }
                                return buttons.join('');
                            }
                        }
                    ]
                ]
            });

            // 绑定新增按钮事件
            $(document).on('click', '.btn-add', function () {
                Controller.add();
            });

            // 绑定刷新按钮事件
            $(document).on('click', '.btn-refresh', function () {
                $("#table").bootstrapTable('refresh');
            });
        },

        add: function () {
            Layer.open({
                type: 2,
                title: '添加岗位',
                shadeClose: true,
                shade: 0.3,
                area: ['600px', '500px'],
                content: '/hr/org/position/add',
                end: function () {
                    $("#table").bootstrapTable('refresh');
                }
            });
        },

        edit: function (id) {
            Layer.open({
                type: 2,
                title: '编辑岗位',
                shadeClose: true,
                shade: 0.3,
                area: ['600px', '500px'],
                content: '/hr/org/position/edit?id=' + id,
                end: function () {
                    $("#table").bootstrapTable('refresh');
                }
            });
        },

        delete: function (id) {
            Layer.confirm('确定要删除该岗位吗？', function (index) {
                $.ajax({
                    url: '/hr/org/position/' + id,
                    type: 'DELETE',
                    data: { tenantId: '1' },
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 200) {
                            Backend.api.toastr.success('删除成功');
                            $("#table").bootstrapTable('refresh');
                        } else {
                            Backend.api.toastr.error(res.msg || '删除失败');
                        }
                    }
                });
                Layer.close(index);
            });
        },

        enable: function (id) {
            $.ajax({
                url: '/hr/org/position/' + id + '/enable',
                type: 'POST',
                data: { tenantId: '1' },
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200) {
                        Backend.api.toastr.success('启用成功');
                        $("#table").bootstrapTable('refresh');
                    } else {
                        Backend.api.toastr.error(res.msg || '启用失败');
                    }
                }
            });
        },

        disable: function (id) {
            Layer.confirm('确定要禁用该岗位吗？', function (index) {
                $.ajax({
                    url: '/hr/org/position/' + id + '/disable',
                    type: 'POST',
                    data: { tenantId: '1' },
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 200) {
                            Backend.api.toastr.success('禁用成功');
                            $("#table").bootstrapTable('refresh');
                        } else {
                            Backend.api.toastr.error(res.msg || '禁用失败');
                        }
                    }
                });
                Layer.close(index);
            });
        },

        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"));
            }
        }
    };

    return Controller;
});
