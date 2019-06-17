package com.base.pojo.bean;

import java.util.List;

public class SysAtreeParamCategoryBean {
    private Integer id;
    private String name;
    private boolean spread;//默认打开
    private List<SysAtreeParamCategoryBean> children;


    public SysAtreeParamCategoryBean(){}
    public SysAtreeParamCategoryBean(Integer id, String name, boolean spread) {
        this.id = id;
        this.name = name;
        this.spread=spread;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<SysAtreeParamCategoryBean> getChildren() {
        return children;
    }

    public void setChildren(List<SysAtreeParamCategoryBean> children) {
        this.children = children;
    }
}
