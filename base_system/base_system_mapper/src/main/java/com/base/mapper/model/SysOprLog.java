/**
 * com.base.dao.ISysOprLogDAO
 */
package com.base.mapper.model;

import cn.rmt.framework.model.BaseEntity;

/**
 * <br>
 * <b>功能：</b>实体类<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@SuppressWarnings("serial")
public class SysOprLog extends BaseEntity {
	
    /**
     * 主键 db_column: C_ID 
     */	
	private Integer id;
    /**
     * 模块ID db_column: I_MODULE_ID 
     */	
	private Integer imoduleId;
    /**
     * 操作内容 db_column: C_OPR_DETAIL 
     */	
	private String coprDetail;
    /**
     * 操作时间 db_column: D_CREATE_TIME 
     */	
	private java.util.Date dcreateTime;
    /**
     * 用户ID db_column: I_USER_ID 
     */	
	private String iuserId;
    /**
     * 用户名 db_column: C_USER_NAME 
     */	
	private String cuserName;
    /**
     * 操作IP db_column: C_HOST_IP 
     */	
	private String chostIp;
    /**
     * 操作结果：00：成功，01：失败 db_column: C_RESULT 
     */	
	private String cresult;

	public SysOprLog(){
	}

	public SysOprLog( Integer id ){
		this.id = id;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	public Integer getImoduleId() {
		return this.imoduleId;
	}
	
	public void setImoduleId(Integer imoduleId) {
		this.imoduleId = imoduleId;
	}
	public String getCoprDetail() {
		return this.coprDetail;
	}
	
	public void setCoprDetail(String coprDetail) {
		this.coprDetail = coprDetail;
	}
	public java.util.Date getDcreateTime() {
		return this.dcreateTime;
	}
	
	public void setDcreateTime(java.util.Date dcreateTime) {
		this.dcreateTime = dcreateTime;
	}
	public String getIuserId() {
		return this.iuserId;
	}
	
	public void setIuserId(String iuserId) {
		this.iuserId = iuserId;
	}
	public String getCuserName() {
		return this.cuserName;
	}
	
	public void setCuserName(String cuserName) {
		this.cuserName = cuserName;
	}
	public String getChostIp() {
		return this.chostIp;
	}
	
	public void setChostIp(String chostIp) {
		this.chostIp = chostIp;
	}
	public String getCresult() {
		return this.cresult;
	}
	
	public void setCresult(String cresult) {
		this.cresult = cresult;
	}
	
}

