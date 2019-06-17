/**
 * com.base.vo.SysModuleSourceVO
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
public class SysModuleSourceVO extends BaseVO {
	
    /**
     * 编号 db_column: C_ID 
     */	
	private Integer id;
    /**
     * 模块编号 db_column: C_MODULE_ID
     */
	private Integer cmoduleId;
    /**
     * 操作类型：00:查询，01:新增,02:修改,03:删除,04:其它 db_column: C_OPR_TYPE
     */
	private String coprType;
    /**
     * 资源类型:00:按钮,01:链接,02:图片,03:文字,04:其它 db_column: C_TYPE
     */
	private String ctype;
    /**
     * 名称 db_column: C_NAME
     */
	private String cname;
    /**
     * 关键字 db_column: C_KEY
     */
	private String ckey;
    /**
     * 页面排序 db_column: C_ORDER
     */
	private Long corder;
    /**
     * 创建时间 db_column: D_CREATE_TIME
     */
	private java.util.Date dcreateTime;
    /**
     * 描述 db_column: C_DESC
     */
	private String cdesc;

	private Integer LAY_TABLE_INDEX;//页面索引

	private Boolean checked;

	public SysModuleSourceVO(){
	}

	public SysModuleSourceVO( Integer id ){
		this.id = id;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
	public Integer getCmoduleId() {
		return this.cmoduleId;
	}
	
	public void setCmoduleId(Integer cmoduleId) {
		this.cmoduleId = cmoduleId;
	}
	public String getCoprType() {
		return this.coprType;
	}
	
	public void setCoprType(String coprType) {
		this.coprType = coprType;
	}
	public String getCtype() {
		return this.ctype;
	}
	
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getCname() {
		return this.cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCkey() {
		return this.ckey;
	}
	
	public void setCkey(String ckey) {
		this.ckey = ckey;
	}
	public Long getCorder() {
		return this.corder;
	}
	
	public void setCorder(Long corder) {
		this.corder = corder;
	}
	public java.util.Date getDcreateTime() {
		return this.dcreateTime;
	}
	
	public void setDcreateTime(java.util.Date dcreateTime) {
		this.dcreateTime = dcreateTime;
	}
	public String getCdesc() {
		return this.cdesc;
	}
	
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}

	public Integer getLAY_TABLE_INDEX() {
		return LAY_TABLE_INDEX;
	}

	public void setLAY_TABLE_INDEX(Integer LAY_TABLE_INDEX) {
		this.LAY_TABLE_INDEX = LAY_TABLE_INDEX;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}

