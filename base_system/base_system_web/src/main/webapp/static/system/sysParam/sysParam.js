$(function () {
    loadSysParamCategoryData();
    loadSysParamData();
})

function loadSysParamCategoryData() {
    $.ajax({
        type: 'post',
        url: ctx + '/sysParam/categoryList',
        dataType: 'json',
        success: function (data) {
            console.log(data.nodes);
            layui.use(['tree', 'layer', 'table'], function () {
                var layer = layui.layer
                    , $ = layui.jquery;
                var table = layui.table;
                layui.tree({
                    elem: '#sysParamCategoryTree' //指定元素
                    , target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
                    , click: function (item) { //点击节点回调
                        //layer.msg('当前节点ID：' + item.id + '当前节名称：' + item.cname + '<br>全部参数：' + JSON.stringify(item));
                        table.reload('paramload', {
                            where: {
                                paramCategoryId: item.id,
                            }
                        });
                        $('#paramCategoryId').val(item.id);
                    }
                    , nodes:
                    data.nodes
                });
            });
        }
    })
}

function loadSysParamData() {
    layui.use(['table', 'form'], function () {
        var table = layui.table;
        var form = layui.form;
        //第一个实例
        table.render({
            elem: '#sysParamTable'
            , toolbar: '#toolbarDemo'
            , url: ctx + '/sysParam/sysParamTable'
            , limit: 5
            , limits: [5, 10, 15, 20]
            , page: true
            , id: 'paramload'
            , cols: [[
                {type: 'checkbox'},
                {
                    field: 'ccode',
                    title: '编号',
                    align: 'center'
                }, {
                    field: 'cname',
                    title: '名称',
                    align: 'center',
                    style: 'height:100px;',
                }, {
                    field: 'curl',
                    title: '链接',
                    align: 'center'
                }, {
                    field: 'cpic',
                    title: '图片',
                    align: 'center',
                    templet: function (d) {
                        if (d.cpic != null && d.cpic != "") {
                            return '<div onclick="show_img(this)" ><img id="pitureChange" style="height: 100%"  src="' + ctx + d.cpic + '">';
                        } else {
                            return '';
                        }
                    }
                }, {
                    field: 'dcreateTime',
                    title: '创建时间',
                    align: 'center',
                    templet: function (d) {
                        if (d.dcreateTime != null) {
                            return formatterTime(d.dcreateTime);
                        } else {
                            return "";
                        }
                    }
                },{
                    field: 'cstatus',
                    title: '状态',
                    align: 'center',
                    templet: function (d) {
                        if (d.cstatus == '00') {
                            return '启用';
                        } else {
                            return '禁用';
                        }
                    }
                }
            ]]
        });

        //头工具栏事件
        table.on('toolbar(sysParamFilter)', function (obj) {
            var checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.open({
                        type: 2,
                        title: '新增页面',
                        area: ['600px', '600px'],
                        shade: 0.4,//遮罩透明度
                        content: ctx + '/sysParam/edit?paramCategoryId=' + $('#paramCategoryId').val(),
                        scrollbar: false,//屏蔽浏览器滚动条
                    });
                    break;
                case 'del':
                    var data = checkStatus.data;
                    if (data.length == 0) {
                        layer.msg('请至少选择一条数据!!');
                        return;
                    }
                    layer.confirm('确定删除：' + data.length + '条数据吗??', function (index) {
                        var paramIds = '';
                        $.each(data, function (index, param) {
                            paramIds += param.id + ',';
                        })
                        $.ajax({
                            url: ctx + '/sysParam/delete',
                            type: 'post',
                            data: {"paramIds": paramIds},
                            dataType: "json",
                            success: function (data) {
                                if (data.success) {
                                    layer.msg(data.msg, {icon: 6, time: 2000});
                                    table.reload('paramload', {
                                        page:1,
                                        limit:5,
                                        paramCategoryId: $('#paramCategoryId').val()==null?'':$('#paramCategoryId').val()
                                    });
                                }
                                else {
                                    layer.msg(data.msg, {icon: 5, time: 2000});
                                }
                            }
                        });
                    });
                    break;
            }
            ;
        });
    });
}

function show_img(t) {
    var t = $(t).find("img");
    //页面层
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['80%', '80%'], //宽高
        shadeClose: true, //开启遮罩关闭
        end: function (index, layero) {
            return false;
        },
        content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
    });
}