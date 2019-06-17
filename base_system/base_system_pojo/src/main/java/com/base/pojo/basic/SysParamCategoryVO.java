/**
 * cn.rmt.admin.vo.SysParamCategoryVO
 */
package com.base.pojo.basic;

import  cn.rmt.framework.vo.BaseVO;

/**
 * <br>
 * <b>功能：</b>实体类<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@SuppressWarnings("serial")
public class SysParamCategoryVO extends BaseVO{
	
    /**
     * 参数分类ID db_column: ID 
     */	
	private Integer id;
    /**
     * 父类目ID=0时，代表的是一级的类目 db_column: I_PID 
     */	
	private Integer ipid;
	/**
	 * 所属机构 db_column :C_ORG_ID
	 */
	private Integer corgId;
    /**
     * 分类名称 db_column: C_NAME 
     */	
	private String cname;
    /**
     * 状态。可选值:00:启用,01:停用 db_column: C_STATUS 
     */	
	private String cstatus;
    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数 db_column: C_ORDER 
     */	
	private String corder;
    /**
     * 该类目是否为父类目，1为true，0为false db_column: C_IS_PARENT 
     */	
	private Boolean cisParent;
    /**
     * 创建时间 db_column: D_CREATE_TIME 
     */	
	private java.util.Date dcreateTime;
    /**
     * 更新时间 db_column: D_UPDATE_TIME 
     */	
	private java.util.Date dupdateTime;

	private String sysOrgVOName;

	public SysParamCategoryVO(){
	}

	public SysParamCategoryVO( Integer id ){
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIpid() {
		return this.ipid;
	}
	
	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}
	public String getCname() {
		return this.cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getCorgId() {
		return corgId;
	}

	public void setCorgId(Integer corgId) {
		this.corgId = corgId;
	}

	public String getCstatus() {
		return this.cstatus;
	}
	
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getCorder() {
		return this.corder;
	}
	
	public void setCorder(String corder) {
		this.corder = corder;
	}
	public Boolean getCisParent() {
		return this.cisParent;
	}
	
	public void setCisParent(Boolean cisParent) {
		this.cisParent = cisParent;
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

	public String getSysOrgVOName() {
		return sysOrgVOName;
	}

	public void setSysOrgVOName(String sysOrgVOName) {
		this.sysOrgVOName = sysOrgVOName;
	}
}

