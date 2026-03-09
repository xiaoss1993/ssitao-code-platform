/**
 * 字典管理JS
 * 左侧：字典类型列表（树形）
 * 右侧：字典项表格
 */
define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'ztree'], function ($, undefined, Backend, Table, Form, Ztree) {

    // 格式化树数据
    function formatTreeData(data) {
        if (!data || !Array.isArray(data)) {
            return [];
        }
        return data.map(function(item) {
            var node = {
                id: item.dictId,
                name: item.dictName || item.dict_code || '',
                pId: item.parentId || '0',
                isParent: true,
                open: false,
                dictType: item.dictType,
                dictSource: item.dictSource
            };
            return node;
        });
    }

    var Controller = {
        index: function () {
            var currentDictId = null; // 当前选中的字典ID
            var table = $("#table"); // 表格实例

            // 初始化zTree
            var zTreeObj;
            var zTreeSettings = {
                view: {
                    dblClickExpand: false,
                    showLine: true,
                    selectedMulti: false,
                    nameIsHTML: true
                },
                async: {
                    enable: false
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        currentDictId = treeNode.id;
                        // 点击节点时刷新右侧表格，显示该字典下的字典项
                        table.bootstrapTable('refresh');
                    }
                }
            };

            // 加载树数据（字典类型列表）
            function loadTree() {
                $.ajax({
                    url: '/meta/dictionary/tree',
                    type: 'get',
                    dataType: 'json',
                    success: function (res) {
                        if (res.code === 200 && res.data) {
                            // 转换数据格式
                            var treeData = formatTreeData(res.data);
                            zTreeObj = $.fn.zTree.init($("#dictTree"), zTreeSettings, treeData);
                            // 默认不展开，需要用户手动点击展开
                        }
                    }
                });
            }

            // 初始化表格参数配置
            Table.api.init({
                extend: {
                    index_url: '/meta/dictionary-item/list',
                    add_url: '/meta/dictionary-item',
                    edit_url: '/meta/dictionary-item',
                    del_url: '/meta/dictionary-item',
                    table: 'core_dictionary_item',
                },
                responseHandler: function (res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        if (data.content) {
                            return { rows: data.content, total: data.totalElements || data.content.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            // 初始化表格
            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'itemId',
                sortName: 'itemSort',
                sidePagination: 'server',
                pagination: true,
                pageSize: 10,
                pageList: [10, 25, 50, 100],
                queryParamsType: 'undefined',
                // 查询参数函数
                queryParams: function (params) {
                    return {
                        page: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder,
                        dictId: currentDictId
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'itemId', title: 'ID', sortable: true},
                        {field: 'itemCode', title: '字典项编码', operate: 'LIKE'},
                        {field: 'itemName', title: '字典项名称', operate: 'LIKE'},
                        {field: 'itemValue', title: '字典项值', operate: 'LIKE'},
                        {
                            field: 'itemStatus',
                            title: '状态',
                            searchList: {"1": "启用", "0": "禁用"},
                            formatter: function (value) {
                                return value == 1 ? '<span class="label label-success">启用</span>' : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'itemIsDefault',
                            title: '默认',
                            searchList: {"1": "是", "0": "否"},
                            formatter: function (value) {
                                return value == 1 ? '<span class="label label-primary">是</span>' : '<span class="label label-default">否</span>';
                            }
                        },
                        {field: 'itemSort', title: '排序', sortable: true},
                        {field: 'itemDesc', title: '描述'},
                        {
                            field: 'operate',
                            title: '操作',
                            table: table,
                            events: Table.api.events.operate,
                            formatter: Table.api.formatter.operate
                        }
                    ]
                ]
            });

            // 绑定工具栏按钮事件
            // 添加字典项按钮
            $(document).on("click", ".btn-add", function () {
                if (!currentDictId) {
                    Layer.msg('请先选择左侧字典类型', {icon: 2});
                    return false;
                }
                var url = '/meta/dictionary/item/add?dictId=' + currentDictId;
                Layer.open({
                    type: 2,
                    title: '添加字典项',
                    shadeClose: true,
                    shade: 0.3,
                    area: ['700px', '500px'],
                    content: url
                });
                return false;
            });

            // 编辑字典项按钮
            $(document).on("click", ".btn-edit", function () {
                var ids = Table.api.selectedids(table);
                if (ids.length === 0) {
                    Layer.msg('请选择要编辑的记录', {icon: 2});
                    return false;
                }
                if (ids.length > 1) {
                    Layer.msg('只能选择一条记录', {icon: 2});
                    return false;
                }
                var id = ids[0];
                var url = '/meta/dictionary/item/edit?ids=' + id;
                Layer.open({
                    type: 2,
                    title: '编辑字典项',
                    shadeClose: true,
                    shade: 0.3,
                    area: ['700px', '500px'],
                    content: url
                });
                return false;
            });

            // 删除字典项按钮
            $(document).on("click", ".btn-del", function () {
                var ids = Table.api.selectedids(table);
                if (ids.length === 0) {
                    Layer.msg('请选择要删除的记录', {icon: 2});
                    return false;
                }
                Layer.confirm('确定要删除选中的记录吗？', function (index) {
                    var url = '/meta/dictionary-item/' + ids.join(',');
                    $.ajax({
                        url: url,
                        type: 'DELETE',
                        dataType: 'json',
                        success: function (res) {
                            if (res.code === 200) {
                                Layer.msg('删除成功', {icon: 1});
                                table.bootstrapTable('refresh');
                            } else {
                                Layer.msg(res.msg || '删除失败', {icon: 2});
                            }
                            Layer.close(index);
                        },
                        error: function () {
                            Layer.msg('网络错误', {icon: 2});
                            Layer.close(index);
                        }
                    });
                });
                return false;
            });

            // 刷新按钮
            $(document).on("click", ".btn-refresh", function () {
                table.bootstrapTable('refresh');
                return false;
            });

            // 添加根字典类型按钮
            $(document).on("click", ".btn-add-root", function () {
                var url = '/meta/dictionary/add';
                Layer.open({
                    type: 2,
                    title: '添加字典类型',
                    shadeClose: true,
                    shade: 0.3,
                    area: ['700px', '500px'],
                    content: url
                });
                return false;
            });

            // 刷新树按钮
            $(document).on("click", ".btn-refresh-tree", function () {
                loadTree();
                return false;
            });

            // 加载树
            loadTree();

            // 绑定表格事件
            Table.api.bindevent(table);
        },
        add: function () {
            Controller.api.bindevent();
        },
        edit: function () {
            Controller.api.bindevent();
        },
        api: {
            bindevent: function () {
                Form.api.bindevent($("form[role=form]"));
            }
        }
    };
    return Controller;
});
