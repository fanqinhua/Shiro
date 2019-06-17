package com.base.web.controller.system;

import com.base.pojo.basic.SysUserRoleVO;
import com.base.pojo.basic.SysUserVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.IpUtil;
import com.base.utils.SystemJSONResult;
import com.base.web.service.ISysUserRoleService;
import com.base.web.service.ISysUserService;
import com.base.web.shiro.ShiroMD5;
import com.base.web.utils.OprLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/userMan")
public class SysUserManController extends SysBaseController{
    private Logger logger= LoggerFactory.getLogger(SysUserManController.class);
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @RequestMapping("/main")
    public String login(HttpServletRequest request){
        return "system/userMan/main";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> list(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                   @RequestParam(value = "cname" ,required = false) String cname)throws Exception{
        if (cname!=null&&cname!=""){
            cname=new String(cname.getBytes("iso-8859-1"),"utf-8");
        }
        Map<String,Object> map=sysUserService.getUserManList(page,limit,cname);
        return map;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView edit(Model model,HttpServletRequest request){
        String id=request.getParameter("id");
        if (id!=null){
            SysUserVO sysUserVO=sysUserService.findById(id);
            model.addAttribute("sysUserVO",sysUserVO);
        }
        return new ModelAndView("/system/userMan/edit",null);
    }


    @RequestMapping(value = "/getRoleList",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRoleAndOrgList(HttpServletRequest request){
        Map<String,Object> map=sysUserService.getRoleList();
        return map;
    }

    @RequestMapping(value = "/getOrgList",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getOrgList(HttpServletRequest request){
        Map<String,Object> map=sysUserService.getOrgList();
        return map;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult save(SysUserVO sysUserVO,HttpServletRequest request){
//        后面要将日志添加进来
        if (sysUserVO.getId()!=null){
            SysUserVO vo=sysUserService.findById(sysUserVO.getId());
            if (!vo.getCpassword().equals(sysUserVO.getCpassword())){
                sysUserVO.setCpassword(ShiroMD5.GetPwd(sysUserVO.getCloginName() , sysUserVO.getCpassword()));
            }
            sysUserVO.setDupdateTime(new Date());
            sysUserVO.setIupdaterId(1);//更新操作员这个要在后面获取当前登陆对象ID
            sysUserVO.setCip(IpUtil.getUserIP(request));
            int i=sysUserService.update(null,sysUserVO);
            if (i==1){
                OprLogUtil.insert("编辑用户:{"+sysUserVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("编辑成功");
            }else {
                OprLogUtil.insert("编辑用户:{"+sysUserVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("编辑失败");
            }
        }else {
            sysUserVO.setCip(IpUtil.getUserIP(request));
            sysUserVO.setDlastInTime(new Date());
            sysUserVO.setDcreateTime(new Date());
            sysUserVO.setDupdateTime(new Date());
            sysUserVO.setIcreateorId(1);//新增操作员这个要在后面获取当前登陆对象ID
            sysUserVO.setIupdaterId(1);//更新操作员这个要在后面获取当前登陆对象ID
            sysUserVO.setCactive(MainConstant.USER_ACTIVE_ONLINE);
            sysUserVO.setCpassword(ShiroMD5.GetPwd(sysUserVO.getCloginName() , sysUserVO.getCpassword()));//密码加密
            int i=sysUserService.insert(null,sysUserVO);
            if (i==1){
                SysUserRoleVO userRoleVO=new SysUserRoleVO();
                userRoleVO.setIuserId(sysUserVO.getId());
                userRoleVO.setIroleId(sysUserVO.getCroleId());
                sysUserRoleService.insert(null,userRoleVO);
                OprLogUtil.insert("添加用户:{"+sysUserVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("添加成功");
            }else {
                OprLogUtil.insert("添加用户:{"+sysUserVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("添加失败");
            }
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult delete(HttpServletRequest request){
        String id=request.getParameter("id");
        SysUserVO sysUserVO=sysUserService.findById(Integer.parseInt(id));
        sysUserVO.setCstatus(MainConstant.USER_STATUS_NO);
        int i=sysUserService.update(null,sysUserVO);
        if (i==1){
            OprLogUtil.insert("禁用用户:{"+sysUserVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
            return SystemJSONResult.ok();
        }else {
            OprLogUtil.insert("禁用用户:{"+sysUserVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
            return SystemJSONResult.errorMsg("操作失败");
        }

    }
}
