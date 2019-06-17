function refreshImg() {
    document.getElementById("u16_img").src = ctx + "/imageCode/showCode?timestamp=" + (new Date()).getTime();
}


layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form,
        layer = layui.layer;

    //自定义验证规则
    form.verify({
        password: [/(.+){6,12}$/, '密码必须6到12位'],
        captcha: [/(.+){4}$/, '验证码必须是4位'],
    });

    //监听提交
    form.on('submit(login)', function (data) {
        console.log('表单数据', data);
        var data = data.field;
        $.ajax({
            url: ctx + '/login',
            method: 'post',
            data: {
                username: data.username,
                password: data.password ? common
                    .passwordEncryptByAes(data.password) : '',
                captcha: data.captcha
            },
            dataType: 'JSON',
            success: function (res) {
                if (res.success) {
                    layer.msg( "登录成功" ,{offset: '60px',icon: 6,anim: 6,time: 3000});
                    // window.location.href=ctx+'/index';
                    location.replace(ctx+'/index')
                }else{
                    refreshImg();
                    console.log("错误信息是: " + res.msg);
                    layer.msg(res.msg ,{offset: '60px',icon: 5,anim: 6,time: 3000});
                }
            }
        });
        // api.login({
        //     data:{
        //         username:data.username,
        //         password:data.password,
        //         captcha:data.captcha
        //     },
        //     success:function(res){
        //         console.log(res);
        //         location.replace(ctx+'/index')
        //     },
        //     error:function () {
        //         location.replace(ctx+'/login')
        //     }
        // });
        return false;
    });

    if (window.top !== window.self) {
        window.top.location = window.location;
    }


});


