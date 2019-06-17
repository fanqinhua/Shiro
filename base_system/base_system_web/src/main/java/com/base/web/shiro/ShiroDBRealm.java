package com.base.web.shiro;

import com.base.pojo.basic.SysUserVO;
import com.base.pojo.bean.ShiroUserBean;
import com.base.pojo.constant.MainConstant;
import com.base.web.service.ISysRoleService;
import com.base.web.service.ISysUserService;
import org.apache.commons.fileupload.util.LimitedInputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description shiro 登录授权认证
 */

public class ShiroDBRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * Shiro登录认证:
     * 原理：用户提交 用户名和密码
     * ---shiro 封装令牌---- realm 通过用户名将密码查询返回
     * ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        // 通过登录名查找用户
        SysUserVO user = sysUserService.findUserByLoginName(username);
        // 账号不存在
        if (user == null) {
            throw new UnknownAccountException();
        }
        // 账号未启用
        if (user.getCstatus().equals(MainConstant.USER_STATUS_NO)) {
            throw new DisabledAccountException();
        }
        //账号冻结
        if (user.getCstatus().equals(MainConstant.USER_STATUS_CLOSE)){
            throw new CredentialsException();
        }
        // 根据用户id 查询出它所对应的角色id , 在 userRole 表中查询
        List<Integer> roleList = sysUserService.findRoleNamesByUserId(user.getId());
        // 将数据放到 ShiroUser(VO) 中。后面将信息存进shiro中
        ShiroUserBean shiroUser = new ShiroUserBean(user.getId(), user.getCloginName(), user.getCname(), roleList);
        //加盐
        ByteSource salt = ByteSource.Util.bytes(user.getCloginName());
        // 最后一步交给 shiro
        // 认证缓存信息
        SimpleAuthenticationInfo getInfo =
                new SimpleAuthenticationInfo(shiroUser,user.getCpassword(),salt,getName());
        return getInfo ;
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //  得到用户身份信息
        ShiroUserBean shiroUser = (ShiroUserBean) principals.getPrimaryPrincipal();
        //  得到用户角色 id
        List<Integer> roleList = shiroUser.roleList;
        Set<String> urlSet = new HashSet<String>() ;
        for (Integer roleId : roleList) {
            List<Map<Integer, String>> roleResourceList = sysUserService.findPermissionsByRoleId(roleId);
            if (roleResourceList != null) {
                for (Map<Integer, String> map : roleResourceList) {
                    if (StringUtils.isNoneBlank(map.get("permission"))) {
                        urlSet.add(map.get("permission"));
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        return info;
    }
    //清除当前用户权限信息
    public void clearCached() {
        //获取当前等的用户凭证，然后清除
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
