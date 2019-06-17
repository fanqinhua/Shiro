package com.base.web.quartz;

import com.base.pojo.basic.SysOprLogVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.DateUtil;
import com.base.utils.IpUtil;
import com.base.web.service.ISysLoginLogService;
import com.base.web.service.ISysOprLogService;
import com.base.web.utils.CustomizedPropertyConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时清除系统日志记录任务
 */
@Component
public class MethodLogQuartz {
    @Autowired
    private ISysLoginLogService sysLoginLogService;
    @Autowired
    private ISysOprLogService sysOprLogService;
    private static final Logger logger = LoggerFactory.getLogger(MethodLogQuartz.class);
    private static final String LOGDAYS = "log.days";

    /** 
     * cron表达式：* * * * * *（共6位，使用空格隔开，具体如下） 
     * cron表达式：*(秒0-59) *(分 钟0-59) *(小时0-23) *(日期1-31) *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT) 
     */
    // 每天中午12点钟 触发
    @Scheduled(cron=" 0 0 12 * * ?")
    public void clearLog() {
        long start=System.currentTimeMillis();
        logger.info("定时任务执行开始-->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            // 获取 10天前的时间  天数可以在 global.properties 文件中设置
            String logdays=(String)CustomizedPropertyConfigurer.getContextProperty(LOGDAYS);
            int logDays = Integer.valueOf(logdays);
            Date date = DateUtil.getDate(DateUtil.getDate(), -logDays);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str=sdf.format(date);
            int count =sysLoginLogService.delByDate(str);
            SysOprLogVO log=new SysOprLogVO();
            log.setCoprDetail("定时删除登陆日志："+count+"条");
            log.setDcreateTime(new Date());
            log.setIuserId("1");
            log.setCuserName("系统自动任务");
            log.setChostIp(IpUtil.getServerIP());
            log.setCresult(MainConstant.USER_OPR_STATUS_OK);
            sysOprLogService.insert(null,log);
        }catch (RuntimeException e){
            logger.error("定时删除失败:" + e.getMessage() );
        }
        long end=System.currentTimeMillis();
        logger.info("定时任务执行结束-->"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        logger.info("定时任务用时-->"+(end-start)+"ms");
    }
}
