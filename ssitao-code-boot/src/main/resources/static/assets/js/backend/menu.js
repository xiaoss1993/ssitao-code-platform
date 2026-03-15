define(['jquery', 'backend', 'adminlte'], function ($, undefined, AdminLTE) {

    /**
     * 菜单渲染模块
     * 通过 AJAX 获取菜单数据并动态渲染
     */
    var MenuRenderer = {
        container: null,
        loaded: false,

        /**
         * 初始化菜单
         */
        init: function() {
            this.container = $('.sidebar-menu');
            if (this.container.length === 0) {
                console.error('菜单容器不存在');
                return;
            }
            this.loadMenus();
        },

        /**
         * 加载菜单数据
         */
        loadMenus: function() {
            var that = this;

            $.ajax({
                url: '/api/menus',
                type: 'GET',
                dataType: 'json',
                cache: false,
                success: function(res) {
                    if (res.code === 200 && res.data) {
                        that.render(res.data);
                        that.loaded = true;
                    } else {
                        console.error('加载菜单失败:', res.msg);
                        that.showError();
                    }
                },
                error: function(xhr, status, error) {
                    console.error('加载菜单失败:', error);
                    that.showError();
                }
            });
        },

        /**
         * 渲染菜单
         * @param {Array} menus 菜单数据
         */
        render: function(menus) {
            var html = this.buildMenuHtml(menus);
            this.container.html(html);
        },

        /**
         * 构建菜单 HTML
         * @param {Array} menus 菜单数据
         * @returns {string} HTML 字符串
         */
        buildMenuHtml: function(menus) {
            var html = '<li class="header">站内导航</li>';

            for (var i = 0; i < menus.length; i++) {
                var menu = menus[i];
                html += this.buildMenuItemHtml(menu);
            }

            // 添加相关链接
            html += '<li class="header">相关链接</li>';
            html += '<li><a href="javascript:;"><i class="fa fa-list text-red"></i> <span>官方文档</span></a></li>';
            html += '<li><a href="javascript:;"><i class="fa fa-comment text-yellow"></i> <span>社区交流</span></a></li>';

            return html;
        },

        /**
         * 构建单个菜单项 HTML
         * @param {Object} menu 菜单对象
         * @returns {string} HTML 字符串
         */
        buildMenuItemHtml: function(menu) {
            var hasChildren = menu.children && menu.children.length > 0;
            var isHeader = menu.isHeader;

            if (hasChildren) {
                // 有子菜单的父菜单
                var html = '<li class="treeview">';
                html += '<a href="javascript:;">';
                html += '<i class="' + menu.icon + '"></i>';
                html += '<span>' + menu.name + '</span>';
                html += '<span class="pull-right-container">';
                html += '<i class="fa fa-angle-left"></i>';
                html += '</span>';
                html += '</a>';
                html += '<ul class="treeview-menu">';

                for (var i = 0; i < menu.children.length; i++) {
                    var child = menu.children[i];
                    html += '<li>';
                    html += '<a href="' + child.url + '" addtabs="' + child.addtabs + '">';
                    html += '<i class="' + child.icon + '"></i>';
                    html += '<span>' + child.name + '</span>';
                    html += '</a>';
                    html += '</li>';
                }

                html += '</ul>';
                html += '</li>';
                return html;
            } else {
                // 没有子菜单的菜单项
                var html = '<li>';
                var url = menu.url || 'javascript:;';
                var badge = menu.isHeader ? '' : '<span class="pull-right-container"><small class="label pull-right bg-blue">hot</small></span>';
                html += '<a href="' + url + '" addtabs="' + menu.addtabs + '">';
                html += '<i class="' + menu.icon + '"></i>';
                html += '<span>' + menu.name + '</span>';
                html += badge;
                html += '</a>';
                html += '</li>';
                return html;
            }
        },

        /**
         * 显示加载错误
         */
        showError: function() {
            if (this.container) {
                this.container.html('<li class="header">站内导航</li>' +
                    '<li><a href="javascript:;"><i class="fa fa-warning text-red"></i> <span>加载菜单失败</span></a></li>');
            }
        }
    };

    // 页面加载完成后初始化菜单
    $(document).ready(function() {
        MenuRenderer.init();
    });

    return MenuRenderer;
});
