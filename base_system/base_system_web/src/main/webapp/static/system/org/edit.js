jQuery(document).ready(function ($) {
    layui.use(['table', 'form'], function () {
        var form = layui.form;
        form.render();
    });
});

layui.use(['form', 'layedit', 'laydate'], function(){
    var form = layui.form
        ,layer = layui.layer

    //自定义验证规则
    form.verify({
        cname: function(value){
            if(value.length < 3){
                return '机构名称至少得3个字符啊';
            }
        }
        ,ccode: [
            /^[\S]{4,12}$/
            ,'代号必须6到12位，且不能出现空格'
        ]
    });

    //监听提交
    form.on('submit(demo)', function(data){

        $.ajax({
            type: "POST",
            url: ctx+"/org/save",
            data: data.field,
            dataType: "json",
            success: function (res) {
                console.log(res.status)
                if (res.status == 200 ){
                    layer.alert('编辑成功!',{icon:1,time:2000},function () {
                        layer.close(layer.index);
                        window.parent.location.reload();
                    });
                }else{
                    layer.alert('编辑失败!',{icon:2,time:2000},function () {
                        layer.close(layer.index);
                        window.parent.location.reload();
                    });
                }
            },
            error: function () {
                layer.msg('编辑异常!', {
                    icon: 5,
                    time: 2000
                });
            }
        });
        return false;
    });

});