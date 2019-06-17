/**
 * com.base.service.impl.SysRoleService
 */
package com.base.web.service.impl;


import cn.rmt.framework.service.BaseBusService;
import com.base.mapper.basic.ISysRoleMapper;
import com.base.mapper.model.SysRole;
import com.base.pojo.basic.SysRoleVO;
import com.base.web.service.ISysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class SysRoleServiceImpl extends BaseBusService<SysRole,SysRoleVO> implements ISysRoleService {

	@Autowired
	private ISysRoleMapper dao;
	
	@Override
	protected ISysRoleMapper getDao() {
		return dao;
	}
	public Map<String,Object> getRoleManList(Integer page, Integer limit, String cname){
		Map<String, Object> map = Maps.newHashMap();
		PageHelper.startPage(page, limit);
		List<SysRoleVO> sysRoleVOList = dao.getRoleManList(cname);
		PageInfo<SysRoleVO> pageList = new PageInfo<>(sysRoleVOList);
		map.put("code", 0);
		map.put("msg", "查询成功");
		map.put("count", pageList.getTotal());
		map.put("data", pageList.getList());
		return map;
	}
}
