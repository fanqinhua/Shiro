<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>登录--XXX管理系统</title>
    <!-- 公用头部JS start -->
    <jsp:include page="common/commonHeaderCSS.jsp"></jsp:include>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/js/jquery-toast/dist/jquery.toast.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/css/cover.css">
    <!-- 公用头部JS end -->
    <style>
        .verifyImg {
            width: 130px;
            height: 42px;
        }
    </style>

</head>
<body class="gray-bg" style="background:url(${ctx}/static/img/login_bg.jpg);background-size:100% 100%;">
<div class="col-sm-12 login_header">
    <img src="${ctx}/static/img/logo.png" alt="" height="50"><span class="headerTxt">XXX管理系统</span>
</div>
<form class="layui-form layui-form-pane" action="">
    <div class="loginBox">
        <div class="loginBox_title">XXX后台管理系统</div>
        <div class="loginBox_h">登录中心</div>
        <div class="admin"><img src="${ctx}/static/img/admin.png"></div>
        <div class="c-red loginTip"></div>
        <div class="iptbox">
            <div class="login_ipt userName">
                <input type="text" name="username" lay-verify="required" placeholder="请输入用户名" autocomplete="off"
                       class="layui-input">
            </div>
            <div class="login_ipt passWord">
                <input type="password" name="password" lay-verify="required" placeholder="请输入密码" autocomplete="off"
                       class="layui-input">
            </div>
            <div class="login_ipt yzm">
                <input type="text" placeholder="请输入验证码" id="captcha" name="captcha" lay-verify="required|captcha"
                       style="width:57%">
                <img id="u16_img" class="verifyImg" onClick="refreshImg()" style="cursor:pointer" alt="点击切换"
                     src="${ctx}/imageCode/showCode"/>
            </div>
        </div>
        <div class="text-center loginBtn">
            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="login">立即登录</button>
        </div>
    </div>
    <div class="filing">
        <p>©2015 FQH版权所有 </p>
        <p>备案：粤ICP备16002795号</p>
    </div>
</form>
<!-- 公用尾部JS start -->
<jsp:include page="common/commonFooterJS.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/static/js/jquery-toast/dist/jquery.toast.min.js"></script>
<script src="<%=request.getContextPath() %>/static/system/common/api.js" type="application/javascript"></script>
<script src="<%=request.getContextPath() %>/static/system/common/common.js" type="application/javascript"></script>
<script src="<%=request.getContextPath() %>/static/js/aes/aes.js" type="application/javascript"></script>
<script src="<%=request.getContextPath() %>/static/js/aes/mode-ecb.js" type="application/javascript"></script>
<!-- 公用尾部JS end -->
<script>
    var ctx = '${ctx}';
</script>
<script src="<%=request.getContextPath() %>/static/system/login.js" type="application/javascript"></script>
</body>
</html>
