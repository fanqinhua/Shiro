/**
 * cn.rmt.admin.service.impl.SysRegionService
 */
package com.base.web.service.impl;

import cn.rmt.framework.service.BaseBusService;
import com.base.mapper.basic.ISysRegionMapper;
import com.base.mapper.model.SysRegion;
import com.base.pojo.basic.SysRegionVO;
import com.base.web.service.ISysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@Service
public class SysRegionServiceImpl extends BaseBusService<SysRegion,SysRegionVO> implements ISysRegionService {

	@Autowired
	private ISysRegionMapper dao;
	
	@Override
	protected ISysRegionMapper getDao() {
		return dao;
	}

}
