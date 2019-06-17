<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>XXX管理系统</title>
    <meta name="keywords" content="XXX管理系统">
    <meta name="description" content="XXX管理系统">
    <jsp:include page="common/commonHeaderCSS.jsp"></jsp:include>
    <link href="<%=request.getContextPath() %>/static/css/index.css" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden;" onload="showTime()">
<div id="wrapper">
    <%--左侧导航栏开始--%>
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header" style="padding:15px 0 0 12px;">
                    <div class="dropdown profile-element">
                        <div style="width: 127px;height: 37px;background: url(${ctx}/static/img/logo.png) no-repeat center center;background-size: 153px auto !important;margin-left: 15px;"></div>
                        <div style="font-size: 14px;color: #e8edf7;padding: 15px 15px 0px 15px;font-weight: bold;">
                            XXX管理系统
                        </div>
                        <span class="clear" style="margin-top:12px;">
                                    <span class="block m-t-xs"></span>
                                </span>
                    </div>
                    <%--<div class="logo-element">渠道管理</div>--%>
                </li>
                <c:if test="${menuList!=null}">
                    <%--<c:forEach items="${menuList}" var="module">--%>
                    <c:forEach items="${menuList.children}" var="module1">
                        <li>
                            <a href="#">
                                <i class="${module1.cicon}"></i>
                                <span class="nav-label">${module1.cname}</span>
                                <span class="fa arrow"></span>
                            </a>

                            <ul class="nav nav-second-level">
                                <c:forEach items="${module1.children}" var="module2">
                                    <li class="leftLi">
                                        <c:choose>
                                            <c:when test="${module2.curl!=null&&module2.curl!=''}">
                                                <a class="J_menuItem" href="${ctx}${module2.curl}"
                                                   data-index="0">${module2.cname}</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="J_menuItem" href="" data-index="0">${module2.cname}</a>
                                                <ul class="nav nav-second-level">
                                                    <c:if test="${module2.children!=null}">
                                                        <c:forEach items="${module2.children}" var="module3">
                                                            <li class="leftLi ml-15">
                                                                <a class="J_menuItem" href="${ctx}${module3.curl}"
                                                                   data-index="0">${module3.cname}</a>
                                                            </li>
                                                        </c:forEach>
                                                    </c:if>
                                                </ul>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </c:forEach>
                    <%--</c:forEach>--%>
                </c:if>
            </ul>
        </div>
    </nav>
    <%--左侧导航栏结束--%>
    <%--右侧部分开始--%>
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0;background:#233645;">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="#"
                       style="background:#2483c6;border-color:#2483c6;"><i
                            class="fa fa-bars"></i> </a>
                    <%-- 当前时间 --%>
                    <div id="div_time"></div>
                    <!-- 天气信息 -->
                    <div class="weather" pc>
                        <div id="tp-weather-widget"></div>
                        <script>(function (T, h, i, n, k, P, a, g, e) {
                            g = function () {
                                P = h.createElement(i);
                                a = h.getElementsByTagName(i)[0];
                                P.src = k;
                                P.charset = "utf-8";
                                P.async = 1;
                                a.parentNode.insertBefore(P, a)
                            };
                            T["ThinkPageWeatherWidgetObject"] = n;
                            T[n] || (T[n] = function () {
                                (T[n].q = T[n].q || []).push(arguments)
                            });
                            T[n].l = +new Date();
                            if (T.attachEvent) {
                                T.attachEvent("onload", g)
                            } else {
                                T.addEventListener("load", g, false)
                            }
                        }(window, document, "script", "tpwidget", "//widget.seniverse.com/widget/chameleon.js"))</script>
                        <script>
                            tpwidget("init", {
                                "flavor": "slim",
                                "location": "WX4FBXXFKE4F",
                                "geolocation": "enabled",
                                "language": "zh-chs",
                                "unit": "c",
                                "theme": "chameleon",
                                "container": "tp-weather-widget",
                                "bubble": "disabled",
                                "alarmType": "badge",
                                "color": "#FFFFFF",
                                "uid": "U9EC08A15F",
                                "hash": "039da28f5581f4bcb5c799fb4cdfb673"
                            });
                            tpwidget("show");</script>
                    </div>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="hidden-xs">
                        <a class="color_fff" data-index="0"><i class="fa fa-user"></i>&nbsp;您好，<shiro:principal
                                property="name"></shiro:principal>！</a>
                    </li>

                    <li class="hidden-xs">
                        <a class="color_fff" data-index="0" data-toggle="modal" data-target="#myModal6"><i
                                class="fa fa-key"></i> 修改密码</a>
                    </li>
                    <li class="hidden-xs">
                        <a class="color_fff" onclick="exit()">
                            <i class="fa fa-sign-out"></i> 退出
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="welcome.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight" style="right:80px;"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right" style="right:0;">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span></button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive">
                        <a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll">
                        <a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther">
                        <a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%"
                    src="${ctx}/welcome" frameborder="0"
                    data-id="welcome.jsp" seamless></iframe>
        </div>
    </div>
    <%--右侧部分结束--%>
</div>
<div class="modal inmodal fade" id="myModal6" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-dialog" style="margin-top:10%">
        <div class="modal-content">
            <div class="modal-header" style="padding:10px 15px;">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span></button>
                <h4 class="modal-title" style="font-size:18px">修改密码</h4>
            </div>
            <div class="modal-body" style="background:#fff;">
                <div class="ibox-content iptBox" style="border:none;">
                    <div class="row cl mb-15">
                        <div class="col-sm-3 lh-46 text-right">原密码：</div>
                        <div class="formControls col-sm-9">
                            <input type="password" placeholder="请输入原密码" class="form-control input-lg radius5 pw_ipt">
                            <div class="c-red mt-5 ml-10 f-12 pwTip pwTip1" style="display:none">请输入原密码！</div>
                        </div>
                    </div>
                    <div class="row cl mb-15">
                        <div class="col-sm-3 lh-46 text-right">新密码：</div>
                        <div class="formControls col-sm-9">
                            <input type="password" placeholder="请输入密码" class="form-control input-lg radius5 pw_ipt"
                                   size="40" onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)"
                                   id="password">
                            <div class="c-red mt-5 ml-10 f-12 pwTip pwTip2" style="display:none">请输入新密码！</div>
                            <ul class="pw_ul clearfix" style="display:none;">
                                <li>安全程度：</li>
                                <li width="100" id="strength_L">弱</li>
                                <li width="100" id="strength_M">中</li>
                                <li width="100" id="strength_H">强</li>
                            </ul>
                        </div>
                    </div>
                    <div class="row cl mb-15">
                        <div class="col-sm-3 lh-46 text-right">再次输入：</div>
                        <div class="formControls col-sm-9">
                            <input type="password" placeholder="请再次输入密码" class="form-control input-lg radius5"
                                   id="retype">
                            <div class="c-red mt-5 ml-10 f-12 pwTip pwTip3" style="display:none">请再次输入密码！</div>
                            <span class="sucess_icon" style="display:none;"><i class="fa fa-check"></i></span>
                            <div class="c-red mt-5 ml-10 f-12 retype_txt" style="display:none;">两次输入的密码不一致！</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" style="text-align:center;">
                <button type="button" class="btn btn-primary bg-main border-color" onclick="changeBtn()">确定</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/system/index.js"></script>

</body>
</html>
