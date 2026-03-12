/**
 * 公共工具模块
 * 提供各业务模块复用的通用方法
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'bootstrap-datetimepicker'], function ($, undefined, Backend, Table, Form) {

    /**
     * 获取URL参数
     * @param {string} name - 参数名称
     * @returns {string|null} 参数值
     */
    var getQueryString = function(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURIComponent(r[2]);
        return null;
    };

    /**
     * 获取编辑页面的主键ID
     * 优先从URL参数获取，其次从表格数据获取
     * @param {string} pkField - 表格配置的主键字段名
     * @returns {string|null} 主键ID
     */
    var getPkId = function(pkField) {
        // 1. 优先从URL参数获取
        var id = getQueryString('id') || getQueryString('ids');
        if (id) return id;

        // 2. 尝试从rowData隐藏字段获取
        var rowData = $('#rowData').val();
        if (rowData) {
            try {
                var data = JSON.parse(rowData);
                // 优先使用配置的pkField
                if (pkField && data[pkField]) {
                    return data[pkField];
                }
                // 兼容多种常见主键字段名
                var pkFields = ['id', 'accountId', 'account_id', 'recordId', 'record_id', 'uuid', 'userId', 'user_id', 'menuId', 'menu_id', 'tenantId', 'tenant_id', 'roleId', 'role_id', 'orgId', 'org_id'];
                for (var i = 0; i < pkFields.length; i++) {
                    if (data[pkFields[i]]) {
                        return data[pkFields[i]];
                    }
                }
            } catch (e) {
                console.error('解析rowData失败:', e);
            }
        }

        return null;
    };

    /**
     * 处理Bootstrap Table响应数据
     * 统一处理分页格式
     * @param {object} res - 响应数据
     * @returns {object} Bootstrap Table格式数据
     */
    var handleTableResponse = function(res) {
        if (res.code === 200 && res.data) {
            var data = res.data;
            // 如果返回的是数组（没有分页信息）
            if (Array.isArray(data)) {
                return { rows: data, total: data.length };
            }
            // 处理分页对象 (records: 数据列表, totalRow: 总数)
            if (data.records) {
                return { rows: data.records, total: data.totalRow || data.records.length };
            }
            // 如果返回的是分页对象 (content: 数据列表, totalElements: 总数)
            if (data.content) {
                return { rows: data.content, total: data.totalElements || data.content.length };
            }
            return { rows: data, total: data.length };
        }
        return { rows: [], total: 0 };
    };

    /**
     * 表单提交处理
     * @param {string} actionUrl - 提交URL
     * @param {string} method - 请求方法
     * @param {object} data - 提交数据
     * @param {function} beforeSend - 发送前回调
     */
    var submitForm = function(actionUrl, method, data, beforeSend) {
        var ajaxOptions = {
            url: actionUrl,
            type: method,
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
        };

        // 根据method决定发送数据方式
        if (method === 'POST' || method === 'PUT') {
            // 判断是否为JSON数据
            if (data && typeof data === 'object' && !(data instanceof FormData)) {
                ajaxOptions.contentType = 'application/json';
                ajaxOptions.data = JSON.stringify(data);
            } else {
                ajaxOptions.data = data;
            }
        } else {
            ajaxOptions.data = data;
        }

        if (beforeSend && typeof beforeSend === 'function') {
            beforeSend(ajaxOptions);
        }

        $.ajax(ajaxOptions);
    };

    /**
     * 初始化日期选择器
     * @param {string} selector - 选择器
     * @param {string} format - 日期格式
     */
    var initDatePicker = function(selector, format) {
        var $el = $(selector);
        if ($el.length) {
            $el.datetimepicker({
                format: format || 'YYYY-MM-DD',
                locale: 'zh-CN'
            });
        }
    };

    /**
     * 加载下拉列表数据
     * @param {string} url - 请求URL
     * @param {string} selector - 选择器
     * @param {string} valueField - 值字段
     * @param {string} textField - 文本字段
     * @param {string} defaultText - 默认选项文本
     */
    var loadSelectOptions = function(url, selector, valueField, textField, defaultText) {
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'json',
            success: function(res) {
                if (res.code === 200 && res.data) {
                    var html = '<option value="">' + (defaultText || '请选择') + '</option>';
                    $.each(res.data, function(i, item) {
                        html += '<option value="' + item[valueField] + '">' + item[textField] + '</option>';
                    });
                    $(selector).html(html);
                    if ($(selector).hasClass('selectpicker')) {
                        $(selector).selectpicker('refresh');
                    }
                }
            }
        });
    };

    /**
     * 表单数据回显
     * 支持 text, radio, checkbox, select 等常用表单元素
     * @param {object} data - 数据对象，key为字段名，value为字段值
     * @param {object} options - 配置选项
     * @param {boolean} options.skipEmpty - 是否跳过空值，默认false
     * @param {function} options.transformer - 值转换函数 (fieldName, value) => transformedValue
     * @returns {jQuery} form元素
     */
    var fillForm = function(formSelector, data, options) {
        options = options || {};
        var $form = $(formSelector);
        if (!$form.length || !data) return $form;

        var skipEmpty = options.skipEmpty !== undefined ? options.skipEmpty : false;
        var transformer = options.transformer;

        $.each(data, function(fieldName, value) {
            if (skipEmpty && (value === null || value === undefined || value === '')) {
                return;
            }

            // 如果有转换函数，则转换值
            if (transformer && typeof transformer === 'function') {
                value = transformer(fieldName, value);
            }

            var $field = $form.find('[name="' + fieldName + '"]');
            if (!$field.length) return;

            var type = $field.attr('type') || $field.prop('tagName').toLowerCase();

            switch (type) {
                case 'radio':
                    $field.filter('[value="' + value + '"]').prop('checked', true);
                    break;
                case 'checkbox':
                    if (Array.isArray(value)) {
                        $.each(value, function(i, v) {
                            $field.filter('[value="' + v + '"]').prop('checked', true);
                        });
                    } else {
                        $field.filter('[value="' + value + '"]').prop('checked', true);
                    }
                    break;
                case 'select':
                case 'select-one':
                    $field.val(value);
                    if ($field.hasClass('selectpicker')) {
                        $field.selectpicker('refresh');
                    }
                    break;
                default:
                    $field.val(value);
            }
        });

        return $form;
    };

    /**
     * 从隐藏字段读取JSON并回显表单
     * @param {string} formSelector - 表单选择器
     * @param {string} hiddenFieldId - 隐藏字段ID
     * @param {object} options - fillForm选项
     * @returns {object} { success: boolean, data: object }
     */
    var loadFormData = function(formSelector, hiddenFieldId, options) {
        var $hidden = $('#' + hiddenFieldId);
        var jsonStr = $hidden.val();

        if (!jsonStr) {
            return { success: false, data: null };
        }

        try {
            var data = JSON.parse(jsonStr);
            fillForm(formSelector, data, options);
            return { success: true, data: data };
        } catch (e) {
            console.error('JSON解析失败:', e);
            return { success: false, error: e };
        }
    };

    // 导出公共方法
    var Utils = {
        getQueryString: getQueryString,
        getPkId: getPkId,
        handleTableResponse: handleTableResponse,
        submitForm: submitForm,
        initDatePicker: initDatePicker,
        loadSelectOptions: loadSelectOptions,
        fillForm: fillForm,
        loadFormData: loadFormData
    };

    return Utils;
});
