package com.base.utils;

public class JsonResVo {
    private boolean success;
    private boolean closed = true;
    private String msg;

    public JsonResVo() {
    }

    public JsonResVo(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public JsonResVo(boolean success, boolean closed, String msg) {
        this.success = success;
        this.closed = closed;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
