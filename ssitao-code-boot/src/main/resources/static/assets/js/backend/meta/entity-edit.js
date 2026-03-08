/**
 * 实体编辑JS
 */
define(['jquery', 'bootstrap', 'backend', 'form', 'utils'], function ($, undefined, Backend, Form, Utils) {

    var Controller = {
        index: function () {
            // 使用通用方法获取主键ID
            var id = Utils.getPkId('id');

            if (id) {
                // 编辑模式 - 加载数据
                $.ajax({
                    url: '/meta/entity/get?id=' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('input[name="entityCode"]').val(data.entity_code);
                            $('input[name="entityName"]').val(data.entity_name);
                            $('input[name="tableName"]').val(data.table_name);
                            $('textarea[name="description"]').val(data.description);
                            $('input[name="status"][value="' + (data.status === 1 ? '1' : '0') + '"]').prop('checked', true);
                        }
                    }
                });
                $('#edit-form').attr('action', '/meta/entity/update');
                $('#edit-form').attr('method', 'PUT');
            } else {
                // 添加模式
                $('#edit-form').attr('action', '/meta/entity/create');
                $('#edit-form').attr('method', 'POST');
            }

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();
                var url = $(this).attr('action');
                var method = $(this).attr('method');

                var formData = {
                    entityCode: $('input[name="entityCode"]').val(),
                    entityName: $('input[name="entityName"]').val(),
                    tableName: $('input[name="tableName"]').val(),
                    description: $('textarea[name="description"]').val(),
                    status: parseInt($('input[name="status"]:checked').val())
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
