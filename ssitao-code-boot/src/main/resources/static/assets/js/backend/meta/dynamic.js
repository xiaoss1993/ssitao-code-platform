/**
 * 动态渲染JS
 * 用于动态渲染表单和表格
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var DynamicRender = {
        /**
         * 渲染表单
         * @param {Object} config - 表单配置
         * @param {Object} data - 表单数据
         * @param {Function} callback - 提交回调
         */
        renderForm: function (config, data, callback) {
            data = data || {};
            config = config || {};

            var fields = config.fields || [];
            var container = $('<form class="dynamic-form"></form>');

            // 渲染字段
            fields.forEach(function (field) {
                if (field.hidden) return;

                var formGroup = $('<div class="form-group"></div>');
                var label = $('<label class="control-label">' + field.fieldName + '</label>');

                if (field.required) {
                    label.append('<span class="text-danger"> *</span>');
                }

                formGroup.append(label);

                var input = DynamicRender.createField(field, data[field.fieldCode]);
                formGroup.append(input);

                container.append(formGroup);
            });

            return container;
        },

        /**
         * 创建字段
         */
        createField: function (field, value) {
            var fieldCode = field.fieldCode;
            var component = field.component || 'Input';
            var placeholder = field.placeholder || '';
            var disabled = field.disabled ? 'disabled' : '';
            var readonly = field.readonly ? 'readonly' : '';

            var input;

            switch (component) {
                case 'Input':
                    input = $('<input type="text" class="form-control" name="' + fieldCode + '" placeholder="' + placeholder + '" ' + disabled + ' ' + readonly + '>');
                    if (value !== undefined) input.val(value);
                    break;

                case 'Textarea':
                    input = $('<textarea class="form-control" name="' + fieldCode + '" placeholder="' + placeholder + '" ' + disabled + ' ' + readonly + '></textarea>');
                    if (value !== undefined) input.val(value);
                    break;

                case 'InputNumber':
                    input = $('<input type="number" class="form-control" name="' + fieldCode + '" placeholder="' + placeholder + '" ' + disabled + '>');
                    if (value !== undefined) input.val(value);
                    break;

                case 'Select':
                    input = $('<select class="form-control" name="' + fieldCode + '" ' + disabled + '><option value="">请选择</option></select>');
                    if (field.options) {
                        field.options.forEach(function (opt) {
                            var selected = value === opt.value ? 'selected' : '';
                            input.append('<option value="' + opt.value + '" ' + selected + '>' + opt.label + '</option>');
                        });
                    }
                    break;

                case 'Radio':
                    input = $('<div class="radio"></div>');
                    if (field.options) {
                        field.options.forEach(function (opt) {
                            var checked = value === opt.value ? 'checked' : '';
                            input.append('<label class="radio-inline"><input type="radio" name="' + fieldCode + '" value="' + opt.value + '" ' + checked + ' ' + disabled + '> ' + opt.label + '</label>');
                        });
                    }
                    break;

                case 'Checkbox':
                    input = $('<div class="checkbox"></div>');
                    if (field.options) {
                        field.options.forEach(function (opt) {
                            var checked = value && value.indexOf(opt.value) > -1 ? 'checked' : '';
                            input.append('<label class="checkbox-inline"><input type="checkbox" name="' + fieldCode + '" value="' + opt.value + '" ' + checked + ' ' + disabled + '> ' + opt.label + '</label>');
                        });
                    }
                    break;

                case 'Switch':
                    var checked = value === 1 || value === true ? 'checked' : '';
                    input = $('<input type="checkbox" name="' + fieldCode + '" data-off-color="danger" data-on-color="success" ' + checked + '>');
                    input.bootstrapSwitch();
                    break;

                case 'Date':
                    input = $('<input type="text" class="form-control datetimepicker" name="' + fieldCode + '" placeholder="' + placeholder + '" ' + disabled + '>');
                    if (value !== undefined) input.val(value);
                    input.datetimepicker({format: 'YYYY-MM-DD'});
                    break;

                case 'DateTime':
                    input = $('<input type="text" class="form-control datetimepicker" name="' + fieldCode + '" placeholder="' + placeholder + '" ' + disabled + '>');
                    if (value !== undefined) input.val(value);
                    input.datetimepicker({format: 'YYYY-MM-DD HH:mm:ss'});
                    break;

                default:
                    input = $('<input type="text" class="form-control" name="' + fieldCode + '" placeholder="' + placeholder + '" ' + disabled + '>');
                    if (value !== undefined) input.val(value);
            }

            return input;
        },

        /**
         * 渲染表格列配置
         */
        renderTableColumns: function (config) {
            var columns = config.columns || [];
            var result = [{checkbox: true}];

            columns.forEach(function (col) {
                if (col.show === false) return;

                var column = {
                    field: col.fieldCode,
                    title: col.fieldName,
                    sortable: col.sortable || false,
                    formatter: function (value, row, index) {
                        // 如果有格式化器
                        if (col.formatter) {
                            try {
                                var formatter = typeof col.formatter === 'string' ? JSON.parse(col.formatter) : col.formatter;
                                if (formatter.type === 'dict' && formatter.options) {
                                    var opt = formatter.options.find(function (o) { return o.value === value; });
                                    return opt ? opt.label : value;
                                }
                            } catch (e) {}
                        }

                        // 开关类型
                        if (col.component === 'Switch') {
                            return value ? '<span class="label label-success">是</span>' : '<span class="label label-default">否</span>';
                        }

                        return value !== undefined ? value : '';
                    }
                };

                if (col.width) column.width = col.width;
                if (col.minWidth) column.minWidth = col.minWidth;
                if (col.align) column.align = col.align;
                if (col.fixed) column.fixed = col.fixed;

                result.push(column);
            });

            return result;
        },

        /**
         * 获取表单数据
         */
        getFormData: function (form) {
            var data = {};
            form.find('[name]').each(function () {
                var name = $(this).attr('name');
                var value = $(this).val();

                if ($(this).is(':checkbox')) {
                    if (!data[name]) data[name] = [];
                    if ($(this).is(':checked')) {
                        data[name].push(value);
                    }
                } else if ($(this).is(':radio')) {
                    if ($(this).is(':checked')) {
                        data[name] = value;
                    }
                } else {
                    data[name] = value;
                }
            });
            return data;
        },

        /**
         * 验证表单
         */
        validateForm: function (form, config) {
            var fields = config.fields || [];
            var isValid = true;

            fields.forEach(function (field) {
                if (field.required) {
                    var input = form.find('[name="' + field.fieldCode + '"]');
                    var value = input.val();

                    if (!value || (Array.isArray(value) && value.length === 0)) {
                        input.closest('.form-group').addClass('has-error');
                        isValid = false;
                    } else {
                        input.closest('.form-group').removeClass('has-error');
                    }
                }
            });

            return isValid;
        }
    };

    // 动态CRUD控制器
    var DynamicCrud = {
        /**
         * 加载动态数据
         */
        loadData: function (entityCode, params, callback) {
            params = params || {};
            $.ajax({
                url: '/meta/dynamic/data/' + entityCode,
                type: 'GET',
                data: params,
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        callback(res.data);
                    } else {
                        Layer.msg(res.msg || '加载失败');
                    }
                },
                error: function () {
                    Layer.msg('请求失败');
                }
            });
        },

        /**
         * 保存数据
         */
        saveData: function (entityCode, data, callback) {
            $.ajax({
                url: '/meta/dynamic/data/' + entityCode,
                type: 'POST',
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200) {
                        callback(res);
                    } else {
                        Layer.msg(res.msg || '保存失败');
                    }
                },
                error: function () {
                    Layer.msg('请求失败');
                }
            });
        },

        /**
         * 更新数据
         */
        updateData: function (entityCode, id, data, callback) {
            $.ajax({
                url: '/meta/dynamic/data/' + entityCode + '/' + id,
                type: 'PUT',
                data: JSON.stringify(data),
                contentType: 'application/json',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200) {
                        callback(res);
                    } else {
                        Layer.msg(res.msg || '更新失败');
                    }
                },
                error: function () {
                    Layer.msg('请求失败');
                }
            });
        },

        /**
         * 删除数据
         */
        deleteData: function (entityCode, id, callback) {
            Layer.confirm('确定要删除该数据吗？', function (index) {
                $.ajax({
                    url: '/meta/dynamic/data/' + entityCode + '/' + id,
                    type: 'DELETE',
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 200) {
                            Layer.msg('删除成功');
                            callback(res);
                        } else {
                            Layer.msg(res.msg || '删除失败');
                        }
                    },
                    error: function () {
                        Layer.msg('请求失败');
                    }
                });
                Layer.close(index);
            });
        }
    };

    // 导出
    return {
        render: DynamicRender,
        crud: DynamicCrud,
        api: {
            // 获取表单配置
            getFormConfig: function (entityId, formType, callback) {
                $.get('/meta/render/form/' + entityId, {formType: formType || 'DEFAULT'}, function (res) {
                    if (res.code === 200) {
                        callback(res.data);
                    }
                });
            },

            // 获取列表配置
            getListConfig: function (entityId, listType, callback) {
                $.get('/meta/render/list/' + entityId, {listType: listType || 'DEFAULT'}, function (res) {
                    if (res.code === 200) {
                        callback(res.data);
                    }
                });
            },

            // 获取实体配置
            getEntityConfig: function (entityId, callback) {
                $.get('/meta/render/entity/' + entityId, function (res) {
                    if (res.code === 200) {
                        callback(res.data);
                    }
                });
            }
        }
    };
});
