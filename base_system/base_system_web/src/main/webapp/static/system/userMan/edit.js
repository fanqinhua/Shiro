
function loadRoleAndOrgList() {
    loadOrgList();
    loadRoleList();
}
function loadOrgList() {
    $.ajax({
        url: ctx + '/userMan/getOrgList',
        dataType: 'json',
        type: 'get',
        success: function (res) {
            layui.use(['table', 'tree', 'form'], function () {
                var tree=layui.tree;
                var form = layui.form;
                var data = res.orgList;
                //加载机构下拉树形列表
                tree({
                    elem: "#classtree",
                    nodes: data,
                    click: function (node) {
                        var $select = $($(this)[0].elem).parents(".layui-form-select");
                        $select.removeClass("layui-form-selected").find(".layui-select-title span").html(node.name).end().find("input:hidden[name='corgId']").val(node.id);
                    }
                });
                $(".downpanel").on("click", ".layui-select-title", function (e) {
                    $(".layui-form-select").not($(this).parents(".layui-form-select")).removeClass("layui-form-selected");
                    $(this).parents(".downpanel").toggleClass("layui-form-selected");
                    layui.stope(e);
                }).on("click", "dl i", function (e) {
                    layui.stope(e);
                });
                // $(document).on("click", function (e) {
                //     $(".layui-form-select").removeClass("layui-form-selected");
                // });
            });
        }
    });
}
function loadRoleList() {
    $.ajax({
        url: ctx + '/userMan/getRoleList',
        dataType: 'json',
        type: 'get',
        success: function (res) {
            layui.use(['table', 'tree', 'form'], function () {
                var form = layui.form;
                var data = res.roleList;
                //加载角色下拉列表
                $.each(data, function (index, item) {
                    $('#croleId').append(new Option(item.cname, item.id));//往下拉菜单里添加元素
                    $('#croleId').val(roleId == '' ? '' : roleId);
                });
                form.render();
            });

        }
    });
}

layui.use(['form', 'layedit', 'laydate'], function () {
    var form = layui.form
        , layer = layui.layer

    //自定义验证规则
    form.verify({
        username: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
        },

        pass: [
            /^[\S]{6,100}$/
            ,'密码不能低于6位，且不能出现空格'
        ],
        rePassword: function(value) {
            if (value === "")
                return "请输入二次密码！";
            var pwd = $('input[name=cpassword]').val();
            if (pwd !== value)
                return "二次输入的密码不一致！";
        }
    });

    //监听提交
    form.on('submit(demo)', function (data) {
        $.ajax({
            type: "POST",
            url: ctx + "/userMan/save",
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