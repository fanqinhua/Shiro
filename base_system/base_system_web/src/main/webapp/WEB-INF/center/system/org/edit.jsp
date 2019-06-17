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
<form class="layui-form layui-form-pane" action="">
    <c:if test="${id!=null&&id!=''&&id!=undefined}">
        <input type="hidden" id="ipid" name="ipid" value="${id}">
    </c:if>

    <c:if test="${porgVO.id!=null&&porgVO.id!=''&&porgVO.id!=undefined}">
        <input type="hidden" id="ipid" name="ipid" value="${porgVO.id}">
    </c:if>

    <c:if test="${orgVO.id!=null&&orgVO.id!=''&&orgVO.id!=undefined}">
        <input type="hidden" id="id" name="id" value="${orgVO.id}">
    </c:if>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">机构名称</label>
            <div class="layui-input-inline">
                <input type="text" name="cname" lay-verify="required|cname" placeholder="请输入" autocomplete="off"
                       class="layui-input" value="${orgVO.cname!=null?orgVO.cname:''}">
            </div>
        </div>
        <c:if test="${cname!=null&&cname!=''&&cname!=undefined}">
            <div class="layui-inline">
                <label class="layui-form-label">上级机构</label>
                <div class="layui-input-inline">
                    <input type="text" id="pcname" readonly lay-verify="required" placeholder="请输入" autocomplete="off"
                           class="layui-input" value="${cname}">
                </div>
            </div>
        </c:if>
        <c:if test="${porgVO.cname!=null&&porgVO.cname!=''&&porgVO.cname!=undefined}">
            <div class="layui-inline">
                <label class="layui-form-label">上级机构</label>
                <div class="layui-input-inline">
                    <input type="text" id="pcname" readonly lay-verify="required" placeholder="请输入" autocomplete="off"
                           class="layui-input" value="${porgVO.cname}">
                </div>
            </div>
        </c:if>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">机构代号</label>
            <div class="layui-input-inline">
                <input type="text" name="ccode" lay-verify="required|ccode" placeholder="请输入" autocomplete="off"
                       class="layui-input" value="${orgVO.ccode!=null?orgVO.ccode:''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
            <c:choose>
                <c:when test="${orgVO.ccode!=null}">
                    <c:choose>
                        <c:when test="${orgVO.cstatus=='00'}">
                            <select name="cstatus" lay-filter="aihao">
                                <option value="00" selected>启用</option>
                                <option value="01">停用</option>
                            </select>

                        </c:when>
                        <c:otherwise>
                            <select name="cstatus" lay-filter="aihao">
                                <option value="00" >启用</option>
                                <option value="01" selected>停用</option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <select name="cstatus" lay-filter="aihao">
                        <option value="00" selected>启用</option>
                        <option value="01">停用</option>
                    </select>
                </c:otherwise>
            </c:choose>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">负责人</label>
            <div class="layui-input-inline">
                <input type="text" name="cperson" lay-verify="required" placeholder="请输入负责人" autocomplete="off"
                       class="layui-input" value="${orgVO.cperson!=null?orgVO.cperson:''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="cemail" lay-verify="required|email" placeholder="请输入邮箱" autocomplete="off"
                       class="layui-input" value="${orgVO.cemail!=null?orgVO.cemail:''}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="ctel" lay-verify="required|phone" placeholder="请输入" autocomplete="off"
                       class="layui-input" value="${orgVO.ctel!=null?orgVO.ctel:''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-inline">
                <input type="text" name="caddress" lay-verify="required" placeholder="请输入" autocomplete="off"
                       class="layui-input" value="${orgVO.caddress!=null?orgVO.caddress:''}">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea name="cdes" placeholder="请输入内容" class="layui-textarea" value="${orgVO.cdes!=null?orgVO.cdes:''}"></textarea>
        </div>
    </div>
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit="" lay-filter="demo">提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>
<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/org/edit.js" type="application/javascript"></script>
</body>
</html>
