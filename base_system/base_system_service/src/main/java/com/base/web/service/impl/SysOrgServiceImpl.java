/**
 * com.base.service.impl.SysOrgService
 */
package com.base.web.service.impl;

import cn.rmt.framework.service.BaseBusService;
import cn.rmt.framework.vo.OrderBean;
import com.base.mapper.basic.ISysOrgMapper;
import com.base.mapper.model.SysOrg;
import com.base.pojo.basic.SysOrgQueryParam;
import com.base.pojo.basic.SysOrgVO;
import com.base.web.service.ISysOrgService;
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
public class SysOrgServiceImpl extends BaseBusService<SysOrg,SysOrgVO> implements ISysOrgService {

	@Autowired
	private ISysOrgMapper dao;
	
	@Override
	protected ISysOrgMapper getDao() {
		return dao;
	}

	public Map<String,Object> getOrgList(){
		Map<String,Object> map= Maps.newHashMap();
		SysOrgQueryParam queryParam=new SysOrgQueryParam();
		OrderBean bean=new OrderBean();
		bean.setOrderBy("D_CREATE_TIME");
		bean.setOrder("desc");
		queryParam.setOrderBean(bean);
		List<SysOrgVO> list= dao.findPage(queryParam);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
		return map;
	}

	public List<SysOrgVO> findByIpid(Integer ipid){
		List<SysOrgVO> list= Lists.newArrayList();
		SysOrgQueryParam queryParam=new SysOrgQueryParam();
		SysOrgVO vo=new SysOrgVO();
		vo.setIpid(ipid);
		queryParam.setSysOrg(vo);
		list=dao.findBy(queryParam);
		return list;
	}

}
