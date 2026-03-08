/**
 * 字典项编辑JS
 */
define(['jquery', 'bootstrap', 'backend', 'form'], function ($, undefined, Backend, Form) {

    var Controller = {
        index: function () {
            // 加载字典列表
            $.ajax({
                url: 'iam/system/dict-types',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择字典</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.dictId + '">' + item.dictName + '</option>';
                        });
                        $('#dictId').html(options);
                    }
                }
            });
            Controller.api.bindevent();
        },
        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"), function (data, ret) {
                    Layer.msg(ret.msg || '保存成功');
                    setTimeout(function () {
                        location.href = Fast.api.fixurl('iam/dict/item');
                    }, 1000);
                }, function (data, ret) {
                    Layer.error(ret.msg || '保存失败');
                });
            }
        }
    };

    return Controller;
});
