layui.use(['form', 'layer', 'jquery','upload'], function () {
    var $ = layui.jquery;
    var form = layui.form;
    var upload = layui.upload;
    var uploadInst = upload.render({
        elem: '#upload1' //绑定元素
        ,url: ctx+'/sysParam/uploadPic'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code > 0){
                return layer.msg('上传失败');
            }
            //上传成功
            //alert("上传成功"+res.pic);
            console.log(res.pic);
            document.getElementById("img_url").value = res.pic;
        }
    });

    form.on('submit(form)', function (data) {
        var loading = layer.load(0, {shade: false});
        $.ajax({
            url: ctx + '/sysParam/save',
            type: 'post',
            data:  data.field,
            dataType: "json",
            success: function (res) {
                if (res.success) {
                    layer.close(loading);
                    layer.alert('编辑成功!',{icon:1,time:2000},function (index) {
                        layer.close(index);
                        window.parent.location.reload();
                    });
                } else {
                    layer.close(loading);
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
        return false;//阻止表单默认提交
    })
});