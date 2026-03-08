/**
 * 用户编辑JS
 */
define(['jquery', 'bootstrap', 'backend', 'form'], function ($, undefined, Backend, Form) {

    var Controller = {
        index: function () {
            // 加载部门列表
            $.ajax({
                url: '/iam/org/department/list',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择部门</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.deptId + '">' + item.deptName + '</option>';
                        });
                        $('#deptId').html(options);
                    }
                }
            });

            // 加载岗位列表
            $.ajax({
                url: '/iam/org/post/list',
                type: 'GET',
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        var options = '<option value="">请选择岗位</option>';
                        res.data.forEach(function (item) {
                            options += '<option value="' + item.postId + '">' + item.postName + '</option>';
                        });
                        $('#postId').html(options);
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
                        location.href = Fast.api.fixurl('iam/user');
                    }, 1000);
                }, function (data, ret) {
                    Layer.error(ret.msg || '保存失败');
                });
            }
        }
    };

    return Controller;
});
