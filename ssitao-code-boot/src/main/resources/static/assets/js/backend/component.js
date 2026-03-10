/**
 * 通用组件库
 * 提供业务模块复用的通用组件
 * 
 * 使用方法:
 * require(['backend/component'], function(Component) {
 *     Component.xxx.method();
 * });
 */
define(['jquery', 'bootstrap', 'layer', 'toastr'], function($, undefined, Layer, Toastr) {

    // ==================== 加载状态组件 ====================
    var Loading = {
        // 全局加载遮罩
        show: function(options) {
            var defaults = {
                type: 0, // 0-4 共5种风格
                shade: [0.3, '#000'],
                shadeClose: false,
                msg: '加载中...',
                time: 0 // 0表示不自动关闭
            };
            return Layer.load($.extend(defaults, options || {}));
        },
        // 关闭全局加载遮罩
        hide: function(index) {
            if (index) {
                Layer.close(index);
            } else {
                Layer.closeAll('loading');
            }
        },
        // 带文字的加载
        showText: function(text) {
            return Layer.msg(text, {
                icon: 16,
                shade: 0.3,
                time: 0
            });
        }
    };

    // ==================== 确认对话框组件 ====================
    var Confirm = {
        // 确认对话框
        show: function(content, title, okCallback, cancelCallback) {
            title = title || '确认操作';
            var index = Layer.confirm(content, {
                icon: 3,
                title: title,
                shadeClose: true,
                btn: ['确定', '取消']
            }, function() {
                if (typeof okCallback === 'function') {
                    okCallback();
                }
                Layer.close(index);
            }, function() {
                if (typeof cancelCallback === 'function') {
                    cancelCallback();
                }
                Layer.close(index);
            });
            return index;
        },
        // 删除确认（快捷方法）
        delete: function(okCallback) {
            Confirm.show('确定要删除选中的记录吗？', '删除确认', okCallback);
        },
        // 危险操作确认
        danger: function(content, okCallback) {
            var index = Layer.confirm(content, {
                icon: 0,
                title: '警告',
                shadeClose: false,
                btn: ['确认执行', '取消']
            }, function() {
                if (typeof okCallback === 'function') {
                    okCallback();
                }
                Layer.close(index);
            });
            return index;
        }
    };

    // ==================== 消息提示组件 ====================
    var Notice = {
        // 成功提示
        success: function(msg, callback) {
            Toastr.success(msg);
            if (typeof callback === 'function') {
                setTimeout(callback, 500);
            }
        },
        // 错误提示
        error: function(msg, callback) {
            Toastr.error(msg);
            if (typeof callback === 'function') {
                setTimeout(callback, 500);
            }
        },
        // 警告提示
        warning: function(msg, callback) {
            Toastr.warning(msg);
            if (typeof callback === 'function') {
                setTimeout(callback, 500);
            }
        },
        // 信息提示
        info: function(msg, callback) {
            Toastr.info(msg);
            if (typeof callback === 'function') {
                setTimeout(callback, 500);
            }
        },
        // 使用 Layer.msg 的快捷方法
        msg: function(msg, icon, callback) {
            Layer.msg(msg, { icon: icon || 1 }, callback);
        }
    };

    // ==================== Ajax 请求组件 ====================
    var Ajax = {
        // GET 请求
        get: function(url, data, success, error) {
            return this.request('GET', url, data, success, error);
        },
        // POST 请求
        post: function(url, data, success, error) {
            return this.request('POST', url, data, success, error);
        },
        // PUT 请求
        put: function(url, data, success, error) {
            return this.request('PUT', url, data, success, error);
        },
        // DELETE 请求
        del: function(url, data, success, error) {
            return this.request('DELETE', url, data, success, error);
        },
        // 通用请求方法
        request: function(method, url, data, success, error, options) {
            options = options || {};
            
            // 处理 url 为字符串的情况
            if (typeof url === 'object') {
                options = url;
                url = options.url;
                data = options.data;
                success = options.success;
                error = options.error;
                method = options.method || 'GET';
            }

            var ajaxOptions = {
                url: url,
                type: method,
                dataType: 'json',
                success: function(res) {
                    Loading.hide(options.loadingIndex);
                    
                    // 统一处理响应格式
                    if (res.hasOwnProperty('code')) {
                        if (res.code === 1 || res.code === 200) {
                            var msg = res.msg || '操作成功';
                            Notice.success(msg);
                            if (typeof success === 'function') {
                                success(res.data, res);
                            }
                        } else {
                            var msg = res.msg || '操作失败';
                            Notice.error(msg);
                            if (typeof error === 'function') {
                                error(res);
                            }
                        }
                    } else {
                        // 非标准格式直接回调
                        if (typeof success === 'function') {
                            success(res);
                        }
                    }
                },
                error: function(xhr, status, err) {
                    Loading.hide(options.loadingIndex);
                    Notice.error('网络错误: ' + (err || '未知错误'));
                    if (typeof error === 'function') {
                        error({ xhr: xhr, status: status, error: err });
                    }
                }
            };

            // 处理数据
            if (method === 'POST' || method === 'PUT') {
                if (options.json !== false && !(data instanceof FormData)) {
                    ajaxOptions.contentType = 'application/json';
                    ajaxOptions.data = JSON.stringify(data);
                } else {
                    ajaxOptions.data = data;
                }
            } else {
                ajaxOptions.data = data;
            }

            // 是否显示加载
            if (options.loading !== false) {
                ajaxOptions.beforeSend = function() {
                    options.loadingIndex = Loading.show({ msg: options.loadingText || '处理中...' });
                };
            }

            return $.ajax(ajaxOptions);
        },
        // 批量删除
        batchDelete: function(url, ids, callback) {
            if (!ids || ids.length === 0) {
                Notice.warning('请选择要删除的记录');
                return;
            }
            Confirm.delete(function() {
                Ajax.del(url, { ids: ids.join(',') }, function() {
                    if (typeof callback === 'function') {
                        callback();
                    }
                });
            });
        }
    };

    // ==================== 模态框组件 ====================
    var Dialog = {
        // 打开弹窗
        open: function(url, title, options) {
            title = title || '';
            var defaults = {
                type: 2,
                title: title,
                shadeClose: true,
                shade: 0.3,
                maxmin: true,
                moveOut: true,
                area: ['80%', '80%'],
                content: url,
                skin: 'layui-layer-noborder',
                success: function(layero, index) {
                    Layer.setTop(layero);
                },
                end: function() {
                    // 弹窗关闭后的回调
                }
            };
            return Layer.open($.extend(defaults, options || {}));
        },
        // 打开详情弹窗
        detail: function(url, title) {
            return this.open(url, title || '详情', { area: ['70%', '70%'] });
        },
        // 打开编辑弹窗
        edit: function(url) {
            return this.open(url, '编辑', { area: ['80%', '80%'] });
        },
        // 打开新增弹窗
        add: function(url) {
            return this.open(url, '新增', { area: ['80%', '80%'] });
        },
        // 关闭当前弹窗
        close: function() {
            var index = Layer.getFrameIndex(window.name);
            Layer.close(index);
        },
        // 关闭所有弹窗
        closeAll: function() {
            Layer.closeAll();
        },
        // 刷新父窗口表格
        refreshParent: function() {
            var index = Layer.getFrameIndex(window.name);
            try {
                parent.$('#table').bootstrapTable('refresh');
            } catch(e) {
                // 如果没有表格，忽略错误
            }
            Layer.close(index);
        }
    };

    // ==================== 表格组件 ====================
    var Table = {
        // 获取选中行 IDs
        getSelectedIds: function(tableSelector) {
            var $table = $(tableSelector);
            var options = $table.bootstrapTable('getOptions');
            var selections = $table.bootstrapTable('getSelections');
            return $.map(selections, function(row) {
                return row[options.pk];
            });
        },
        // 获取选中行数据
        getSelectedRows: function(tableSelector) {
            return $(tableSelector).bootstrapTable('getSelections');
        },
        // 检查是否有选中
        hasSelected: function(tableSelector) {
            return this.getSelectedIds(tableSelector).length > 0;
        },
        // 刷新表格
        refresh: function(tableSelector) {
            $(tableSelector).bootstrapTable('refresh');
        },
        // 获取表格数据（当前页）
        getData: function(tableSelector) {
            return $(tableSelector).bootstrapTable('getData');
        },
        // 跳转到指定页
        goToPage: function(tableSelector, pageNumber) {
            $(tableSelector).bootstrapTable('selectPage', pageNumber);
        }
    };

    // ==================== 表单组件 ====================
    var Form = {
        // 序列化表单为对象
        serialize: function(formSelector) {
            var data = {};
            var array = $(formSelector).serializeArray();
            $.each(array, function() {
                if (data[this.name]) {
                    if (!data[this.name].push) {
                        data[this.name] = [data[this.name]];
                    }
                    data[this.name].push(this.value || '');
                } else {
                    data[this.name] = this.value || '';
                }
            });
            return data;
        },
        // 重置表单
        reset: function(formSelector) {
            $(formSelector)[0].reset();
            $(formSelector).find('.selectpicker').selectpicker('refresh');
        },
        // 验证表单
        validate: function(formSelector, rules, messages) {
            return $(formSelector).validate({
                rules: rules || {},
                messages: messages || {},
                errorClass: 'help-block',
                errorElement: 'span',
                highlight: function(element) {
                    $(element).closest('.form-group').addClass('has-error');
                },
                unhighlight: function(element) {
                    $(element).closest('.form-group').removeClass('has-error');
                }
            }).form();
        },
        // 提交表单
        submit: function(formSelector, url, method, successCallback, errorCallback) {
            var $form = $(formSelector);
            var data = method === 'POST' || method === 'PUT' ? JSON.stringify(this.serialize(formSelector)) : this.serialize(formSelector);
            
            Ajax.request({
                url: url,
                method: method,
                data: data,
                json: method === 'POST' || method === 'PUT',
                success: function() {
                    if (typeof successCallback === 'function') {
                        successCallback();
                    }
                },
                error: function() {
                    if (typeof errorCallback === 'function') {
                        errorCallback();
                    }
                }
            });
        }
    };

    // ==================== URL 工具 ====================
    var Url = {
        // 获取 URL 参数
        getParam: function(name, url) {
            if (!url) url = window.location.href;
            name = name.replace(/[\[\]]/g, '\\$&');
            var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)');
            var results = regex.exec(url);
            if (!results) return null;
            if (!results[2]) return '';
            return decodeURIComponent(results[2].replace(/\+/g, ' '));
        },
        // 获取所有参数
        getParams: function(url) {
            if (!url) url = window.location.href;
            var params = {};
            var queryString = url.split('?')[1];
            if (!queryString) return params;
            
            var pairs = queryString.split('&');
            $.each(pairs, function(i, pair) {
                var kv = pair.split('=');
                params[kv[0]] = kv[1] ? decodeURIComponent(kv[1]) : '';
            });
            return params;
        },
        // 构建 URL 参数
        buildUrl: function(url, params) {
            if (!params || Object.keys(params).length === 0) return url;
            
            var queryString = $.param(params);
            return url + (url.indexOf('?') > -1 ? '&' : '?') + queryString;
        },
        // 修复 URL（相对路径转绝对路径）
        fix: function(url) {
            if (!url) return '';
            if (url.substr(0, 1) === '/') return url;
            if (/^(?:[a-z]+:)?\/\//i.test(url)) return url;
            
            // 相对路径添加前缀
            return Fast.api.fixurl ? Fast.api.fixurl(url) : (Config.moduleurl + '/' + url);
        },
        // CDN URL
        cdn: function(url) {
            if (!url) return '';
            if (/^(?:[a-z]+:)?\/\//i.test(url)) return url;
            return (Config.upload && Config.upload.cdnurl ? Config.upload.cdnurl : '') + url;
        }
    };

    // ==================== 数据格式化组件 ====================
    var Format = {
        // 日期时间格式化
        datetime: function(value, format) {
            if (!value) return '-';
            format = format || 'YYYY-MM-DD HH:mm:ss';
            
            var moment = window.moment;
            if (!moment) return value;
            
            // 处理时间戳
            if (typeof value === 'number') {
                value = value < 10000000000 ? value * 1000 : value;
            }
            
            return moment(value).format(format);
        },
        // 日期格式化
        date: function(value) {
            return this.datetime(value, 'YYYY-MM-DD');
        },
        // 时间格式化
        time: function(value) {
            return this.datetime(value, 'HH:mm:ss');
        },
        // 相对时间
        relativeTime: function(value) {
            if (!value) return '-';
            
            var moment = window.moment;
            if (!moment) return value;
            
            if (typeof value === 'number') {
                value = value < 10000000000 ? value * 1000 : value;
            }
            
            return moment(value).fromNow();
        },
        // 文件大小格式化
        fileSize: function(bytes) {
            if (!bytes || bytes === 0) return '0 B';
            var units = ['B', 'KB', 'MB', 'GB', 'TB'];
            var i = 0;
            while (bytes >= 1024 && i < units.length - 1) {
                bytes /= 1024;
                i++;
            }
            return bytes.toFixed(2) + ' ' + units[i];
        },
        // 数字千分位格式化
        number: function(value, decimals) {
            if (!value && value !== 0) return '0';
            decimals = decimals || 0;
            return parseFloat(value).toLocaleString('zh-CN', {
                minimumFractionDigits: decimals,
                maximumFractionDigits: decimals
            });
        },
        // 货币格式化
        currency: function(value, symbol) {
            symbol = symbol || '¥';
            if (!value && value !== 0) return symbol + '0.00';
            return symbol + parseFloat(value).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
        },
        // 百分比格式化
        percent: function(value, decimals) {
            if (!value && value !== 0) return '0%';
            decimals = decimals || 0;
            return parseFloat(value).toFixed(decimals) + '%';
        },
        // 布尔值格式化
        bool: function(value, trueText, falseText) {
            trueText = trueText || '是';
            falseText = falseText || '否';
            return value ? '<span class="label label-success">' + trueText + '</span>' 
                        : '<span class="label label-default">' + falseText + '</span>';
        },
        // 状态标签格式化
        status: function(value, statusMap) {
            statusMap = statusMap || {
                'normal': 'success',
                'hidden': 'default',
                'deleted': 'danger',
                'locked': 'info'
            };
            var color = statusMap[value] || 'primary';
            var text = value;
            return '<span class="label label-' + color + '">' + text + '</span>';
        },
        // 图片格式化
        image: function(value, className) {
            if (!value) return '-';
            className = className || 'img-sm img-center';
            return '<img class="' + className + '" src="' + Url.cdn(value) + '">';
        },
        // 多图片格式化
        images: function(value, className) {
            if (!value) return '-';
            className = className || 'img-sm img-center';
            var arr = value.split(',');
            var html = [];
            $.each(arr, function(i, v) {
                if (v) {
                    html.push('<img class="' + className + '" src="' + Url.cdn(v) + '">');
                }
            });
            return html.join(' ');
        },
        // 截断文本
        truncate: function(value, length) {
            length = length || 50;
            if (!value || value.length <= length) return value;
            return '<span title="' + value + '">' + value.substring(0, length) + '...</span>';
        },
        // 手机号格式化
        mobile: function(value) {
            if (!value) return '-';
            return value.replace(/(\d{3})\d{4}(\d{4})/, '$1****$2');
        },
        // 身份证号格式化
        idCard: function(value) {
            if (!value) return '-';
            return value.replace(/(\d{4})\d{10}(\d{4})/, '$1**********$2');
        }
    };

    // ==================== 快捷方法导出 ====================
    var Component = {
        loading: Loading,
        confirm: Confirm,
        notice: Notice,
        ajax: Ajax,
        dialog: Dialog,
        table: Table,
        form: Form,
        url: Url,
        format: Format,
        
        // 便捷方法
        success: Notice.success,
        error: Notice.error,
        warning: Notice.warning,
        info: Notice.info,
        msg: Notice.msg,
        
        // 初始化
        init: function() {
            console.log('Component loaded');
        }
    };

    return Component;
});
