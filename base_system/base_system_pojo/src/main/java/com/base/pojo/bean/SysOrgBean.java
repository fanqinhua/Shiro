package com.base.pojo.bean;

import com.google.common.collect.Lists;

import java.util.List;

public class SysOrgBean {
    private Integer id;
    private Integer pid;
    private String name;
    private boolean spread= true;
    private List<SysOrgBean> children = Lists.newArrayList();

    public SysOrgBean(){}

    public SysOrgBean(Integer id){
        this.id=id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public List<SysOrgBean> getChildren() {
        return children;
    }

    public void setChildren(List<SysOrgBean> children) {
        this.children = children;
    }
}
