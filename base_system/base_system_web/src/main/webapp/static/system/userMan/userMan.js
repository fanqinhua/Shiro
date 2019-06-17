layui.use('table', function(){
    var table = layui.table;
    var renderTable=function () {
        layer.load(3);
        table.render({
            elem: '#userManTable'
            ,url: ctx+'/userMan/list'
            ,toolbar: '#toolbarDemo'
            ,title: '用户数据表'
            ,page: true
            ,limit:5
            ,limits:[5,10,15,20]
            ,id:'userManLoad'
            ,cols: [[
                {type: 'radio', fixed: 'left'}
                ,{field:'cloginName', title:'登陆账号',align:'center'}
                ,{field:'cname', title:'用户名称', align:'center',style:'background-color: #009688; color: #fff;'}
                ,{field:'sysRoleVOName', title:'所属角色',align:'center'}
                ,{field:'sysOrgVOName', title:'所属机构', align:'center'}
                ,{field:'cphone', title:'手机', align:'center'}
                ,{field:'cemail', title:'邮箱', align:'center', templet: function(res){
                        return '<em>'+ res.cemail +'</em>'
                    }}

                ,{field:'caddress', title:'地址', align:'center'}
                ,{field:'cstatus', title:'状态', align:'center',templet:function (res) {
                        if (res.cstatus=='00'){
                            return '已启用';
                        }
                        else if(res.cstatus=='01'){
                            return '未启用';
                        }else {
                            return '冻结登陆';
                        }
                    }}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo',align:'center'}
            ]],
            done: function () {
                layer.closeAll('loading');
            }
        });
    }
    renderTable();
    //头工具栏事件
    table.on('toolbar(userManFilter)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'addUserMan':
                var data = checkStatus.data;
                if(data!=null&&data!=''&&data!=undefined){
                    layer.alert('新增按钮不能选择',function (index) {
                        layer.close(index);
                        renderTable();
                        return;
                    });
                }else {
                    layer.open({
                        type: 2,
                        title: '新增用户',
                        area: ['800px', '550px'],
                        shade: 0.4,//遮罩透明度
                        content: ctx + '/userMan/edit',
                        scrollbar: false,//屏蔽浏览器滚动条
                    })
                }

                break;
            case 'editUserMan':
                var data = checkStatus.data;
                console.log(data);
                if(data.length==0){
                    layer.alert('请选择编辑的记录',function (index) {
                        layer.close(index);
                        renderTable();
                        return;
                    });
                }else {
                    layer.open({
                        type: 2,
                        title: '编辑用户',
                        area: ['800px', '550px'],
                        shade: 0.4,//遮罩透明度
                        content: ctx + '/userMan/edit?id='+data[0].id,
                        scrollbar: false,//屏蔽浏览器滚动条
                    })
                }
                break;
        };
    });

    //监听行工具事件
    table.on('tool(userManFilter)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('是否禁用此用户:' + data.cname + '??', function (index) {
                $.ajax({
                    url: ctx + '/userMan/delete',
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
        }
    });

    var $ = layui.$, active = {

        reload: function(){
            var cname = $('#cname');
            //执行重载
            table.reload('userManLoad', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    cname: cname.val()
                }
            });
        }
    };

    $('#btn-search').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $('#btn-refresh').on('click',function () {
        renderTable();
    });


});
