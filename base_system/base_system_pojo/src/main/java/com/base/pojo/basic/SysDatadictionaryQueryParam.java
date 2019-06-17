/**
 * cn.rmt.admin.vo.SysDatadictionaryQueryParam
 */
package com.base.pojo.basic;

import cn.rmt.framework.vo.QueryParam;

/**
 * <br>
 * <b>功能：</b>查询参数<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
public class SysDatadictionaryQueryParam extends QueryParam{
	
	private SysDatadictionaryVO sysDatadictionary;

	public SysDatadictionaryVO getSysDatadictionary() {
		return sysDatadictionary;
	}

	public void setSysDatadictionary(SysDatadictionaryVO sysDatadictionary) {
		super.setParamBean(sysDatadictionary);
		this.sysDatadictionary = sysDatadictionary;
	}
	
}