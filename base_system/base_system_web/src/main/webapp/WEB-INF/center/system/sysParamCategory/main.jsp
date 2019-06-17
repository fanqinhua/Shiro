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
    <title>系统参数分类管理</title>
    <meta name="keywords" content="XXX管理系统">
    <meta name="description" content="XXX管理系统">
    <jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
</head>
<body>
<div class="layui-row layui-col-space10 layui-form-item" style="margin: 10px;">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 150px">分类名称</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="cname" id="cName-search" placeholder="输入分类名称" autocomplete="off"
                       class="layui-input">
            </div>
            <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-search"><i
                    class="fa fa-search mr-5"></i>搜索
            </button>
            <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-expand">展开
            </button>
            <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-fold">折叠
            </button>
            <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-refresh">
                刷新
            </button>
            <div class="clear"></div>
        </div>
        <table id="paramCategoryTable" class="layui-table" lay-filter="paramCategoryFilter"></table>
    </div>
</div>
<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/sysParamCategory/sysParamCategory.js" type="application/javascript"></script>
</body>
</html>
