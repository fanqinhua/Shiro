/**
 * cn.rmt.admin.service.impl.SysParamCategoryService
 */
package com.base.web.service.impl;

import cn.rmt.framework.service.BaseBusService;
import cn.rmt.framework.vo.OrderBean;
import com.base.mapper.basic.ISysParamCategoryMapper;
import com.base.mapper.model.SysParamCategory;
import com.base.pojo.basic.SysModuleVO;
import com.base.pojo.basic.SysParamCategoryQueryParam;
import com.base.pojo.basic.SysParamCategoryVO;
import com.base.web.service.ISysParamCategoryService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-28<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
@Service
public class SysParamCategoryServiceImpl extends BaseBusService<SysParamCategory,SysParamCategoryVO> implements ISysParamCategoryService {

	@Autowired
	private ISysParamCategoryMapper dao;
	
	@Override
	protected ISysParamCategoryMapper getDao() {
		return dao;
	}

	public Map<String,Object> getParamCategoryList(){
		Map<String,Object> map= Maps.newHashMap();
		List<SysModuleVO> list= dao.getParamCategoryList();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		return map;
	}
}
