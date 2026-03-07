define(['jquery', 'bootstrap', 'moment', 'moment/locale/zh-cn', 'bootstrap-table', 'bootstrap-table-lang', 'bootstrap-table-mobile', 'bootstrap-table-export', 'bootstrap-table-commonsearch', 'bootstrap-table-template'], function ($, undefined, Moment) {
    var Table = {
        list: {},
        // Bootstrap-table 基础配置
        defaults: {
            url: '',
            sidePagination: 'server',
            method: 'get',
            toolbar: "#toolbar",
            search: true,
            cache: false,
            commonSearch: true,
            searchFormVisible: false,
            titleForm: '', //为空则不显示标题，不定义默认显示：普通搜索
            idTable: 'commonTable',
            showExport: true,
            exportDataType: "all",
            exportTypes: ['json', 'xml', 'csv', 'txt', 'doc', 'excel'],
            pageSize: 10,
            pageList: [10, 25, 50, 'All'],
            pagination: true,
            clickToSelect: true,
            showRefresh: false,
            locale: 'zh-CN',
            showToggle: true,
            showColumns: true,
            pk: 'id',
            sortName: 'id',
            sortOrder: 'desc',
            paginationFirstText: __("First"),
            paginationPreText: __("Previous"),
            paginationNextText: __("Next"),
            paginationLastText: __("Last"),
            mobileResponsive: true,
            cardView: true,
            checkOnInit: true,
            escape: true,
            extend: {
                index_url: '',
                add_url: '',
                edit_url: '',
                del_url: '',
                multi_url: '',
                dragsort_url: 'data/weigh.json',
            }
        },
        // Bootstrap-table 列配置
        columnDefaults: {
            align: 'center',
            valign: 'middle',
        },
        config: {
            firsttd: 'tbody tr td:first-child:not(:has(div.card-views))',
            toolbar: '.toolbar',
            refreshbtn: '.btn-refresh',
            addbtn: '.btn-add',
            editbtn: '.btn-edit',
            delbtn: '.btn-del',
            multibtn: '.btn-multi',
            disabledbtn: '.btn-disabled',
            editonebtn: '.btn-editone',
            dragsortfield: 'weigh',
        },
        api: {
            init: function (defaults, columnDefaults, locales) {
                defaults = defaults ? defaults : {};
                columnDefaults = columnDefaults ? columnDefaults : {};
                locales = locales ? locales : {};
                // 写入bootstrap-table默认配置
                $.extend(true, $.fn.bootstrapTable.defaults, Table.defaults, defaults);
                // 写入bootstrap-table column配置
                $.extend($.fn.bootstrapTable.columnDefaults, Table.columnDefaults, columnDefaults);
                // 写入bootstrap-table locale配置
                $.extend($.fn.bootstrapTable.locales[Table.defaults.locale], {
                    formatCommonSearch: function () {
                        return __('Common search');
                    },
                    formatCommonSubmitButton: function () {
                        return __('Submit');
                    },
                    formatCommonResetButton: function () {
                        return __('Reset');
                    },
                    formatCommonCloseButton: function () {
                        return __('Close');
                    },
                    formatCommonChoose: function () {
                        return __('Choose');
                    }
                }, locales);
            },
            // 绑定事件
            bindevent: function (table) {
                //Bootstrap-table的父元素,包含table,toolbar,pagnation
                var parenttable = table.closest('.bootstrap-table');
                //Bootstrap-table配置
                var options = table.bootstrapTable('getOptions');
                //Bootstrap操作区
                var toolbar = $(options.toolbar, parenttable);
                //当刷新表格时
                table.on('load-error.bs.table', function (status, res) {
                    Toastr.error(__('Unknown data format'));
                });
                //当刷新表格时
                table.on('refresh.bs.table', function (e, settings, data) {
                    $(Table.config.refreshbtn, toolbar).find(".fa").addClass("fa-spin");
                });
                //当双击单元格时
                table.on('dbl-click-row.bs.table', function (e, row, element, field) {
                    $(Table.config.editonebtn, element).trigger("click");
                });
                //当内容渲染完成后
                table.on('post-body.bs.table', function (e, settings, json, xhr) {
                    $(Table.config.refreshbtn, toolbar).find(".fa").removeClass("fa-spin");
                    $(Table.config.disabledbtn, toolbar).toggleClass('disabled', true);

                    if ($(Table.config.firsttd, table).find("input[type='checkbox'][data-index]").length > 0) {
                        // 挺拽选择,需要重新绑定事件
                        require(['drag', 'drop'], function () {
                            $(Table.config.firsttd, table).drag("start", function (ev, dd) {
                                return $('<div class="selection" />').css('opacity', .65).appendTo(document.body);
                            }).drag(function (ev, dd) {
                                $(dd.proxy).css({
                                    top: Math.min(ev.pageY, dd.startY),
                                    left: Math.min(ev.pageX, dd.startX),
                                    height: Math.abs(ev.pageY - dd.startY),
                                    width: Math.abs(ev.pageX - dd.startX)
                                });
                            }).drag("end", function (ev, dd) {
                                $(dd.proxy).remove();
                            });
                            $(Table.config.firsttd, table).drop("start", function () {
                                Table.api.toggleattr(this);
                            }).drop(function () {
                                Table.api.toggleattr(this);
                            }).drop("end", function () {
                                Table.api.toggleattr(this);
                            });
                            $.drop({
                                multi: true
                            });
                        });
                    }
                });

                // 处理选中筛选框后按钮的状态统一变更
                table.on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table fa.event.check', function () {
                    var ids = Table.api.selectedids(table);
                    $(Table.config.disabledbtn, toolbar).toggleClass('disabled', !ids.length);
                });

                // 刷新按钮事件
                $(toolbar).on('click', Table.config.refreshbtn, function () {
                    table.bootstrapTable('refresh');
                });
                // 添加按钮事件
                $(toolbar).on('click', Table.config.addbtn, function () {
                    var ids = Table.api.selectedids(table);
                    Fast.api.open(options.extend.add_url + (options.extend.add_url.match(/(\?|&)+/) ? "&ids=" : "?ids=") + ids.join(","), __('Add'));
                });
                // 批量编辑按钮事件
                $(toolbar).on('click', Table.config.editbtn, function () {
                    var ids = Table.api.selectedids(table);
                    //循环弹出多个编辑框
                    $.each(ids, function (i, j) {
                        Fast.api.open(options.extend.edit_url + (options.extend.edit_url.match(/(\?|&)+/) ? "&ids=" : "?ids=") + j, __('Edit'));
                    });
                });
                // 批量操作按钮事件
                $(toolbar).on('click', Table.config.multibtn, function () {
                    var ids = Table.api.selectedids(table);
                    Table.api.multi($(this).data("action"), ids, table, this);
                });
                // 批量删除按钮事件
                $(toolbar).on('click', Table.config.delbtn, function () {
                    var that = this;
                    var ids = Table.api.selectedids(table);
                    var index = Layer.confirm(
                            __('Are you sure you want to delete the %s selected item?', ids.length),
                            {icon: 3, title: __('Warning'), offset: 0, shadeClose: true},
                            function () {
                                Table.api.multi("del", ids, table, that);
                                Layer.close(index);
                            }
                    );
                });
                // 拖拽排序
                require(['dragsort'], function () {
                    //绑定拖动排序
                    $("tbody", table).dragsort({
                        itemSelector: 'tr',
                        dragSelector: "a.btn-dragsort",
                        dragEnd: function () {
                            var data = table.bootstrapTable('getData');
                            var current = data[parseInt($(this).data("index"))];
                            var options = table.bootstrapTable('getOptions');
                            //改变的值和改变的ID集合
                            var ids = $.map($("tbody tr:visible", table), function (tr) {
                                return data[parseInt($(tr).data("index"))][options.pk];
                            });
                            var changeid = current[options.pk];
                            var pid = typeof current.pid != 'undefined' ? current.pid : '';
                            var params = {
                                url: table.bootstrapTable('getOptions').extend.dragsort_url,
                                data: {
                                    ids: ids.join(','),
                                    changeid: changeid,
                                    pid: pid,
                                    field: Table.config.dragsortfield,
                                    orderway: options.sortOrder,
                                    table: options.extend.table
                                }
                            };
                            Fast.api.ajax(params, function (data) {
                                Toastr.success(__('Operation completed'));
                                table.bootstrapTable('refresh');
                            });
                        },
                        placeHolderTemplate: ""
                    });
                });
                $(table).on("click", "input[data-id][name='checkbox']", function (e) {
                    table.trigger('fa.event.check');
                });
                $(table).on("click", "[data-id].btn-change", function (e) {
                    e.preventDefault();
                    Table.api.multi($(this).data("action") ? $(this).data("action") : '', [$(this).data("id")], table, this);
                });
                $(table).on("click", "[data-id].btn-edit", function (e) {
                    e.preventDefault();
                    Fast.api.open(options.extend.edit_url + (options.extend.edit_url.match(/(\?|&)+/) ? "&ids=" : "?ids=") + $(this).data("id"), __('Edit'));
                });
                $(table).on("click", "[data-id].btn-del", function (e) {
                    e.preventDefault();
                    var id = $(this).data("id");
                    var that = this;
                    var index = Layer.confirm(
                            __('Are you sure you want to delete this item?'),
                            {icon: 3, title: __('Warning'), shadeClose: true},
                            function () {
                                Table.api.multi("del", id, table, that);
                                Layer.close(index);
                            }
                    );

                });

                var id = table.attr("id");
                Table.list[id] = table;
                return table;
            },
            // 批量操作请求
            multi: function (action, ids, table, element) {
                var options = table.bootstrapTable('getOptions');
                var data = element ? $(element).data() : {};
                var url = typeof data.url !== "undefined" ? data.url : (action == "del" ? options.extend.del_url : options.extend.multi_url);
                url = url + (url.match(/(\?|&)+/) ? "&ids=" : "?ids=") + ($.isArray(ids) ? ids.join(",") : ids);
                var params = typeof data.params !== "undefined" ? (typeof data.params == 'object' ? $.param(data.params) : data.params) : '';
                var options = {url: url, data: {action: action, ids: ids, params: params}};
                Fast.api.ajax(options, function (data) {
                    Toastr.success(__('Operation completed'));
                    table.bootstrapTable('refresh');
                });
            },
            // 单元格元素事件
            events: {
                operate: {
                    'click .btn-editone': function (e, value, row, index) {
                        e.stopPropagation();
                        var options = $(this).closest('table').bootstrapTable('getOptions');
                        Fast.api.open(options.extend.edit_url + (options.extend.edit_url.match(/(\?|&)+/) ? "&ids=" : "?ids=") + row[options.pk], __('Edit'));
                    },
                    'click .btn-delone': function (e, value, row, index) {
                        e.stopPropagation();
                        var that = this;
                        var top = $(that).offset().top - $(window).scrollTop();
                        var left = $(that).offset().left - $(window).scrollLeft() - 260;
                        if (top + 154 > $(window).height()) {
                            top = top - 154;
                        }
                        if ($(window).width() < 480) {
                            top = left = undefined;
                        }
                        var index = Layer.confirm(
                                __('Are you sure you want to delete this item?'),
                                {icon: 3, title: __('Warning'), offset: [top, left], shadeClose: true},
                                function () {
                                    var table = $(that).closest('table');
                                    var options = table.bootstrapTable('getOptions');
                                    Table.api.multi("del", row[options.pk], table, that);
                                    Layer.close(index);
                                }
                        );
                    }
                }
            },
            // 单元格数据格式化
            formatter: {
                icon: function (value, row, index) {
                    if (!value)
                        return '';
                    value = value.indexOf(" ") > -1 ? value : "fa fa-" + value;
                    //渲染fontawesome图标
                    return '<i class="' + value + '"></i> ' + value;
                },
                image: function (value, row, index, custom) {
                    var classname = typeof custom !== 'undefined' ? custom : 'img-sm img-center';
                    return '<img class="' + classname + '" src="' + Fast.api.cdnurl(value) + '" />';
                },
                images: function (value, row, index, custom) {
                    var classname = typeof custom !== 'undefined' ? custom : 'img-sm img-center';
                    var arr = value.split(',');
                    var html = [];
                    $.each(arr, function (i, value) {
                        html.push('<img class="' + classname + '" src="' + Fast.api.cdnurl(value) + '" />');
                    });
                    return html.join(' ');
                },
                status: function (value, row, index, custom) {
                    //颜色状态数组,可使用red/yellow/aqua/blue/navy/teal/olive/lime/fuchsia/purple/maroon
                    var colorArr = {normal: 'success', hidden: 'grey', deleted: 'danger', locked: 'info'};
                    //如果有自定义状态,可以按需传入
                    if (typeof custom !== 'undefined') {
                        colorArr = $.extend(colorArr, custom);
                    }
                    value = value.toString();
                    var color = value && typeof colorArr[value] !== 'undefined' ? colorArr[value] : 'primary';
                    value = value.charAt(0).toUpperCase() + value.slice(1);
                    //渲染状态
                    var html = '<span class="text-' + color + '"><i class="fa fa-circle"></i> ' + __(value) + '</span>';
                    return html;
                },
                url: function (value, row, index) {
                    return '<div class="input-group input-group-sm" style="width:250px;"><input type="text" class="form-control input-sm" value="' + value + '"><span class="input-group-btn input-group-sm"><a href="' + value + '" target="_blank" class="btn btn-default btn-sm"><i class="fa fa-link"></i></a></span></div>';
                },
                search: function (value, row, index) {
                    return '<a href="javascript:;" class="searchit" data-field="' + this.field + '" data-value="' + value + '">' + value + '</a>';
                },
                addtabs: function (value, row, index, url) {
                    return '<a href="' + url + '" class="addtabsit" title="' + __("Search %s", value) + '">' + value + '</a>';
                },
                flag: function (value, row, index, custom) {
                    var colorArr = {index: 'success', hot: 'warning', recommend: 'danger', 'new': 'info'};
                    //如果有自定义状态,可以按需传入
                    if (typeof custom !== 'undefined') {
                        colorArr = $.extend(colorArr, custom);
                    }
                    //渲染Flag
                    var html = [];
                    var arr = value.split(',');
                    $.each(arr, function (i, value) {
                        value = value.toString();
                        if (value == '')
                            return true;
                        var color = value && typeof colorArr[value] !== 'undefined' ? colorArr[value] : 'primary';
                        value = value.charAt(0).toUpperCase() + value.slice(1);
                        html.push('<span class="label label-' + color + '">' + __(value) + '</span>');
                    });
                    return html.join(' ');
                },
                label: function (value, row, index, custom) {
                    var colorArr = ['success', 'warning', 'danger', 'info'];
                    //渲染Flag
                    var html = [];
                    var arr = value.split(',');
                    $.each(arr, function (i, value) {
                        value = value.toString();
                        var color = colorArr[i % colorArr.length];
                        html.push('<span class="label label-' + color + '">' + __(value) + '</span>');
                    });
                    return html.join(' ');
                },
                /**
                 * 时间格式化
                 * @param {*} value 值
                 * @param {*} row 行数据
                 * @param {*} index 行索引
                 * @param {*} custom 自定义格式: 输出格式字符串，或配置对象 {format: '输出格式', inputFormat: '输入格式'}
                 * @returns 格式化后的时间字符串
                 */
                datetime: function (value, row, index, custom) {
                    if (!value && value !== 0) return __('None');

                    var inputFormat = null;
                    var outputFormat = 'YYYY-MM-DD HH:mm:ss';

                    // 解析自定义配置
                    if (typeof custom === 'string') {
                        outputFormat = custom;
                    } else if (typeof custom === 'object') {
                        inputFormat = custom.inputFormat || null;
                        outputFormat = custom.format || outputFormat;
                    }

                    var momentObj = null;
                    var valueType = typeof value;

                    // 时间戳（数字类型）
                    if (valueType === 'number') {
                        // 小于100亿认为是秒级时间戳，否则是毫秒级
                        momentObj = Moment(value < 10000000000 ? value * 1000 : value);
                    }
                    // 字符串类型
                    else if (valueType === 'string') {
                        // 纯数字字符串
                        if (/^\d+$/.test(value)) {
                            var num = parseInt(value);
                            momentObj = Moment(num < 10000000000 ? num * 1000 : num);
                        }
                        // 有指定输入格式
                        else if (inputFormat) {
                            momentObj = Moment(value, inputFormat, true);
                            if (!momentObj.isValid()) {
                                momentObj = Moment(value, inputFormat);
                            }
                        }
                        // 无指定格式，尝试多种常见格式
                        else {
                            // 支持的输入格式列表
                            var formats = [
                                'YYYY-MM-DD HH:mm:ss',      // 2024-01-01 12:30:00
                                'YYYY-MM-DDTHH:mm:ss',      // 2024-01-01T12:30:00 (ISO)
                                'YYYY-MM-DD',               // 2024-01-01
                                'YYYY/MM/DD HH:mm:ss',     // 2024/01/01 12:30:00
                                'YYYY/MM/DD',              // 2024/01/01
                                'DD/MM/YYYY HH:mm:ss',     // 01/01/2024 12:30:00
                                'DD/MM/YYYY',              // 01/01/2024
                                'MM/DD/YYYY HH:mm:ss',     // 01/01/2024 12:30:00
                                'MM/DD/YYYY',              // 01/01/2024
                                'HH:mm:ss',                // 12:30:00
                                'YYYYMMDD',                // 20240101
                                'yyyy-MMM-DD HH:mm:ss',    // 2024-Jan-01 12:30:00
                            ];

                            // 尝试严格解析
                            momentObj = Moment(value, formats, true);
                            // 如果严格解析失败，尝试宽松解析
                            if (!momentObj.isValid()) {
                                momentObj = Moment(value);
                            }
                        }
                    }
                    // Date对象
                    else if (value instanceof Date) {
                        momentObj = Moment(value);
                    }
                    // Moment对象
                    else if (value._isMoment) {
                        momentObj = value;
                    }

                    return momentObj && momentObj.isValid() ? momentObj.format(outputFormat) : __('None');
                },
                // 开关切换
                switch: function (value, row, index, field) {
                    if (!value && value !== 0) return '';
                    var checked = value ? 'checked' : '';
                    var disabled = (typeof row.disabled !== 'undefined' && row.disabled) ? 'disabled' : '';
                    return '<input type="checkbox" class="bs-switch" data-field="' + (field || this.field) + '" ' + checked + ' ' + disabled + '>';
                },
                // 进度条
                progress: function (value, row, index, custom) {
                    if (!value && value !== 0) return '0%';
                    value = parseFloat(value);
                    if (isNaN(value)) value = 0;
                    if (value < 0) value = 0;
                    if (value > 100) value = 100;

                    var color = 'progress-bar-success';
                    if (typeof custom !== 'undefined') {
                        if (custom === 'danger' || value < 30) color = 'progress-bar-danger';
                        else if (custom === 'warning' || value < 60) color = 'progress-bar-warning';
                        else if (custom === 'info' || value < 80) color = 'progress-bar-info';
                    } else {
                        if (value < 30) color = 'progress-bar-danger';
                        else if (value < 60) color = 'progress-bar-warning';
                        else if (value < 80) color = 'progress-bar-info';
                    }
                    return '<div class="progress progress-xs" style="margin-bottom:0;">' +
                        '<div class="progress-bar ' + color + '" style="width: ' + value + '%"></div>' +
                        '</div><span class="sr-only">' + value + '%</span>';
                },
                // 进度条（带文字）
                progressText: function (value, row, index, custom) {
                    if (!value && value !== 0) return '0%';
                    value = parseFloat(value);
                    if (isNaN(value)) value = 0;
                    if (value < 0) value = 0;
                    if (value > 100) value = 100;

                    var color = 'success';
                    if (typeof custom !== 'undefined') {
                        if (custom === 'danger' || value < 30) color = 'danger';
                        else if (custom === 'warning' || value < 60) color = 'warning';
                        else if (custom === 'info' || value < 80) color = 'info';
                    } else {
                        if (value < 30) color = 'danger';
                        else if (value < 60) color = 'warning';
                        else if (value < 80) color = 'info';
                    }
                    return '<div class="progress progress-xs" style="margin-bottom:0;">' +
                        '<div class="progress-bar bg-' + color + '" style="width: ' + value + '%"></div>' +
                        '</div><span class="text-xs">' + value + '%</span>';
                },
                // 文件大小格式化
                byte: function (value, row, index) {
                    if (!value && value !== 0) return '0 B';
                    value = parseInt(value);
                    if (isNaN(value)) return '0 B';
                    if (value < 1024) return value + ' B';
                    var units = ['B', 'KB', 'MB', 'GB', 'TB', 'PB'];
                    var i = 0;
                    while (value >= 1024 && i < units.length - 1) {
                        value /= 1024;
                        i++;
                    }
                    return value.toFixed(2) + ' ' + units[i];
                },
                // 数字格式化（千分位）
                number: function (value, row, index, custom) {
                    if (!value && value !== 0) return '0';
                    value = parseFloat(value);
                    if (isNaN(value)) return '0';

                    var decimals = (typeof custom !== 'undefined') ? parseInt(custom) : 0;
                    return value.toLocaleString('zh-CN', { minimumFractionDigits: decimals, maximumFractionDigits: decimals });
                },
                // 货币格式化
                currency: function (value, row, index, custom) {
                    if (!value && value !== 0) return '¥0.00';
                    value = parseFloat(value);
                    if (isNaN(value)) return '¥0.00';

                    var symbol = '¥';
                    if (typeof custom !== 'undefined') {
                        symbol = custom;
                    }
                    return symbol + value.toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
                },
                // 文本截断
                truncate: function (value, row, index, custom) {
                    if (!value) return '';
                    var len = (typeof custom !== 'undefined') ? parseInt(custom) : 50;
                    if (value.length <= len) return value;
                    return '<span title="' + value + '">' + value.substring(0, len) + '...</span>';
                },
                // 布尔值显示
                bool: function (value, row, index, custom) {
                    var trueText = '是';
                    var falseText = '否';
                    var trueClass = 'success';
                    var falseClass = 'default';

                    if (typeof custom !== 'undefined') {
                        if (typeof custom === 'object') {
                            trueText = custom.trueText || trueText;
                            falseText = custom.falseText || falseText;
                            trueClass = custom.trueClass || trueClass;
                            falseClass = custom.falseClass || falseClass;
                        } else {
                            var arr = custom.split(',');
                            if (arr.length >= 1) trueText = arr[0];
                            if (arr.length >= 2) falseText = arr[1];
                            if (arr.length >= 3) trueClass = arr[2];
                            if (arr.length >= 4) falseClass = arr[3];
                        }
                    }

                    if (value === true || value === 'true' || value === 1 || value === '1' || value === 'yes') {
                        return '<span class="label label-' + trueClass + '">' + trueText + '</span>';
                    }
                    return '<span class="label label-' + falseClass + '">' + falseText + '</span>';
                },
                // 百分比
                percent: function (value, row, index, custom) {
                    if (!value && value !== 0) return '0%';
                    value = parseFloat(value);
                    if (isNaN(value)) return '0%';
                    if (value < 0) value = 0;
                    if (value > 100) value = 100;

                    var decimals = (typeof custom !== 'undefined') ? parseInt(custom) : 0;
                    return value.toFixed(decimals) + '%';
                },
                // 短日期（仅日期）
                date: function (value, row, index) {
                    if (!value) return __('None');
                    var result = value;
                    if (typeof value === 'number') {
                        result = Moment(value < 10000000000 ? value * 1000 : value);
                    } else if (typeof value === 'string') {
                        if (/^\d+$/.test(value)) {
                            var num = parseInt(value);
                            result = Moment(num < 10000000000 ? num * 1000 : num);
                        } else {
                            result = Moment(value, 'YYYY-MM-DD', true);
                            if (!result.isValid()) {
                                result = Moment(value);
                            }
                        }
                    }
                    return result && result.isValid() ? result.format('YYYY-MM-DD') : __('None');
                },
                // 时间（仅时间）
                time: function (value, row, index) {
                    if (!value) return __('None');
                    var result = value;
                    if (typeof value === 'number') {
                        result = Moment(value < 10000000000 ? value * 1000 : value);
                    } else if (typeof value === 'string') {
                        if (/^\d+$/.test(value)) {
                            var num = parseInt(value);
                            result = Moment(num < 10000000000 ? num * 1000 : num);
                        } else {
                            result = Moment(value, 'HH:mm:ss', true);
                            if (!result.isValid()) {
                                result = Moment(value);
                            }
                        }
                    }
                    return result && result.isValid() ? result.format('HH:mm:ss') : __('None');
                },
                // 相对时间（如：刚刚、5分钟前）
                relativeTime: function (value, row, index) {
                    if (!value) return __('None');
                    var result = value;
                    if (typeof value === 'number') {
                        result = Moment(value < 10000000000 ? value * 1000 : value);
                    } else if (typeof value === 'string') {
                        if (/^\d+$/.test(value)) {
                            var num = parseInt(value);
                            result = Moment(num < 10000000000 ? num * 1000 : num);
                        } else {
                            result = Moment(value);
                        }
                    }
                    if (!result || !result.isValid()) return __('None');

                    var now = Moment();
                    var diff = now.diff(result, 'seconds');

                    if (diff < 60) return '刚刚';
                    if (diff < 3600) return Math.floor(diff / 60) + '分钟前';
                    if (diff < 86400) return Math.floor(diff / 3600) + '小时前';
                    if (diff < 604800) return Math.floor(diff / 86400) + '天前';
                    return result.format('YYYY-MM-DD');
                },
                operate: function (value, row, index, table) {
                    var showweigh = true;
                    var showedit = true;
                    var showdel = true;
                    if (typeof table != 'undefined') {
                        var options = table.bootstrapTable('getOptions');
                        if (options.extend.del_url == '')
                            showdel = false;
                        if (options.extend.edit_url == '')
                            showedit = false;
                    }
                    showweigh = typeof row[Table.config.dragsortfield] != 'undefined' ? true : false;
                    //行操作
                    var html = [];
                    if (showweigh)
                        html.push('<a href="javascript:;" class="btn btn-primary btn-dragsort btn-xs"><i class="fa fa-arrows"></i></a>');
                    if (showedit)
                        html.push('<a href="javascript:;" class="btn btn-success btn-editone btn-xs"><i class="fa fa-pencil"></i></a>');
                    if (showdel)
                        html.push('<a href="javascript:;" class="btn btn-danger btn-delone btn-xs"><i class="fa fa-trash"></i></a>');
                    return html.join(' ');
                }
            },
            // 获取选中的条目ID集合
            selectedids: function (table) {
                var options = table.bootstrapTable('getOptions');
                if (options.templateView) {
                    return $.map($("input[data-id][name='checkbox']:checked"), function (dom) {
                        return $(dom).data("id");
                    });
                } else {
                    return $.map(table.bootstrapTable('getSelections'), function (row) {
                        return row[options.pk];
                    });
                }
            },
            // 切换复选框状态
            toggleattr: function (table) {
                $("input[type='checkbox']", table).trigger('click');
            }
        },
    };
    return Table;
});
