layui.use('table', function () {
    var table = layui.table;
    var renderTable = function () {
        layer.load(3);
        table.render({
            elem: '#roleTable'
            , url: ctx + '/roleMan/list'
            , toolbar: '#toolbarDemo'
            , title: '角色数据表'
            , page: true
            , limit: 5
            , limits: [5, 10, 15, 20]
            , id: 'roleManLoad'
            , cols: [[
                {type: 'radio', fixed: 'left'}
                , {field: 'ccode', title: '编号', align: 'center'}
                , {field: 'cname', title: '角色名称', align: 'center'}
                , {
                    field: 'cstatus', title: '状态', align: 'center', templet: function (d) {
                        if (d.cstatus == '00') {
                            return '启用';
                        }else{
                            return '禁用';
                        }
                    }
                }
                , {field: 'cdes', title: '描述', align: 'center',templet:function (d) {
                        if (d.cdes!=null){
                            return d.cdes;
                        }else {
                            return '-';
                        }
                    }}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', align: 'center'}
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        });
    }
    renderTable();
    //头工具栏事件
    table.on('toolbar(roleManFilter)', function (obj) {
        var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'addRoleMan':
                var data = checkStatus.data;
                if (data != null && data != '' && data != undefined) {
                    layer.alert('新增按钮不能选择', function (index) {
                        layer.close(index);
                        renderTable();
                        return;
                    });
                } else {
                    layer.open({
                        type: 2,
                        title: '新增角色',
                        area: ['600px', '450px'],
                        shade: 0.4,//遮罩透明度
                        content: ctx + '/roleMan/edit',
                        scrollbar: false,//屏蔽浏览器滚动条
                    })
                }

                break;
            case 'editRoleMan':
                var data = checkStatus.data;
                console.log(data);
                if (data.length == 0) {
                    layer.alert('请选择编辑的记录', function (index) {
                        layer.close(index);
                        renderTable();
                        return;
                    });
                } else {
                    layer.open({
                        type: 2,
                        title: '编辑角色',
                        area: ['600px', '450px'],
                        shade: 0.4,//遮罩透明度
                        content: ctx + '/roleMan/edit?id=' + data[0].id,
                        scrollbar: false,//屏蔽浏览器滚动条
                    })
                }
                break;
        }
        ;
    });

    //监听行工具事件
    table.on('tool(roleManFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('是否禁用此角色:' + data.cname + '??', function (index) {
                $.ajax({
                    url: ctx + '/roleMan/delete',
                    type: 'post',
                    data: {"id": data.id},
                    dataType: "json",
                    success: function (res) {
                        console.log(res)
                        if (res.status == 200) {
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
        }

        if(obj.event === 'permission'){
            layer.open({
                type: 2,
                title: '权限设置',
                area: ['700px', '500px'],
                shade: 0.4,//遮罩透明度
                content: ctx + '/roleMan/moduleSetPage/'+data.id,
                scrollbar: false,//屏蔽浏览器滚动条
            })
        }
    });

    var $ = layui.$, active = {

        reload: function () {
            var cname = $('#cname');
            //执行重载
            table.reload('roleManLoad', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    cname: cname.val()
                }
            });
        }
    };

    $('#btn-search').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $('#btn-refresh').on('click', function () {
        renderTable();
    });


});