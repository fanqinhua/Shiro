<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<jsp:include page="../common/commonHeaderCSS.jsp"></jsp:include>
<jsp:include page="../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script>
    layui.use(['layer'], function () {
        var layer = layui.layer;
        window.alertMsg = function () {
            layer.alert('此账号已在其它地方登录，请重新登陆！',{icon: 5},function () {
                top.location.href = ctx + "/login";
            });
        };
        alertMsg();
    });
</script>
</body>