/**
 * com.base.vo.SysRoleModuleQueryParam
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
public class SysRoleModuleQueryParam extends QueryParam {
	
	private SysRoleModuleVO sysRoleModule;

	public SysRoleModuleVO getSysRoleModule() {
		return sysRoleModule;
	}

	public void setSysRoleModule(SysRoleModuleVO sysRoleModule) {
		super.setParamBean(sysRoleModule);
		this.sysRoleModule = sysRoleModule;
	}
	
}