package com.base.web.controller.system;

import com.base.pojo.basic.SysModuleVO;
import com.base.pojo.basic.SysUserVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.IpUtil;
import com.base.utils.JsonResVo;
import com.base.utils.PasswordAesUtil;
import com.base.web.service.ISysModuleService;
import com.base.web.service.ISysUserService;
import com.base.web.shiro.ShiroUtils;
import com.base.web.utils.CustomizedPropertyConfigurer;
import com.base.web.utils.LoginLogUtil;
import com.google.common.collect.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * <br>
 * <b>功能：</b><br>
 * <b>作者：</b>fqhua<br>
 * <b>日期：</b><br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@Controller
public class SysMainController extends SysBaseController {
    private Logger logger = LoggerFactory.getLogger(SysMainController.class);
    @Autowired
    private ISysModuleService sysModuleService;
    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView gotoLogin(HttpServletRequest request) {
        String msg=(String)request.getSession().getAttribute("toLogin");
        if (msg!=null&&msg.equals("0")){
            request.getSession().removeAttribute("toLogin");
            return new ModelAndView("error/kickout", null);
        }
        return new ModelAndView("/login", null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResVo login(String username, String password, String captcha, ServletRequest request, HttpSession session) throws Exception{
        JsonResVo result = new JsonResVo();
        try {
            // 比对验证码是否正确
            String debugModel = (String) CustomizedPropertyConfigurer.getContextProperty("debugModel");
            //如果debugModel为0就是生产，debugModel为1就是调试
            if (debugModel.equals("0")) {
                if (!(captcha.equals(session.getAttribute("code")))) {
                    result.setMsg("验证码错误");
                    result.setSuccess(false);
                    return result;
                }
            }
            //对密码进行解锁
            password = PasswordAesUtil.decrypt(password);
            //shiro主体
            Subject user = SecurityUtils.getSubject();
            UsernamePasswordToken token =
                    new UsernamePasswordToken(username, password);
            try {
                user.login(token);
            } catch (UnknownAccountException e) {
                result.setMsg("账号不存在");
                result.setSuccess(false);
                return result;
            } catch (DisabledAccountException e) {
                result.setSuccess(false);
                result.setMsg("账号未启用");
                return result;
            } catch (IncorrectCredentialsException e) {
                result.setSuccess(false);
                result.setMsg("密码错误");
                return result;
            } catch (CredentialsException e) {
                result.setSuccess(false);
                result.setMsg("账号被冻结");
                return result;
            } catch (ExcessiveAttemptsException e) {
                result.setSuccess(false);
                result.setMsg("密码输入错误超过3次,请1分钟后再尝试");
                return result;
            } catch (RuntimeException e) {
                e.getStackTrace();
                result.setSuccess(false);
                result.setMsg("未知错误,请刷新界面重新登录！" + e.getMessage());
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setSuccess(true);
        SysUserVO userVO=getCurrentUser();
        LoginLogUtil.insert(userVO.getId(),userVO.getCname(), IpUtil.getServerIP(),new Date(), MainConstant.USER_LOGIN_STATUS_OK);
        return result;
    }


    /**
     * 退出
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    /**
     * 未授权
     */
    @RequestMapping(value = "/unAuth")
    public String unAuth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "error/unAuth";
    }

    /**
     * 并发访问
     */
    @RequestMapping("/kickoutUser")
    public String kickout(){
        return "error/kickout";
    }
    /**
     * 获取菜单树形结构
     *
     * @param request
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request, Model model) {

        List<SysModuleVO> menuList = Lists.newArrayList();
        try {
            SysUserVO userVO = getCurrentUser();
            menuList = sysModuleService.loadMenuData(userVO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("查询树形菜单出错");
        }
        model.addAttribute("menuList", menuList.get(0));
        return new ModelAndView("/index", null);
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome(HttpServletRequest request) {
        return new ModelAndView("welcome", null);
    }


}
