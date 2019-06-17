package com.base.web.utils;

import com.base.web.service.ISysLoginLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 登陆日志工具类
 */
public class LoginLogUtil {
    private static Logger logger = LoggerFactory.getLogger(LoginLogUtil.class);
    public static void insert(Integer loginUserId, String loginUserName, String loginIP, Date loginTime,String result){
        try {
            ISysLoginLogService sysLoginLogService = (ISysLoginLogService) SpringContextHelper.getBean("sysLoginLogService");
            sysLoginLogService.insertLoginLog(loginUserId,loginUserName,loginIP,loginTime,result);
        } catch (Exception e) {
            logger.info("登陆日志添加异常");
            e.printStackTrace();
        }
    }
}
