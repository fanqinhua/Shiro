/**
 * com.base.service.ISysModuleSourceService
 */
package com.base.web.service;


import cn.rmt.framework.service.IBaseBusService;
import com.base.pojo.basic.SysModuleSourceVO;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
public interface ISysModuleSourceService extends IBaseBusService<SysModuleSourceVO> {
    List<SysModuleSourceVO> getModuleSourceList(int moduleId);
    Integer moduleSourceDel(Integer moduleSourceId);
}
