/**
 * 字典编辑JS
 */
define(['jquery', 'bootstrap', 'backend', 'form'], function ($, undefined, Backend, Form) {

    var Controller = {
        index: function () {
            Controller.api.bindevent();
        },
        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"), function (data, ret) {
                    Layer.msg(ret.msg || '保存成功');
                    setTimeout(function () {
                        location.href = Fast.api.fixurl('iam/dict');
                    }, 1000);
                }, function (data, ret) {
                    Layer.error(ret.msg || '保存失败');
                });
            }
        }
    };

    return Controller;
});
