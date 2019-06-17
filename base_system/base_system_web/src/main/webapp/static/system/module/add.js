jQuery(document).ready(function ($) {
    layui.use(['table', 'form'], function () {
        var form = layui.form;
        form.render();
    });
});

layui.use('form', function () {
    var form = layui.form;
    var layer = layui.layer;
    form.verify({});
    //监听提交
    form.on('submit(formDemo)', function (data) {
        $.ajax({
            type: "POST",
            url: ctx+"/module/save",
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
                layer.msg('添加异常!', {
                    icon: 5,
                    time: 2000
                });
            }
        });
        return false;
    });
});