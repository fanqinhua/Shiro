/**
 * com.base.service.impl.SysModuleService
 */
package com.base.web.service.impl;


import cn.rmt.framework.service.BaseBusService;
import cn.rmt.framework.vo.OrderBean;
import com.base.mapper.basic.ISysModuleMapper;
import com.base.mapper.basic.ISysModuleSourceMapper;
import com.base.mapper.model.SysModule;
import com.base.pojo.basic.SysModuleQueryParam;
import com.base.pojo.basic.SysModuleSourceQueryParam;
import com.base.pojo.basic.SysModuleSourceVO;
import com.base.pojo.basic.SysModuleVO;
import com.base.pojo.constant.MainConstant;
import com.base.web.service.ISysModuleService;
import com.base.web.service.ISysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.tools.corba.se.idl.InterfaceGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Iterator;
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
public class SysModuleServiceImpl extends BaseBusService<SysModule,SysModuleVO> implements ISysModuleService {

	@Autowired
	private ISysModuleMapper dao;
	@Autowired
    private ISysModuleSourceMapper moduleSourceMapper;
	@Autowired
    private ISysUserService sysUserService;
	
	@Override
	protected ISysModuleMapper getDao() {
		return dao;
	}

    public List<SysModuleVO> loadMenuData(Integer userId){
        Map<String, String> navTextMap = Maps.newHashMap();
        List<SysModuleVO> menuTree = Lists.newArrayList();
        List<Integer> roleList=sysUserService.findRoleNamesByUserId(userId);
        Integer[] roles=new Integer[roleList.size()];
        if (roleList!=null&&roleList.size()>0){
            for (int i=0;i<roleList.size();i++){
                roles[i]=roleList.get(i);
            }
        }
        List<SysModuleVO> moduleList=dao.findByPid(MainConstant.MODULE_PID,roles);
        menuTree=packResTree(moduleList,navTextMap);
        return menuTree;
    }

    public Map<String,Object> getModuleList(){
	    Map<String,Object> map=Maps.newHashMap();
	    List<SysModuleVO> list= dao.findByOrder();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    public List<SysModuleVO> findSysModules(){
        SysModuleQueryParam queryParam=new SysModuleQueryParam();
        OrderBean bean=new OrderBean();
        bean.setOrderBy("corer");
        bean.setOrder("asc");
        queryParam.setOrderBean(bean);
        List<SysModuleVO> list= dao.findBy(queryParam);
        return list;
    }

    public List<SysModuleVO> packResTree(List<SysModuleVO> moduleList, Map<String, String> navTextMap){
        Map<String, SysModuleVO> moduleMap = Maps.newHashMap();
        SysModuleVO topNode=new SysModuleVO(MainConstant.MODULE_PID);
        moduleMap.put("0", topNode);
        for (Iterator<SysModuleVO> it = moduleList.iterator(); it.hasNext(); ) {
            SysModuleVO module = it.next();
            moduleMap.put(module.getId().toString(), module);
        }
        //递归处理菜单树
        packResTree(moduleList, MainConstant.MODULE_PID, moduleMap, navTextMap);
        return topNode.getChildren();
    }

    private void packResTree(List<SysModuleVO> moduleList, Integer pid, Map<String, SysModuleVO> moduleMap,
                             Map<String, String> navTextMap) {
        List<SysModuleVO> childList = Lists.newArrayList();
        for (Iterator<SysModuleVO> it = moduleList.iterator(); it.hasNext(); ) {
            SysModuleVO module = it.next();
            String moduleId = module.getId().toString();
            if (pid.intValue() == module.getIpid()) {
                moduleMap.get(pid.toString()).getChildren().add(module);
                it.remove();
                childList.add(module);
                if (navTextMap.containsKey(pid.toString())) {
                    //设置菜单的完整路径
                    navTextMap.put(moduleId, navTextMap.get(pid.toString()) + "," + module.getCname());
                } else {
                    navTextMap.put(moduleId, module.getCname());
                }
            }
        }
        for (SysModuleVO childRes : childList) {
            packResTree(moduleList, childRes.getId(), moduleMap, navTextMap);
        }
    }


}
