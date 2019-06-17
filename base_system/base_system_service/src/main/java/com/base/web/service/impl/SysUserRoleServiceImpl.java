/**
 * com.base.service.impl.SysUserRoleService
 */
package com.base.web.service.impl;


import cn.rmt.framework.service.BaseBusService;
import com.base.mapper.basic.ISysUserRoleMapper;
import com.base.mapper.model.SysUserRole;
import com.base.pojo.basic.SysUserRoleVO;
import com.base.web.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@Service
public class SysUserRoleServiceImpl extends BaseBusService<SysUserRole,SysUserRoleVO> implements ISysUserRoleService {

	@Autowired
	private ISysUserRoleMapper dao;
	
	@Override
	protected ISysUserRoleMapper getDao() {
		return dao;
	}

}
