/**
 * com.base.service.impl.SysRoleModuleService
 */
package com.base.web.service.impl;


import cn.rmt.framework.service.BaseBusService;
import cn.rmt.framework.vo.PageBean;
import com.base.mapper.basic.ISysRoleModuleMapper;
import com.base.mapper.model.SysRoleModule;
import com.base.pojo.basic.SysRoleModuleQueryParam;
import com.base.pojo.basic.SysRoleModuleVO;
import com.base.web.service.ISysRoleModuleService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@Service
public class SysRoleModuleServiceImpl extends BaseBusService<SysRoleModule,SysRoleModuleVO> implements ISysRoleModuleService {

	@Autowired
	private ISysRoleModuleMapper dao;
	
	@Override
	protected ISysRoleModuleMapper getDao() {
		return dao;
	}

	public List<SysRoleModuleVO> findusrrolemodulesByid(Integer roleId){
		SysRoleModuleQueryParam queryParam=new SysRoleModuleQueryParam();
		SysRoleModuleVO vo=new SysRoleModuleVO();
		vo.setIroleId(roleId);
		queryParam.setSysRoleModule(vo);
		List<SysRoleModuleVO> list=dao.findBy(queryParam);
		return list;
	}

	public List<Map<String, Integer>> getNewRoleModule(List<SysRoleModuleVO> sysRoleModuleVOS, String []moduleIdsArr, Integer roleId){
		List<Map<String, Integer>> addUsrRoleModules = new ArrayList();
		String[] var5 = moduleIdsArr;
		int var6 = moduleIdsArr.length;

		for(int var7 = 0; var7 < var6; ++var7) {
			Integer moduleId = Integer.parseInt( var5[var7]);
			boolean flag = true;
			Iterator var10 = sysRoleModuleVOS.iterator();

			while(var10.hasNext()) {
				SysRoleModuleVO usrRoleModule = (SysRoleModuleVO)var10.next();
				if (usrRoleModule.getImoduleId()==moduleId) {
					flag = false;
					break;
				}
			}
			if (flag) {
				Map<String, Integer> map = new HashMap();
				map.put("moduleId", moduleId);
				map.put("rodeId", roleId);
				map.put("moduleSourceId", null);
				addUsrRoleModules.add(map);
			}
		}
		return addUsrRoleModules;
	}


	public List<Integer> getDeleteRoleModule(List<SysRoleModuleVO> sysRoleModuleVOS,String []moduleIdsArr){
		List<Integer> deleteUsrRoleModules = new ArrayList();
		//1.遍历关于这个角色的权限集合
		Iterator var4 = sysRoleModuleVOS.iterator();

		while(var4.hasNext()) {
			SysRoleModuleVO usrRoleModule = (SysRoleModuleVO)var4.next();
			boolean flag = false;
			String[] var7 = moduleIdsArr;
			int var8 = moduleIdsArr.length;
			for(int var9 = 0; var9 < var8; ++var9) {
				Integer moduleId = Integer.parseInt(var7[var9]);
				if (usrRoleModule.getImoduleId()==moduleId) {
					flag = true;
					break;
				}
			}
//			加入即将删除掉的权限集合
			if (!flag) {
				deleteUsrRoleModules.add(usrRoleModule.getId());
			}
		}

		return deleteUsrRoleModules;
	}


	public void updateMoudule(List<Map<String, Integer>> newSysRoleModuleVOS,List<Integer> deleteRoleModules){
		Iterator var3 = newSysRoleModuleVOS.iterator();
		while(var3.hasNext()) {
			Map<String, Integer> map = (Map)var3.next();
			SysRoleModule vo=new SysRoleModule();
			vo.setIroleId(map.get("rodeId")!=null?map.get("rodeId"):null);
			vo.setImoduleId(map.get("moduleId")!=null?map.get("moduleId"):null);
			vo.setImoduleSourceId(map.get("moduleSourceId")!=null?map.get("moduleSourceId"):null);
			dao.insert(vo);
		}

		var3 = deleteRoleModules.iterator();

		while(var3.hasNext()) {
			Integer roleModuleId = (Integer)var3.next();
			dao.deleteById(roleModuleId);
		}
	}

	public List<Integer> findByRoleIdAndMouduleId(Integer roleId,Integer moduleId){
		return dao.findByRoleIdAndMouduleId(roleId,moduleId);
	}
	public List<Integer> findByRoleIdAndMouduleId2(Integer roleId,Integer moduleId){
		return dao.findByRoleIdAndMouduleId2(roleId,moduleId);
	}

	public List<Map<String, Integer>> getNewRoleModule2(List<Integer> dataModuleSourceIds,String[] moduleSourceIdsArr,Integer roleId,Integer moduleId){
		List<Map<String, Integer>> addUsrRoleModules = Lists.newArrayList();
		String[] var6 = moduleSourceIdsArr;
		int var7 = moduleSourceIdsArr.length;
		for(int var8 = 0; var8 < var7; ++var8) {
			Integer moduleSourceId = Integer.parseInt(var6[var8]);
			boolean flag = true;
			Iterator var11 = dataModuleSourceIds.iterator();
			while(var11.hasNext()) {
				Integer dataModuleSourceId = (Integer) var11.next();
				if (dataModuleSourceId==moduleSourceId) {
					flag = false;
					break;
				}
			}
			if (flag) {
				Map<String, Integer> map = Maps.newHashMap();
				map.put("moduleId",moduleId) ;
				map.put("rodeId", roleId);
				map.put("moduleSourceId", moduleSourceId);
				addUsrRoleModules.add(map);
			}
		}
		return addUsrRoleModules;
	}

	public List<Integer> getDeleteRoleModule2(List<Integer> dataModuleSourceIds, String[] moduleSourceIdsArr){
		List<Integer> deleteUsrRoleModules = Lists.newArrayList();
		Iterator var4 = dataModuleSourceIds.iterator();
		while(var4.hasNext()) {
			Integer dataModuleSourceId = (Integer) var4.next();
			boolean flag = false;
			String[] var7 = moduleSourceIdsArr;
			int var8 = moduleSourceIdsArr.length;
			for(int var9 = 0; var9 < var8; ++var9) {
				Integer moduleSourceId = Integer.parseInt(var7[var9]);
				if (dataModuleSourceId==moduleSourceId) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				deleteUsrRoleModules.add(dataModuleSourceId);
			}
		}
		return deleteUsrRoleModules;
	}

	public void updateMoudule2(List<Map<String, Integer>> newRoleModules,List<Integer> deleteRoleModules,Integer roleId,Integer moduleId){
		Iterator var4 = newRoleModules.iterator();

		while(var4.hasNext()) {
			Map<String, Integer> map = (Map)var4.next();
			SysRoleModule vo=new SysRoleModule();
			vo.setIroleId(map.get("rodeId")!=null?map.get("rodeId"):null);
			vo.setImoduleId(map.get("moduleId")!=null?map.get("moduleId"):null);
			vo.setImoduleSourceId(map.get("moduleSourceId")!=null?map.get("moduleSourceId"):null);
			dao.insert(vo);
		}

		var4 = deleteRoleModules.iterator();

		while(var4.hasNext()) {
			Integer roleModuleId = (Integer)var4.next();
			this.dao.deleteUsrRoleModule(roleId, roleModuleId,moduleId);
		}
	}
}
