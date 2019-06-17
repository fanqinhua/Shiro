/**
 * com.base.service.ISysModuleService
 */
package com.base.web.service;


import cn.rmt.framework.service.IBaseBusService;
import com.base.pojo.basic.SysModuleVO;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
public interface ISysModuleService extends IBaseBusService<SysModuleVO> {
    List<SysModuleVO> loadMenuData(Integer userId);
    Map<String,Object> getModuleList();
    List<SysModuleVO> findSysModules();
}
