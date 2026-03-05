define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/user-profile/page',
                    add_url: 'userprofile-edit.html',
                    edit_url: 'userprofile-edit.html',
                    del_url: '/iam/user-profile',
                    multi_url: 'userprofile/multi',
                    table: 'iam_user_profile',
                    list_url: '/iam/user-profile/list',
                },
                // 配置响应数据处理
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        if (res.data.records) {
                            return { rows: res.data.records, total: res.data.totalRow || res.data.records.length };
                        }
                        if (res.data.content) {
                            return { rows: res.data.content, total: res.data.totalElements || res.data.total };
                        }
                        return { rows: res.data, total: res.data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'createTime',
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'nickname', title: '昵称', operate: 'LIKE'},
                        {field: 'realName', title: '真实姓名', operate: 'LIKE'},
                        {field: 'departmentName', title: '所属部门', operate: false},
                        {field: 'postName', title: '岗位', operate: false},
                        {field: 'phone', title: '手机号', operate: 'LIKE'},
                        {field: 'email', title: '邮箱', operate: 'LIKE'},
                        {
                            field: 'gender',
                            title: '性别',
                            searchList: {"male": "男", "female": "女", "": "未知"},
                            formatter: function (value, row, index) {
                                var genderMap = {
                                    'male': '男',
                                    'female': '女'
                                };
                                return genderMap[value] || '未知';
                            }
                        },
                        {field: 'birthday', title: '出生日期'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function (value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'createTime',
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
                            formatter: Table.api.formatter.operate,
                            buttons: [
                                {
                                    name: 'enable',
                                    text: '启用',
                                    title: '启用',
                                    classname: 'btn btn-xs btn-success btn-dialog',
                                    icon: 'fa fa-check',
                                    url: function (row) {
                                        return '/iam/user-profile/' + row.id + '/enable';
                                    },
                                    callback: function (data) {
                                        $("#table").bootstrapTable('refresh');
                                    }
                                },
                                {
                                    name: 'disable',
                                    text: '禁用',
                                    title: '禁用',
                                    classname: 'btn btn-xs btn-warning btn-dialog',
                                    icon: 'fa fa-ban',
                                    url: function (row) {
                                        return '/iam/user-profile/' + row.id + '/disable';
                                    },
                                    callback: function (data) {
                                        $("#table").bootstrapTable('refresh');
                                    }
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
            Form.api.bindevent($("form[role=form]"));
        },
        edit: function () {
            Form.api.bindevent($("form[role=form]"));
        },
        api: {
            formatter: {
                status: function (value, row, index) {
                    return value === true
                        ? '<span class="label label-success">正常</span>'
                        : '<span class="label label-default">禁用</span>';
                }
            }
        }
    };
    return Controller;
});
