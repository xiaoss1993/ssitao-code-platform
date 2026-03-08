define(['jquery', 'bootstrap', 'backend', 'form', 'utils'], function ($, undefined, Backend, Form, Utils) {

    var Controller = {
        index: function () {
            // 使用通用方法获取主键ID
            var id = Utils.getPkId('id');

            if (id) {
                // 编辑模式 - 加载数据
                $.ajax({
                    url: '/meta/table/' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('#tableName').val(data.table_name);
                            $('#tableDesc').val(data.table_desc);
                            $('#tableType').val(data.table_type || 1);
                            $('#className').val(data.class_name);
                            $('#classDesc').val(data.class_desc);
                            $('#entityName').val(data.entity_name);
                            $('#packageName').val(data.package_name);
                            $('#moduleName').val(data.module_name);
                            $('#author').val(data.author);
                            $('#genPath').val(data.gen_path);
                            $('#remark').val(data.remark);

                            if (data.enabled === true || data.enabled === 1) {
                                $('#enabled').prop('checked', true);
                            } else {
                                $('#enabled').prop('checked', false);
                            }
                        }
                    }
                });
                $('#edit-form').attr('action', '/meta/table');
                $('#edit-form').attr('method', 'PUT');
            } else {
                // 添加模式
                $('#edit-form').attr('action', '/meta/table');
                $('#edit-form').attr('method', 'POST');
            }

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();
                var url = $(this).attr('action');
                var method = $(this).attr('method');

                var formData = {
                    tableName: $('#tableName').val(),
                    tableDesc: $('#tableDesc').val(),
                    tableType: parseInt($('#tableType').val()),
                    className: $('#className').val(),
                    classDesc: $('#classDesc').val(),
                    entityName: $('#entityName').val(),
                    packageName: $('#packageName').val(),
                    moduleName: $('#moduleName').val(),
                    author: $('#author').val(),
                    genPath: $('#genPath').val(),
                    enabled: $('#enabled').is(':checked'),
                    remark: $('#remark').val()
                };

                if (id) {
                    formData.id = id;
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
                    },
                    error: function() {
                        Layer.msg('网络错误', {icon: 2});
                    }
                });
            });
        },
        api: {
            getQueryString: function(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURIComponent(r[2]);
                return null;
            },
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"));
            }
        }
    };

    // 执行index方法
    $(function() {
        Controller.index();
    });

    return Controller;
});
