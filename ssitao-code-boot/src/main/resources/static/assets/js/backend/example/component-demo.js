/**
 * 组件使用示例
 * 
 * 如何在业务页面中使用通用组件：
 * 
 * 1. 在 require-backend.js 中添加路径配置:
 *    'component': '/assets/js/backend/component'
 * 
 * 2. 在页面中引入:
 *    define(['backend', 'component'], function(Backend, Component) {
 *        ...
 *    });
 * 
 * 3. 使用示例:
 */

define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'component'], function($, undefined, Backend, Table, Form, Component) {

    var Controller = {
        // 列表页示例
        index: function() {
            // 初始化表格
            Table.api.init({
                url: '/api/user/list',
                pk: 'id',
                sortName: 'createTime',
                columns: [
                    { checkbox: true },
                    { field: 'id', title: 'ID', sortable: true },
                    { field: 'username', title: '用户名' },
                    { 
                        field: 'avatar', 
                        title: '头像',
                        formatter: Component.format.image
                    },
                    { 
                        field: 'status', 
                        title: '状态',
                        formatter: Component.format.status
                    },
                    { 
                        field: 'createTime', 
                        title: '创建时间',
                        formatter: Component.format.datetime
                    },
                    {
                        field: 'operate',
                        title: '操作',
                        table: Table.api,
                        events: Table.api.events.operate,
                        formatter: Table.api.formatter.operate
                    }
                ]
            });

            // 绑定事件
            Table.api.bindevent();

            // 使用 Component 示例
            $(document).on('click', '.btn-add', function() {
                // 打开新增弹窗
                Component.dialog.add('/admin/user/add');
            });

            $(document).on('click', '.btn-export', function() {
                // 使用 Ajax 请求
                Component.ajax.get('/api/user/export', {}, function() {
                    Component.notice.success('导出成功');
                });
            });

            $(document).on('click', '.btn-delete', function() {
                // 批量删除
                var ids = Component.table.getSelectedIds('#table');
                Component.ajax.batchDelete('/api/user/delete', ids, function() {
                    Component.table.refresh('#table');
                });
            });
        },

        // 编辑页示例
        edit: function() {
            // 表单验证规则
            var rules = {
                username: {
                    required: true,
                    minlength: 3,
                    maxlength: 20
                },
                email: {
                    required: true,
                    email: true
                },
                mobile: {
                    required: true,
                    mobile: true
                }
            };

            // 绑定表单事件
            Form.api.bindevent($("form[role=form]"));

            // 提交表单
            $(document).on('click', '.btn-save', function() {
                var data = Component.form.serialize('#form');
                
                // 验证表单
                if (!Component.form.validate('#form', rules)) {
                    Component.notice.error('请检查表单填写');
                    return;
                }

                // 提交
                Component.ajax.post('/api/user/save', data, function() {
                    Component.dialog.refreshParent();
                });
            });

            // 使用 URL 工具获取参数
            var id = Component.url.getParam('id');
            if (id) {
                // 加载数据
                Component.ajax.get('/api/user/info?id=' + id, {}, function(data) {
                    Component.form.fillForm('#form', data);
                });
            }
        },

        // 查看详情示例
        detail: function() {
            var id = Component.url.getParam('id');
            if (!id) {
                Component.notice.error('参数错误');
                Component.dialog.close();
                return;
            }

            Component.ajax.get('/api/user/info?id=' + id, {}, function(data) {
                // 格式化显示
                $('#username').text(data.username);
                $('#email').text(data.email);
                $('#mobile').text(Component.format.mobile(data.mobile));
                $('#avatar').html(Component.format.image(data.avatar, 'img-responsive'));
                $('#createTime').text(Component.format.datetime(data.createTime));
                $('#status').html(Component.format.status(data.status));
            });
        }
    };

    return Controller;
});
