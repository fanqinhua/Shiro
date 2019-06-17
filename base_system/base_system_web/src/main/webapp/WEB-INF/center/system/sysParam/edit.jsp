<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
</head>
<body>
<br>
<form class="layui-form" enctype="text/plain">
    <input type="hidden" id="paramCategoryId" name="iparamCategoryId" value="${paramCategoryId}">
    <input type="hidden" name="nsContentCategoryId" id="nsContentCategoryId" value="${nsContentCategory.id}">
    <div class="layui-form-item">
        <label class="layui-form-label">配置项</label>
        <div class="layui-input-block">
            <input type="text" name="ccode" placeholder="请输入配置项" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">配置值</label>
        <div class="layui-input-block">
            <input type="text" name="cname" placeholder="请输入配置值" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">链接</label>
        <div class="layui-input-block">
            <input type="text" name="curl" placeholder="请输入链接" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">图片</label>
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="upload1">上传图片</button>
            <input type="hidden" id="img_url" name="cpic" value=""/>
            <div class="layui-upload-list">
                <img class="layui-upload-img" width="100px" height="80px" id="demo1"/>
                <p id="demoText"></p>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <input type="radio" name="cstatus" value="00" title="启用" checked>
            <input type="radio" name="cstatus" value="01" title="禁用">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="form">提交</button>
            <button class="layui-btn layui-btn-primary" type="reset">重置</button>
        </div>
    </div>
</form>
<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/sysParam/edit.js" type="application/javascript"></script>
</body>
</html>
