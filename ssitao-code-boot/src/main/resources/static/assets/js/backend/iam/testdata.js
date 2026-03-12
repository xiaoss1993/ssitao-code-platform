/**
 * 测试数据生成JS
 */
define(['jquery', 'bootstrap', 'backend', 'form'], function ($, undefined, Backend, Form) {

    var Controller = {
        index: function () {
            // 生成全部测试数据
            $('#btnGenerateAll').on('click', function () {
                var count = parseInt($('#allCount').val()) || 100;
                var tenantId = $('#allTenantId').val() || 'TEST_TENANT';

                $(this).prop('disabled', true).html('<i class="fa fa-spinner fa-spin"></i> 生成中...');

                $.ajax({
                    url: '/api/test-data/all',
                    type: 'POST',
                    data: { count: count, tenantId: tenantId },
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 200 && res.data) {
                            Controller.showResult(res.data);
                            Layer.msg('生成完成', {icon: 1});
                        } else {
                            Layer.msg(res.msg || '生成失败', {icon: 2});
                        }
                    },
                    error: function () {
                        Layer.msg('网络错误', {icon: 2});
                    },
                    complete: function () {
                        $('#btnGenerateAll').prop('disabled', false).html('<i class="fa fa-plus"></i> 生成全部');
                    }
                });
            });

            // 清空全部测试数据
            $('#btnClearAll').on('click', function () {
                Layer.confirm('确定要清空所有测试数据吗？', function (index) {
                    $(this).prop('disabled', true).html('<i class="fa fa-spinner fa-spin"></i> 清空中...');

                    $.ajax({
                        url: '/api/test-data/clear',
                        type: 'DELETE',
                        data: { type: 'all' },
                        dataType: 'json',
                        success: function (res) {
                            if (res.code === 200) {
                                $('#resultContent').html('<p class="text-success">已清空 ' + res.data + ' 条数据</p>');
                                Layer.msg('清空完成', {icon: 1});
                            } else {
                                Layer.msg(res.msg || '清空失败', {icon: 2});
                            }
                        },
                        error: function () {
                            Layer.msg('网络错误', {icon: 2});
                        },
                        complete: function () {
                            $('#btnClearAll').prop('disabled', false).html('<i class="fa fa-trash"></i> 清空全部');
                            layer.close(index);
                        }
                    });
                });
            });

            // 生成单个类型测试数据
            $('#btnGenerateSingle').on('click', function () {
                var type = $('#dataType').val();
                var count = parseInt($('#singleCount').val()) || 100;
                var tenantId = $('#singleTenantId').val() || 'TEST_TENANT';

                $(this).prop('disabled', true).html('<i class="fa fa-spinner fa-spin"></i> 生成中...');

                $.ajax({
                    url: '/api/test-data/iam/' + type,
                    type: 'POST',
                    data: { count: count, tenantId: tenantId },
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 200 && res.data) {
                            Controller.showResult(res.data);
                            Layer.msg('生成完成', {icon: 1});
                        } else {
                            Layer.msg(res.msg || '生成失败', {icon: 2});
                        }
                    },
                    error: function () {
                        Layer.msg('网络错误', {icon: 2});
                    },
                    complete: function () {
                        $('#btnGenerateSingle').prop('disabled', false).html('<i class="fa fa-plus"></i> 生成');
                    }
                });
            });

            // 清空单个类型测试数据
            $('#btnClearSingle').on('click', function () {
                var type = $('#dataType').val();

                Layer.confirm('确定要清空 ' + type + ' 测试数据吗？', function (index) {
                    $(this).prop('disabled', true).html('<i class="fa fa-spinner fa-spin"></i> 清空中...');

                    $.ajax({
                        url: '/api/test-data/clear',
                        type: 'DELETE',
                        data: { type: type },
                        dataType: 'json',
                        success: function (res) {
                            if (res.code === 200) {
                                $('#resultContent').html('<p class="text-success">已清空 ' + res.data + ' 条' + type + '数据</p>');
                                Layer.msg('清空完成', {icon: 1});
                            } else {
                                Layer.msg(res.msg || '清空失败', {icon: 2});
                            }
                        },
                        error: function () {
                            Layer.msg('网络错误', {icon: 2});
                        },
                        complete: function () {
                            $('#btnClearSingle').prop('disabled', false).html('<i class="fa fa-trash"></i> 清空');
                            layer.close(index);
                        }
                    });
                });
            });
        },

        showResult: function (data) {
            var html = '';
            if (data.success) {
                html += '<div class="alert alert-success">';
                html += '<h4><i class="fa fa-check-circle"></i> 生成成功</h4>';
                html += '<p><strong>总数量：</strong>' + data.totalCount + ' 条</p>';
                html += '<p><strong>耗时：</strong>' + data.elapsedTime + 'ms</p>';
                html += '<p><strong>租户ID：</strong>' + data.tenantId + '</p>';
                html += '<p><strong>各类型数量：</strong></p>';
                html += '<ul>';
                for (var key in data.typeCountMap) {
                    html += '<li>' + key + ': ' + data.typeCountMap[key] + ' 条</li>';
                }
                html += '</ul>';
                html += '</div>';
            } else {
                html += '<div class="alert alert-danger">';
                html += '<h4><i class="fa fa-times-circle"></i> 生成失败</h4>';
                html += '<p><strong>错误信息：</strong>' + (data.errorMessage || '未知错误') + '</p>';
                html += '</div>';
            }
            $('#resultContent').html(html);
        },

        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"));
            }
        }
    };

    return Controller;
});
