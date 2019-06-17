package com.base.beans;

import java.util.Set;

/**
 * <br>
 * <b>功能：</b><br>
 * <b>作者：</b>fqhua<br>
 * <b>日期：</b><br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
public class Mail {
    private String subject;

    private String message;

    private Set<String> receivers;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<String> receivers) {
        this.receivers = receivers;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", receivers=" + receivers +
                '}';
    }
}
