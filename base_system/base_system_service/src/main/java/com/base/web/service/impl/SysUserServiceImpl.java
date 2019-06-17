/**
 * com.base.service.impl.SysUserService
 */
package com.base.web.service.impl;

import cn.rmt.framework.service.BaseBusService;
import cn.rmt.framework.vo.OrderBean;
import com.base.mapper.basic.ISysOrgMapper;
import com.base.mapper.basic.ISysRoleMapper;
import com.base.mapper.basic.ISysUserMapper;
import com.base.mapper.model.SysUser;
import com.base.pojo.basic.*;
import com.base.pojo.bean.SysOrgBean;
import com.base.pojo.constant.MainConstant;
import com.base.web.service.ISysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
public class SysUserServiceImpl extends BaseBusService<SysUser, SysUserVO> implements ISysUserService {

    @Autowired
    private ISysUserMapper dao;
    @Autowired
    private ISysRoleMapper sysRoleMapper;
    @Autowired
    private ISysOrgMapper sysOrgMapper;

    @Override
    protected ISysUserMapper getDao() {
        return dao;
    }


    /** ----------------------------系统功能模块------------------------------------*/
    public Map<String, Object> getUserManList(Integer page, Integer limit, String cname) {
        Map<String, Object> map = Maps.newHashMap();
        PageHelper.startPage(page, limit);
        List<SysUserVO> sysUserVOList = dao.getUserManList(cname);
        PageInfo<SysUserVO> pageList = new PageInfo<>(sysUserVOList);
        map.put("code", 0);
        map.put("msg", "查询成功");
        map.put("count", pageList.getTotal());
        map.put("data", pageList.getList());
        return map;
    }

    public Map<String, Object> getRoleList() {
        Map<String, Object> map = Maps.newHashMap();
        //加载角色列表
        SysRoleQueryParam queryParam = new SysRoleQueryParam();
        SysRoleVO roleVO = new SysRoleVO();
        roleVO.setCstatus(MainConstant.ROLE_STATUS_OK);
        OrderBean bean = new OrderBean();
        bean.setOrderBy("dcreateTime");
        bean.setOrder("desc");
        queryParam.setSysRole(roleVO);
        queryParam.setOrderBean(bean);
        List<SysRoleVO> roleList = sysRoleMapper.findBy(queryParam);
        map.put("roleList",roleList);
        return map;
    }

    public Map<String, Object> getOrgList() {
        //加载机构列表
        Map<String, Object> map = Maps.newHashMap();
        Map<String, String> navTextMap = Maps.newHashMap();
        SysOrgQueryParam param = new SysOrgQueryParam();
        SysOrgVO orgVO = new SysOrgVO();
        orgVO.setCstatus(MainConstant.ORG_STATUS_OK);
        param.setSysOrg(orgVO);
        List<SysOrgVO> list1 = sysOrgMapper.findBy(param);
        List<SysOrgBean> orgList= Lists.newArrayList();
        if (list1 != null && list1.size() > 0) {
            for (SysOrgVO vo : list1) {
                SysOrgBean orgBean = new SysOrgBean();
                orgBean.setId(vo.getId());
                orgBean.setPid(vo.getIpid());
                orgBean.setName(vo.getCname());
                orgList.add(orgBean);
            }
        }
        orgList=packResTree(orgList,navTextMap);
        map.put("orgList",orgList);
        return map;
    }

    public List<SysOrgBean> packResTree(List<SysOrgBean> orgList, Map<String, String> navTextMap){
        Map<String, SysOrgBean> moduleMap = Maps.newHashMap();
        SysOrgBean topNode=new SysOrgBean(0);
        moduleMap.put("0", topNode);
        for (Iterator<SysOrgBean> it = orgList.iterator(); it.hasNext(); ) {
            SysOrgBean org = it.next();
            moduleMap.put(org.getId().toString(), org);
        }
        //递归处理菜单树
        packResTree(orgList, 0, moduleMap, navTextMap);
        return topNode.getChildren();
    }

    private void packResTree(List<SysOrgBean> orgList, Integer pid, Map<String, SysOrgBean> moduleMap,
                             Map<String, String> navTextMap) {
        List<SysOrgBean> childList = Lists.newArrayList();
        for (Iterator<SysOrgBean> it = orgList.iterator(); it.hasNext(); ) {
            SysOrgBean org = it.next();
            String orgId = org.getId().toString();
            if (pid.intValue() == org.getPid()) {
                moduleMap.get(pid.toString()).getChildren().add(org);
                it.remove();
                childList.add(org);
                if (navTextMap.containsKey(pid.toString())) {
                    //设置菜单的完整路径
                    navTextMap.put(orgId, navTextMap.get(pid.toString()) + "," + org.getName());
                } else {
                    navTextMap.put(orgId, org.getName());
                }
            }
        }
        for (SysOrgBean childRes : childList) {
            packResTree(orgList, childRes.getId(), moduleMap, navTextMap);
        }
    }

    public void updateStatusByLoginName(String loginName,String status){
        this.dao.updateStatusByLoginName(loginName,status);
    }


    /** ----------------------------系统登陆模块------------------------------------*/
    public SysUserVO findUserByLoginName(String username){
        return dao.getByCloginName(username);
    }

    public List<Integer> findRoleNamesByUserId(Integer userId){
        return dao.findRoleNamesByUserId(userId);
    }

    public List<Map<Integer, String>> findPermissionsByRoleId(Integer roleId){
        return dao.findPermissionsByUserId(roleId);
    }


}
