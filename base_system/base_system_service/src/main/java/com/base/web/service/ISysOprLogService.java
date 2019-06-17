/**
 * com.base.service.ISysOprLogService
 */
package com.base.web.service;


import cn.rmt.framework.service.IBaseBusService;
import com.base.pojo.basic.SysOprLogVO;

import java.util.Date;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
public interface ISysOprLogService extends IBaseBusService<SysOprLogVO> {
    void insertOprLog(String oprContent, Date oprDate, Integer oprUserId, String oprName, String oprIP, String result);
    Map<String,Object> getOprLogList(Integer page,Integer limit,String cname,String startTime,String endTime);
}
