/**
 * 实体编辑JS
 */
define(['jquery', 'bootstrap', 'backend', 'form'], function ($, undefined, Backend, Form) {

    var Controller = {
        index: function () {
            Controller.api.bindevent();
        },
        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"), function (data, ret) {
                    // 提交成功后的回调
                    Layer.msg(ret.msg || '保存成功');
                    setTimeout(function () {
                        location.href = Fast.api.fixurl('meta/entity');
                    }, 1000);
                }, function (data, ret) {
                    // 提交失败后的回调
                    Layer.error(ret.msg || '保存失败');
                });
            }
        }
    };

    return Controller;
});
