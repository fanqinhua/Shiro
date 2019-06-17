<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>用户管理</title>
    <meta name="keywords" content="XXX管理系统">
    <meta name="description" content="XXX管理系统">
    <jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
    <style>
        .laytable-cell-radio {
            overflow: visible;
            padding: 10px 25px 0 15px;
        }
    </style>
</head>
<body>

<div class="layui-row layui-col-space10 layui-form-item" style="margin: 10px;">
    <div class="demoTable">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 150px">用户名称</label>
                <div class="layui-input-inline" style="width: 150px;">
                    <input type="text" id="cname" name="cname" id="cName-search" placeholder="输入用户名称" autocomplete="off"
                           class="layui-input">
                </div>


                <shiro:hasPermission name="userMan:read">
                    <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button"
                            id="btn-search" data-type="reload"><i
                            class="fa fa-search mr-5"></i>搜索
                    </button>
                </shiro:hasPermission>
                <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button"
                        id="btn-refresh">
                    刷新
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="toolbarDemo">
    <div class="layui-inline">
        <shiro:hasPermission name="userMan:add">
            <button class="btn btn-primary bg-main border-color" lay-event="addUserMan"><i
                    class="layui-icon layui-icon-add-1"></i>新增
            </button>
        </shiro:hasPermission>
        <shiro:hasPermission name="userMan:edit">
            <button class="btn btn-primary bg-main border-color" lay-event="editUserMan"><i
                    class="layui-icon layui-icon-edit"></i>修改
            </button>
        </shiro:hasPermission>
    </div>
</script>

<script type="text/html" id="barDemo">
    <shiro:hasPermission name="userMan:ice">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">禁用</a>
    </shiro:hasPermission>
</script>


<table class="layui-hide" id="userManTable" lay-filter="userManFilter"></table>

<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/userMan/userMan.js" type="application/javascript"></script>
</body>
</html>
