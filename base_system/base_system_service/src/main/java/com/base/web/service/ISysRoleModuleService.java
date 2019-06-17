/**
 * com.base.service.ISysRoleModuleService
 */
package com.base.web.service;


import cn.rmt.framework.service.IBaseBusService;
import com.base.pojo.basic.SysRoleModuleVO;
import com.sun.tools.corba.se.idl.InterfaceGen;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
public interface ISysRoleModuleService extends IBaseBusService<SysRoleModuleVO> {
    List<SysRoleModuleVO> findusrrolemodulesByid(Integer roleId);
    List<Map<String,Integer>> getNewRoleModule(List<SysRoleModuleVO> sysRoleModuleVOS, String []moduleIdsArr,Integer roleId);
    List<Integer> getDeleteRoleModule(List<SysRoleModuleVO> sysRoleModuleVOS,String []moduleIdsArr);
    void updateMoudule(List<Map<String, Integer>> newSysRoleModuleVOS,List<Integer> deleteRoleModules);
    List<Integer> findByRoleIdAndMouduleId(Integer roleId,Integer moduleId);
    List<Integer> findByRoleIdAndMouduleId2(Integer roleId,Integer moduleId);
    List<Map<String, Integer>> getNewRoleModule2(List<Integer> dataModuleSourceIds,String[] moduleSourceIdsArr,Integer roleId,Integer moduleId);
    List<Integer> getDeleteRoleModule2(List<Integer> dataModuleSourceIds, String[] moduleSourceIdsArr);
    void updateMoudule2(List<Map<String, Integer>> newRoleModules, List<Integer> deleteRoleModules, Integer roleId,Integer moduleId);
}
