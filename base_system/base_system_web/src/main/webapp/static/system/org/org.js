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
            elem: '#orgTable',
            url: ctx + '/org/list',
            page: false,
            toolbar: '#toolbarDemo',
            cols: [[
                {type:'radio'},
                {field: 'cname', title: '合作机构',width:250},
                {field: 'cperson', title: '负责人'},
                {field: 'ctel', title: '电话'},
                {field: 'cemail', title: '邮箱'},
                {field: 'caddress', title: '地址'},
                {field: 'ccode', title: '机构代号', sort: true},
                {
                    field: 'cstatus', title: '状态',
                    templet: function (d) {
                        if (d.cstatus == '00') {
                            return '启用';
                        } else {
                            return '停用';
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

    //新增机构
    //新增子机构

    //展开
    $('#btn-expand').click(function () {
        treetable.expandAll('#orgTable');
    });
    //折叠
    $('#btn-fold').click(function () {
        treetable.foldAll('#orgTable');
    });
    //刷新
    $('#btn-refresh').click(function () {
        renderTable();
    });

    $('#btn-search').click(function () {
        var keyword = $('#cName-search').val();
        var searchCount = 0;
        $('#orgTable').next('.treeTable').find('.layui-table-body tbody tr td').each(function () {
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




    //监听工具条
    table.on('tool(orgFilter)', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (data.id != null) {
            if (layEvent === 'del') {
                layer.confirm('是否禁用此机构:' + data.cname + '??', function (index) {
                    $.ajax({
                        url: ctx + '/org/delete',
                        type: 'post',
                        data: {"id": data.id},
                        dataType: "json",
                        success: function (res) {
                            console.log(res)
                            if (res.status==200) {
                                layer.close(index);
                                layer.msg("操作成功", {icon: 1});
                                renderTable();
                            }
                            else {
                                layer.msg("操作失败", {icon: 2});
                                renderTable();
                            }
                        }
                    });
                })
            } else if (layEvent === 'edit') {
                layer.open({
                    type: 2,
                    title: '编辑机构',
                    area: ['800px', '550px'],
                    shade: 0.4,//遮罩透明度
                    content: ctx + '/org/edit?id=' + data.id+'&cname=0',
                    scrollbar: false,//屏蔽浏览器滚动条
                })
            }
        }
    });
    //监听头部工具栏
    table.on('toolbar(orgFilter)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
        switch(obj.event){
            case 'btn-addOrg':
                var data = checkStatus.data;  //获取选中行数据
                if (data==null||data==''||data==undefined){
                    layer.open({
                        type: 2,
                        title: '编辑机构',
                        area: ['800px', '550px'],
                        shade: 0.4,//遮罩透明度
                        content: ctx + '/org/edit',
                        scrollbar: false,//屏蔽浏览器滚动条
                    })
                }else {
                    layer.alert('顶点机构不能选择');
                }
                break;
            case 'btn-addChilOrg':
                var data = checkStatus.data;

                console.log(data[0])
                if (data==null||data==''||data==undefined){
                    layer.alert('请选择一个机构!');
                }else {
                    layer.open({
                        type: 2,
                        title: '编辑机构',
                        area: ['800px', '550px'],
                        shade: 0.4,//遮罩透明度
                        content: ctx + '/org/edit?id=' + data[0].id+'&cname='+data[0].cname,
                        scrollbar: false,//屏蔽浏览器滚动条
                    })
                }
                break;
        };
    });
});