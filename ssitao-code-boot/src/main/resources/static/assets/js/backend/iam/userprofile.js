define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'utils'], function ($, undefined, Backend, Table, Form, Utils) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/user/page',
                    add_url: '/iam/user/add',
                    edit_url: '/iam/user/edit',
                    del_url: '/iam/user',
                    table: 'iam_user_profile',
                },
                // 配置响应数据处理
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
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        if (data.content) {
                            return { rows: data.content, total: data.totalElements || data.total };
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
                        {field: 'nickname', title: '昵称', operate: 'LIKE'},
                        {field: 'real_name', title: '真实姓名', operate: 'LIKE'},
                        {field: 'department_name', title: '所属部门', operate: false},
                        {field: 'post_name', title: '岗位', operate: false},
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
                                        return '/iam/user/' + row.id + '/enable';
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
                                        return '/iam/user/' + row.id + '/disable';
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

            // 加载部门列表
            function loadDepartments() {
                $.ajax({
                    url: '/iam/org/department/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择部门</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.deptName + '</option>';
                            });
                            $('#departmentId').html(html);
                            $('#departmentId').selectpicker('refresh');
                        }
                    }
                });
            }

            // 加载岗位列表
            function loadPosts() {
                $.ajax({
                    url: '/iam/org/post/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择岗位</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.postName + '</option>';
                            });
                            $('#postId').html(html);
                            $('#postId').selectpicker('refresh');
                        }
                    }
                });
            }

            // 初始化日期选择器
            $('#birthday').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: 'zh-CN'
            });

            loadDepartments();
            loadPosts();

            // 添加模式
            $('#edit-form').attr('action', '/iam/user');
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

            var id = Utils.getPkId('id');

            // 加载部门列表
            function loadDepartments() {
                $.ajax({
                    url: '/iam/org/department/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择部门</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.deptName + '</option>';
                            });
                            $('#departmentId').html(html);
                            $('#departmentId').selectpicker('refresh');
                        }
                    }
                });
            }

            // 加载岗位列表
            function loadPosts() {
                $.ajax({
                    url: '/iam/org/post/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择岗位</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.postName + '</option>';
                            });
                            $('#postId').html(html);
                            $('#postId').selectpicker('refresh');
                        }
                    }
                });
            }

            // 初始化日期选择器
            $('#birthday').datetimepicker({
                format: 'YYYY-MM-DD',
                locale: 'zh-CN'
            });

            loadDepartments();
            loadPosts();

            // 字段映射：后端字段 -> 表单字段
            var fieldMapping = {
                'id': 'id', 'userId': 'id',
                'nickname': 'nickname',
                'realName': 'realName', 'real_name': 'realName',
                'departmentId': 'departmentId', 'deptId': 'departmentId',
                'postId': 'postId',
                'phone': 'phone', 'userPhone': 'phone',
                'email': 'email', 'userMail': 'email',
                'gender': 'gender', 'userSex': 'gender',
                'birthday': 'birthday', 'userBirthday': 'birthday',
                'avatar': 'avatar', 'userPhoto': 'avatar',
                'status': 'status',
                'remark': 'remark'
            };

            // 表单数据回显函数
            function fillForm(data) {
                if (!data) return;
                // 使用字段映射转换数据
                var mappedData = {};
                $.each(fieldMapping, function(backendField, formField) {
                    if (data[backendField] !== undefined) {
                        mappedData[formField] = data[backendField];
                    }
                });
                Utils.fillForm('#edit-form', mappedData);
                // 刷新 selectpicker
                $('#departmentId').selectpicker('refresh');
                $('#postId').selectpicker('refresh');
            }

            if (id) {
                // 读取 JSON 数据并回显
                var loadAndFill = function(data) {
                    fillForm(data);
                };

                // 编辑模式 - 优先从隐藏字段读取 JSON 数据
                var rowData = $('#rowData').val();
                if (rowData) {
                    try {
                        var data = JSON.parse(rowData);
                        loadAndFill(data);
                    } catch (e) {
                        // JSON 解析失败，尝试通过 AJAX 获取
                        $.ajax({
                            url: '/iam/user/' + id,
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
                        url: '/iam/user/' + id,
                        type: 'GET',
                        dataType: 'json',
                        success: function(res) {
                            if (res.code === 200 && res.data) {
                                loadAndFill(res.data);
                            }
                        }
                    });
                }

                $('#edit-form').attr('action', '/iam/user');
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
