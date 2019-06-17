<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>管理功能点</title>
    <jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
    <style type="text/css">
        /*您可以将下列样式写入自己的样式表中*/
        .childBody{padding: 15px;}

        /*layui 元素样式改写*/
        .layui-btn-sm{line-height: normal; font-size: 12.5px;}
        .layui-table-view .layui-table-body{min-height: 256px;}
        .layui-table-cell .layui-input.layui-unselect{height: 30px; line-height: 30px;}

        /*设置 layui 表格中单元格内容溢出可见样式*/
        .table-overlay .layui-table-view,
        .table-overlay .layui-table-box,
        .table-overlay .layui-table-body{overflow: visible;}
        .table-overlay .layui-table-cell{height: auto; overflow: visible;}

        /*文本对齐方式*/
        .text-center{text-align: center;}
    </style>
</head>
<body class="childBody">

<input type="hidden" name="moduleId" id="moduleId" value="${id}">
<section class="layui-col-md10" style="margin: 0 auto; float: none;">
    <div class="layui-card">
        <div class="layui-card-body layui-text">
            <div id="toolbar">
                <div>
                    <button type="button" class="layui-btn layui-btn-sm" data-type="addRow" title="添加一行">
                        <i class="layui-icon layui-icon-add-1"></i> 添加一行
                    </button>
                </div>
            </div>
            <div id="tableRes" class="table-overlay">
                <table id="dataTable" lay-filter="dataTable" class="layui-hide"></table>
            </div>
            <div id="action" class="text-center">
                <button type="button" name="btnSave" class="layui-btn" data-type="save"><i class="layui-icon layui-icon-ok-circle"></i>保存</button>
                <button type="reset" name="btnReset" class="layui-btn layui-btn-primary">取消</button>
            </div>
        </div>
    </div>

    <!--保存结果输出-->
    <%--<div class="layui-card">--%>
        <%--<div class="layui-card-header">保存结果输出</div>--%>
        <%--<div class="layui-card-body layui-text">--%>
            <%--<blockquote class="layui-elem-quote layui-quote-nm">--%>
                <%--<pre id="jsonResult"><span class="layui-word-aux">请点击“保存”后查看输出信息……</span></pre>--%>
            <%--</blockquote>--%>
        <%--</div>--%>
    <%--</div>--%>
</section>

<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
    var list= '${list}';
</script>
<script src="${ctx}/static/system/module/operation.js" type="application/javascript"></script>
</body>
</html>
