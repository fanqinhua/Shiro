<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
    <style type="text/css">
        .downpanel .layui-select-title span {
            line-height: 38px;
        }

        /*继承父类颜色*/
        .downpanel dl dd:hover {
            background-color: inherit;
        }
    </style>
</head>
<body onload="loadOrgList()">
<br/>
<form class="layui-form layui-form-pane" action="">
    <input type="hidden" id="id" name="id" value="${childParamCategory.id}"/>
    <input type="hidden" id="ipid" name="ipid" value="${parentParamCategory.id}"/>
    <input type="hidden" id="dcreateTime" name="dcreateTime" value="${childParamCategory.dcreateTime}">
    <input type="hidden" id="dupdateTime" name="dupdateTime" value="${childParamCategory.dupdateTime}">
    <div class="layui-form-item">
        <label class="layui-form-label">父节点</label>
        <div class="layui-input-block">
            <input type="text" id="iPname" disabled="disabled" value="${parentParamCategory.cname}"
                   autocomplete="off" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">机构</label>
            <div class="layui-input-inline">
                <div class="layui-unselect layui-form-select downpanel">
                    <div class="layui-select-title" align="center">
                        <span class="layui-input layui-unselect" id="treeclass" style="width: 269px;">--请选择--</span>
                        <c:choose>
                            <c:when test="${childParamCategory.corgId!=null}">
                                <input id="corgId" type="hidden" name="corgId" value="${childParamCategory.corgId}">
                            </c:when>
                            <c:otherwise>
                                <input id="corgId" type="hidden" name="corgId" value="0">
                            </c:otherwise>
                        </c:choose>
                        <i class="layui-edge"></i>
                    </div>
                    <dl class="layui-anim layui-anim-upbit">
                        <dd>
                            <ul id="classtree"></ul>
                        </dd>
                    </dl>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">分类名称</label>
        <div class="layui-input-block">
            <input type="text" id="cname" name="cname" placeholder="请输入分类名称" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${childParamCategory.cname!=null?childParamCategory.cname:''}">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">叶子节点</label>
        <div id="cisParent" class="layui-input-block">
            <c:choose>
                <c:when test="${childParamCategory.cisParent}">
                    <input type="radio" name="cisParent" value="0" title="是" checked>
                    <input type="radio" name="cisParent" value="1" title="否">
                </c:when>
                <c:otherwise>
                    <input type="radio" name="cisParent" value="0" title="是">
                    <input type="radio" name="cisParent" value="1" title="否" checked>
                </c:otherwise>
            </c:choose>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">排序</label>
        <div class="layui-input-block">
            <input type="text" id="corder" name="corder" placeholder="请输入排序" autocomplete="off" class="layui-input"
                   lay-verify="required" value="${childParamCategory.corder!=null?childParamCategory.corder:''}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">状态</label>
        <div class="layui-input-block">
            <select id="cstatus" name="cstatus" lay-filter="status">
                <c:choose>
                    <c:when test="${childParamCategory.cstatus=='00'}">
                        <option value="00" selected>启用</option>
                        <option value="01">禁用</option>
                    </c:when>
                    <c:otherwise>
                        <option value="00">启用</option>
                        <option value="01" selected>禁用</option>
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
<script src="${ctx}/static/system/sysParamCategory/add.js" type="application/javascript"></script>
</body>
</html>
