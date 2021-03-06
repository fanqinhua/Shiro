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
    <title>操作日志</title>
    <meta name="keywords" content="XXX管理系统">
    <meta name="description" content="XXX管理系统">
    <jsp:include page="../../../common/commonHeaderCSS.jsp"></jsp:include>
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

                <label class="layui-form-label">操作时间</label>
                <div class="layui-input-inline" style="width: 180px;">
                    <input type="text" name="startTime" class="layui-input" id="startTime"
                           placeholder="开始时间">
                </div>
                <div class="layui-input-inline" style="width: 180px;">
                    <input type="text" name="endTime" class="layui-input" id="endTime"
                           placeholder="结束时间">
                </div>

                <button data-toggle="button" class="btn btn-primary bg-main border-color layui-btn" type="button"
                        data-type="reload"><i
                        class="fa fa-search mr-5"></i>搜索
                </button>
                <button data-toggle="button" class="btn btn-primary bg-main border-color layui-btn" type="button"
                        id="btn-refresh">
                    刷新
                </button>
            </div>
        </div>
    </div>
</div>


<script type="text/html" id="toolbarDemo">
    <div class="layui-inline">
        <shiro:hasPermission name="oprlog:del">
            <button class="btn btn-primary bg-main border-color" lay-event="delOprLog"><i
                    class="layui-icon layui-icon-delete"></i>删除
            </button>
        </shiro:hasPermission>
    </div>
</script>

<table class="layui-hide" id="oprLogTable" lay-filter="oprLogFilter"></table>


<jsp:include page="../../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/log/oprlog/oprlog.js" type="application/javascript"></script>

</body>
</html>
