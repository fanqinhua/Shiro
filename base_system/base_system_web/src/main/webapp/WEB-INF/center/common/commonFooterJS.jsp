<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.min.js?v=2.1.4"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/basic.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/bootstrap.min.js?v=3.3.6"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/hplus.min.js?v=4.1.0"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/contabs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/pw.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/layui/layui.js"></script>
<!-- 引入EasyUI -->
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
<%--引入Echarts--%>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/echarts/echarts-all.js"></script>
<!--日期格式转换器-->

<script type="text/javascript">
    function formatterTime(v) {
        var date = new Date(v);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? '0' + m : m;
        var d = date.getDate();
        d = d < 10 ? ("0" + d) : d;
        var h = date.getHours();
        h = h < 10 ? ("0" + h) : h;
        var M = date.getMinutes();
        M = M < 10 ? ("0" + M) : M;
        var S = date.getSeconds();
        S = S < 10 ? ("0" + S) : S;
        var str = y + "-" + m + "-" + d + " " + h + ":" + M + ":" + S;
        return str;
    }
</script>
