define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'utils'], function ($, undefined, Backend, Table, Form, Utils) {

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

            // 使用通用方法获取主键ID
            var id = Utils.getPkId('id');

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

                // 构建 JSON 数据
                var submitData = {
                    roleName: $('#roleName').val(),
                    dataScope: $('#dataScope').val(),
                    sortOrder: parseInt($('#sortOrder').val()) || 0,
                    remark: $('#remark').val(),
                    status: $('input[name="status"]:checked').val() === 'true'
                };

                var url, method;
                if (id) {
                    // 编辑模式
                    submitData.id = parseInt(id);
                    url = '/iam/role';
                    method = 'PUT';
                } else {
                    // 添加模式
                    submitData.roleCode = $('#roleCode').val();
                    submitData.roleType = $('#roleType').val();
                    url = '/iam/role';
                    method = 'POST';
                }

                $.ajax({
                    url: url,
                    type: method,
                    contentType: 'application/json',
                    data: JSON.stringify(submitData),
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
