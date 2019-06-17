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
    <title>机构管理</title>
    <meta name="keywords" content="XXX管理系统">
    <meta name="description" content="XXX管理系统">
    <jsp:include page="../../common/commonHeaderCSS.jsp"></jsp:include>
    <style>
        .laytable-cell-radio {
            overflow: visible;
            padding: 10px 25px 0 15px;
        }
    </style>
</head>
<body>
<div class="layui-row layui-col-space10 layui-form-item" style="margin: 10px;">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 150px">机构名称</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="cname" id="cName-search" placeholder="输入机构名称" autocomplete="off"
                       class="layui-input">
            </div>
            <shiro:hasPermission name="org:read">
                <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-search">
                    <i
                            class="fa fa-search mr-5"></i>搜索
                </button>
            </shiro:hasPermission>

            <%--<button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-addOrg">新增机构--%>
            <%--</button>--%>

            <%--<button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-addChilOrg">新增子机构--%>
            <%--</button>--%>

            <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-expand">展开
            </button>
            <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-fold">折叠
            </button>
            <button data-toggle="button" class="btn btn-primary bg-main border-color" type="button" id="btn-refresh">
                刷新
            </button>
            <div class="clear"></div>
        </div>
        <table id="orgTable" class="layui-hide" lay-filter="orgFilter"></table>
    </div>
</div>


<script type="text/html" id="toolbarDemo">
    <div class="layui-inline">
        <shiro:hasPermission name="org:add">
            <button class="btn btn-primary bg-main border-color" lay-event="btn-addOrg">新增机构</button>
        </shiro:hasPermission>
        <shiro:hasPermission name="org:chilAdd">
            <button class="btn btn-primary bg-main border-color" lay-event="btn-addChilOrg">新增子机构</button>
        </shiro:hasPermission>
    </div>
</script>
<jsp:include page="../../common/commonFooterJS.jsp"></jsp:include>
<script>
    var ctx = '${ctx}';
</script>
<script>
    function complain(d) {//操作中显示的内容
        var edit = '';
        var del = '';
        if (d.ipid != 0) {
            <shiro:hasPermission name="org:edit">
            edit = '<a class="operation" lay-event="edit" title="编辑"><i class="layui-icon layui-icon-edit"></i></a>&nbsp;';
            </shiro:hasPermission>
            <shiro:hasPermission name="org:del">
            del = '<a class="operation" lay-event="del" title="删除"><i class="layui-icon layui-icon-delete"></i></a>'
            </shiro:hasPermission>
        } else {
            edit = '';
            del = '';
        }
        return [edit, del].join('');
    }
</script>
<script src="${ctx}/static/system/org/org.js" type="application/javascript"></script>
</body>
</html>
