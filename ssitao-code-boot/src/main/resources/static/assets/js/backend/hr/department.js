/**
 * 部门管理JS
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'layer', 'ztree'], function ($, undefined, Backend, Table, Form, Layer) {

    var Controller = {
        index: function () {
            // 加载部门树
            Controller.loadDepartmentTree();

            // 绑定按钮事件
            $(document).on('click', '.btn-add', function () {
                Controller.add();
            });

            $(document).on('click', '.btn-edit', function () {
                var id = $(this).data('id');
                Controller.edit(id);
            });

            $(document).on('click', '.btn-del', function () {
                var id = $(this).data('id');
                Controller.delete(id);
            });

            $(document).on('click', '.btn-refresh', function () {
                Controller.loadDepartmentTree();
            });
        },

        loadDepartmentTree: function () {
            $.ajax({
                url: '/hr/org/department/tree',
                type: 'GET',
                data: { tenantId: '1' },
                dataType: 'json',
                success: function (res) {
                    if (res.code === 200 && res.data) {
                        Controller.initTree(res.data);
                    } else {
                        Layer.msg(res.msg || '加载部门树失败');
                    }
                }
            });
        },

        initTree: function (data) {
            var setting = {
                view: {
                    showLine: true,
                    showIcon: true,
                    dblClickExpand: true
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: 'deptId',
                        pIdKey: 'parentId',
                        rootPId: '0'
                    },
                    key: {
                        name: 'deptName'
                    }
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        // 点击节点事件，可以加载部门详情
                        console.log('点击部门:', treeNode);
                    }
                }
            };

            // 添加操作按钮到节点
            Controller.addTreeNodes(data);

            $.fn.zTree.init($("#departmentTree"), setting, data);
        },

        addTreeNodes: function (data) {
            data.forEach(function (item) {
                item.icon = '/assets/libs/zTree/css/zTreeStyle/img/diy/1_close.png';
                item.name = item.deptName;
            });
        },

        add: function (parentId) {
            parentId = parentId || 0;
            Layer.open({
                type: 2,
                title: '添加部门',
                shadeClose: true,
                shade: 0.3,
                area: ['600px', '450px'],
                content: '/hr/org/department/add?parentId=' + parentId,
                end: function () {
                    Controller.loadDepartmentTree();
                }
            });
        },

        edit: function (id) {
            Layer.open({
                type: 2,
                title: '编辑部门',
                shadeClose: true,
                shade: 0.3,
                area: ['600px', '450px'],
                content: '/hr/org/department/edit?id=' + id,
                end: function () {
                    Controller.loadDepartmentTree();
                }
            });
        },

        delete: function (id) {
            Layer.confirm('确定要删除该部门吗？', function (index) {
                $.ajax({
                    url: '/hr/org/department/' + id,
                    type: 'DELETE',
                    data: { tenantId: '1' },
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 200) {
                            Backend.api.toastr.success('删除成功');
                            Controller.loadDepartmentTree();
                        } else {
                            Backend.api.toastr.error(res.msg || '删除失败');
                        }
                    }
                });
                Layer.close(index);
            });
        },

        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"));
            }
        }
    };

    return Controller;
});
