/**
 * com.base.vo.SysModuleVO
 */
package com.base.pojo.basic;
import cn.rmt.framework.vo.BaseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <br>
 * <b>功能：</b>实体类<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@SuppressWarnings("serial")
public class SysModuleVO extends BaseVO {
	
    /**
     * 主键 db_column: C_ID 
     */	
	private Integer id;
    /**
     * 父ID db_column: I_PID 
     */	
	private Integer ipid;
    /**
     * 编号 db_column: C_CODE 
     */	
	private String ccode;
    /**
     * 模块名称 db_column: C_NAME 
     */	
	private String cname;
    /**
     * 模块地址 db_column: C_URL 
     */	
	private String curl;
    /**
     * 图标地址 db_column: C_ICON 
     */	
	private String cicon;
    /**
     * 层级 db_column: C_LEVEL
     */	
	private String clevel;
    /**
     * 是否叶子结点 db_column: C_IS_LEAF 
     */	
	private String cisLeaf;
    /**
     * 排序 db_column: C_ORER 
     */	
	private Integer corer;
    /**
     * 状态：00为启用，01为禁用 db_column: C_STATUS 
     */	
	private String cstatus;
    /**
     * 是否可见：0为可见，1为不可见 db_column: C_VISABLE 
     */	
	private String cvisable;
    /**
     * 描述 db_column: C_DESC 
     */	
	private String cdesc;

	/**
	 * 子菜单
	 */
	private List<SysModuleVO> children = new ArrayList<SysModuleVO>();


	private Boolean isParent;
	private Boolean open = false;
	private Boolean checked;
	private Boolean dataBaseChecked;


	public SysModuleVO(){
	}

	public SysModuleVO( Integer id ){
		this.id = id;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIpid() {
		return this.ipid;
	}
	
	public void setIpid(Integer ipid) {
		this.ipid = ipid;
	}
	public String getCcode() {
		return this.ccode;
	}
	
	public void setCcode(String ccode) {
		this.ccode = ccode;
	}
	public String getCname() {
		return this.cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCurl() {
		return this.curl;
	}
	
	public void setCurl(String curl) {
		this.curl = curl;
	}
	public String getCicon() {
		return this.cicon;
	}
	
	public void setCicon(String cicon) {
		this.cicon = cicon;
	}
	public String getClevel() {
		return this.clevel;
	}
	
	public void setClevel(String clevel) {
		this.clevel = clevel;
	}
	public String getCisLeaf() {
		return this.cisLeaf;
	}
	
	public void setCisLeaf(String cisLeaf) {
		this.cisLeaf = cisLeaf;
	}
	public Integer getCorer() {
		return this.corer;
	}
	
	public void setCorer(Integer corer) {
		this.corer = corer;
	}
	public String getCstatus() {
		return this.cstatus;
	}
	
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getCvisable() {
		return this.cvisable;
	}
	
	public void setCvisable(String cvisable) {
		this.cvisable = cvisable;
	}
	public String getCdesc() {
		return this.cdesc;
	}
	
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	public List<SysModuleVO> getChildren() {
		return children;
	}

	public void setChildren(List<SysModuleVO> children) {
		this.children = children;
	}

	public Boolean getParent() {
		return isParent;
	}

	public void setParent(Boolean parent) {
		isParent = parent;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getDataBaseChecked() {
		return dataBaseChecked;
	}

	public void setDataBaseChecked(Boolean dataBaseChecked) {
		this.dataBaseChecked = dataBaseChecked;
	}
}

