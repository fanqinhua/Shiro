
/*封装jquery.toast几个常用的方法*/
var wToast = {
    loading: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'info',
            text: text || '加载中...',
            icon: 'info',
            stack: 1,
            position: 'top-center',
            hideAfter: time || 10000
        })
    },
    warning: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'warning',
            text: text,
            stack: 1,
            icon: 'warning',
            position: 'top-center',
            hideAfter: time || 3000
        })
    },
    error: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'error',
            text: text,
            icon: 'error',
            stack: 1,
            position: 'top-center',
            hideAfter: time || 3000
        })
    },
    info: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'info',
            text: text,
            stack: 1,
            icon: 'info',
            position: 'top-center',
            hideAfter: time || 3000
        })
    },
    success: function (text, time) {
        $.toast({
            loader: time ? true : false,
            heading: 'success',
            text: text,
            stack: 1,
            icon: 'success',
            position: 'top-center',
            hideAfter: time || 3000
        })
    },
}
var api = {
    downloadQrCode:function (data,fileName,callback) {
        console.log('download');

		var form=$("<form>");//定义一个form表单
		form.attr("style","display:none");
		form.attr("target","");
		form.attr("method","post");
		form.attr("action",apiPath + '/vender/device/qrcodeDown');//请求url
		var input1=$("<input>");
		input1.attr("type","hidden");
		input1.attr("name","qrCode");//设置属性的名字
		input1.attr("value",data);//设置属性的值
		
		var input2=$("<input>");
		input2.attr("type","hidden");
		input2.attr("name","fileName");//设置属性的名字
		input2.attr("value",fileName);//设置属性的值
		
		$("body").append(form);//将表单放置在web中
		form.append(input1).append(input2);
		form.submit();//表单提交       
  
    },
	getTodayData : function(callback) { // 今天销量
		common.ajax({
			url : apiPath + '/vender/homePage/todayData',
			type : 'get',
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},

	getYesterDayData : function(callback) { // 昨日销量
		common.ajax({
			url : apiPath + '/vender/homePage/yesterdayData',
			type : 'get',
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},
	
	
	getThisMonthCountData : function(callback) { // 本月销量
		common.ajax({
			url : apiPath + '/vender/homePage/thisMonthCount',
			type : 'get',
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},
	
	
	getLastMonthCountData : function(callback) { // 上月销量
		common.ajax({
			url : apiPath + '/vender/homePage/lastMonthCount',
			type : 'get',
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},

	getThisMonthData : function(callback) { // 本月销量
		common.ajax({
			url : apiPath + '/vender/homePage/thisMonthData',
			type : 'get',
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},
	getThisYearData : function(callback) { // 今年销量
		common.ajax({
			url : apiPath + '/vender/homePage/thisYearData',
			type : 'get',
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},
	
	getIsYunyingData : function(callback) { //判断后台角色
		common.ajax({
			url : apiPath + '/vender/homePage/isYunying',
			type : 'get',
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},
	
	getDeviceOOSData : function(callback){
		common.ajax({
			url:apiPath+'/vender/homePage/isOOS',
			type:'get',
			success:function(res){
				if(callback){
					callback(res);
				}
			}
		})
	},
	deleteRevelationMenu : function(id, callback) { // 菜单管理删除接口
		common.ajax({
			url : apiPath + '/menuMan',
			type : 'delete',
			data : JSON.stringify({
				id : id || ''
			}),
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},
	postRevelationMenu : function(data, callback) {// 菜单管理新增/编辑接口
		common.ajax({
			url : apiPath + '/menuMan',
			type : 'post',
			data : data || {},
			success : function(res) {
				if (callback) {
					callback(res);
				}
			}
		})
	},
	logout : function(obj) {
		console.log('logout');
		common.ajax({
			url : apiPath + '/logout',
			type : 'post'
		})
	},
	login : function(obj) {

		wToast.loading('登陆中...',1000);
		$.ajax({
			url : ctx + '/login',
			type : 'post',
			data : {
				username : obj.data && obj.data.username ? obj.data.username
						: '',
				captcha : obj.data && obj.data.captcha ? obj.data.captcha
						: '',
				// password : obj.data && obj.data.password ? common
				// 		.passwordEncrypt(obj.data.password) : ''
                password : obj.data && obj.data.password ? common
                		.passwordEncryptByAes(obj.data.password) : ''
			},
            dataType: 'json',
			success : function(res) {
				setTimeout(function() {
					if (res.result) {
						wToast.success(res.msg);
						setTimeout(function() {
							obj.success();
						}, 500)
					} else {
						wToast.error(res.msg);
                        setTimeout(function() {
                            obj.error();
                        }, 500)
					}
				}, 300);
			}
		})
	},
	getMenuList : function(obj) { // 获取商品列表

		/*
		 * var JSESSIONID=sessionStorage.getItem('JSESSIONID')
		 * document.cookie="JSESSIONID="+JSESSIONID;
		 */

		common.ajax({
			url : apiPath + '/revelationMenu',
			type : 'get',
			data : {
				rows : 10000,
				page : 1
			},
			success : function(res) {
				if (obj.success) {
					obj.success(res);
				}
			}
		})
	}
}
