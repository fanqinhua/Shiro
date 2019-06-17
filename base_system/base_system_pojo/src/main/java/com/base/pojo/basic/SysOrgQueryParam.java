/**
 * com.base.vo.SysOrgQueryParam
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
public class SysOrgQueryParam extends QueryParam {
	
	private SysOrgVO sysOrg;

	public SysOrgVO getSysOrg() {
		return sysOrg;
	}

	public void setSysOrg(SysOrgVO sysOrg) {
		super.setParamBean(sysOrg);
		this.sysOrg = sysOrg;
	}
	
}