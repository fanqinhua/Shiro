/**
 * com.base.vo.SysRoleQueryParam
 */
package com.base.pojo.basic;


import cn.rmt.framework.vo.QueryParam;

/**
 * <br>
 * <b>功能：</b>查询参数<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
public class SysRoleQueryParam extends QueryParam {
	
	private SysRoleVO sysRole;

	public SysRoleVO getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRoleVO sysRole) {
		super.setParamBean(sysRole);
		this.sysRole = sysRole;
	}
	
}