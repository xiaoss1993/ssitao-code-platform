/**
 * 公共工具模块
 * 提供各业务模块复用的通用方法
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

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

    // 导出公共方法
    var Utils = {
        getQueryString: getQueryString,
        handleTableResponse: handleTableResponse,
        submitForm: submitForm,
        initDatePicker: initDatePicker,
        loadSelectOptions: loadSelectOptions
    };

    return Utils;
});
