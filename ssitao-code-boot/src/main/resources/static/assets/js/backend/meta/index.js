/**
 * Meta模块入口文件
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form'], function ($, undefined, Backend, Table, Form) {

    // 导出控制器
    var Controller = {
        api: {
            // 实体相关API
            entity: {
                index: function () {
                    require(['backend/meta/entity'], function (ctrl) {
                        ctrl.index();
                    });
                },
                add: function () {
                    require(['backend/meta/entity'], function (ctrl) {
                        ctrl.add();
                    });
                },
                edit: function () {
                    require(['backend/meta/entity'], function (ctrl) {
                        ctrl.edit();
                    });
                }
            },
            // 表单相关API
            form: {
                index: function () {
                    require(['backend/meta/form'], function (ctrl) {
                        ctrl.index();
                    });
                },
                add: function () {
                    require(['backend/meta/form'], function (ctrl) {
                        ctrl.add();
                    });
                },
                edit: function () {
                    require(['backend/meta/form'], function (ctrl) {
                        ctrl.edit();
                    });
                }
            },
            // 列表相关API
            list: {
                index: function () {
                    require(['backend/meta/list'], function (ctrl) {
                        ctrl.index();
                    });
                },
                add: function () {
                    require(['backend/meta/list'], function (ctrl) {
                        ctrl.add();
                    });
                },
                edit: function () {
                    require(['backend/meta/list'], function (ctrl) {
                        ctrl.edit();
                    });
                }
            }
        }
    };

    return Controller;
});
