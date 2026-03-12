define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'utils', 'layer'], function ($, undefined, Backend, Table, Form, Utils, Layer) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/role/page',
                    add_url: '/iam/role/add',
                    edit_url: '/iam/role/edit',
                    del_url: '/iam/role/delete',
                    table: 'iam_role',
                },
                responseHandler: function (res) {
                    console.log('Table response:', res);
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        // 如果返回的是分页对象 (records)
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
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
                sortName: 'sort_order',
                sidePagination: 'server',
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                queryParamsType: 'undefined',
                queryParams: function (params) {
                    console.log('Pagination params:', params);
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
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'role_code', title: '角色编码', operate: 'LIKE'},
                        {field: 'role_name', title: '角色名称', operate: 'LIKE'},
                        {field: 'role_type', title: '角色类型', searchList: {"SYSTEM": "系统角色", "CUSTOM": "自定义角色"},
                            formatter: function (value, row, index) {
                                return value === 'SYSTEM'
                                    ? '<span class="label label-danger">系统角色</span>'
                                    : '<span class="label label-primary">自定义角色</span>';
                            }
                        },
                        {field: 'data_scope', title: '数据范围', searchList: {"ALL": "全部", "DEPT": "本部门", "DEPT_AND_CHILD": "本部门及子部门", "SELF": "本人"},
                            formatter: function (value, row, index) {
                                var scopeMap = {
                                    "ALL": "全部",
                                    "DEPT": "本部门",
                                    "DEPT_AND_CHILD": "本部门及子部门",
                                    "SELF": "本人"
                                };
                                return scopeMap[value] || value;
                            }
                        },
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function (value, row, index) {
                                return value === true || value === 1
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {field: 'sort_order', title: '排序', sortable: true},
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
                                // 编辑按钮
                                buttons.push('<a href="javascript:;" class="btn btn-xs btn-primary btn-edit" data-id="' + row.id + '"><i class="fa fa-edit"></i> 编辑</a> ');
                                // 删除按钮
                                buttons.push('<a href="javascript:;" class="btn btn-xs btn-danger btn-del" data-id="' + row.id + '"><i class="fa fa-trash"></i> 删除</a> ');
                                // 启用/禁用按钮
                                if (row.status === false || row.status === 'false' || row.status === 0) {
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

            // 注意：不再调用 Table.api.bindevent，因为我们已经在 events 中自定义了操作按钮事件

            // 绑定新增按钮事件
            $(document).on('click', '.btn-add', function() {
                Layer.open({
                    type: 2,
                    title: '添加角色',
                    shadeClose: true,
                    shade: 0.3,
                    area: ['600px', '500px'],
                    content: '/iam/role/add',
                    end: function() {
                        $("#table").bootstrapTable('refresh');
                    }
                });
            });
        },
        add: function () {
            // 添加页面 - 也需要手动处理表单提交，不使用 Form.api.bindevent
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();

                // 构建 JSON 数据
                var submitData = {
                    roleCode: $('#roleCode').val(),
                    roleName: $('#roleName').val(),
                    roleType: $('#roleType').val() || 'CUSTOM',
                    dataScope: $('#dataScope').val() || 'ALL',
                    sortOrder: parseInt($('#sortOrder').val()) || 0,
                    remark: $('#remark').val(),
                    status: $('input[name="status"]:checked').val() === 'true'
                };

                console.log('Adding:', 'POST', '/iam/role', JSON.stringify(submitData));

                $.ajax({
                    url: '/iam/role',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(submitData),
                    dataType: 'json',
                    success: function(res) {
                        console.log('Response:', res);
                        if (res.code === 200) {
                            Backend.api.toastr.success('添加成功');
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.$("#table").bootstrapTable('refresh');
                        } else {
                            Backend.api.toastr.error(res.msg || '添加失败');
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error('Error:', status, error, xhr.responseText);
                        Backend.api.toastr.error('网络错误: ' + (xhr.responseText || error));
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

            // 使用通用方法获取主键ID - 支持 id 和 ids 两种参数
            var id = Utils.getPkId('id') || Utils.getPkId('ids');

            if (id) {
                // 编辑模式 - 获取数据
                $.ajax({
                    url: '/iam/role/get',
                    type: 'GET',
                    data: { id: id },
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('#roleCode').val(data.role_code);
                            $('#roleCode').attr('readonly', true);
                            $('#roleName').val(data.role_name);
                            $('#roleType').val(data.role_type || 'CUSTOM');
                            $('#dataScope').val(data.data_scope || 'ALL');
                            $('#sortOrder').val(data.sort_order || 0);
                            $('input[name="status"][value="' + (data.status === true || data.status === 1 || data.status === 'true' ? 'true' : 'false') + '"]').prop('checked', true);
                            $('#remark').val(data.remark);
                        }
                    }
                });
            }

            // 表单提交处理
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();

                // 获取ID - 优先从URL，其次从表单隐藏字段
                var submitId = id || $('#id').val();
                console.log('Submit id:', submitId, 'id var:', id, 'form val:', $('#id').val());

                // 构建 JSON 数据
                var submitData = {
                    roleName: $('#roleName').val(),
                    dataScope: $('#dataScope').val(),
                    sortOrder: parseInt($('#sortOrder').val()) || 0,
                    remark: $('#remark').val(),
                    status: $('input[name="status"]:checked').val() === 'true'
                };

                var url, method;
                console.log('DEBUG: submitId =', submitId);

                // 直接根据是否有ID来判断是编辑还是添加
                if (submitId) {
                    // 编辑模式 - ID可能是字符串(如ROLEc76a4fd8)或数字
                    var finalId = isNaN(submitId) ? submitId : parseInt(submitId);
                    submitData.id = finalId;
                    console.log('EDIT mode, id =', finalId);
                    url = '/iam/role';
                    method = 'PUT';
                } else {
                    // 添加模式
                    submitData.roleCode = $('#roleCode').val();
                    submitData.roleType = $('#roleType').val();
                    url = '/iam/role';
                    method = 'POST';
                }

                console.log('Submitting:', method, url, JSON.stringify(submitData));

                $.ajax({
                    url: url,
                    type: method,
                    contentType: 'application/json',
                    data: JSON.stringify(submitData),
                    dataType: 'json',
                    success: function(res) {
                        console.log('Response:', res);
                        if (res.code === 200) {
                            Backend.api.toastr.success('操作成功');
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.$("#table").bootstrapTable('refresh');
                        } else {
                            Backend.api.toastr.error(res.msg || '操作失败');
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error('Error:', status, error, xhr.responseText);
                        Backend.api.toastr.error('网络错误: ' + (xhr.responseText || error));
                    }
                });
            });

            Form.api.bindevent($("form[role=form]"));
        },

        // 处理编辑
        handleEdit: function(row) {
            Layer.open({
                type: 2,
                title: '编辑角色',
                shadeClose: true,
                shade: 0.3,
                area: ['600px', '500px'],
                content: '/iam/role/edit?ids=' + row.id,
                end: function() {
                    $("#table").bootstrapTable('refresh');
                }
            });
        },

        // 处理删除
        handleDelete: function(row) {
            Layer.confirm('确定要删除角色 "' + row.role_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/role/delete',
                    type: 'POST',
                    data: { id: row.id },
                    dataType: 'json',
                    success: function(res) {
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

        // 处理启用
        handleEnable: function(row) {
            Layer.confirm('确定要启用角色 "' + row.role_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/role/enable',
                    type: 'POST',
                    data: { id: row.id },
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Backend.api.toastr.success('启用成功');
                            $("#table").bootstrapTable('refresh');
                        } else {
                            Backend.api.toastr.error(res.msg || '启用失败');
                        }
                    }
                });
                Layer.close(index);
            });
        },

        // 处理禁用
        handleDisable: function(row) {
            Layer.confirm('确定要禁用角色 "' + row.role_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/role/disable',
                    type: 'POST',
                    data: { id: row.id },
                    dataType: 'json',
                    success: function(res) {
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
            formatter: {
                status: function (value, row, index) {
                    return value === true || value === 1
                        ? '<span class="label label-success">正常</span>'
                        : '<span class="label label-default">禁用</span>';
                }
            }
        }
    };
    return Controller;
});
