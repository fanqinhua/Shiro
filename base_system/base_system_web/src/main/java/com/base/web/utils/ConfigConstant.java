package com.base.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ConfigConstant {
    /* 图片路径 */
    private String rootDir;
    /* tomcat的url */
    private String fileServer;
    /* 是否debug模式 */
    private String debugModel;
    /* web根路径 */
    private String picUrl;
    @Autowired
    private CustomizedPropertyConfigurer configurer;

    @PostConstruct
    public void initial() {
        rootDir = configurer.paserString("file.rootDir");
        fileServer = configurer.paserString("file.fileServer");
        debugModel = configurer.paserString("debugModel");
        picUrl = configurer.paserString("PIC.PRE_URL");

    }
    public String getFileServer() {
        return fileServer;
    }

    public void setFileServer(String fileServer) {
        this.fileServer = fileServer;
    }

    public String getRootDir() {
        return rootDir;
    }

    public void setRootDir(String rootDir) {
        this.rootDir = rootDir;
    }

    public String getDebugModel() {
        return debugModel;
    }

    public String getPicUrl() {
        return picUrl;
    }
}
