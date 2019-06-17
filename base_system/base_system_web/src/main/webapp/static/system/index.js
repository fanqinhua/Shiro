jQuery(document).ready(function ($) {
    $(".sidebar-collapse li a").click(function (event) {
        $(".sidebar-collapse li a").removeClass('navLeft');
        $(this).addClass('navLeft');
    });
});


//显示当前时间
var weeks = ["日", "一", "二", "三", "四", "五", "六"];

function showTime() {
    var date = new Date();
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();
    var w = date.getDay();
    var h = date.getHours();
    var mi = date.getMinutes();
    var s = date.getSeconds();
    var ms = m < 10 ? "0" + m : m;
    var ds = d < 10 ? "0" + d : d;
    var hs = h < 10 ? "0" + h : h;
    var mis = mi < 10 ? "0" + mi : mi;
    var ss = s < 10 ? "0" + s : s;
    var time = y + "年" + ms + "月" + ds + "日  " + hs + ":" + mis + ":" + ss + "  星期" + weeks[w];
    document.getElementById("div_time").innerText = time;
    document.getElementById("div_time").style.color = "#ffffff";
    document.getElementById("div_time").style.fontSize = "14px";
    document.getElementById("div_time").style.lineHeight = "60px";
    document.getElementById("div_time").style.marginLeft = "20px";
    setTimeout("showTime()", 1000);
}

function exit() {
    parent.layer.confirm('您确定要退出？', {
        title: "退出",
        btn: ['确定', '取消'], //按钮
        shade: false //不显示遮罩
    }, function () {
        window.location.href = ctx+'/logout';
    })
}

/*校验密码*/
$("#retype").blur(function (event) {
    var pw = $("#password").val();
    var retype = $("#retype").val();
    if (pw != retype) {
        $(".retype_txt").show();
        $(".sucess_icon").hide();
        $(".pwTip3").hide();
    } else if (pw != "") {
        $(".retype_txt").hide();
        $(".sucess_icon").show();
    }
    ;
});

function changeBtn() {
    $(".iptBox .row").each(function (index, el) {
        if ($(this).find('input').val() == "") {
            $(this).find('.pwTip').show();
        } else {
            $(this).find('.pwTip').hide();
        }
    });
}

$(".pw_ipt").blur(function (event) {
    if ($(this).val() != "") {
        $(this).siblings('.pwTip').hide();
    }
    ;
});

$(document).on('click', '.page-tabs-content a', function(event) {
    var thisIndex = $(this).index();
    $(".J_mainContent iframe").eq(thisIndex).show().siblings('iframe').hide()
});

// $(document).on({
//     click : function() { reSearch(this); }
// }, '.J_menuTab');
//
// // $(document).on({
// //     click : function() { reSearch(); }
// // }, '.leftLi');
//
// function reSearch(obj){
//     var href = $(obj).attr("href");
//     var dataId = $(obj).attr("data-id");
//
//     $(".J_menuTab").each(function(index){
//         var url = $(this).attr("data-id");
//         // console.log("href:"+href+",data-id:"+dataId+"url:"+url);
//         if(url==href||url==dataId){
//             callIframeSearch(index);
//         }
//     });
// }
//
// function callIframeSearch(index){
//     console.log("index:"+index);
//     var iframe = document.getElementsByName('iframe0')[index].contentWindow;
//     iframe.search();
//     //console.log("index22:"+$(iframe).find(".fa-search").parent());
//     //$(iframe).find(".fa-search").parent().click();
// }