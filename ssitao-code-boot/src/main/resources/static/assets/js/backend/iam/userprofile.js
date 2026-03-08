define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

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

            var id = getQueryString('id');

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

            if (id) {
                // 编辑模式 - 获取数据
                $.ajax({
                    url: '/iam/user/' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('#nickname').val(data.nickname);
                            $('#realName').val(data.realName);
                            $('#departmentId').val(data.departmentId);
                            $('#departmentId').selectpicker('refresh');
                            $('#postId').val(data.postId);
                            $('#postId').selectpicker('refresh');
                            $('#phone').val(data.phone);
                            $('#email').val(data.email);
                            $('#gender').val(data.gender);
                            $('#birthday').val(data.birthday);
                            $('#avatar').val(data.avatar);
                            $('input[name="status"][value="' + data.status + '"]').prop('checked', true);
                            $('#remark').val(data.remark);
                        }
                    }
                });

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
