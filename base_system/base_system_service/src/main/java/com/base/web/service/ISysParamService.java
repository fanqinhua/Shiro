/**
 * cn.rmt.admin.service.ISysParamService
 */
package com.base.web.service;


import cn.rmt.framework.service.IBaseBusService;
import com.base.pojo.basic.SysParamVO;

import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
public interface ISysParamService extends IBaseBusService<SysParamVO> {
    Map<String, Object> categoryList();

    Map<String, Object> sysParamTable(Integer page, Integer limit, Integer paramCategoryId);
}
