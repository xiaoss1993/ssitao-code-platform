/**
 * 用户管理JS
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格
            Table.api.init({
                extend: {
                    index_url: '/iam/user/page',
                    add_url: '/iam/user/add',
                    edit_url: '/iam/user/edit',
                    del_url: '/iam/user',
                    multi_url: '/iam/user/multi',
                    table: 'iam_user',
                },
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        // 如果返回的是分页对象 (content)
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
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'user_name', title: '用户名', operate: 'LIKE'},
                        {field: 'nickname', title: '昵称', operate: 'LIKE'},
                        {field: 'real_name', title: '真实姓名', operate: 'LIKE'},
                        {field: 'dept_id', title: '部门ID', visible: false},
                        {field: 'phone', title: '手机号', operate: 'LIKE'},
                        {field: 'email', title: '邮箱', operate: 'LIKE'},
                        {
                            field: 'gender',
                            title: '性别',
                            searchList: {"male": "男", "female": "女", "": "未知"}
                        },
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
            // 加载部门列表
            $.ajax({
                url: '/iam/org/department/list',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择部门</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.deptId + '">' + item.deptName + '</option>';
                        });
                        $('#deptId').html(options);
                    }
                }
            });
            Controller.api.bindevent();
        },
        edit: function () {
            // 加载部门列表
            $.ajax({
                url: '/iam/org/department/list',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择部门</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.deptId + '">' + item.deptName + '</option>';
                        });
                        $('#deptId').html(options);
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
