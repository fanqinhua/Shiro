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
    <title>账单管理</title>
    <meta name="keywords" content="XXX管理系统">
    <meta name="description" content="XXX管理系统">
    <jsp:include page="../../../common/commonHeaderCSS.jsp"></jsp:include>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div style="border: 1px solid #eee; padding-top: 5px;" >
                    <div id="sumMain1" style="height:500px;"></div>
                    <!-- <div id="totalMain" style="height:300px;"></div> -->
                </div>
                <div style="border: 1px solid #eee; padding-top: 5px;" >
                    <div id="sumMain2" style="height:300px;"></div>
                    <!-- <div id="totalMain" style="height:300px;"></div> -->
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/echarts/billMan/billMan.js" type="application/javascript"></script>

</body>
</html>
