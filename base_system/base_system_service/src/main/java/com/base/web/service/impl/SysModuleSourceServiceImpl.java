/**
 * com.base.service.impl.SysModuleSourceService
 */
package com.base.web.service.impl;


import cn.rmt.framework.service.BaseBusService;
import cn.rmt.framework.vo.OrderBean;
import com.base.mapper.basic.ISysModuleSourceMapper;
import com.base.mapper.model.SysModuleSource;
import com.base.pojo.basic.SysModuleSourceQueryParam;
import com.base.pojo.basic.SysModuleSourceVO;
import com.base.web.service.ISysModuleSourceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@Service
public class SysModuleSourceServiceImpl extends BaseBusService<SysModuleSource,SysModuleSourceVO> implements ISysModuleSourceService {

	@Autowired
	private ISysModuleSourceMapper dao;
	@Autowired
	private ISysModuleSourceMapper sysModuleSourceMapper;
	
	@Override
	protected ISysModuleSourceMapper getDao() {
		return dao;
	}

	public List<SysModuleSourceVO> getModuleSourceList(int moduleId){
		List<SysModuleSourceVO> list= Lists.newArrayList();
		Map<String,Object> map= Maps.newHashMap();
		SysModuleSourceQueryParam queryParam=new SysModuleSourceQueryParam();
		SysModuleSourceVO vo=new SysModuleSourceVO();
		vo.setCmoduleId(moduleId);
		queryParam.setSysModuleSource(vo);
		OrderBean bean=new OrderBean();
		bean.setOrderBy("corder");
		bean.setOrder("asc");
		queryParam.setOrderBean(bean);
		list=dao.findBy(queryParam);
		return list;
	}

	public Integer moduleSourceDel(Integer moduleSourceId){
		return dao.deleteById(moduleSourceId);
	}


}
