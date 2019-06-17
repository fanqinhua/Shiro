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
    <title>系统参数管理</title>
    <meta name="keywords" content="XXX管理系统">
    <meta name="description" content="XXX管理系统">
    <jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>系统参数</legend>
</fieldset>
<div style="width:200px; height: 500px; padding:25px; border: 1px solid #ddd; overflow: auto; float: left;">
    <ul id="sysParamCategoryTree"></ul>
</div>

<div style="width: auto; height: 500px; padding: 10px; border: 1px solid #ddd; overflow: auto;">
    <table id="sysParamTable" lay-filter="sysParamFilter"></table>
</div>
<input type="hidden" name="paramCategoryId" id="paramCategoryId" value="">
<script type="text/html" id="toolbarDemo">
    <div class="layui-inline">
        <shiro:hasPermission name="sysParam:add">
            <button class="btn btn-primary bg-main border-color" lay-event="add"><i
                    class="layui-icon layui-icon-add-1"></i>添加</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="sysParam:del">
            <button class="btn btn-primary bg-main border-color" lay-event="del"><i
                    class="layui-icon layui-icon-delete"></i>删除</button>
        </shiro:hasPermission>
    </div>
</script>
<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = "${ctx}";
</script>
<script src="${ctx}/static/system/sysParam/sysParam.js" type="application/javascript"></script>
</body>
</html>
