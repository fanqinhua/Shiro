/**
 * cn.rmt.admin.dao.ISysParamDAO
 */
package com.base.mapper.model;

import cn.rmt.framework.model.BaseEntity;

/**
 * <br>
 * <b>功能：</b>实体类<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@SuppressWarnings("serial")
public class SysParam extends BaseEntity{
	
    /**
     * 参数ID db_column: ID 
     */	
	private Integer id;
    /**
     * 系统参数分类ID db_column: I_PARAM_CATEGORY_ID 
     */	
	private Integer iparamCategoryId;
    /**
     * 参数名称 db_column: C_NAME 
     */	
	private String cname;
    /**
     * 代号 db_column: C_CODE 
     */	
	private String ccode;
    /**
     * 类型：01为运通架构，02为银行类型，03为其它企业类型 db_column: C_VALUE 
     */	
	private String cvalue;
    /**
     * 链接 db_column: C_URL 
     */	
	private String curl;
    /**
     * 图片绝对路径 db_column: C_PIC 
     */	
	private String cpic;
    /**
     * 状态：00：启用，01停用 db_column: C_STATUS 
     */	
	private String cstatus;
    /**
     * 描述 db_column: C_DES 
     */	
	private String cdes;
    /**
     * 创建时间 db_column: D_CREATE_TIME 
     */	
	private java.util.Date dcreateTime;
    /**
     * 更新时间 db_column: D_UPDATE_TIME 
     */	
	private java.util.Date dupdateTime;

	public SysParam(){
	}

	public SysParam( Integer id ){
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIparamCategoryId() {
		return this.iparamCategoryId;
	}
	
	public void setIparamCategoryId(Integer iparamCategoryId) {
		this.iparamCategoryId = iparamCategoryId;
	}
	public String getCname() {
		return this.cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCcode() {
		return this.ccode;
	}
	
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCvalue() {
		return this.cvalue;
	}
	
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}
	public String getCurl() {
		return this.curl;
	}
	
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public String getCpic() {
		return this.cpic;
	}
	
	public void setCpic(String cpic) {
		this.cpic = cpic;
	}
	public String getCstatus() {
		return this.cstatus;
	}
	
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getCdes() {
		return this.cdes;
	}
	
	public void setCdes(String cdes) {
		this.cdes = cdes;
	}
	public java.util.Date getDcreateTime() {
		return this.dcreateTime;
	}
	
	public void setDcreateTime(java.util.Date dcreateTime) {
		this.dcreateTime = dcreateTime;
	}
	public java.util.Date getDupdateTime() {
		return this.dupdateTime;
	}
	
	public void setDupdateTime(java.util.Date dupdateTime) {
		this.dupdateTime = dupdateTime;
	}
	
}

