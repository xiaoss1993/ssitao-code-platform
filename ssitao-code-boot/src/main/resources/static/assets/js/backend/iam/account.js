define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'utils'], function ($, undefined, Backend, Table, Form, Utils) {

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
                // 兼容不同的主键字段名
                pk: 'id',
                // 配置响应数据处理
                responseHandler: function (res) {
                    // 处理后端返回的数据格式
                    if (res.code === 200 && res.data) {
                        // 如果返回的是数组（没有分页信息），转换为bootstrap-table格式
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
                        {field: 'account_code', title: '账号编码', operate: 'LIKE'},
                        {field: 'account_name', title: '账号名称', operate: 'LIKE'},
                        {field: 'phone', title: '手机号', operate: 'LIKE'},
                        {field: 'email', title: '邮箱', operate: 'LIKE'},
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
                        {
                            field: 'password_inited',
                            title: '密码状态',
                            searchList: {"true": "已初始化", "false": "未初始化"},
                            formatter: function (value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">已初始化</span>'
                                    : '<span class="label label-warning">未初始化</span>';
                            }
                        },
                        {
                            field: 'create_time',
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
                                        return '/iam/account/' + row.id + '/enable';
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
                                        return '/iam/account/' + row.id + '/disable';
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

            // 启用/禁用按钮事件
            $(document).on("click", ".btn-enable,.btn-disable", function () {
                var url = $(this).attr("href");
                if (url) {
                    Layer.confirm('确定要执行此操作吗？', function (index) {
                        $.ajax({
                            url: url,
                            type: 'PUT',
                            dataType: 'json',
                            success: function (res) {
                                if (res.code === 200) {
                                    $("#table").bootstrapTable('refresh');
                                    Layer.close(index);
                                    Toastr.success(res.msg || '操作成功');
                                } else {
                                    Toastr.error(res.msg || '操作失败');
                                }
                            }
                        });
                    });
                }
                return false;
            });

            // 为表格绑定事件
            Table.api.bindevent(table);
        },
        add: function () {
            Form.api.bindevent($("form[role=form]"));
        },
        edit: function () {
            // 从表格配置获取主键字段名
            var pkField = 'id'; // 默认主键字段
            var $table = parent.$("#table");
            if ($table.length) {
                var tableOptions = $table.bootstrapTable('getOptions');
                pkField = tableOptions.pk || 'id';
            }

            // 使用通用方法获取主键ID
            var id = Utils.getPkId(pkField);

            // 字段名映射：后端字段 -> 表单字段
            var fieldMapping = {
                'id': 'id',
                'account_code': 'accountCode',
                'account_name': 'accountName',
                'phone': 'phone',
                'email': 'email',
                'status': 'status',
                'remark': 'remark'
            };

            // 字段转换器：将后端字段名转换为表单字段名
            function fieldTransformer(fieldName, value) {
                var mappedField = fieldMapping[fieldName];
                if (mappedField) {
                    return { field: mappedField, value: value };
                }
                return { field: fieldName, value: value };
            }

            if (id) {
                // 编辑模式 - 获取数据
                $('#password-group .control-label').html('密码 <span class="text-danger">*</span>（留空不修改）');
                $('#password-group input').removeAttr('data-rule');

                // 读取 JSON 数据并回显
                var loadAndFill = function(data) {
                    if (!data) return;
                    // 字段映射转换
                    var mappedData = {};
                    $.each(fieldMapping, function(backendField, formField) {
                        if (data[backendField] !== undefined) {
                            mappedData[formField] = data[backendField];
                        }
                    });
                    Utils.fillForm('#edit-form', mappedData);
                    $('#accountCode').attr('readonly', true);
                };

                // 优先从隐藏字段读取 JSON 数据
                var rowData = $('#rowData').val();
                if (rowData) {
                    try {
                        var data = JSON.parse(rowData);
                        loadAndFill(data);
                    } catch (e) {
                        // JSON 解析失败，通过 AJAX 获取
                        $.ajax({
                            url: '/iam/account/' + id,
                            type: 'GET',
                            dataType: 'json',
                            success: function(res) {
                                if (res.code === 200 && res.data) {
                                    loadAndFill(res.data);
                                }
                            }
                        });
                    }
                } else {
                    // 没有隐藏数据，通过 AJAX 获取
                    $.ajax({
                        url: '/iam/account/' + id,
                        type: 'GET',
                        dataType: 'json',
                        success: function(res) {
                            if (res.code === 200 && res.data) {
                                loadAndFill(res.data);
                            }
                        }
                    });
                }

                // 设置表单提交地址
                $('#edit-form').attr('action', '/iam/account');
                $('#edit-form').attr('method', 'PUT');
            } else {
                // 添加模式
                $('#edit-form').attr('action', '/iam/account');
                $('#edit-form').attr('method', 'POST');
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
                            // 关闭当前弹窗并刷新父页面
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
