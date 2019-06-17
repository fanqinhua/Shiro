//菜单树的setting
var setting = {
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true,
            pIdKey: "ipid"
        },
        key: {
            name: "cname"
        }
    },
    async: {
        enable: true,
        url: ctx + "/revelationMenu/role/" + $('#roleId').val(),
        autoParam: ["id"]
    },
    callback: {
        onClick: onClick,
        onAsyncSuccess: zTreeOnAsyncSuccess
    }
};


//单击菜单列表
function onClick(event, treeId, treeNode, clickFlag) {
    if (treeNode.dataBaseChecked == true) {
        refPermissionTree(treeNode.id);
    } else {
        layui.use('layer', function () {
            layer.alert('该权限未放开',{
                icon:5
            },function (index) {
                layer.close(index);
                $.fn.zTree.destroy("permissionTree");
            });
        });

    }
}

function zTreeOnAsyncSuccess() {
    // var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    // var nodes = treeObj.getNodes();
    // for (var i = 0, l = nodes.length; i < l; i++) {
    //     treeObj.setChkDisabled(nodes[i], true, true, true);
    // }
}

//菜单列表资源树
function moduleSubmit() {
    var checkIds = getNode();
    $.ajax({
        type: 'POST',
        url: ctx + '/revelationMenu/addRoleAndModule/' + $('#roleId').val(),
        data: {menuIds: checkIds},
        dataType: 'json',
        success: function (data) {
            layui.use(['table', 'form', 'layer'], function () {
                var layer = layui.layer;
                if (data.success) {
                    layer.alert(data.msg, function (index) {
                        layer.close(index);
                        $.fn.zTree.init($("#menuTree"), setting);
                        if ($('#moduleId').val()!=null&&$('#moduleId').val()!=''&&$('#moduleId').val()!=undefined){
                            refPermissionTree($('#moduleId').val())
                        };
                    });
                } else {
                    layer.msg("操作失败");
                    return;
                }
            });

        }
    });
}

//资源列表权限树
function permissionSubmit() {
    var menuTreeObj = $.fn.zTree.getZTreeObj("menuTree");
    var menuNodes = menuTreeObj.getSelectedNodes();
    var moduleId;
    if (menuNodes.length = 1) {
        moduleId = menuNodes[0].id;
        var permissionTreeObj = $.fn.zTree.getZTreeObj("permissionTree");
        var permissionNodes = permissionTreeObj.getCheckedNodes(true);
        var permission = '';
        for (var i = 0; i < permissionNodes.length; i++) {
            if (permissionNodes.length == i + 1) {
                permission = permission + permissionNodes[i].id;
            } else {
                permission = permission + permissionNodes[i].id + '|';
            }
        }
        $.ajax({
            type: 'POST',
            url: ctx + '/revelationMenu/moduleSourcePermissionSave/'+$('#roleId').val()+'/' + moduleId,
            data: "moduleSourceIds=" + permission,
            success: function (data) {
                layui.use('layer', function () {
                    var layer = layui.layer;
                    if (data.success) {
                        layer.alert(data.msg, function (index) {
                            layer.close(index);
                            refPermissionTree(moduleId);//更新权限树
                        });
                    } else {
                        layer.msg(data.msg);
                        return;
                    }
                });

            }
        });
    }
}

//更新权限树
function refPermissionTree(moduleId) {
    $('#moduleId').val(moduleId);
    var permissionSetting = {
        check: {
            enable: true
        }, data: {
            simpleData: {
                enable: true
            },
            key: {
                name: "cname"
            }
        }, async: {
            enable: true,
            type: "get",
            url: ctx + "/revelationMenu/moduleSourcePermission/"+$('#roleId').val()+"/" + moduleId
        }, callback: {
            onAsyncSuccess: permissionTreeOnAsyncSuccess
        }
    };
    $.fn.zTree.init($("#permissionTree"), permissionSetting);
}


function permissionTreeOnAsyncSuccess() {
    // var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
    // var nodes = treeObj.getNodes();
    // for (var i = 0, l = nodes.length; i < l; i++) {
    //     treeObj.setChkDisabled(nodes[i], true, true, true);
    // }
}

function getNode() {
    var treeObj = $.fn.zTree.getZTreeObj("menuTree");
    var cnodes = '';
    var nodes = treeObj.getCheckedNodes(true);
    for (var i = 0; i < nodes.length; i++) {
        cnodes += nodes[i].id + ',';
    }
    return cnodes;
}

$(function () {
    $("#functionListPanel").panel(
        {
            title: "菜单列表(编号:" + $('#roleCode').val() + ",名称:" + $('#roleCname').val() + ")",
            tools: [{
                iconCls: 'icon-save', handler: function () {
                    moduleSubmit();
                }
            }]
        }
    );
    $("#operationListpanel").panel(
        {
            title: "资源列表",
            tools: [{
                iconCls: 'icon-save', handler: function () {
                    permissionSubmit();
                }
            }]
        }
    );
    var treeObj = $.fn.zTree.init($("#menuTree"), setting);//展示菜单树
    $("#selecrAllBtn").click(function () {
            treeObj.checkAllNodes(true);
        }
    );
    $("#resetBtn").click(function () {
            treeObj.reAsyncChildNodes(null, "refresh");
        }
    );
});