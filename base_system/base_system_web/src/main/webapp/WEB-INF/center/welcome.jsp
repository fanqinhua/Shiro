<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
    <meta name="keywords" content="渠道管理平台">
    <meta name="description" content="渠道管理平台">
    <jsp:include page="common/commonHeaderCSS.jsp"></jsp:include>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-8">
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="col-sm-3 text-success">今日</div>
                        <div class="col-sm-3">本周</div>
                        <div class="col-sm-3">本月</div>
                        <div class="col-sm-3">全部</div>
                    </div>
                    <div class="ibox-content col-sm-12">
                        <div class="col-sm-4">
                            <p><img src="${ctx}/static/img/nopay.png"></p>
                            <h3>未缴费</h3>
                        </div>
                        <div class="col-sm-6">
                            <h3 class="font-bold no-margins text-center">25笔</h3>
                            <h1 class="text-center">￥1,405.00</h1>
                            <div class="stat-percent font-bold text-info">查看详情>></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="col-sm-3 text-success">今日</div>
                        <div class="col-sm-3">本周</div>
                        <div class="col-sm-3">本月</div>
                        <div class="col-sm-3">全部</div>
                    </div>
                    <div class="ibox-content col-sm-12">
                        <div class="col-sm-4">
                            <p><img src="${ctx}/static/img/jiaofei.png"></p>
                            <h3>已缴费</h3>
                        </div>
                        <div class="col-sm-6">
                            <h3 class="font-bold no-margins text-center">25笔</h3>
                            <h1 class="text-center">￥1,405.00</h1>
                            <div class="stat-percent font-bold text-info">查看详情>></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 mt-15">
                <div class="row">
                    <div class="col-sm-12 relativeBox">
                        <div class="data_show ibox-content" id="c_n_charts" style="height:380px;"></div>
                        <div class="payBox">
                            <p>本月应收：<span class="c-red">￥553,550</span></p>
                            <p>已完成：<span class="c-red">68%</span></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>待办任务</h5>
                </div>
                <div class="ibox-content maxh">
                    <table class="table table-hover no-margins">
                        <colgroup>
                            <col width="10%">
                            <col width="80%">
                            <col width="10%">
                        </colgroup>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td style="overflow:hidden;max-width:266px;"><span class="newline" title="【缴费名称】项目将于xxxx年xx月xx日过期，该项目存在待缴费记录，请前往处理！">【缴费名称】项目将于xxxx年xx月xx日过期，该项目存在待缴费记录，请前往处理！</span>
                            </td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>xxx缴费项目即将到期</td>
                            <td class="text-navy text-center">></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
<jsp:include page="common/commonFooterJS.jsp"></jsp:include>
<script src="<%=request.getContextPath() %>/static/js/content.min.js?v=1.0.0"></script>
<script src="<%=request.getContextPath() %>/static/js/echarts.min.js"></script>
<script>
    $(document).ready(function () {
        $(".contact-box").each(function () {
            animationHover(this, "pulse")
        })
    });
    var myChart = echarts.init(document.getElementById('c_n_charts'));
    option = {
        title: {
            text: '1月缴费汇总',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['未缴费', '已缴费']
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [
                    {value: 335, name: '未缴费', itemStyle: {color: '#f28033'}},
                    {value: 310, name: '已缴费', itemStyle: {color: '#4775c8'}}
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    },
                    normal: {
                        label: {
                            show: true,
                            formatter: '{b} : {c} ({d}%)'
                        }
                    }
                }
            }
        ]
    };
    myChart.setOption(option);
</script>
</body>
</html>
