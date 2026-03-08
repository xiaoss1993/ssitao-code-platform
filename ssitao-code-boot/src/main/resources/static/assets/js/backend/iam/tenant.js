define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/tenant/page',
                    add_url: '/iam/tenant/add',
                    edit_url: '/iam/tenant/edit',
                    del_url: '/iam/tenant',
                    table: 'iam_tenant',
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
                sortName: 'create_time',
                sidePagination: 'server',
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'tenant_code', title: '租户编码', operate: 'LIKE'},
                        {field: 'tenant_name', title: '租户名称', operate: 'LIKE'},
                        {field: 'contact_name', title: '联系人'},
                        {field: 'contact_phone', title: '联系电话', operate: 'LIKE'},
                        {field: 'contact_email', title: '联系邮箱', operate: 'LIKE'},
                        {field: 'expire_time', title: '过期时间', operate: 'RANGE', addclass: 'datetimerange'},
                        {field: 'max_users', title: '最大用户数'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "启用", "false": "禁用"},
                            formatter: function (value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">启用</span>'
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
                                        return '/iam/tenant/' + row.id + '/enable';
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
                                        return '/iam/tenant/' + row.id + '/disable';
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
            // 获取URL参数
            var getQueryString = function(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURIComponent(r[2]);
                return null;
            };

            // 初始化日期选择器
            $('#expireTime').datetimepicker({
                format: 'YYYY-MM-DD HH:mm:ss',
                locale: 'zh-CN'
            });

            // 添加模式
            $('#edit-form').attr('action', '/iam/tenant');
            $('#edit-form').attr('method', 'POST');

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();

                var url = $(this).attr('action');
                var method = $(this).attr('method');
                var formData = $(this).serialize();

                $.ajax({
                    url: url,
                    type: method,
                    data: formData,
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
                    },
                    error: function() {
                        Layer.msg('网络错误', {icon: 2});
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

            var id = getQueryString('id');

            // 初始化日期选择器
            $('#expireTime').datetimepicker({
                format: 'YYYY-MM-DD HH:mm:ss',
                locale: 'zh-CN'
            });

            if (id) {
                // 编辑模式 - 获取数据
                $.ajax({
                    url: '/iam/tenant/' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('#tenantName').val(data.tenantName);
                            $('#tenantCode').val(data.tenantCode);
                            $('#tenantCode').attr('readonly', true);
                            $('#contactName').val(data.contactName);
                            $('#contactPhone').val(data.contactPhone);
                            $('#contactEmail').val(data.contactEmail);
                            $('#expireTime').val(data.expireTime);
                            $('#maxUsers').val(data.maxUsers);
                            $('input[name="status"][value="' + data.status + '"]').prop('checked', true);
                            $('#remark').val(data.remark);
                        }
                    }
                });

                $('#edit-form').attr('action', '/iam/tenant');
                $('#edit-form').attr('method', 'PUT');
            }

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();

                var url = $(this).attr('action');
                var method = $(this).attr('method');
                var formData = $(this).serialize();

                $.ajax({
                    url: url,
                    type: method,
                    data: formData,
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
                    },
                    error: function() {
                        Layer.msg('网络错误', {icon: 2});
                    }
                });
            });

            Form.api.bindevent($("form[role=form]"));
        }
    };
    return Controller;
});
