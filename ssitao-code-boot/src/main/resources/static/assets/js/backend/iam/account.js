/**
 * 账号管理 - 使用 Vue-Table 组件
 * Vue-Table = Bootstrap Table + Vue.js
 */
define(['jquery', 'bootstrap', 'backend', 'vue-table', 'form', 'layer'], function ($, undefined, Backend, VueTable, Form, Layer) {

    var Controller = {
        index: function () {
            // 使用 Vue-Table 组件
            var table = new VueTable({
                el: '#vue-app',
                table: {
                    selector: '#table',
                    url: '/iam/account/page',
                    pk: 'id',
                    sortName: 'create_time',
                    sortOrder: 'desc',
                    sidePagination: 'server',
                    pagination: true,
                    pageSize: 10,
                    pageList: [10, 25, 50, 100],
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
                                formatter: function (value, row, index) {
                                    if (!value) return '-';
                                    var date = new Date(value);
                                    var year = date.getFullYear();
                                    var month = ('0' + (date.getMonth() + 1)).slice(-2);
                                    var day = ('0' + date.getDate()).slice(-2);
                                    var hours = ('0' + date.getHours()).slice(-2);
                                    var minutes = ('0' + date.getMinutes()).slice(-2);
                                    var seconds = ('0' + date.getSeconds()).slice(-2);
                                    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
                                }
                            },
                            {
                                field: 'operate',
                                title: '操作',
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
                },
                search: {
                    enabled: true,
                    data: {
                        accountCode: '',
                        accountName: ''
                    }
                },
                crud: {
                    add: {
                        url: '/iam/account/add',
                        title: '添加账号',
                        area: ['600px', '500px']
                    },
                    edit: {
                        url: '/iam/account/edit',
                        title: '编辑账号',
                        area: ['600px', '500px']
                    },
                    del: {
                        url: '/iam/account'
                    }
                }
            });
        },

        // 静态方法：处理编辑
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

        // 静态方法：处理删除
        handleDelete: function(row) {
            Layer.confirm('确定要删除账号 "' + row.account_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/account/' + row.id,
                    type: 'DELETE',
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

        // 静态方法：处理启用
        handleEnable: function(row) {
            Layer.confirm('确定要启用账号 "' + row.account_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/account/' + row.id + '/enable',
                    type: 'PUT',
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

        // 静态方法：处理禁用
        handleDisable: function(row) {
            Layer.confirm('确定要禁用账号 "' + row.account_name + '" 吗？', function(index) {
                $.ajax({
                    url: '/iam/account/' + row.id + '/disable',
                    type: 'PUT',
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

        add: function () {
            console.log('Account add function called');
            // 添加页面 - 初始化空表单
            Controller.api.initForm({});
        },
        edit: function () {
            console.log('Account edit function called');
            // 编辑页面 - 获取ID并加载数据
            var id = Controller.api.getQueryParam('id');
            console.log('Edit id:', id);
            if (id) {
                $.ajax({
                    url: '/iam/account/' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        console.log('GET account response:', res);
                        if (res.code === 200 && res.data) {
                            Controller.api.initForm(res.data);
                        } else if (res.data) {
                            // 有些接口直接返回数据，没有 code
                            Controller.api.initForm(res.data);
                        } else {
                            Backend.api.toastr.error(res.msg || '获取数据失败');
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error('GET account error:', error);
                        Backend.api.toastr.error('网络错误');
                    }
                });
            } else {
                console.log('No id found, initializing empty form');
                Controller.api.initForm({});
            }
        },
        api: {
            // 获取URL参数
            getQueryParam: function(name) {
                var url = window.location.href;
                var params = url.split('?')[1];
                if (!params) return null;
                var paramArr = params.split('&');
                for (var i = 0; i < paramArr.length; i++) {
                    var pair = paramArr[i].split('=');
                    if (pair[0] === name) {
                        return pair[1];
                    }
                }
                return null;
            },
            // 初始化Vue表单
            initForm: function(data) {
                var Vue = window.Vue;
                if (!Vue) {
                    console.error('Vue is not loaded!');
                    Backend.api.toastr.error('Vue 未加载');
                    return;
                }

                console.log('Initializing form with data:', data);
                console.log('Vue version:', Vue.version);

                // 字段名映射工具函数 - 支持后端返回的下划线命名和驼峰命名
                var getField = function(data, underscoreName, camelCaseName) {
                    // 尝试下划线命名
                    if (data[underscoreName] !== undefined) {
                        return data[underscoreName];
                    }
                    // 尝试驼峰命名
                    if (data[camelCaseName] !== undefined) {
                        return data[camelCaseName];
                    }
                    return '';
                };

                // 处理日期时间格式
                var expireTimeVal = getField(data, 'expire_time', 'expireTime');
                var expireTime = '';
                if (expireTimeVal) {
                    // 转换为 datetime-local 需要的格式
                    var date = new Date(expireTimeVal);
                    var year = date.getFullYear();
                    var month = ('0' + (date.getMonth() + 1)).slice(-2);
                    var day = ('0' + date.getDate()).slice(-2);
                    var hours = ('0' + date.getHours()).slice(-2);
                    var minutes = ('0' + date.getMinutes()).slice(-2);
                    expireTime = year + '-' + month + '-' + day + 'T' + hours + ':' + minutes;
                }

                var isEdit = !!getField(data, 'id', 'id');
                var vm = new Vue({
                    el: '#app',
                    data: {
                        isEdit: isEdit,
                        form: {
                            id: getField(data, 'id', 'id'),
                            accountCode: getField(data, 'account_code', 'accountCode'),
                            accountName: getField(data, 'account_name', 'accountName'),
                            password: '',
                            phone: getField(data, 'phone', 'phone'),
                            email: getField(data, 'email', 'email'),
                            avatar: getField(data, 'avatar', 'avatar'),
                            expireTime: expireTime,
                            remark: getField(data, 'remark', 'remark')
                        }
                    },
                    methods: {
                        handleSubmit: function() {
                            console.log('Submit form data:', this.form);
                            // 验证表单
                            if (!this.form.accountCode) {
                                Backend.api.toastr.warning('请输入账号编码');
                                return;
                            }
                            if (!this.form.accountName) {
                                Backend.api.toastr.warning('请输入账号名称');
                                return;
                            }
                            if (!isEdit && !this.form.password) {
                                Backend.api.toastr.warning('请输入密码');
                                return;
                            }

                            var url = isEdit ? '/iam/account/' + this.form.id : '/iam/account';
                            var method = isEdit ? 'PUT' : 'POST';

                            // 构造提交数据（匹配后端Command字段）
                            var submitData = {
                                id: isEdit ? this.form.id : undefined,
                                accountCode: this.form.accountCode,
                                accountName: this.form.accountName,
                                phone: this.form.phone || null,
                                email: this.form.email || null,
                                avatar: this.form.avatar || null,
                                remark: this.form.remark || null
                            };

                            // 处理过期时间
                            if (this.form.expireTime) {
                                // 转换为 ISO 8601 格式
                                var date = new Date(this.form.expireTime);
                                submitData.expireTime = date.toISOString();
                            }

                            // 只有填写了密码才提交
                            if (this.form.password) {
                                submitData.password = this.form.password;
                            }

                            console.log('Submitting to:', url, 'method:', method, 'data:', submitData);

                            $.ajax({
                                url: url,
                                type: method,
                                contentType: 'application/json',
                                data: JSON.stringify(submitData),
                                dataType: 'json',
                                success: function(res) {
                                    console.log('Submit response:', res);
                                    if (res.code === 200) {
                                        Backend.api.toastr.success('操作成功');
                                        var index = parent.layer.getFrameIndex(window.name);
                                        parent.layer.close(index);
                                    } else {
                                        Backend.api.toastr.error(res.msg || '操作失败');
                                    }
                                },
                                error: function(xhr, status, error) {
                                    console.error('Submit error:', error);
                                    Backend.api.toastr.error('网络错误');
                                }
                            });
                        },
                        handleCancel: function() {
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }
                    }
                });
            }
        }
    };
    return Controller;
});
