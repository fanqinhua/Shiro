/**
 * com.base.vo.SysOrgVO
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
public class SysOrgVO extends BaseVO {
	
    /**
     * 编号 db_column: C_ID 
     */	
	private Integer id;
    /**
     * 父结点ID db_column: I_PID 
     */	
	private Integer ipid;
    /**
     * 代号 db_column: C_CODE 
     */	
	private String ccode;
    /**
     * 名称 db_column: C_NAME 
     */	
	private String cname;
    /**
     * 类型：01为运通架构，02为银行类型，03为其它企业类型 db_column: C_TYPE 
     */	
	private String ctype;
    /**
     * 描述 db_column: C_DES 
     */	
	private String cdes;
    /**
     * 00：启用，01停用 db_column: C_STATUS 
     */	
	private String cstatus;
    /**
     * 电话 db_column: C_TEL 
     */	
	private String ctel;
    /**
     * 地址 db_column: C_ADDRESS 
     */	
	private String caddress;
    /**
     * 创建时间 db_column: D_CREATE_TIME 
     */	
	private java.util.Date dcreateTime;
    /**
     * 邮箱 db_column: C_EMAIL 
     */	
	private String cemail;
    /**
     * 负责人员 db_column: C_PERSON 
     */	
	private String cperson;

	public SysOrgVO(){
	}

	public SysOrgVO( Integer id ){
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
	public String getCtype() {
		return this.ctype;
	}
	
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getCdes() {
		return this.cdes;
	}
	
	public void setCdes(String cdes) {
		this.cdes = cdes;
	}
	public String getCstatus() {
		return this.cstatus;
	}
	
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public String getCtel() {
		return this.ctel;
	}
	
	public void setCtel(String ctel) {
		this.ctel = ctel;
	}
	public String getCaddress() {
		return this.caddress;
	}
	
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public java.util.Date getDcreateTime() {
		return this.dcreateTime;
	}
	
	public void setDcreateTime(java.util.Date dcreateTime) {
		this.dcreateTime = dcreateTime;
	}
	public String getCemail() {
		return this.cemail;
	}
	
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getCperson() {
		return this.cperson;
	}
	
	public void setCperson(String cperson) {
		this.cperson = cperson;
	}
	
}

