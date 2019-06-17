/**
 * cn.rmt.admin.dao.ISysRegionDAO
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
public class SysRegion extends BaseEntity{
	
    /**
     * 编号 db_column: ID 
     */	
	private Integer id;
    /**
     * 名称 db_column: C_NAME 
     */	
	private String cname;
    /**
     * 编码 db_column: C_REGION_CODE 
     */	
	private String cregionCode;
    /**
     * 级别 db_column: I_LEVEL_ID 
     */	
	private Long ilevelId;
    /**
     * 父编码 db_column: C_PCODE 
     */	
	private String cpcode;
    /**
     * 排序 db_column: I_ORDER 
     */	
	private Long iorder;
    /**
     * 状态：00：为启用，01停用 db_column: C_STATUS 
     */	
	private String cstatus;
    /**
     * 描述 db_column: C_DESC 
     */	
	private String cdesc;

	public SysRegion(){
	}

	public SysRegion( Integer id ){
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCname() {
		return this.cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCregionCode() {
		return this.cregionCode;
	}
	
	public void setCregionCode(String cregionCode) {
		this.cregionCode = cregionCode;
	}
	public Long getIlevelId() {
		return this.ilevelId;
	}
	
	public void setIlevelId(Long ilevelId) {
		this.ilevelId = ilevelId;
	}
	public String getCpcode() {
		return this.cpcode;
	}
	
	public void setCpcode(String cpcode) {
		this.cpcode = cpcode;
	}
	public Long getIorder() {
		return this.iorder;
	}
	
	public void setIorder(Long iorder) {
		this.iorder = iorder;
	}
	public String getCstatus() {
		return this.cstatus;
	}
	
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getCdesc() {
		return this.cdesc;
	}
	
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	
}

