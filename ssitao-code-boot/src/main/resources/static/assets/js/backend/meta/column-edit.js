define(['jquery', 'bootstrap', 'backend', 'form', 'utils'], function ($, undefined, Backend, Form, Utils) {

    var Controller = {
        index: function () {
            // 使用通用方法获取主键ID
            var id = Utils.getPkId('id');
            var tableId = Controller.api.getQueryString('tableId');

            // 设置tableId
            if (tableId) {
                $('#tableId').val(tableId);
            }

            if (id) {
                // 编辑模式 - 加载数据
                $.ajax({
                    url: '/meta/column/' + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);
                            $('#tableId').val(data.table_id);
                            $('#columnName').val(data.column_name);
                            $('#columnDesc').val(data.column_desc);
                            $('#columnType').val(data.column_type);
                            $('#javaType').val(data.java_type);
                            $('#javaField').val(data.java_field);
                            $('#length').val(data.length);
                            $('#decimalDigits').val(data.decimal_digits);
                            $('#defaultValue').val(data.default_value);

                            if (data.is_pk === true || data.is_pk === 1) {
                                $('#isPk').prop('checked', true);
                            }
                            if (data.is_required === true || data.is_required === 1) {
                                $('#isRequired').prop('checked', true);
                            }
                            if (data.is_list === true || data.is_list === 1) {
                                $('#isList').prop('checked', true);
                            }
                            if (data.is_query === true || data.is_query === 1) {
                                $('#isQuery').prop('checked', true);
                            }

                            $('#queryType').val(data.query_type);
                            $('#dictType').val(data.dict_type);
                            $('#remark').val(data.remark);
                        }
                    }
                });
                $('#edit-form').attr('action', '/meta/column');
                $('#edit-form').attr('method', 'PUT');
            } else {
                // 添加模式
                $('#edit-form').attr('action', '/meta/column');
                $('#edit-form').attr('method', 'POST');
            }

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();
                var url = $(this).attr('action');
                var method = $(this).attr('method');

                var formData = {
                    tableId: $('#tableId').val(),
                    columnName: $('#columnName').val(),
                    columnDesc: $('#columnDesc').val(),
                    columnType: $('#columnType').val(),
                    javaType: $('#javaType').val(),
                    javaField: $('#javaField').val(),
                    length: parseInt($('#length').val()) || 0,
                    decimalDigits: parseInt($('#decimalDigits').val()) || 0,
                    defaultValue: $('#defaultValue').val(),
                    isPk: $('#isPk').is(':checked'),
                    isRequired: $('#isRequired').is(':checked'),
                    isList: $('#isList').is(':checked'),
                    isQuery: $('#isQuery').is(':checked'),
                    queryType: $('#queryType').val(),
                    dictType: $('#dictType').val(),
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
