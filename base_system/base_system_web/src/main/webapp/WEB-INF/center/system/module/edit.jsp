<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
</head>
<body>
<br/>
<form class="layui-form">
    <input type="hidden" id="id" name="id" value="${childModule.id}"/>
    <input type="hidden" id="ipid" name="ipid" value="${parentModule.id}"/>
    <div class="layui-form-item">
        <label class="layui-form-label">父节点</label>
        <div class="layui-input-block">
            <input type="text" id="iPname" disabled="disabled" value="${parentModule.cname}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">菜单名称</label>
        <div class="layui-input-block">
            <input type="text" id="cname" name="cname" placeholder="请输入菜单名称" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${childModule.cname}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <input type="text" id="curl" name="curl" placeholder="请输入地址" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${childModule.curl}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否叶子节点</label>
        <div id="cIsLeaf" class="layui-input-block">
            <c:choose>
                <c:when test="${childModule.cisLeaf=='0'}">
                    <input type="radio" name="cisLeaf" value="0" title="是" checked>
                    <input type="radio" name="cisLeaf" value="1" title="否">
                </c:when>
                <c:otherwise>
                    <input type="radio" name="cisLeaf" value="0" title="是" >
                    <input type="radio" name="cisLeaf" value="1" title="否" checked>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">编号</label>
        <div class="layui-input-block">
            <input type="text" id="ccode" name="ccode" placeholder="请输入编号" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${childModule.ccode}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" id="corer" name="corer" placeholder="请输入排序" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${childModule.corer}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">层级</label>
        <div class="layui-input-block">
            <input type="text" id="clevel" name="clevel" placeholder="请输入层级" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${childModule.clevel}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <select id="cstatus" name="cstatus" lay-filter="status">
                <c:choose>
                    <c:when test="${childModule.cstatus=='00'}">
                        <option value="00" selected>启用</option>
                        <option value="01">禁用</option>
                    </c:when>
                    <c:otherwise>
                        <option value="00" >启用</option>
                        <option value="01" selected>禁用</option>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否可见</label>
        <div class="layui-input-block">
            <select id="cvisable" name="cvisable" lay-filter="status">
                <c:choose>
                    <c:when test="${childModule.cvisable=='0'}">
                        <option value="0" selected>可见</option>
                        <option value="1">不可见</option>
                    </c:when>
                    <c:otherwise>
                        <option value="0" >可见</option>
                        <option value="1" selected>不可见</option>
                    </c:otherwise>
                </c:choose>

            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>

</form>

<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/module/add.js" type="application/javascript"></script>
</body>
</html>
