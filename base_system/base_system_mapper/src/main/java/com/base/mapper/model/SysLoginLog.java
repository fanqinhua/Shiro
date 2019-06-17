/**
 * com.base.dao.ISysLoginLogDAO
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
public class SysLoginLog extends BaseEntity {
	
    /**
     * 编号 db_column: C_ID 
     */	
	private Integer id;
    /**
     * 用户编号 db_column: I_USER_ID 
     */	
	private Integer iuserId;
    /**
     * 用户登陆名称 db_column: C_USER_LOGIN_NAME 
     */	
	private String cuserLoginName;
    /**
     * 用户登陆IP db_column: C_HOST_IP 
     */	
	private String chostIp;
    /**
     * 登入时间 db_column: D_LOGIN_TIME 
     */	
	private java.util.Date dloginTime;
    /**
     * 登出时间 db_column: D_LOGOUT_TIME 
     */	
	private java.util.Date dlogoutTime;
    /**
     * 登陆结果：00：成功，01：失败 db_column: C_RESULT 
     */	
	private String cresult;

	public SysLoginLog(){
	}

	public SysLoginLog( Integer id ){
		this.id = id;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	public Integer getIuserId() {
		return this.iuserId;
	}
	
	public void setIuserId(Integer iuserId) {
		this.iuserId = iuserId;
	}
	public String getCuserLoginName() {
		return this.cuserLoginName;
	}
	
	public void setCuserLoginName(String cuserLoginName) {
		this.cuserLoginName = cuserLoginName;
	}
	public String getChostIp() {
		return this.chostIp;
	}
	
	public void setChostIp(String chostIp) {
		this.chostIp = chostIp;
	}
	public java.util.Date getDloginTime() {
		return this.dloginTime;
	}
	
	public void setDloginTime(java.util.Date dloginTime) {
		this.dloginTime = dloginTime;
	}
	public java.util.Date getDlogoutTime() {
		return this.dlogoutTime;
	}
	
	public void setDlogoutTime(java.util.Date dlogoutTime) {
		this.dlogoutTime = dlogoutTime;
	}
	public String getCresult() {
		return this.cresult;
	}
	
	public void setCresult(String cresult) {
		this.cresult = cresult;
	}
	
}

