jQuery(document).ready(function ($) {
    layui.use(['table', 'form'], function () {
        var form = layui.form;
        form.render();
    });
});


layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form
        , layer = layui.layer;
    //监听提交
    form.on('submit(demo)', function (data) {
        $.ajax({
            type: "POST",
            url: ctx + "/roleMan/save",
            data: data.field,
            dataType: "json",
            success: function (res) {
                console.log(res)
                if (res.status == 200) {
                    layer.alert('编辑成功!', {icon: 1, time: 2000}, function (index) {
                        layer.close(index);
                        window.parent.location.reload();
                    });
                } else {
                    layer.alert('编辑失败!', {icon: 2, time: 2000}, function (index) {
                        layer.close(index);
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