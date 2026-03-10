require.config({
    packages: [{
        name: 'moment',
        location: '/assets/libs/moment',
        main: 'moment'
    }],
    //在打包压缩时将会把include中的模块合并到主文件中
    include: ['css', 'layer', 'toastr', 'fast', 'backend', 'table', 'form', 'dragsort', 'drag', 'drop', 'addtabs', 'selectpage', 'utils'],
    paths: {
        // lang 模块路径
        'lang': '/assets/js/lang',
        'form': '/assets/js/require-form',
        'table': '/assets/js/require-table',
        'bootstrap-treegrid': '/assets/libs/bootstrap-table/bootstrap-treegrid',
        'upload': '/assets/js/require-upload',
        'validator': '/assets/js/require-validator',
        'drag': '/assets/js/jquery.drag.min',
        'drop': '/assets/js/jquery.drop.min',
        'dragsort': '/assets/libs/dragsort/jquery.dragsort',
        'echarts': '/assets/js/echarts.min',
        'echarts-theme': '/assets/js/echarts-theme',
        'adminlte': '/assets/js/adminlte',
        'bootstrap-table-commonsearch': '/assets/js/bootstrap-table-commonsearch',
        'bootstrap-table-template': '/assets/js/bootstrap-table-template',
        'jquery-ui': "/assets/libs/jquery-ui/jquery-ui.min",
        //
        // 以下的包从libs目录加载（使用绝对路径）
        'jquery': '/assets/libs/jquery/dist/jquery.min',
        'bootstrap': '/assets/libs/bootstrap/js/bootstrap.min',
        'bootstrap-datetimepicker': '/assets/libs/datapicker/bootstrap-datetimepicker.min',
        'bootstrap-select': '/assets/libs/bootstrap-select/dist/js/bootstrap-select.min',
        'bootstrap-select-lang': '/assets/libs/bootstrap-select/dist/js/i18n/defaults-zh_CN',
        'bootstrap-table': '/assets/libs/bootstrap-table/bootstrap-table.min',
        'bootstrap-table-export': '/assets/libs/bootstrap-table/extensions/export/bootstrap-table-export.min',
        'bootstrap-table-mobile': '/assets/libs/bootstrap-table/extensions/mobile/bootstrap-table-mobile.min',
        'bootstrap-table-lang': '/assets/libs/bootstrap-table/locale/bootstrap-table-zh-CN.min',
        'tableexport': '/assets/libs/tableExport.jquery.plugin/tableExport.min',
        'qrcode': '/assets/libs/jquery-qrcode/jquery.qrcode.min',
        'sortable': '/assets/libs/Sortable/Sortable.min',
        'addtabs': '/assets/libs/jquery-addtabs/jquery.addtabs',
        'slimscroll': '/assets/libs/jquery-slimscroll/jquery.slimscroll.min',
        'summernote': '/assets/libs/summernote/lang/summernote-zh-CN.min',
        'validator-core': '/assets/libs/nice-validator/jquery.validator.min',
        'validator-lang': '/assets/libs/nice-validator/zh-CN',
        'plupload': '/assets/libs/plupload/js/plupload.min',
        'plupload-moxie': '/assets/libs/plupload/js/moxie.min',
        'toastr': '/assets/libs/toastr/toastr.min',
        'jstree': '/assets/libs/jstree/jstree.min',
        'ztree': '/assets/libs/jquery-ztree/js/jquery.ztree.all-3.5',
        'layer': '/assets/libs/layer/layer',
        'cookie': '/assets/libs/jquery.cookie/jquery.cookie',
        'cxselect': '/assets/libs/jquery-cxselect/js/jquery.cxselect',
        'template': '/assets/libs/art-template/template-native',
        'selectpage': '/assets/libs/selectpage/selectpage',
        'css': '/assets/js/require-css.min',
        // 公共工具模块
        'utils': '/assets/js/backend/utils',
    },
    // shim依赖配置
    shim: {
        // 核心库
        'jquery': {exports: "$"},
        'jquery-ui': {
            deps: ['jquery'],
            exports: '$.ui'
        },
        'bootstrap': ['jquery'],
        'bootstrap-table': {
            deps: [
                'bootstrap',
            ],
            exports: '$.fn.bootstrapTable'
        },
        'bootstrap-table-lang': {
            deps: ['bootstrap-table'],
            exports: '$.fn.bootstrapTable.defaults'
        },
        'bootstrap-table-export': {
            deps: ['bootstrap-table', 'tableexport'],
            exports: '$.fn.bootstrapTable.defaults'
        },
        'bootstrap-treegrid': {
            deps: ['bootstrap-table'],
            exports: '$.fn.bootstrapTable.defaults'
        },
        'bootstrap-table-mobile': {
            deps: ['bootstrap-table'],
            exports: '$.fn.bootstrapTable.defaults'
        },
        'bootstrap-table-advancedsearch': {
            deps: ['bootstrap-table'],
            exports: '$.fn.bootstrapTable.defaults'
        },
        'bootstrap-table-commonsearch': {
            deps: ['bootstrap-table'],
            exports: '$.fn.bootstrapTable.defaults'
        },
        'bootstrap-table-template': {
            deps: ['bootstrap-table', 'template'],
            exports: '$.fn.bootstrapTable.defaults'
        },
        'tableexport': {
            deps: ['jquery'],
            exports: '$.fn.extend'
        },
        'slimscroll': {
            deps: ['jquery'],
            exports: '$.fn.slimscroll'
        },
        'adminlte': {
            deps: ['bootstrap', 'slimscroll'],
            exports: '$.AdminLTE'
        },
        'bootstrap-datetimepicker': [
            'css!/assets/libs/datapicker/bootstrap-datetimepicker.min.css'
        ],
        'bootstrap-select': ['css!/assets/libs/bootstrap-select/dist/css/bootstrap-select.css'],
        'bootstrap-select-lang': ['bootstrap-select'],
        'summernote': ['/assets/libs/summernote/summernote.min', 'css!/assets/libs/summernote/summernote.css'],
        // jstree CSS 已移除 - 文件不存在
        'ztree': ['css!/assets/libs/jquery-ztree/css/default/zTreeStyle.css'],
        'plupload': {
            deps: ['plupload-moxie'],
            exports: "plupload"
        },
        'validator-lang': ['validator-core'],
    },
    baseUrl: '/assets/js/', //资源基础路径
    // 别名配置（用于 css! 插件）
    map: {
        '*': {
            'css': 'css'
        }
    },
    waitSeconds: 30,
    charset: 'utf-8' // 文件编码
});

require(['jquery', 'bootstrap'], function ($, undefined) {
    //初始配置
    var Config = requirejs.s.contexts._.config.config;
    //将Config渲染到全局
    window.Config = Config;
    // 配置语言包的路径（lang 模块已在页面中预先定义）
    var paths = {};
    //如果语言包想要动态加载，则使用下面一行
    //paths['lang'] = Config.moduleurl + 'lang?callback=define&controllername=' + Config.controllername;
    // 避免目录冲突
    paths['backend/'] = 'backend/';
    require.config({paths: paths});

    // 初始化
    $(function () {
        require(['fast'], function (Fast) {
            require(['backend'], function (Backend) {
                //加载相应模块
                if (Config.jsname) {
                    require([Config.jsname], function (Controller) {
                        Controller[Config.actionname] != undefined && Controller[Config.actionname]();
                    }, function (e) {
                        console.error(e);
                        // 这里可捕获模块加载的错误
                    });
                }
            });
        });
    });
});
