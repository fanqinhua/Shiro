package com.base.web.controller.system;


import com.base.pojo.basic.SysRoleVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.IpUtil;
import com.base.utils.StringUtil;
import com.base.utils.SystemJSONResult;
import com.base.web.service.ISysRoleService;
import com.base.web.utils.OprLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/roleMan")
public class SysRoleManController extends SysBaseController{
    private Logger logger= LoggerFactory.getLogger(SysRoleManController.class);
    @Autowired
    private ISysRoleService sysRoleService;
    @RequestMapping("/main")
    public String login(HttpServletRequest request){
        return "system/roleMan/main";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> list(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                   @RequestParam(value = "cname" ,required = false) String cname)throws Exception{
        if (cname!=null&&cname!=""){
            cname=new String(cname.getBytes("iso-8859-1"),"utf-8");
        }
        Map<String,Object> map=sysRoleService.getRoleManList(page,limit,cname);
        return map;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public ModelAndView edit(Model model, HttpServletRequest request){
        String id=request.getParameter("id");
        if (id!=null){
            SysRoleVO sysRoleVO=sysRoleService.findById(id);
            model.addAttribute("sysRoleVO",sysRoleVO);
        }
        return new ModelAndView("/system/roleMan/edit",null);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult delete(HttpServletRequest request){
        String id=request.getParameter("id");
        SysRoleVO sysRoleVO=sysRoleService.findById(Integer.parseInt(id));
        sysRoleVO.setCstatus(MainConstant.ROLE_STATUS_NO);
        int i=sysRoleService.update(null,sysRoleVO);
        if (i==1){
            OprLogUtil.insert("禁用角色:{"+sysRoleVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
            return SystemJSONResult.ok();
        }else {
            OprLogUtil.insert("禁用角色:{"+sysRoleVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
            return SystemJSONResult.errorMsg("操作失败");
        }

    }


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult save(SysRoleVO sysRoleVO,HttpServletRequest request){
//        后面要将日志添加进来
        if (sysRoleVO.getId()!=null){
            SysRoleVO vo=sysRoleService.findById(sysRoleVO.getId());
            sysRoleVO.setDupdateTime(new Date());
            sysRoleVO.setIoperator("管理员");//更新操作员这个要在后面获取当前登陆对象ID
            int i=sysRoleService.update(null,sysRoleVO);
            if (i==1){
                OprLogUtil.insert("编辑角色:{"+sysRoleVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("编辑成功");
            }else {
                OprLogUtil.insert("编辑角色:{"+sysRoleVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("编辑失败");
            }
        }else {
            sysRoleVO.setCcode(StringUtil.generateUID());
            sysRoleVO.setDcreateTime(new Date());
            sysRoleVO.setIoperator("管理员");//更新操作员这个要在后面获取当前登陆对象ID
            int i=sysRoleService.insert(null,sysRoleVO);
            if (i==1){
                OprLogUtil.insert("添加角色:{"+sysRoleVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("添加成功");
            }else {
                OprLogUtil.insert("添加角色:{"+sysRoleVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("添加失败");
            }
        }
    }



    @RequestMapping(value = {"/moduleSetPage/{id}"},method = {RequestMethod.GET})
    public String moduleSetPage(@PathVariable("id") String id, Model model) {
        SysRoleVO sysRoleVO = sysRoleService.findById(id);
        model.addAttribute("sysRoleVO", sysRoleVO);
        return "/system/roleMan/moduleSet";
    }



}
