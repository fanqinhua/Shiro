layui.config({
    base: ctx + '/static/js/treetable-lay/module/'
}).extend({
    treetable: 'treetable-lay/treetable'
}).use(['layer', 'table', 'treetable'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var layer = layui.layer;
    var treetable = layui.treetable;
    //渲染表格
    var renderTable = function () {
        layer.load(2);
        treetable.render({
            treeColIndex: 1,//树形图标显示在第几列
            treeSpid: 0,//最上级的父级id
            treeIdName: 'id',//id字段的名称
            treePidName: 'ipid',//pid字段的名称
            treeDefaultClose: false,//是否默认折叠
            treeLinkage: true,//父级展开时是否自动展开所有子级
            elem: '#paramCategoryTable',
            url: ctx + '/sysParamCategory/list',
            page: false,
            cols: [[
                {type: 'numbers', title: '编号', width: 100},
                {field: 'cname', title: '类别名称', width: 300},
                {field: 'sysOrgVOName', title: '所属机构', width: 300},
                {field: 'corder', title: '排序', sort: true},
                {
                    field: 'cisParent', title: '是否叶子节点',
                    templet: function (d) {
                        if (d.cisParent) {
                            return '菜单';
                        } else {
                            return '按钮';
                        }
                    }
                },
                {
                    field: 'cstatus', title: '状态',
                    templet: function (d) {
                        if (d.cstatus == '00') {
                            return '启用';
                        } else {
                            return '禁用';
                        }
                    }
                },
                {templet: complain, title: '操作'}
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        })
    };
    renderTable();


    //触发三个button按钮
    $('#btn-expand').click(function () {
        treetable.expandAll('#paramCategoryTable');
    });

    $('#btn-fold').click(function () {
        treetable.foldAll('#paramCategoryTable');
    });

    $('#btn-refresh').click(function () {
        renderTable();
    });

    $('#btn-search').click(function () {
        var keyword = $('#cName-search').val();
        var searchCount = 0;
        $('#paramCategoryTable').next('.treeTable').find('.layui-table-body tbody tr td').each(function () {
            $(this).css('background-color', 'transparent');
            var text = $(this).text();
            if (keyword != '' && text.indexOf(keyword) >= 0) {
                $(this).css('background-color', 'rgba(250,230,160,0.5)');
                if (searchCount == 0) {
                    treetable.expandAll('#auth-table');
                    $('html,body').stop(true);
                    $('html,body').animate({scrollTop: $(this).offset().top - 150}, 500);
                }
                searchCount++;
            }
        });
        if (keyword == '') {
            layer.msg("请输入搜索内容", {icon: 5});
        } else if (searchCount == 0) {
            layer.msg("没有匹配结果", {icon: 5});
        }
    });

    function complain(d) {//操作中显示的内容
        var add = '';
        var edit = '';
        var del = '';
        if (d.ipid != 0) {
            add = '<a class="operation" lay-event="add" title="新增"><i class="layui-icon layui-icon-add-1"></i></a>&nbsp;';
            edit = '<a class="operation" lay-event="edit" title="编辑"><i class="layui-icon layui-icon-edit"></i></a>&nbsp;';
            // del = '<a class="operation" lay-event="del" title="删除"><i class="layui-icon layui-icon-delete"></i></a>'
        }else{
            add = '<a class="operation" lay-event="add" title="新增"><i class="layui-icon layui-icon-add-1"></i></a>&nbsp;';
            //edit = '<a class="operation" lay-event="edit" title="编辑"><i class="layui-icon layui-icon-edit"></i></a>';
        }
        return [add, edit, del].join('');
    }

    //监听工具条
    table.on('tool(paramCategoryFilter)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (data.id != null) {
            if (layEvent === 'del') {
                layer.confirm('是否删除此按钮:' + data.cname + '??', function (index) {
                    $.ajax({
                        url: ctx + '/module/delete',
                        type: 'post',
                        data: {"id": data.id},
                        dataType: "json",
                        success: function (res) {
                            console.log(res)
                            if (res.status == 200) {
                                layer.close(index);
                                layer.msg(res.data, {icon: 1});
                                renderTable();
                            }
                            else {
                                layer.msg(res.data, {icon: 2});
                                renderTable();
                            }
                        }
                    });
                })
            } else if (layEvent === 'edit') {
                layer.open({
                    type: 2,
                    title: '编辑分类',
                    area: ['800px', '600px'],
                    shade: 0.4,//遮罩透明度
                    content: ctx + '/sysParamCategory/edit?id=' + data.id,
                    scrollbar: false,//屏蔽浏览器滚动条
                })
            } else if (layEvent === 'add') {
                layer.open({
                    type: 2,
                    title: '新增分类',
                    area: ['800px', '600px'],
                    shade: 0.4,//遮罩透明度
                    content: ctx + '/sysParamCategory/add?id=' + data.id,
                    scrollbar: false,//屏蔽浏览器滚动条
                })
            }
        }
    });
});