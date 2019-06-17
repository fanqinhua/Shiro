/**
 * com.base.vo.SysRoleVO
 */
package com.base.pojo.basic;
import cn.rmt.framework.vo.BaseVO;

/**
 * <br>
 * <b>功能：</b>实体类<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@SuppressWarnings("serial")
public class SysRoleVO extends BaseVO {
	
    /**
     * 角色ID db_column: C_ID 
     */	
	private Integer id;
    /**
     * 角色名称 db_column: C_NAME 
     */	
	private String cname;
    /**
     * 描述 db_column: C_DES 
     */	
	private String cdes;
    /**
     * 创建时间 db_column: D_CREATE_TIME 
     */	
	private java.util.Date dcreateTime;
    /**
     * 操作人 db_column: I_OPERATOR 
     */	
	private String ioperator;
    /**
     * 更新时间 db_column: D_UPDATE_TIME 
     */	
	private java.util.Date dupdateTime;
    /**
     * 更新人 db_column: I_UPDATER 
     */	
	private String iupdater;
    /**
     * 角色状态：00：启用，01：信用 db_column: C_STATUS 
     */	
	private String cstatus;
    /**
     * ccode db_column: C_CODE 
     */	
	private String ccode;

	public SysRoleVO(){
	}

	public SysRoleVO( Integer id ){
		this.id = id;
	}

    public Integer getId() {
        return id;
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
	public String getIoperator() {
		return this.ioperator;
	}
	
	public void setIoperator(String ioperator) {
		this.ioperator = ioperator;
	}
	public java.util.Date getDupdateTime() {
		return this.dupdateTime;
	}
	
	public void setDupdateTime(java.util.Date dupdateTime) {
		this.dupdateTime = dupdateTime;
	}
	public String getIupdater() {
		return this.iupdater;
	}
	
	public void setIupdater(String iupdater) {
		this.iupdater = iupdater;
	}
	public String getCstatus() {
		return this.cstatus;
	}
	
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getCcode() {
		return this.ccode;
	}
	
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	
}

