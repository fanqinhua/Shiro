/**
 * com.base.dao.ISysUserDAO
 */
package com.base.mapper.model;

import cn.rmt.framework.model.BaseEntity;

import java.util.Date;

/**
 * <br>
 * <b>功能：</b>实体类<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@SuppressWarnings("serial")
public class SysUser extends BaseEntity {
	
    /**
     * 编号 db_column: C_ID 
     */	
	private Integer id;
    /**
     * 登录名 db_column: C_LOGIN_NAME 
     */	
	private String cloginName;
    /**
     * 密码 db_column: C_PASSWORD 
     */	
	private String cpassword;
    /**
     * 用户姓名 db_column: C_NAME 
     */	
	private String cname;
    /**
     * 密码盐 db_column: C_SALT 
     */	
	private String csalt;
    /**
     * 电话 db_column: C_PHONE 
     */	
	private String cphone;
    /**
     * 邮箱 db_column: C_EMAIL 
     */	
	private String cemail;
    /**
     * 地址 db_column: C_ADDRESS 
     */	
	private String caddress;
    /**
     * 登陆IP db_column: C_IP 
     */	
	private String cip;
    /**
     * 上次登陆时间 db_column: D_LAST_IN_TIME 
     */	
	private java.util.Date dlastInTime;
    /**
     * 上次登出时间 db_column: D_LAST_OUT_TIME 
     */	
	private java.util.Date dlastOutTime;
    /**
     * 新增时间 db_column: D_CREATE_TIME 
     */	
	private java.util.Date dcreateTime;
    /**
     * 新增操作员id db_column: I_CREATEOR_ID 
     */	
	private Integer icreateorId;
    /**
     * 更新时间 db_column: D_UPDATE_TIME 
     */	
	private java.util.Date dupdateTime;
    /**
     * 更新操作员id db_column: I_UPDATER_ID 
     */	
	private Integer iupdaterId;
    /**
     * 用户状态：00：启用，01：未启用,02：冻结登陆 db_column: C_STATUS 
     */	
	private String cstatus;
    /**
     * 身份证号 db_column: C_IDENTITY_NO 
     */	
	private String cidentityNo;
    /**
     * 性别 db_column: C_SEX 
     */	
	private String csex;
    /**
     * 机构 db_column: C_ORG_ID 
     */	
	private Integer corgId;
    /**
     * cremark db_column: C_REMARK 
     */	
	private String cremark;
    /**
     * 角色 db_column: C_ROLE_ID 
     */	
	private Integer croleId;
    /**
     * ctoken db_column: C_TOKEN 
     */	
	private String ctoken;
    /**
     * 用户活动状态:0离线,1在线,2,繁忙 db_column: C_ACTIVE 
     */	
	private String cactive;

	/**
	 * 错误登陆次数
	 */
	private Integer ierrloginTimes;

	/**
	 * 错误登陆时间
	 */
	private Date ierrLoginTime;

	public SysUser(){
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	
	public void setCid(Integer id) {
		this.id = id;
	}
	public String getCloginName() {
		return this.cloginName;
	}
	
	public void setCloginName(String cloginName) {
		this.cloginName = cloginName;
	}
	public String getCpassword() {
		return this.cpassword;
	}
	
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	public String getCname() {
		return this.cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCsalt() {
		return this.csalt;
	}
	
	public void setCsalt(String csalt) {
		this.csalt = csalt;
	}
	public String getCphone() {
		return this.cphone;
	}
	
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCemail() {
		return this.cemail;
	}
	
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getCaddress() {
		return this.caddress;
	}
	
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public String getCip() {
		return this.cip;
	}
	
	public void setCip(String cip) {
		this.cip = cip;
	}
	public java.util.Date getDlastInTime() {
		return this.dlastInTime;
	}
	
	public void setDlastInTime(java.util.Date dlastInTime) {
		this.dlastInTime = dlastInTime;
	}
	public java.util.Date getDlastOutTime() {
		return this.dlastOutTime;
	}
	
	public void setDlastOutTime(java.util.Date dlastOutTime) {
		this.dlastOutTime = dlastOutTime;
	}
	public java.util.Date getDcreateTime() {
		return this.dcreateTime;
	}
	
	public void setDcreateTime(java.util.Date dcreateTime) {
		this.dcreateTime = dcreateTime;
	}
	public Integer getIcreateorId() {
		return this.icreateorId;
	}
	
	public void setIcreateorId(Integer icreateorId) {
		this.icreateorId = icreateorId;
	}
	public java.util.Date getDupdateTime() {
		return this.dupdateTime;
	}
	
	public void setDupdateTime(java.util.Date dupdateTime) {
		this.dupdateTime = dupdateTime;
	}
	public Integer getIupdaterId() {
		return this.iupdaterId;
	}
	
	public void setIupdaterId(Integer iupdaterId) {
		this.iupdaterId = iupdaterId;
	}
	public String getCstatus() {
		return this.cstatus;
	}
	
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}

	public String getCidentityNo() {
		return cidentityNo;
	}

	public void setCidentityNo(String cidentityNo) {
		this.cidentityNo = cidentityNo;
	}

	public String getCsex() {
		return this.csex;
	}
	
	public void setCsex(String csex) {
		this.csex = csex;
	}
	public Integer getCorgId() {
		return this.corgId;
	}
	
	public void setCorgId(Integer corgId) {
		this.corgId = corgId;
	}
	public String getCremark() {
		return this.cremark;
	}
	
	public void setCremark(String cremark) {
		this.cremark = cremark;
	}
	public Integer getCroleId() {
		return this.croleId;
	}
	
	public void setCroleId(Integer croleId) {
		this.croleId = croleId;
	}
	public String getCtoken() {
		return this.ctoken;
	}
	
	public void setCtoken(String ctoken) {
		this.ctoken = ctoken;
	}
	public String getCactive() {
		return this.cactive;
	}
	
	public void setCactive(String cactive) {
		this.cactive = cactive;
	}

	public Integer getIerrloginTimes() {
		return ierrloginTimes;
	}

	public void setIerrloginTimes(Integer ierrloginTimes) {
		this.ierrloginTimes = ierrloginTimes;
	}

	public Date getIerrLoginTime() {
		return ierrLoginTime;
	}

	public void setIerrLoginTime(Date ierrLoginTime) {
		this.ierrLoginTime = ierrLoginTime;
	}
}

