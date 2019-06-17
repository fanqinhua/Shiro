$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	timeout : 30000,
	cache : false,
	beforeSend:function(XHR){
		if(this!=null&&this.showLoading==true){
				ajaxLoading();
		}
	},
	complete : function(XHR, TS) {
		if(this!=null&&this.showLoading==true){
			ajaxLoadEnd();
		}
		var resText = XHR.responseText;
		if(resText==undefined){
			return alert("请求超时!");
		}
		
		if (resText.indexOf("<form id=\"loginForm\"")!=-1) {
			alert("登录超时，请重新登录");
			if (navigator.userAgent.indexOf("MSIE 6.0") > -1) {
				window.opener = null;
				window.close();
				window.open(CTX+'/login/', '');
			} else {
				window.open(CTX+'/login/', '_top');
			}
		}
	}
});

function ajaxLoading(){
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});
 }
 function ajaxLoadEnd(){
     $(".datagrid-mask").remove();
     $(".datagrid-mask-msg").remove();            
}




var iconData = [ 
	 {value : '',	text : '默认'},
	 {value : 'icon-add',text : 'icon-add'},
	 {value : 'icon-edit',text : 'icon-edit'},
	 {value : 'icon-remove',text : 'icon-remove'},
	 {value : 'icon-save',text : 'icon-save'},
	 {value : 'icon-cut',	text : 'icon-cut'},
	 {value : 'icon-ok',	text : 'icon-ok'},
	 {value : 'icon-no',	text : 'icon-no'},
	 {value : 'icon-cancel',text : 'icon-cancel'}, 
	 {value : 'icon-reload',text : 'icon-reload'},
	 {value : 'icon-search',text : 'icon-search'},
	 {value : 'icon-print',text : 'icon-print'}, 
	 {value : 'icon-help',text : 'icon-help'},
	 {value : 'icon-undo',	text : 'icon-undo'},
	 {value : 'icon-redo',text : 'icon-redo'}, 
	 {value : 'icon-back',text : 'icon-back'	}, 
	 {value : 'icon-sum',text : 'icon-sum'},
	 {value : 'icon-tip',text : 'icon-tip'} ];
	
/**
 * dataGrid日期显示
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function dataGridDateFormatter(value,row,index){  
	if(value==null||value.length!=8){
		return value;
	}
	var y = value.substring(0,4);
    var m =  value.substring(4,6);
    var d = value.substring(6,8);

   
    return y+'年'+m+'月'+d+'日';
}

/**
 * 加载DataGrid
 * @param targetId DataGrid的ID
 * @param reqUrl 数据路径
 * @param singleSelect  true：单选，false：多选
 * @param frozenColumns 冰冻列
 * @param columns 列
 * @param toolbar 工具栏
 */
function loadDatagrid(targetId,reqUrl,singleSelect,frozenColumns,columns,toolbar) {
		$('#'+targetId).datagrid({
			url : reqUrl,
			fit : true,
			fitColumns : false,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			singleSelect : singleSelect,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'text',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			frozenColumns : frozenColumns,
			columns : columns,
			toolbar : toolbar
		});
	};
	
	function loadDatagrid(targetId,reqUrl,singleSelect,frozenColumns,columns,toolbar,queryParams) {
		$('#'+targetId).datagrid({
			url : reqUrl,
			fit : true,
			fitColumns : false,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			singleSelect : singleSelect,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'text',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			frozenColumns : frozenColumns,
			columns : columns,
			toolbar : toolbar,
			queryParams:queryParams
		});
	};
	

/**
 * 新增或修改数据的弹出窗口 dataGrid类型
 * @param targetId dataGrid的Id
 * @param pageUrl 弹出窗口页面路径
 * @param width 宽
 * @param height 高 
 * @param winTitle 弹出框标题
 */
function dataGridBtnWindowFunction(targetId,pageUrl,width,height,winTitle) {
	btnWindowFunction('dataGrid',targetId,pageUrl,width,height,winTitle);
}

/**
 * 新增或修改数据的弹出窗口 treeGrid类型
 * @param targetId dataGrid的Id
 * @param pageUrl 弹出窗口页面路径
 * @param width 宽
 * @param height 高 
 * @param winTitle 弹出框标题
 */
function treeGridBtnWindowFunction(targetId,pageUrl,width,height,winTitle) {

	btnWindowFunction('treeGrid',targetId,pageUrl,width,height,winTitle);
}


