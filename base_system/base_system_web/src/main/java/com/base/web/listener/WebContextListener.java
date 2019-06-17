package com.base.web.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * <br> 监听器：用来加载不同环境的properties
 * <b>功能：</b><br>
 * <b>作者：</b>fqhua<br>
 * <b>日期：</b><br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
public class WebContextListener extends ContextLoaderListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        String active = context.getInitParameter("spring.profiles.active");
        if("develop".equals(active)){
            LOGGER.info("开发环境");
        }else if("product".equals(active)){
            LOGGER.info("生产环境");
        }else{
            LOGGER.error("环境配置错误!");
        }
    }
}
