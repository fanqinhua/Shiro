package com.base.web.controller.system;


import com.base.pojo.basic.SysUserVO;
import com.base.pojo.bean.ShiroUserBean;
import com.base.utils.StringEscapeEditor;
import com.base.web.service.ISysJedisClusterClient;
import com.base.web.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基础 Controller
 */
public class SysBaseController {
    @Autowired
    private ISysUserService sysUserService;


    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

        /**
         * 防止XSS攻击
         */
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }

    /**
     * 获取当前登录用户对象
     * @return
     */
    public SysUserVO getCurrentUser() {
        ShiroUserBean shiroUser= (ShiroUserBean) SecurityUtils.getSubject().getPrincipal();
        SysUserVO currentUser = sysUserService.findById(shiroUser.id);
        return currentUser;
    }

    /**
     * 获取当前登录用户id
     * @return
     */
    public Integer getUserId() {
        return this.getCurrentUser().getId();
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public String getStaffName() {
        return this.getCurrentUser().getCname();
    }


}
