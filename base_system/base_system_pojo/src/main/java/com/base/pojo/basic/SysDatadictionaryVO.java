/**
 * cn.rmt.admin.vo.SysDatadictionaryVO
 */
package com.base.pojo.basic;

import  cn.rmt.framework.vo.BaseVO;

/**
 * <br>
 * <b>功能：</b>实体类<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@SuppressWarnings("serial")
public class SysDatadictionaryVO extends BaseVO{
	
    /**
     * 编号 db_column: ID 
     */	
	private Integer id;
    /**
     * 数据代码 db_column: C_CODE 
     */	
	private String ccode;
    /**
     * 数据名 db_column: C_KEY 
     */	
	private String ckey;
    /**
     * 数值 db_column: C_VALUE 
     */	
	private String cvalue;
    /**
     * 状态：00：为启用，01停用 db_column: C_STATUS 
     */	
	private String cstatus;
    /**
     * 描述 db_column: C_DESC 
     */	
	private String cdesc;

	public SysDatadictionaryVO(){
	}

	public SysDatadictionaryVO( Integer id ){
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCcode() {
		return this.ccode;
	}
	
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCkey() {
		return this.ckey;
	}
	
	public void setCkey(String ckey) {
		this.ckey = ckey;
	}
	public String getCvalue() {
		return this.cvalue;
	}
	
	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}
	public String getCstatus() {
		return this.cstatus;
	}
	
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getCdesc() {
		return this.cdesc;
	}
	
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	
}

