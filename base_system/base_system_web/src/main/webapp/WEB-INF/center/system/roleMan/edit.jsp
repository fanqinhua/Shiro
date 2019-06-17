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
<br>
<form class="layui-form" action="" lay-filter="example">

    <c:if test="${sysRoleVO.id!=null&&sysRoleVO.id!=''&&sysRoleVO.id!=undefined}">
        <input type="hidden" id="id" name="id" value="${sysRoleVO.id}">
    </c:if>
    <c:if test="${sysRoleVO.dcreateTime!=null&&sysRoleVO.dcreateTime!=''&&sysRoleVO.dcreateTime!=undefined}">
        <input type="hidden" id="dcreateTime" name="dcreateTime" value="${sysRoleVO.dcreateTime}">
    </c:if>
    <c:if test="${sysRoleVO.ccode!=null&&sysRoleVO.ccode!=''&&sysRoleVO.ccode!=undefined}">
        <input type="hidden" id="ccode" name="ccode" value="${sysRoleVO.ccode}">
    </c:if>

    <div class="layui-form-item">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-block">
            <input type="text" name="cname" lay-verify="required" lay-verify="title" autocomplete="off" placeholder="请输入角色名称" class="layui-input" value="${sysRoleVO.cname!=null?sysRoleVO.cname:''}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <c:choose>
                <c:when test="${sysRoleVO.cstatus!=null}">
                    <c:choose>
                        <c:when test="${sysRoleVO.cstatus=='00'}">
                            <input type="radio" name="cstatus" value="00" title="启用" checked>
                            <input type="radio" name="cstatus" value="01" title="禁用">
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="cstatus" value="00" title="启用" >
                            <input type="radio" name="cstatus" value="01" title="禁用" checked>
                        </c:otherwise>
                    </c:choose>

                </c:when>
                <c:otherwise>
                    <input type="radio" name="cstatus" value="00" title="启用" checked>
                    <input type="radio" name="cstatus" value="01" title="禁用" >
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-block">
            <textarea placeholder="请输入备注" class="layui-textarea" name="cdes">${sysRoleVO.cdes!=null?sysRoleVO.cdes:''}</textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="demo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/roleMan/edit.js" type="application/javascript"></script>
</body>
</html>
