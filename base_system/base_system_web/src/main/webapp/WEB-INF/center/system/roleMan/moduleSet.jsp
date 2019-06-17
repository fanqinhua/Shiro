<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/js/zTree_v3/css/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" href="<%=request.getContextPath() %>/static/js/jquery-validation/1.10.0/validate.css">
<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/static/js/common.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/static/js/easyui-validation/easyui.validate.extend.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/static/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/static/js/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>


<body class="gray-bg">
<input type="hidden" id="roleId"  value="${sysRoleVO.id}">
<input type="hidden" id="roleCode"  value="${sysRoleVO.ccode}">
<input type="hidden" id="roleCname"  value="${sysRoleVO.cname}">
<input type="hidden" id="moduleId" value="">
<div class="easyui-layout" fit="true" >
    <div region="center" style="padding:1px;">
        <div id="functionListPanel" class="easyui-panel" style="padding:1px;" fit="true" border="false">
            <a id="selecrAllBtn" href="#">全选</a>
            <a id="resetBtn" href="#">重置</a>
            <ul id="menuTree" class="ztree"></ul>
        </div>
    </div>
    <div region="east" style="width:350px; overflow: hidden;" split="true">
        <div id="operationListpanel" class="easyui-panel" style="padding:1px;" fit="true" border="false">
            <ul id="permissionTree" class="ztree"></ul>
        </div>
    </div>
</div>

<script>
    var ctx = '${ctx}';
</script>
<script src="${ctx}/static/system/roleMan/moduleSet.js" type="application/javascript"></script>
</body>