/*
 * @Author: sizt 982637685@qq.com
 * @Date: 2025-04-13 12:00:50
 * @LastEditors: sizt 982637685@qq.com
 * @LastEditTime: 2025-05-14 10:57:42
 * @FilePath: /fastadmin-html/assets/js/backend/general/config.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
define(["jquery", "bootstrap", "backend", "table", "form", "vue"], function (
  $,
  undefined,
  Backend,
  Table,
  Form,
  Vue
) {
  var Controller = {
    index: function () {
      // 创建Vue实例【在上面调整完vue，再用】
      this.app = new Vue({
        // 1. el 用于指定当前Vue实例为哪个容器服务，值->通常为css选择器字符串。
        el: "#app", // elment,类似css id选择器
        // el: '.demo', // elment,类似css 类选择器
        // 2. data 中用于存储数据，数据供el所指定的容器去使用，值我们暂时先写成一个对象。
        data: {
          name: "atguigu",
          address: "北京",
          msg: ["香蕉", "苹果", "柚子", "雪梨"],
          person: {
            name: "banmoon",
            age: 18,
            sex: "男",
          },
        },
        mounted() {
          this.init();
        },
        // methods：方法集合对象
        methods: {
          init: function () {
            // 初始化表格参数配置
            Table.api.init({
              extend: {
                index_url: "general/config/index",
                add_url: "general/config/add",
                edit_url: "general/config/edit",
                del_url: "general/config/del",
                multi_url: "general/config/multi",
                table: "config",
              },
            });
            var table = $("#table");
            // 初始化表格
            table.bootstrapTable({
              url: $.fn.bootstrapTable.defaults.extend.index_url,
              pk: "id",
              sortName: "id",
              columns: [
                [
                  { field: "state", checkbox: true },
                  { field: "id", title: __("Id") },
                  { field: "name", title: __("Name") },
                  { field: "intro", title: __("Intro") },
                  { field: "group", title: __("Group") },
                  { field: "type", title: __("Type") },
                  {
                    field: "operate",
                    title: __("Operate"),
                    events: Table.api.events.operate,
                    formatter: Table.api.formatter.operate,
                  },
                ],
              ],
            });

            // 为表格绑定事件
            Table.api.bindevent(table);

            $("form.edit-form").data("validator-options", {
              display: function (elem) {
                return $(elem).closest("tr").find("td:first").text();
              },
            });
            Form.api.bindevent($("form.edit-form"));

            //不可见的元素不验证
            $("form#add-form").data("validator-options", { ignore: ":hidden" });
            Form.api.bindevent($("form#add-form"));

            $(document).on("click", ".fieldlist .append", function () {
              var rel = parseInt($(this).closest("dl").attr("rel")) + 1;
              var name = $(this).closest("dl").data("name");
              $(this).closest("dl").attr("rel", rel);
              $(
                '<dd class="form-inline"><input type="text" name="' +
                  name +
                  "[field][" +
                  rel +
                  ']" class="form-control" value="" size="10" /> <input type="text" name="' +
                  name +
                  "[value][" +
                  rel +
                  ']" class="form-control" value="" size="40" /> <span class="btn btn-sm btn-danger btn-remove"><i class="fa fa-times"></i></span> <span class="btn btn-sm btn-primary btn-dragsort"><i class="fa fa-arrows"></i></span></dd>'
              ).insertBefore($(this).parent());
            });
            $(document).on("click", ".fieldlist dd .btn-remove", function () {
              $(this).parent().remove();
            });
            //拖拽排序
            require(["dragsort"], function () {
              //绑定拖动排序
              $("dl.fieldlist").dragsort({
                itemSelector: "dd",
                dragSelector: ".btn-dragsort",
                dragEnd: function () {},
                placeHolderTemplate: "<dd></dd>",
              });
            });

            //切换显示隐藏变量字典列表
            $(document).on(
              "change",
              "form#add-form select[name='row[type]']",
              function (e) {
                $("#add-content-container").toggleClass(
                  "hide",
                  ["select", "selects", "checkbox", "radio"].indexOf(
                    $(this).val()
                  ) > -1
                    ? false
                    : true
                );
              }
            );

            //添加向发件人发送测试邮件按钮和方法
            $('input[name="row[mail_from]"]')
              .parent()
              .next()
              .append(
                '<a class="btn btn-info testmail">' +
                  __("Send a test message") +
                  "</a>"
              );
            $(document).on("click", ".testmail", function () {
              Backend.api.ajax({
                url: "data/edit.json",
                data: { receiver: $('input[name="row[mail_from]"]').val() },
              });
            });
          },
          submit: function () {
            alert("模拟登录");
          },
          reset: function () {
            alert("模拟重置");
          },
        },
      });
    },
    app: null,
    add: function () {
      Controller.api.bindevent();
    },
    edit: function () {
      Controller.api.bindevent();
    },
    api: {
      bindevent: function () {
        Form.api.bindevent($("form[role=form]"));
      },
    },
  };
  return Controller;
});
