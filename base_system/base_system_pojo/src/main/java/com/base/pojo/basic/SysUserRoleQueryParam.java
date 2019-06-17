/**
 * com.base.vo.SysUserRoleQueryParam
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
public class SysUserRoleQueryParam extends QueryParam {
	
	private SysUserRoleVO sysUserRole;

	public SysUserRoleVO getSysUserRole() {
		return sysUserRole;
	}

	public void setSysUserRole(SysUserRoleVO sysUserRole) {
		super.setParamBean(sysUserRole);
		this.sysUserRole = sysUserRole;
	}
	
}