function btnWindowFunction(type,targetId,pageUrl,width,height,winTitle) {

	//var default_left=0;
	//var default_top=0;
/*	if(type=='dataGrid'){
		$('#'+targetId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	}else if(type=='treeGrid'){
		$('#'+targetId).treegrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	}*/
	var d = $('<div/>').dialog({
	//$('<div/>', window.parent.document).dialog({
/*	var dig = window.top.$('<div/>').appendTo(window.top.document.body);
	dig.dialog({*/
		href : pageUrl,
		width : width,
		height : height,
		modal : true,
		title : winTitle,
		top:0,
		draggable:false,
		buttons : [ {
			text :'确定',
			//iconCls : 'icon-save',
			handler : function() {
				$("#sync").trigger('click');
			//	var d = $(this).closest('.window-body');
/*			$('#windowForm').form('submit', {
					dataType : 'json',
					success : function(result) {
						var r = $.parseJSON(result);
						if(type=='dataGrid'){
							$('#'+targetId).datagrid('reload');
						}else if(type=='treeGrid'){
							$('#'+targetId).treegrid('reload');
						}
						$.messager.alert('提示', r.msg);
						d.dialog('destroy');
					}
				});*/
				var flag=$("#windowForm").form('validate');
				
				if(flag==true){
				  	var params = $('#windowForm').serializeArray();
				  	var processDataVal = true;
				    var contentTypeVal = true;
				  	if ($('#windowForm').attr("enctype")) {
				  		params = new FormData(document.getElementById("windowForm"));
				  		$.ajax({
							  type: "POST",
							  url: $("#windowForm").attr("action"),
							  processData: false,
						      contentType: false,
							  data: params,
						      success: function (data) {
						    	  
							       $.messager.alert('提示', data.msg);
							       if(data.closed==true){
							    	   d.dialog('destroy');
							       }
							      
									if(type=='dataGrid'){
										$('#'+targetId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
										$('#'+targetId).datagrid('reload');
									}else if(type=='treeGrid'){
										$('#'+targetId).treegrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
										$('#'+targetId).treegrid('reload');
									}
						      } 
						}); 
					}

					$.ajax({
						  type: "POST",
						  url: $("#windowForm").attr("action"),
						  data: params,
					      success: function (data) { 
					       $.messager.alert('提示', data.msg);
					       if(data.closed==true){
					       d.dialog('destroy');
					       }
							if(type=='dataGrid'){
								$('#'+targetId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
								$('#'+targetId).datagrid('reload');
								//isSelect=-1;
							}else if(type=='treeGrid'){
								$('#'+targetId).treegrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
								$('#'+targetId).treegrid('reload');
								//isSelect=-1;
							}
					      } 
					});   
				}		
			}
		} ],
		onClose : function() {
			$(this).dialog('destroy');
		}//,
/*	    onOpen:function(){ 	
	    	//dialog原始left
	 		 default_left=$(this).panel('options').left; 
	 		//dialog原始top
	 		 default_top=$(this).panel('options').top;   	   
	    },
	    onMove:function(left,top){
			   var body_width=document.documentElement.clientWidth;
			   var body_height=document.documentElement.clientHeight;
			   var dd_width= $(this).panel('options').width;//dialog的宽度
			   var dd_height= $(this).panel('options').height;//dialog的高度		
			   if(left<1||left>(body_width-100)||top<1||top>(body_height-50)){
				   $(this).dialog('move',{left:default_left,top:default_top});
			   }
	    }*/
	});
}
/**
 * 新增或修改数据的弹出窗口 treeGrid类型
 * @param targetId dataGrid的Id
 * @param pageUrl 弹出窗口页面路径

 * @param width 宽
 * @param height 高 
 * @param winTitle 弹出框标题
 */
function treeGridWindowFunction(targetId,pageUrl,width,height,winTitle) {
	windowFunction('treeGrid',targetId,pageUrl,width,height,winTitle);
}
/**
 * 新增或修改数据的弹出窗口 dataGrid类型
 * @param targetId dataGrid的Id
 * @param pageUrl 弹出窗口页面路径
 * @param width 宽
 * @param height 高 
 * @param winTitle 弹出框标题
 */
function dataGridWindowFunction(targetId,pageUrl,width,height,winTitle) {
	windowFunction('dataGrid',targetId,pageUrl,width,height,winTitle);
}
function windowFunction(type,targetId,pageUrl,width,height,winTitle) {
	if($('#'+targetId)){
		if(type=='dataGrid'){
			$('#'+targetId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		}else if(type=='treeGrid'){
			$('#'+targetId).treegrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		}
	}
	
	$('<div/>').dialog({
		href : pageUrl,
		width : width,
		height : height,
		modal : true,
		title : winTitle,
	
		onClose : function() {
			$(this).dialog('destroy');
		}
	});
}


//加载TreeGrid
function loadTreeGrid(targetId,reqUrl,idField,treeField,parentField,singleSelect,columns,toolbar){
	$('#'+targetId).treegrid({
		url : reqUrl,
		idField :idField,
		treeField : treeField,
		parentField :parentField,
		fit : true,
		fitColumns : true,
		border : false,
		columns : columns,
		toolbar :toolbar
	});
}
/**
 *  treeGrid的公共按钮json
 * @param targetId
 * @returns {Array}
 */
function getTreeGridToolsBar(targetId){
	var toolbar =  [
	   	       {text : '展开',	iconCls : 'icon-redo',	handler : function() {	var node = $('#'+targetId).treegrid('getSelected');
	   					if (node) {
	   						$('#'+targetId).treegrid('expandAll', node.cid);
	   					} else {
	   						$('#'+targetId).treegrid('expandAll');
	   					}
	   				}
	   			},  {
	   				text : '折叠',
	   				iconCls : 'icon-undo',
	   				handler : function() {
	   					var node = $('#'+targetId).treegrid('getSelected');
	   					if (node) {
	   						$('#'+targetId).treegrid('collapseAll', node.cid);
	   					} else {
	   						$('#'+targetId).treegrid('collapseAll');
	   					}
	   				}
	   			},  {
	   				text : '刷新',
	   				iconCls : 'icon-reload',
	   				handler : function() {
	   					$('#'+targetId).treegrid('reload');
	   				}
	   			} ];
	return toolbar;
}

/**
 * 到处报表
 * @param datagridId
 * @param reportName
 * @param reportType
 */
function exportReport(datagridId, reportName, reportType) {
	var datagridUrl = $('#' + datagridId).datagrid('options').url
	var queryParams = $('#' + datagridId).datagrid('options').queryParams;
	var method = $('#' + datagridId).datagrid('options').method;
	var columns = $('#' + datagridId).datagrid('options').columns;
	var columnsStr = JSON.stringify(columns);
	var formId = new Date().getMilliseconds() + ''
			+ parseInt(Math.random() * 1000000);
	var form = $("<form>");//定义一个form表单
	form.attr("id", formId);
	form.attr("style", "display:none");
	form.attr("target", "");
	form.attr("method", method);
	form.attr("action", datagridUrl);
	var input1 = $("<input>");
	input1.attr("type", "hidden");
	input1.attr("name", "exportData");
	input1.attr("value", (new Date()).getMilliseconds());
	var input2 = $("<input>");
	input2.attr("type", "hidden");
	input2.attr("name", "_exportReport");
	input2.attr("value", 'true');
	var input3 = $("<input>");
	input3.attr("type", "hidden");
	input3.attr("name", "columns");
	input3.attr("value", columnsStr);
	var input4 = $("<input>");
	input4.attr("type", "hidden");
	input4.attr("name", "reportType");
	input4.attr("value", reportType);
	var input5 = $("<input>");
	input5.attr("type", "hidden");
	input5.attr("name", "reportName");
	input5.attr("value", reportName);
	$("body").append(form);//将表单放置在web中
	form.append(input1);
	form.append(input2);
	form.append(input3);
	form.append(input4);
	form.append(input5);
	addParamsToForm(formId, queryParams);
	form.submit();//表单提交  
}

function addParamsToForm(formId, params) {
	$.each(params, function(i, n) {
		var input1 = $("<input>");
		input1.attr("type", "hidden");
		input1.attr("name", i);
		input1.attr("value", n);
		$("#" + formId).append(input1);
	});
}

function unfold(){
	var node = $('#'+targetId).treegrid('getSelected');
   					if (node) {
   						$('#'+targetId).treegrid('expandAll', node.cid);
   					} else {
   						$('#'+targetId).treegrid('expandAll');
   					}
}

function fold(){
	var node = $('#'+targetId).treegrid('getSelected');
		if (node) {
			$('#'+targetId).treegrid('collapseAll', node.cid);
		} else {
			$('#'+targetId).treegrid('collapseAll');
		}
}

function refresh(){
	$('#'+targetId).treegrid('reload');
}

function getRootPath(){
    var strPath = window.document.location.pathname;
    var rootPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    return rootPath;
}

function hasPermission(permission){
    var rootPath=getRootPath();
    var returnFlag;
	$.ajax({
		  type: "POST",
		  url: rootPath+"/permission/hasPermission",
		  data: "permission="+permission,
		  async: false,
	      success: function (data) { 
	    	  returnFlag=data;
	      } 
		}); 
	return returnFlag;
}

function hasAnyPermissions(permissions){
    var rootPath=getRootPath();
    var returnFlag;
	$.ajax({
		  type: "POST",
		  url: rootPath+"/permission/hasAnyPermissions",
		  data: "permissions="+permissions,
		  async: false,
	      success: function (data) { 
	    	  returnFlag=data;
	      } 
		}); 
	return returnFlag;
}

function hasAllPermissions(permissions){
    var rootPath=getRootPath();
    var returnFlag;
	$.ajax({
		  type: "POST",
		  url: rootPath+"/permission/hasAllPermissions",
		  data: "permissions="+permissions,
		  async: false,
	      success: function (data) { 
	    	  returnFlag=data;
	      } 
		}); 
	return returnFlag;
}	

function hasRole(role){
    var rootPath=getRootPath();
    var returnFlag;
	$.ajax({
		  type: "POST",
		  url: rootPath+"/permission/hasRole",
		  data: "role="+role,
		  async: false,
	      success: function (data) { 
	    	  returnFlag=data;
	      } 
		}); 
	return returnFlag;
}

function hasAnyRoles(roles){
    var rootPath=getRootPath();
    var returnFlag;
	$.ajax({
		  type: "POST",
		  url: rootPath+"/permission/hasAnyRoles",
		  data: "roles="+roles,
		  async: false,
	      success: function (data) { 
	    	  returnFlag=data;
	      } 
		}); 
	return returnFlag;
}

function authenticated(){
    var rootPath=getRootPath();
    var returnFlag;
	$.ajax({
		  type: "POST",
		  url: rootPath+"/permission/authenticated",
		  async: false,
	      success: function (data) { 
	    	  returnFlag=data;
	      } 
		}); 
	return returnFlag;
}

function user(){
    var rootPath=getRootPath();
    var returnFlag;
	$.ajax({
		  type: "POST",
		  url: rootPath+"/permission/user",
		  async: false,
	      success: function (data) { 
	    	  returnFlag=data;
	      } 
		}); 
	return returnFlag;
}

function getParmFromParmString(parmString,key){
    var result = undefined;
	if(jQuery.type(parmString) === "string"){
	var arr = parmString.split('&');
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].split('=')[0] === key) {
            if (!result) {
                result = arr[i].split('=')[1];
            } else {
                result = [result].concat(arr[i].split('=')[1]);
            }
        }
    }
	}
    return result;	
}




