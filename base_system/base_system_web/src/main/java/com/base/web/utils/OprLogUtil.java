package com.base.web.utils;

import com.base.web.service.ISysOprLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

/**
 * 业务日志工具类
 */
public class OprLogUtil {
    private static Logger logger = LoggerFactory.getLogger(OprLogUtil.class);
    public static void insert(String oprContent, Date oprDate, Integer oprUserId, String oprName, String oprIP,String result){
        try {
            ISysOprLogService sysOprLogService = (ISysOprLogService) SpringContextHelper.getBean("sysOprLogService");
            sysOprLogService.insertOprLog(oprContent,oprDate,oprUserId,oprName,oprIP,result);
        } catch (Exception e) {
            logger.info("操作日志添加异常");
            e.printStackTrace();
        }
    }
}
