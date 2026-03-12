require.config({
    urlArgs: "v=" + requirejs.s.contexts._.config.config.site.version,
    //在打包压缩时将会把include中的模块合并到主文件中
    include: ['css', 'layer', 'toastr', 'fast', 'backend', 'table', 'form', 'dragsort', 'drag', 'drop', 'addtabs', 'selectpage', 'utils'],
    paths: {
        'lang': "empty:",
        'fast': 'fast',
        'backend': 'backend',
        'utils': 'backend/utils',
        'form': 'require-form',
        'table': 'require-table',
        'moment': '/assets/libs/moment/min/moment.min',
        'moment/locale': '/assets/libs/moment/locale',
        'upload': 'require-upload',
        'validator': 'require-validator',
        'drag': 'jquery.drag.min',
        'drop': 'jquery.drop.min',
        'echarts': 'echarts.min',
        'echarts-theme': 'echarts-theme',
        'adminlte': 'adminlte',
        'bootstrap-table-commonsearch': 'bootstrap-table-commonsearch',
        'bootstrap-table-template': 'bootstrap-table-template',
        //
        // 以下的包从bower的libs目录加载
        'jquery': '/assets/libs/jquery/dist/jquery.min',
        'bootstrap': '/assets/libs/bootstrap/dist/js/bootstrap.min',
        'bootstrap-datetimepicker': '/assets/libs/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min',
        'bootstrap-select': '/assets/libs/bootstrap-select/dist/js/bootstrap-select.min',
        'bootstrap-select-lang': '/assets/libs/bootstrap-select/dist/js/i18n/defaults-zh_CN',
        'bootstrap-table': '/assets/libs/bootstrap-table/dist/bootstrap-table.min',
        'bootstrap-table-export': '/assets/libs/bootstrap-table/dist/extensions/export/bootstrap-table-export.min',
        'bootstrap-table-mobile': '/assets/libs/bootstrap-table/dist/extensions/mobile/bootstrap-table-mobile',
        'bootstrap-table-lang': '/assets/libs/bootstrap-table/dist/locale/bootstrap-table-zh-CN',
        'tableexport': '/assets/libs/tableExport.jquery.plugin/tableExport.min',
        'dragsort': '/assets/libs/dragsort/jquery.dragsort',
        'qrcode': '/assets/libs/jquery-qrcode/jquery.qrcode.min',
        'sortable': '/assets/libs/Sortable/Sortable.min',
        'addtabs': '/assets/libs/jquery-addtabs/jquery.addtabs',
        'slimscroll': '/assets/libs/jquery-slimscroll/jquery.slimscroll.min',
        'summernote': '/assets/libs/summernote/dist/lang/summernote-zh-CN.min',
        'validator-core': '/assets/libs/nice-validator/dist/jquery.validator.min',
        'validator-lang': '/assets/libs/nice-validator/dist/local/zh-CN',
        'plupload': '/assets/libs/plupload/js/plupload.min',
        'moxie': '/assets/libs/plupload/js/moxie.min',
        'toastr': '/assets/libs/toastr/toastr.min',
        'jstree': '/assets/libs/jstree/dist/jstree.min',
        'layer': '/assets/libs/layer/build/layer',
        'cookie': '/assets/libs/jquery.cookie/jquery.cookie',
        'cxselect': '/assets/libs/jquery-cxselect/js/jquery.cxselect',
        'template': '/assets/libs/art-template/dist/template-native',
        'selectpage': '/assets/libs/selectpage/selectpage',
    },
    // shim依赖配置
    shim: {
        'bootstrap': ['jquery'],
        'bootstrap-table': {
            deps: [
                'bootstrap',
//                'css!/assets/libs/bootstrap-table/dist/bootstrap-table.min.css'
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
            exports: '$.fn.extend'
        },
        'adminlte': {
            deps: ['bootstrap', 'slimscroll'],
            exports: '$.AdminLTE'
        },
        'bootstrap-datetimepicker': [
            'moment/locale/zh-cn',
//            'css!/assets/libs/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css',
        ],
        'bootstrap-select': ['css!/assets/libs/bootstrap-select/dist/css/bootstrap-select.min.css', ],
        'bootstrap-select-lang': ['bootstrap-select'],
        'summernote': ['/assets/libs/summernote/dist/summernote.min', 'css!/assets/libs/summernote/dist/summernote.css'],
//        'toastr': ['css!/assets/libs/toastr/toastr.min.css'],
        'jstree': ['css!/assets/libs/jstree/dist/themes/default/style.css', ],
        'plupload': {
            deps: ['moxie'],
            exports: "plupload"
        },
//        'layer': ['css!/assets/libs/layer/build/skin/default/layer.css'],
//        'validator-core': ['css!/assets/libs/nice-validator/dist/jquery.validator.css'],
        'validator-lang': ['validator-core'],
//        'selectpage': ['css!/assets/libs/selectpage/selectpage.css'],
    },
    baseUrl: (function() {
        var cdnurl = requirejs.s.contexts._.config.config.site.cdnurl;
        // 处理 cdnurl: 移除 ./ 或 / 结尾，统一添加 /assets/js/
        if (!cdnurl || cdnurl === './' || cdnurl === '.') {
            return '/assets/js/';
        }
        // 移除结尾的 /
        cdnurl = cdnurl.replace(/\/$/, '');
        return cdnurl + '/assets/js/';
    })(), //资源基础路径
    map: {
        '*': {
            'css': '/assets/libs/require-css/css.min'
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
    // 配置语言包的路径
    var paths = {};
    paths['lang'] = 'lang';
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
