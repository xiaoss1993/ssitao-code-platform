define(['jquery', 'bootstrap', 'backend', 'table', 'form', 'utils'], function ($, undefined, Backend, Table, Form, Utils) {

    var Controller = {
        index: function () {
            // 初始化Tab切换
            Controller.initTab();

            // 初始化各表格
            Controller.initGroupTable();
            Controller.initCompanyTable();
            Controller.initDepartmentTable();
        },

        initTab: function() {
            // Tab切换事件
            $('ul.nav-tabs a').on('shown.bs.tab', function(e) {
                var type = $(this).data('value');
                $("#table-" + type).bootstrapTable('refresh');
            });
        },

        initGroupTable: function() {
            Table.api.init({
                extend: {
                    index_url: '/iam/org/group/list',
                    add_url: '/iam/org/add',
                    edit_url: '/iam/org/edit',
                    del_url: '/iam/org/group',
                },
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table-group");

            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'createTime',
                sidePagination: 'server',
                queryParamsType: 'undefined',
                queryParams: function (params) {
                    return {
                        page: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'groupCode', title: '集团编码', operate: 'LIKE'},
                        {field: 'groupName', title: '集团名称', operate: 'LIKE'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function(value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'createTime',
                            title: '创建时间',
                            operate: 'RANGE',
                            addclass: 'datetimerange',
                            formatter: Table.api.formatter.datetime
                        },
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

            Table.api.bindevent(table);
        },

        initCompanyTable: function() {
            Table.api.init({
                extend: {
                    index_url: '/iam/org/company/list',
                    add_url: '/iam/org/add',
                    edit_url: '/iam/org/edit',
                    del_url: '/iam/org/company',
                },
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table-company");

            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'createTime',
                sidePagination: 'server',
                queryParamsType: 'undefined',
                queryParams: function (params) {
                    return {
                        page: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'companyCode', title: '公司编码', operate: 'LIKE'},
                        {field: 'companyName', title: '公司名称', operate: 'LIKE'},
                        {field: 'groupName', title: '所属集团', operate: false},
                        {field: 'legalPerson', title: '法定代表人'},
                        {field: 'unifiedSocialCreditCode', title: '统一社会信用代码'},
                        {field: 'contactPhone', title: '联系电话'},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function(value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'createTime',
                            title: '创建时间',
                            operate: 'RANGE',
                            addclass: 'datetimerange',
                            formatter: Table.api.formatter.datetime
                        },
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

            Table.api.bindevent(table);
        },

        initDepartmentTable: function() {
            Table.api.init({
                extend: {
                    index_url: '/iam/org/department/list',
                    add_url: '/iam/org/add',
                    edit_url: '/iam/org/edit',
                    del_url: '/iam/org/department',
                },
                responseHandler: function(res) {
                    if (res.code === 200 && res.data) {
                        var data = res.data;
                        if (Array.isArray(data)) {
                            return { rows: data, total: data.length };
                        }
                        // 处理分页对象 (rows: 数据列表, total: 总数)
                        if (data.rows) {
                            return { rows: data.rows, total: data.total };
                        }
                        if (data.records) {
                            return { rows: data.records, total: data.totalRow || data.records.length };
                        }
                        return { rows: data, total: data.length };
                    }
                    return { rows: [], total: 0 };
                }
            });

            var table = $("#table-department");

            table.bootstrapTable({
                url: $.fn.bootstrapTable.defaults.extend.index_url,
                pk: 'id',
                sortName: 'sort',
                sidePagination: 'server',
                queryParamsType: 'undefined',
                queryParams: function (params) {
                    return {
                        page: params.pageNumber,
                        size: params.pageSize,
                        sort: params.sortName,
                        order: params.sortOrder
                    };
                },
                columns: [
                    [
                        {field: 'state', checkbox: true},
                        {field: 'id', title: 'ID', sortable: true},
                        {field: 'deptCode', title: '部门编码', operate: 'LIKE'},
                        {field: 'deptName', title: '部门名称', operate: 'LIKE'},
                        {field: 'companyName', title: '所属公司', operate: false},
                        {field: 'pid', title: '上级部门ID'},
                        {field: 'manager', title: '负责人'},
                        {field: 'contactPhone', title: '联系电话'},
                        {field: 'sort', title: '排序', sortable: true},
                        {
                            field: 'status',
                            title: '状态',
                            searchList: {"true": "正常", "false": "禁用"},
                            formatter: function(value, row, index) {
                                return value === true
                                    ? '<span class="label label-success">正常</span>'
                                    : '<span class="label label-default">禁用</span>';
                            }
                        },
                        {
                            field: 'createTime',
                            title: '创建时间',
                            operate: 'RANGE',
                            addclass: 'datetimerange',
                            formatter: Table.api.formatter.datetime
                        },
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

            Table.api.bindevent(table);
        },

        add: function() {
            // 获取URL参数
            var getQueryString = function(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURIComponent(r[2]);
                return null;
            };

            var orgType = getQueryString('type') || 'group';
            $('#orgType').val(orgType);

            // 根据组织类型显示对应字段
            function showFields(type) {
                $('.group-fields, .company-fields, .department-fields').hide();
                if (type === 'group') {
                    $('.group-fields').show();
                } else if (type === 'company') {
                    $('.company-fields').show();
                    loadGroups();
                } else if (type === 'department') {
                    $('.department-fields').show();
                    loadCompanies();
                }
            }

            // 加载集团列表
            function loadGroups() {
                $.ajax({
                    url: '/iam/org/group/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择集团</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.groupName + '</option>';
                            });
                            $('#groupId').html(html);
                            $('#groupId').selectpicker('refresh');
                        }
                    }
                });
            }

            // 加载公司列表
            function loadCompanies() {
                $.ajax({
                    url: '/iam/org/company/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择公司</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.companyName + '</option>';
                            });
                            $('#companyId').html(html);
                            $('#companyId').selectpicker('refresh');

                            // 加载部门列表（用于上级部门选择）
                            loadDepartments();
                        }
                    }
                });
            }

            // 加载部门列表
            function loadDepartments() {
                $.ajax({
                    url: '/iam/org/department/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择上级部门</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.deptName + '</option>';
                            });
                            $('#pid').html(html);
                            $('#pid').selectpicker('refresh');
                        }
                    }
                });
            }

            showFields(orgType);

            // 添加模式
            $('#edit-form').attr('action', '/iam/org/' + orgType);
            $('#edit-form').attr('method', 'POST');

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();

                var url = $(this).attr('action');
                var method = $(this).attr('method');
                var formData = $(this).serialize();

                $.ajax({
                    url: url,
                    type: method,
                    data: formData,
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('操作成功', {icon: 1});
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.$("#table-" + orgType).bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '操作失败', {icon: 2});
                        }
                    },
                    error: function() {
                        Layer.msg('网络错误', {icon: 2});
                    }
                });
            });

            Form.api.bindevent($("form[role=form]"));
        },

        edit: function() {
            // 获取URL参数
            var getQueryString = function(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return decodeURIComponent(r[2]);
                return null;
            };

            var id = Utils.getPkId('id');
            var orgType = Utils.getQueryString('type') || 'group';

            $('#orgType').val(orgType);

            // 根据组织类型显示对应字段
            function showFields(type) {
                $('.group-fields, .company-fields, .department-fields').hide();
                if (type === 'group') {
                    $('.group-fields').show();
                } else if (type === 'company') {
                    $('.company-fields').show();
                    loadGroups();
                } else if (type === 'department') {
                    $('.department-fields').show();
                    loadCompanies();
                }
            }

            // 加载集团列表
            function loadGroups() {
                $.ajax({
                    url: '/iam/org/group/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择集团</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.groupName + '</option>';
                            });
                            $('#groupId').html(html);
                            $('#groupId').selectpicker('refresh');
                        }
                    }
                });
            }

            // 加载公司列表
            function loadCompanies() {
                $.ajax({
                    url: '/iam/org/company/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择公司</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.companyName + '</option>';
                            });
                            $('#companyId').html(html);
                            $('#companyId').selectpicker('refresh');

                            // 加载部门列表（用于上级部门选择）
                            loadDepartments();
                        }
                    }
                });
            }

            // 加载部门列表
            function loadDepartments() {
                $.ajax({
                    url: '/iam/org/department/list',
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var html = '<option value="">请选择上级部门</option>';
                            $.each(res.data, function(i, item) {
                                html += '<option value="' + item.id + '">' + item.deptName + '</option>';
                            });
                            $('#pid').html(html);
                            $('#pid').selectpicker('refresh');
                        }
                    }
                });
            }

            showFields(orgType);

            if (id) {
                // 编辑模式
                var apiMap = {
                    'group': '/iam/org/group/',
                    'company': '/iam/org/company/',
                    'department': '/iam/org/department/'
                };

                $.ajax({
                    url: apiMap[orgType] + id,
                    type: 'GET',
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200 && res.data) {
                            var data = res.data;
                            $('#id').val(data.id);

                            if (orgType === 'group') {
                                $('#groupName').val(data.groupName);
                                $('#groupCode').val(data.groupCode);
                                $('#groupCode').attr('readonly', true);
                            } else if (orgType === 'company') {
                                $('#companyName').val(data.companyName);
                                $('#companyCode').val(data.companyCode);
                                $('#companyCode').attr('readonly', true);
                                $('#groupId').val(data.groupId);
                                $('#groupId').selectpicker('refresh');
                                $('#legalPerson').val(data.legalPerson);
                                $('#unifiedSocialCreditCode').val(data.unifiedSocialCreditCode);
                                $('#contactPhone').val(data.contactPhone);
                                $('#address').val(data.address);
                            } else if (orgType === 'department') {
                                $('#deptName').val(data.deptName);
                                $('#deptCode').val(data.deptCode);
                                $('#deptCode').attr('readonly', true);
                                $('#companyId').val(data.companyId);
                                $('#companyId').selectpicker('refresh');
                                $('#pid').val(data.pid);
                                $('#pid').selectpicker('refresh');
                                $('#manager').val(data.manager);
                                $('#deptContactPhone').val(data.contactPhone);
                                $('#sort').val(data.sort || 0);
                            }

                            $('input[name="status"][value="' + data.status + '"]').prop('checked', true);
                            $('#remark').val(data.remark);
                        }
                    }
                });

                $('#edit-form').attr('action', '/iam/org/' + orgType);
                $('#edit-form').attr('method', 'PUT');
            }

            // 表单提交
            $('#edit-form').on('submit', function(e) {
                e.preventDefault();

                var url = $(this).attr('action');
                var method = $(this).attr('method');
                var formData = $(this).serialize();

                $.ajax({
                    url: url,
                    type: method,
                    data: formData,
                    dataType: 'json',
                    success: function(res) {
                        if (res.code === 200) {
                            Layer.msg('操作成功', {icon: 1});
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            parent.$("#table-" + orgType).bootstrapTable('refresh');
                        } else {
                            Layer.msg(res.msg || '操作失败', {icon: 2});
                        }
                    },
                    error: function() {
                        Layer.msg('网络错误', {icon: 2});
                    }
                });
            });

            Form.api.bindevent($("form[role=form]"));
        }
    };

    return Controller;
});