function openDialogFn(pageUrl,width,height,winTitle) {
	$('<div/>').dialog({
		top: 0,
		draggable :false,
	    id : 'targetId',
		href : pageUrl,
		width : width,
		height : height,
		modal : true,
		closed : false,
		shadow : true,
		cache: false, 
		title : winTitle,
		onClose : function() {
			$(this).dialog('destroy');
		},onMove: function(left,top) {
			 
		}
	});
}


function openDialogFn2(id,pageUrl,width,height,winTitle) {
	$('<div/>').dialog({
		top: 0,
		draggable :false,
	    id : id,
		href : pageUrl,
		width : width,
		height : height,
		modal : true,
		closed : false,
		shadow : true,
		cache: false, 
		title : winTitle,
		onClose : function() {
			$(this).dialog('destroy');
		}
	});

}


function fileDownLoad(url ,data){
	
	 $.fileDownload(url , {
		// preparingMessageHtml: "We are preparing your report, please wait...",
	      
		 	httpMethod: "POST",
	       
			data:data,
	        
           successCallback: function (data) {

           	$preparingDialog.dialog('close');
           },
           failCallback: function (responseHtml, data) {

                //  alert("hellooooo");
               
           }
       });
};










function btnWindowFunction4(type,targetId,pageUrl,width,height,winTitle,callOther) {
	
	var d = $('<div/>').dialog({

		href : pageUrl,
		width : width,
		height : height,
		modal : true,
		title : winTitle,
		top:0,
		draggable:false,
		buttons : [ {
			text :'确定',
			//iconCls : 'icon-save',
			handler : function() {
				
				//var d = $(this).closest('.window-body');

				var flag=$("#windowForm").form('validate');
				
				if(flag==true){
				  	var params = $('#windowForm').serializeArray();
				  	var processDataVal = true;
				    var contentTypeVal = true;
					$.ajax({
						  type: "POST",
						  url: $("#windowForm").attr("action"),
						  data: params,
					      success: function (data) {
							   $.messager.alert('提示', data.msg);
							   if(data.closed==true){
									d.dialog('destroy');
							   }
								if(type=='dataGrid'){
									$('#'+targetId).datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
									$('#'+targetId).datagrid('reload');
								}else if(type=='treeGrid'){
									$('#'+targetId).treegrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
									$('#'+targetId).treegrid('reload');
									
								}else{
									callOther();
								}
					      } 
					});   
				}		
			}
		} ],
		onClose : function() {
			$(this).dialog('destroy');
		}

	});
}

