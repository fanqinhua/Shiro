package com.base.pojo.bean;

public class SysPermissionBean {
    private String key;//资源表关键字
    private String code;//菜单表编号
    private String oprType;//操作类型
    private String type;//资源类型

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOprType() {
        return oprType;
    }

    public void setOprType(String oprType) {
        this.oprType = oprType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
