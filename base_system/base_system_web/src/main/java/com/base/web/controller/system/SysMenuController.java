package com.base.web.controller.system;

import com.base.pojo.basic.SysModuleSourceVO;
import com.base.pojo.basic.SysModuleVO;
import com.base.pojo.basic.SysRoleModuleVO;
import com.base.pojo.basic.SysUserVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.IpUtil;
import com.base.utils.JsonResVo;
import com.base.web.service.ISysModuleService;
import com.base.web.service.ISysModuleSourceService;
import com.base.web.service.ISysRoleModuleService;
import com.base.web.service.ISysRoleService;
import com.base.web.utils.OprLogUtil;
import com.base.web.utils.PermissionUtil;
import com.base.web.utils.ZtreeUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/revelationMenu")
public class SysMenuController extends SysBaseController{

    private Logger logger= LoggerFactory.getLogger(SysMenuController.class);
    @Autowired
    private ISysModuleService sysModuleService;
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISysRoleModuleService sysRoleModuleService;
    @Autowired
    private ISysModuleSourceService sysModuleSourceService;

    @RequestMapping({"role/{id}"})
    @ResponseBody
    public List<SysModuleVO> roleZtreeMenuJson(@PathVariable("id") String id, @RequestParam(value = "id",required = false) String ztreeParentId) {
        List<SysModuleVO> sysModules=Lists.newArrayList();
        if (ztreeParentId == null) {
            sysModules = sysModuleService.findSysModules();
            List<SysRoleModuleVO> sysRoleModules = sysRoleModuleService.findusrrolemodulesByid(Integer.parseInt(id));
            sysModules = ZtreeUtil.toSysModuleDTOsFromSysModules(sysModules);
            ZtreeUtil.dealRoleIsChecked(sysModules, sysRoleModules);
            ZtreeUtil.dealOpen(sysModules);
        }
        return sysModules;
    }

    @RequestMapping({"addRoleAndModule/{roleId}"})
    @ResponseBody
    public JsonResVo addRoleAndModule(@PathVariable("roleId") Integer roleId, @RequestParam(value = "menuIds",required = false) String menuIds){
        boolean success = true;
        String msg = "更新菜单成功";
        SysUserVO userVO=getCurrentUser();
        try {
            char c = ',';
            String []moduleIdsArr= StringUtils.split(menuIds,c);
            List<SysRoleModuleVO> sysRoleModuleVOS =sysRoleModuleService.findusrrolemodulesByid(roleId);//查询角色菜单关系表中所有的角色与菜单的关系
            List<Map<String,Integer>> newSysRoleModuleVOS=sysRoleModuleService.getNewRoleModule(sysRoleModuleVOS, moduleIdsArr, roleId);
            List<Integer> deleteRoleModules = sysRoleModuleService.getDeleteRoleModule(sysRoleModuleVOS, moduleIdsArr);
            sysRoleModuleService.updateMoudule(newSysRoleModuleVOS, deleteRoleModules);
        }catch (Exception e) {
            success = false;
            msg = e.getMessage();
            OprLogUtil.insert("更新角色的菜单权限失败",new Date(),userVO.getId(),userVO.getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_NO);
            logger.error("exception", e);
        }
        if (success) {
            //操作成功加入日志
            OprLogUtil.insert("更新角色的菜单权限成功",new Date(),userVO.getId(),userVO.getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_OK);
        }
        JsonResVo jsonResVo = new JsonResVo();
        jsonResVo.setSuccess(success);
        jsonResVo.setMsg(msg);
        return jsonResVo;
    }


    @RequestMapping(
            value = {"/moduleSourcePermission/{roleId}/{moduleId}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<SysModuleSourceVO> moduleSourcePermission(@PathVariable("roleId") Integer roleId,
                                                          @PathVariable("moduleId") Integer moduleId, Model model) {
        List<SysModuleSourceVO> sysModuleSources = sysModuleSourceService.getModuleSourceList(moduleId);
        if (sysModuleSources != null && sysModuleSources.size() != 0) {
            List<Integer> moduleSourceIds = sysRoleModuleService.findByRoleIdAndMouduleId(roleId, moduleId);
            List<SysModuleSourceVO> sysModuleSourceVOS = PermissionUtil.toSysModuleSourceDTOsFromSysModuleSources(sysModuleSources, moduleSourceIds);
            return sysModuleSourceVOS;
        } else {
            return new ArrayList();
        }
    }

    @RequestMapping(value = {"/moduleSourcePermissionSave/{roleId}/{moduleId}"},method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public JsonResVo moduleSourcePermissionSave(@PathVariable("roleId") Integer roleId,
                                                @PathVariable("moduleId") Integer moduleId, @RequestParam(value = "moduleSourceIds",required = false) String moduleSourceIds,Model model){
        char c = '|';
        boolean success = true;
        String msg = "更新权限成功";
        SysUserVO userVO=getCurrentUser();
        try {
            String[] moduleSourceIdsArr = StringUtils.split(moduleSourceIds, c);
            List<Integer> dataModuleSourceIds = sysRoleModuleService.findByRoleIdAndMouduleId2(roleId,moduleId);
            List<Map<String, Integer>> newRoleModules = sysRoleModuleService.getNewRoleModule2(dataModuleSourceIds, moduleSourceIdsArr, roleId, moduleId);
            List<Integer> deleteRoleModules=sysRoleModuleService.getDeleteRoleModule2(dataModuleSourceIds,moduleSourceIdsArr);
            sysRoleModuleService.updateMoudule2(newRoleModules, deleteRoleModules, roleId,moduleId);
        }catch (Exception var11){
            success = false;
            msg = var11.getMessage();
            OprLogUtil.insert("更新角色的资源权限失败",new Date(),userVO.getId(),userVO.getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_NO);
            logger.error("exception", var11);
        }

        if (success) {
           //成功就加入到日志去
            OprLogUtil.insert("更新角色的资源权限成功",new Date(),userVO.getId(),userVO.getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_OK);
        }
        JsonResVo jsonResVo = new JsonResVo();
        jsonResVo.setMsg(msg);
        jsonResVo.setSuccess(success);
        return jsonResVo;
    }

}
