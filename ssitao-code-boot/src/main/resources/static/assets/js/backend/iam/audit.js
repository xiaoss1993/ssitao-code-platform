define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    var Controller = {
        index: function () {
            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/iam/audit/operate-log/page',
                    del_url: '/iam/audit/operate-log/before',
                    table: 'iam_audit_log',
                },
                // 配置响应数据处理
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        if (res.data.records) {
                            return { rows: res.data.records, total: res.data.totalRow || res.data.records.length };
                        }
                        if (res.data.content) {
                            return { rows: res.data.content, total: res.data.totalElements || res.data.total };
                        }
                        return { rows: res.data, total: res.data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table");

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'operateTime',
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'accountCode', title: '操作人账号'},
                        {field: 'accountName', title: '操作人姓名'},
                        {field: 'module', title: '模块'},
                        {field: 'operation', title: '操作类型'},
                        {field: 'method', title: '请求方法'},
                        {field: 'requestUrl', title: '请求URL'},
                        {field: 'requestParams', title: '请求参数', formatter: function(value, row, index) {
                            if (value && value.length > 50) {
                                return '<span title="' + value + '">' + value.substring(0, 50) + '...</span>';
                            }
                            return value || '-';
                        }},
                        {field: 'responseResult', title: '响应结果', formatter: function(value, row, index) {
                            if (value && value.length > 50) {
                                return '<span title="' + value + '">' + value.substring(0, 50) + '...</span>';
                            }
                            return value || '-';
                        }},
                        {
                            field: 'status',
                            title: '状态',
                            formatter: function (value, row, index) {
                                return value === 200 || value === 'success'
                                    ? '<span class="label label-success">成功</span>'
                                    : '<span class="label label-danger">失败</span>';
                            }
                        },
                        {field: 'ipAddress', title: 'IP地址'},
                        {field: 'operateTime', title: '操作时间', operate: 'RANGE', addclass: 'datetimerange', formatter: Table.api.formatter.datetime},
                        {field: 'costTime', title: '耗时(ms)', sortable: true}
                    ]
                ]
            });

            // 自定义删除历史日志功能
            $(document).on("click", ".btn-del", function() {
                var that = this;
                Layer.confirm('确定要删除所有历史日志吗？此操作不可恢复！', function(index) {
                    Layer.prompt({
                        title: '请输入要删除多少天前的日志',
                        value: '30',
                        btn: ['确定', '取消'],
                        btn2: function(index, layero) {
                            Layer.close(index);
                        }
                    }, function(days, index) {
                        $.ajax({
                            url: '/iam/audit/operate-log/before/' + days,
                            type: 'DELETE',
                            dataType: 'json',
                            success: function(res) {
                                if (res.code === 200) {
                                    $("#table").bootstrapTable('refresh');
                                    Layer.close(index);
                                    Layer.msg(res.msg || '删除成功', {icon: 1});
                                } else {
                                    Layer.msg(res.msg || '删除失败', {icon: 2});
                                }
                            }
                        });
                    });
                });
                return false;
            });

            // 为表格绑定事件
            Table.api.bindevent(table);
        },
        api: {
            formatter: {
                status: function (value, row, index) {
                    return value === 200 || value === 'success'
                        ? '<span class="label label-success">成功</span>'
                        : '<span class="label label-danger">失败</span>';
                }
            }
        }
    };
    return Controller;
});
