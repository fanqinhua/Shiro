/**
 * com.base.vo.SysRoleModuleVO
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
public class SysRoleModuleVO extends BaseVO {
	
    /**
     * 编号 db_column: C_ID 
     */	
	private Integer id;
    /**
     * 角色ID db_column: I_ROLE_ID 
     */	
	private Integer iroleId;
    /**
     * 模块ID db_column: I_MODULE_ID 
     */	
	private Integer imoduleId;
    /**
     * 功能ID db_column: I_MODULE_SOURCE_ID 
     */	
	private Integer imoduleSourceId;

	public SysRoleModuleVO(){
	}

	public SysRoleModuleVO( Integer id ){
		this.id = id;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	public Integer getIroleId() {
		return this.iroleId;
	}
	
	public void setIroleId(Integer iroleId) {
		this.iroleId = iroleId;
	}
	public Integer getImoduleId() {
		return this.imoduleId;
	}
	
	public void setImoduleId(Integer imoduleId) {
		this.imoduleId = imoduleId;
	}
	public Integer getImoduleSourceId() {
		return this.imoduleSourceId;
	}
	
	public void setImoduleSourceId(Integer imoduleSourceId) {
		this.imoduleSourceId = imoduleSourceId;
	}
	
}

