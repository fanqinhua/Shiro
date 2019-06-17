function loadOrgList() {
    loadOrgList();
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


layui.use('form', function () {
    var form = layui.form;
    var layer = layui.layer;
    form.verify({});
    //监听提交
    form.on('submit(formDemo)', function (data) {
        $.ajax({
            type: "POST",
            url: ctx+"/sysParamCategory/save",
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
