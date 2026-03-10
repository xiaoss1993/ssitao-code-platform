/**
 * Vue-Table 组件 - 整合 Bootstrap Table + Vue.js
 * 直接使用全局Vue对象
 */
define(['jquery', 'bootstrap', 'layer', 'moment', 'moment/locale/zh-cn', 'bootstrap-table', 'bootstrap-table-lang'], function ($, undefined, Layer, Moment, undefined, undefined) {

    // 使用全局对象（通过 script 标签预加载）和依赖注入的 layer
    var Vue = window.Vue;
    // Layer 已通过依赖注入，这里保留 window.layer 作为备用
    if (!Layer && window.layer) {
        Layer = window.layer;
    }

    if (!Vue) {
        console.error('Vue is not loaded!');
        return;
    }

    if (!Layer) {
        console.error('Layer is not loaded!');
    }

    var VueTable = function(options) {
        this.options = $.extend(true, {}, VueTable.defaults, options);
        this.vm = null;
        this.table = null;
        this.selectedIds = [];
        this._init();
    };

    VueTable.prototype = {
        _init: function() {
            var self = this;
            this._initVue();
            this._initTable();
            return this;
        },

        _initVue: function() {
            var self = this;
            this.vm = new Vue({
                el: this.options.el,
                data: {
                    search: self.options.search ? self.options.search.data : {},
                    selectedIds: []
                },
                computed: {
                    hasSelected: function() {
                        return this.selectedIds && this.selectedIds.length > 0;
                    }
                },
                methods: {
                    doSearch: function() {
                        self.refresh();
                    },
                    resetSearch: function() {
                        var searchData = self.options.search ? self.options.search.data : {};
                        for (var key in searchData) {
                            this.search[key] = '';
                        }
                        self.refresh();
                    },
                    refreshTable: function() {
                        self.refresh();
                    },
                    handleAdd: function() {
                        if (self.options.crud && self.options.crud.add) {
                            Layer.open({
                                type: 2,
                                title: self.options.crud.add.title || '添加',
                                shadeClose: true,
                                shade: 0.3,
                                area: self.options.crud.add.area || ['600px', '500px'],
                                content: self.options.crud.add.url,
                                end: function() { self.refresh(); }
                            });
                        }
                    },
                    handleEdit: function(row) {
                        if (self.options.crud && self.options.crud.edit) {
                            var url = self.options.crud.edit.url + '?id=' + row.id;
                            Layer.open({
                                type: 2,
                                title: self.options.crud.edit.title || '编辑',
                                shadeClose: true,
                                shade: 0.3,
                                area: self.options.crud.edit.area || ['600px', '500px'],
                                content: url,
                                end: function() { self.refresh(); }
                            });
                        }
                    },
                    handleDelete: function(row) {
                        if (self.options.crud && self.options.crud.del) {
                            Layer.confirm('确定要删除该记录吗？', function(index) {
                                $.ajax({
                                    url: self.options.crud.del.url + '/' + row.id,
                                    type: 'DELETE',
                                    dataType: 'json',
                                    success: function(res) {
                                        if (res.code === 200) {
                                            Layer.msg('删除成功', {icon: 1});
                                            self.refresh();
                                        } else {
                                            Layer.msg(res.msg || '删除失败', {icon: 2});
                                        }
                                    }
                                });
                                Layer.close(index);
                            });
                        }
                    },
                    handleBatchDelete: function() {
                        if (!this.selectedIds.length) {
                            Layer.msg('请选择要删除的记录', {icon: 0});
                            return;
                        }
                        if (self.options.crud && self.options.crud.del) {
                            var ids = this.selectedIds;
                            Layer.confirm('确定要删除选中的 ' + ids.length + ' 条记录吗？', function(index) {
                                var promises = ids.map(function(id) {
                                    return $.ajax({
                                        url: self.options.crud.del.url + '/' + id,
                                        type: 'DELETE',
                                        dataType: 'json'
                                    });
                                });
                                $.when.apply($, promises).done(function() {
                                    Layer.msg('删除成功', {icon: 1});
                                    self.refresh();
                                }).fail(function() {
                                    Layer.msg('删除失败', {icon: 2});
                                    self.refresh();
                                });
                                Layer.close(index);
                            });
                        }
                    }
                }
            });
        },

        _initTable: function() {
            var self = this;
            var $table = $(self.options.table.selector);

            if ($table.length === 0) {
                console.error('Table element not found!');
                return;
            }

            var tableOptions = $.extend(true, {}, {
                url: self.options.table.url,
                pk: self.options.table.pk || 'id',
                sortName: self.options.table.sortName || 'id',
                sortOrder: self.options.table.sortOrder || 'desc',
                sidePagination: self.options.table.sidePagination || 'server',
                pagination: self.options.table.pagination !== false,
                pageSize: self.options.table.pageSize || 10,
                pageList: self.options.table.pageList || [10, 25, 50, 100],
                columns: self.options.table.columns,
                queryParams: function(params) {
                    var searchData = self.vm.search;
                    var queryParams = {
                        pageNum: params.pageNumber,
                        pageSize: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder
                    };
                    for (var key in searchData) {
                        if (searchData[key]) {
                            queryParams[key] = searchData[key];
                        }
                    }
                    return queryParams;
                },
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            $table.bootstrapTable(tableOptions);

            // 绑定选择事件
            $table.on('check.bs.table', function() { self._updateSelectedIds(); });
            $table.on('uncheck.bs.table', function() { self._updateSelectedIds(); });
            $table.on('check-all.bs.table', function() { self._updateSelectedIds(); });
            $table.on('uncheck-all.bs.table', function() { self.vm.selectedIds = []; });
        },

        _updateSelectedIds: function() {
            var $table = $(this.options.table.selector);
            this.vm.selectedIds = $table.bootstrapTable('getSelections').map(function(item) {
                return item.id;
            });
        },

        refresh: function() {
            $(this.options.table.selector).bootstrapTable('refresh');
        },

        getSelectedIds: function() {
            return this.vm.selectedIds;
        }
    };

    VueTable.defaults = {
        el: '#app',
        table: {
            selector: '#table',
            pk: 'id',
            sortName: 'id',
            sortOrder: 'desc',
            sidePagination: 'server',
            pagination: true,
            pageSize: 10,
            pageList: [10, 25, 50, 100]
        },
        search: {
            enabled: true,
            data: {}
        },
        crud: {}
    };

    return VueTable;
});
