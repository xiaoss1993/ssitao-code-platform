/**
 * 账号管理 - 原生 jQuery + Bootstrap Table
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'layer'], function ($, undefined, Backend, Table, Form, Layer) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/account/page',
                    add_url: '/iam/account/add',
                    edit_url: '/iam/account/edit',
                    del_url: '/iam/account',
                    table: 'iam_account',
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
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
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
                sortName: 'create_time',
                sidePagination: 'server',
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                queryParamsType: 'undefined',
                queryParams: function (params) {
                    return {
                        current: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true, visible: false},
                        {field: 'account_code', title: '账号编码', operate: 'LIKE'},
                        {field: 'account_name', title: '账号名称', operate: 'LIKE'},
                        {field: 'phone', title: '手机号'},
                        {field: 'email', title: '邮箱'},
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
                            field: 'locked',
                            title: '锁定',
                            searchList: {"true": "已锁定", "false": "未锁定"},
                            formatter: function (value, row, index) {
                                return value === true
                                    ? '<span class="label label-danger">已锁定</span>'
                                    : '<span class="label label-default">未锁定</span>';
                            }
                        },
                        {field: 'create_time', title: '创建时间', operate: 'RANGE', addclass: 'datetimerange', formatter: Table.api.formatter.datetime},
                        {
                            field: 'operate',
                            title: '操作',
                            table: table,
                            events: {
                                'click .btn-edit': function (e, value, row, index) {
                                    Controller.handleEdit(row);
                                },
                                'click .btn-del': function (e, value, row, index) {
                                    Controller.handleDelete(row);
                                },
                                'click .btn-enable': function (e, value, row, index) {
                                    Controller.handleEnable(row);
                                },
                                'click .btn-disable': function (e, value, row, index) {
                                    Controller.handleDisable(row);
                                }
                            },
                            formatter: function (value, row, index) {
                                var buttons = [];
                                buttons.push('<a href="javascript:;" class="btn btn-xs btn-primary btn-edit" data-id="' + row.id + '"><i class="fa fa-edit"></i> 编辑</a> ');
                                buttons.push('<a href="javascript:;" class="btn btn-xs btn-danger btn-del" data-id="' + row.id + '"><i class="fa fa-trash"></i> 删除</a> ');
                                if (row.status === false) {
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

            // 绑定添加按钮事件
            $(document).on('click', '.btn-add', function() {
                Layer.open({
                    type: 2,
                    title: '添加账号',
                    shadeClose: true,
                    shade: 0.3,
                    area: ['600px', '500px'],
                    content: '/iam/account/add',
                    end: function() {
                        table.bootstrapTable('refresh');
                    }
                });
            });

            // 绑定刷新按钮事件
            $(document).on('click', '.btn-refresh', function() {
                table.bootstrapTable('refresh');
            });

            // 绑定删除按钮事件（批量删除）
            $(document).on('click', '.btn-del', function() {
                var ids = Table.api.selectedids(table);
                if (ids.length === 0) {
                    Layer.msg('请至少选择一条记录');
                    return false;
                }
                Layer.confirm('确认删除选中的账号吗？', function() {
                    $.ajax({
                        url: '/iam/account/delete',
                        type: 'POST',
                        data: { ids: ids.join(',') },
                        dataType: 'json',
                        success: function(res) {
                            if (res.code === 200) {
                                Layer.msg('删除成功');
                                table.bootstrapTable('refresh');
                            } else {
                                Layer.msg(res.msg || '删除失败');
                            }
                        }
                    });
                });
            });
        },
        add: function () {
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();
                var url = $(this).attr('action') || '/iam/account';
                var method = $(this).attr('method') || 'POST';

                var formData = {
                    accountCode: $('#accountCode').val(),
                    accountName: $('#accountName').val(),
                    phone: $('#phone').val() || null,
                    email: $('#email').val() || null,
                    avatar: $('#avatar').val() || null,
                    remark: $('#remark').val() || null,
                    password: $('#password').val()
                };

                if (!formData.accountCode) {
                    Layer.msg('请输入账号编码', {icon: 2});
                    return;
                }
                if (!formData.accountName) {
                    Layer.msg('请输入账号名称', {icon: 2});
                    return;
                }
                if (!formData.password) {
                    Layer.msg('请输入密码', {icon: 2});
                    return;
                }

                $.ajax({
                    url: url,
                    type: method,
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('添加成功', {icon: 1});
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.$("#table").bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '添加失败', {icon: 2});
                        }
                    }
                });
            });

            Form.api.bindevent($("form[role=form]"));
        },
        edit: function () {
            // 获取URL参数
            var getQueryString = function(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURIComponent(r[2]);
                return null;
            };

            var id = getQueryString('id') || getQueryString('ids');

            if (id) {
                $.ajax({
                    url: '/iam/account/' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('#accountCode').val(data.account_code);
                            $('#accountCode').attr('readonly', true);
                            $('#accountName').val(data.account_name);
                            $('#phone').val(data.phone || '');
                            $('#email').val(data.email || '');
                            $('#avatar').val(data.avatar || '');
                            $('#remark').val(data.remark || '');

                            // 编辑模式下隐藏密码必填
                            $('#password-group').find('.text-danger').hide();
                            $('#password-hint').text('留空表示不修改密码');
                            $('#password').removeAttr('data-rule');
                            $('#password').attr('placeholder', '留空不修改密码');
                        }
                    }
                });

                $('#edit-form').attr('action', '/iam/account');
                $('#edit-form').attr('method', 'PUT');
            } else {
                $('#edit-form').attr('action', '/iam/account');
                $('#edit-form').attr('method', 'POST');
            }

            $('#edit-form').on('submit', function(e) {
                e.preventDefault();
                var url = $(this).attr('action');
                var method = $(this).attr('method');

                var formData = {
                    accountName: $('#accountName').val(),
                    phone: $('#phone').val() || null,
                    email: $('#email').val() || null,
                    avatar: $('#avatar').val() || null,
                    remark: $('#remark').val() || null
                };

                var password = $('#password').val();
                if (password) {
                    formData.password = password;
                }

                if (!formData.accountName) {
                    Layer.msg('请输入账号名称', {icon: 2});
                    return;
                }

                if (id) {
                    formData.id = id;
                } else {
                    formData.accountCode = $('#accountCode').val();
                    if (!formData.accountCode) {
                        Layer.msg('请输入账号编码', {icon: 2});
                        return;
                    }
                    if (!password) {
                        Layer.msg('请输入密码', {icon: 2});
                        return;
                    }
                }

                $.ajax({
                    url: url,
                    type: method,
                    contentType: 'application/json',
                    data: JSON.stringify(formData),
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('操作成功', {icon: 1});
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.$("#table").bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '操作失败', {icon: 2});
                        }
                    }
                });
            });

            Form.api.bindevent($("form[role=form]"));
        },

        handleEdit: function(row) {
            Layer.open({
                type: 2,
                title: '编辑账号',
                shadeClose: true,
                shade: 0.3,
                area: ['600px', '500px'],
                content: '/iam/account/edit?id=' + row.id,
                end: function() {
                    $("#table").bootstrapTable('refresh');
                }
            });
        },

        handleDelete: function(row) {
            Layer.confirm('确定要删除账号 "' + row.account_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/account/' + row.id,
                    type: 'DELETE',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('删除成功', {icon: 1});
                            $("#table").bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '删除失败', {icon: 2});
                        }
                    }
                });
                Layer.close(index);
            });
        },

        handleEnable: function(row) {
            Layer.confirm('确定要启用账号 "' + row.account_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/account/' + row.id + '/enable',
                    type: 'PUT',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('启用成功', {icon: 1});
                            $("#table").bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '启用失败', {icon: 2});
                        }
                    }
                });
                Layer.close(index);
            });
        },

        handleDisable: function(row) {
            Layer.confirm('确定要禁用账号 "' + row.account_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/account/' + row.id + '/disable',
                    type: 'PUT',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('禁用成功', {icon: 1});
                            $("#table").bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '禁用失败', {icon: 2});
                        }
                    }
                });
                Layer.close(index);
            });
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
