<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
<div class="layui-row layui-col-space10 layui-form-item" style="margin: 10px;">
    <div class="demoTable">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 150px">角色名称</label>
                <div class="layui-input-inline" style="width: 150px;">
                    <input type="text" id="cname" name="cname" id="cName-search" placeholder="输入角色名称"
                           autocomplete="off"
                           class="layui-input">
                </div>
                <shiro:hasPermission name="roleMan:read">
                    <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button"
                            id="btn-search" data-type="reload"><i
                            class="fa fa-search mr-5"></i>搜索
                    </button>
                </shiro:hasPermission>
                <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button"
                        id="btn-refresh">
                    刷新
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/html" id="toolbarDemo">
    <div class="layui-inline">
        <shiro:hasPermission name="roleMan:add">
            <button class="btn btn-primary bg-main border-color" lay-event="addRoleMan"><i
                    class="layui-icon layui-icon-add-1"></i>新增
            </button>
        </shiro:hasPermission>
        <shiro:hasPermission name="roleMan:edit">
            <button class="btn btn-primary bg-main border-color" lay-event="editRoleMan"><i
                    class="layui-icon layui-icon-edit"></i>修改
            </button>
        </shiro:hasPermission>
    </div>
</script>

<script type="text/html" id="barDemo">
    <shiro:hasPermission name="roleMan:ice">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">禁用</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="roleMan:permission">
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="permission">权限设置</a>
    </shiro:hasPermission>
</script>

<table id="roleTable" lay-filter="roleManFilter"></table>

<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/common.js"></script>
<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/roleMan/roleMan.js" type="application/javascript"></script>
