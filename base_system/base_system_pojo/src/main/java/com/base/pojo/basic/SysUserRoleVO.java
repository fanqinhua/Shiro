/**
 * com.base.vo.SysUserRoleVO
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
public class SysUserRoleVO extends BaseVO {
	
    /**
     * id db_column: C_ID 
     */	
	private Integer id;
    /**
     * 用户ID db_column: I_USER_ID 
     */	
	private Integer iuserId;
    /**
     * 角色ID db_column: I_ROLE_ID 
     */	
	private Integer iroleId;

	public SysUserRoleVO(){
	}

	public SysUserRoleVO( Integer id ){
		this.id = id;
	}

	public Integer getCid() {
		return this.id;
	}
	
	public void setCid(Integer id) {
		this.id = id;
	}
	public Integer getIuserId() {
		return this.iuserId;
	}
	
	public void setIuserId(Integer iuserId) {
		this.iuserId = iuserId;
	}
	public Integer getIroleId() {
		return this.iroleId;
	}
	
	public void setIroleId(Integer iroleId) {
		this.iroleId = iroleId;
	}
	
}

