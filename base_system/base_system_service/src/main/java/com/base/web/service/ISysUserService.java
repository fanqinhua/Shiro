/**
 * com.base.service.ISysUserService
 */
package com.base.web.service;


import cn.rmt.framework.service.IBaseBusService;
import com.base.pojo.basic.SysUserVO;
import com.base.pojo.bean.SysPermissionBean;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b>Service<br>
 * <b>作者：</b>fqh@rm-tech.com.cn<br>
 * <b>日期：</b>2019-05-09<br>
 * <b>版权所有：<b>前海融脉科技有限公司版权所有(C) 2019<br>
 */
public interface ISysUserService extends IBaseBusService<SysUserVO> {
    /** ----------------------------系统功能模块------------------------------------*/
    Map<String,Object> getUserManList(Integer page,Integer limit,String cname);
    Map<String,Object> getRoleList();
    Map<String,Object> getOrgList();
    void updateStatusByLoginName(String loginName,String status);
    /** ----------------------------系统登陆模块------------------------------------*/
    SysUserVO findUserByLoginName(String username);
    List<Integer> findRoleNamesByUserId(Integer userId);
    List<Map<Integer, String>> findPermissionsByRoleId(Integer roleId);

}
