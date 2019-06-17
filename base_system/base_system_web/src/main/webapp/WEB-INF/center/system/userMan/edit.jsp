<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
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
<body onload="loadRoleAndOrgList()">
<br>
<form class="layui-form layui-form-pane" action="">
    <c:if test="${sysUserVO.id!=null&&sysUserVO.id!=''&&sysUserVO.id!=undefined}">
        <input type="hidden" id="id" name="id" value="${sysUserVO.id}">
    </c:if>
    <c:if test="${sysUserVO.icreateorId!=null&&sysUserVO.icreateorId!=''&&sysUserVO.icreateorId!=undefined}">
        <input type="hidden" id="icreateorId" name="icreateorId" value="${sysUserVO.icreateorId}">
    </c:if>
    <c:if test="${sysUserVO.cactive!=null&&sysUserVO.cactive!=''&&sysUserVO.cactive!=undefined}">
        <input type="hidden" id="cactive" name="cactive" value="${sysUserVO.cactive}">
    </c:if>
    <c:if test="${sysUserVO.csalt!=null&&sysUserVO.csalt!=''&&sysUserVO.csalt!=undefined}">
        <input type="hidden" id="csalt" name="csalt" value="${sysUserVO.csalt}">
    </c:if>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">登陆账号</label>
            <div class="layui-input-inline">
                <input type="text" name="cloginName" lay-verify="required|username" placeholder="请输入账号" autocomplete="off"
                       class="layui-input" value="${sysUserVO.cloginName!=null?sysUserVO.cloginName:''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">用户姓名</label>
            <div class="layui-input-inline">
                <input type="text" id="cname" name="cname" lay-verify="required" placeholder="请输入姓名" autocomplete="off"
                       class="layui-input" value="${sysUserVO.cname!=null?sysUserVO.cname:''}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="cpassword" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off"
                       class="layui-input" value="${sysUserVO.cpassword!=null?sysUserVO.cpassword:''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">再次输入密码</label>
            <div class="layui-input-inline">
                <input type="password" id="cpassword" lay-verify="required|rePassword" placeholder="请再输入密码" autocomplete="off"
                       class="layui-input" value="${sysUserVO.cpassword!=null?sysUserVO.cpassword:''}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline" >
            <label class="layui-form-label">角色</label>
            <div class="layui-input-inline">
                <select name="croleId" id="croleId" lay-verify="required" lay-filter="rolefilter">
                    <option value="">--请选择--</option>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">

                <c:choose>
                    <c:when test="${sysUserVO.cstatus!=null}">
                        <c:choose>
                            <c:when test="${sysUserVO.cstatus=='00'}">
                                <select name="cstatus" lay-filter="aihao">
                                    <option value="00" selected>启用</option>
                                    <option value="01">停用</option>
                                    <option value="02">冻结登陆</option>
                                </select>
                            </c:when>
                            <c:when test="${sysUserVO.cstatus=='01'}">
                                <select name="cstatus" lay-filter="aihao">
                                    <option value="00">启用</option>
                                    <option value="01" selected>停用</option>
                                    <option value="02">冻结登陆</option>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <select name="cstatus" lay-filter="aihao">
                                    <option value="00">启用</option>
                                    <option value="01">停用</option>
                                    <option value="02" selected>冻结登陆</option>
                                </select>
                            </c:otherwise>
                        </c:choose>

                    </c:when>
                    <c:otherwise>
                        <select name="cstatus" lay-filter="aihao">
                            <option value="00" selected>启用</option>
                            <option value="01">停用</option>
                            <option value="02">冻结登陆</option>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>
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
                            <c:when test="${sysUserVO.corgId!=null}">
                                <input id="corgId" type="hidden" name="corgId" value="${sysUserVO.corgId}">
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
        <div class="layui-inline">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-inline">
                <input type="text" name="cphone" lay-verify="required|phone|number" placeholder="请输入电话" autocomplete="off"
                       class="layui-input" value="${sysUserVO.cphone!=null?sysUserVO.cphone:''}">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="cemail" lay-verify="required|email" placeholder="请输入邮箱" autocomplete="off"
                       class="layui-input" value="${sysUserVO.cemail!=null?sysUserVO.cemail:''}">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-inline">
                <input type="text" name="caddress" lay-verify="" placeholder="请输入地址" autocomplete="off"
                       class="layui-input" value="${sysUserVO.caddress!=null?sysUserVO.caddress:''}">
            </div>
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">描述</label>
        <div class="layui-input-block">
            <textarea name="cremark" placeholder="请输入描述" class="layui-textarea"
                      value="${sysUserVO.cremark!=null?sysUserVO.cremark:''}"></textarea>
        </div>
    </div>

    <div class="layui-input-block">
        <button class="layui-btn" lay-submit="" lay-filter="demo">提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</form>
<br>
<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
    var roleId = '${sysUserVO.croleId!=null?sysUserVO.croleId:''}';
</script>
<script src="${ctx}/static/system/userMan/edit.js" type="application/javascript"></script>
</body>
</html>
