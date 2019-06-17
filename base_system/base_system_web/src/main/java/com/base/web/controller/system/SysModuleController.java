package com.base.web.controller.system;

import com.base.pojo.basic.SysModuleSourceVO;
import com.base.pojo.basic.SysModuleVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.IpUtil;
import com.base.utils.JsonMapper;
import com.base.utils.SystemJSONResult;
import com.base.web.service.ISysModuleService;
import com.base.web.service.ISysModuleSourceService;
import com.base.web.utils.OprLogUtil;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <br>
 * <b>功能：</b><br>
 * <b>作者：</b>fqhua<br>
 * <b>日期：</b><br>
 * <b>版权所有：<b>fqh版权所有(C)<br>
 */
@Controller
@RequestMapping(value = "/module")
public class SysModuleController extends SysBaseController{
    private Logger logger= LoggerFactory.getLogger(SysModuleController.class);
    @Autowired
    private ISysModuleService sysModuleService;
    @Autowired
    private ISysModuleSourceService sysModuleSourceService;
    @RequestMapping("/main")
    public String login(HttpServletRequest request){
        return "system/module/main";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(HttpServletRequest request){
        Map<String,Object> map= Maps.newHashMap();
        map=sysModuleService.getModuleList();
        return map;
    }
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request){
        String id =request.getParameter("id");
        SysModuleVO sysModule=sysModuleService.findById(id);
        model.addAttribute("syModule",sysModule);
        return "system/module/add";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(Model model,HttpServletRequest request){
        String id =request.getParameter("id");
        SysModuleVO childModule=sysModuleService.findById(id);
        SysModuleVO parentModule=sysModuleService.findById(String.valueOf(childModule.getIpid()));
        model.addAttribute("childModule",childModule);
        model.addAttribute("parentModule",parentModule);
        return "system/module/edit";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult save(SysModuleVO module){
        if (module.getId()!=null){
            int i=sysModuleService.update(null,module);
            if (i==1){
                OprLogUtil.insert("编辑菜单:{"+module.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("编辑成功");
            }else {
                OprLogUtil.insert("编辑菜单:{"+module.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("编辑失败");
            }
        }else {
            int i=sysModuleService.insert(null,module);
            if (i==1){
                OprLogUtil.insert("添加菜单:{"+module.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("添加成功");
            }else {
                OprLogUtil.insert("添加菜单:{"+module.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("添加失败");
            }
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult delete(HttpServletRequest request){
        String id =request.getParameter("id");
        SysModuleVO module=sysModuleService.findById(id);
        module.setCstatus(MainConstant.MODULE_STATUS_NO);
        int i = sysModuleService.update(null,module);
        if (i==1){
            OprLogUtil.insert("删除菜单:{"+module.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_OK);
            return  SystemJSONResult.ok("删除成功");
        }else{
            OprLogUtil.insert("删除菜单:{"+module.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(),MainConstant.USER_OPR_STATUS_NO);
            return  SystemJSONResult.errorMsg("删除失败");
        }
    }

    @RequestMapping(value = "/operation",method = RequestMethod.GET)
    public String operation(HttpServletRequest request,Model model){
        String id =request.getParameter("id");
        List<SysModuleSourceVO> list=sysModuleSourceService.getModuleSourceList(Integer.parseInt(id));
        String data=JsonMapper.obj2String(list);
        model.addAttribute("id",id);
        model.addAttribute("list",data);
        return "system/module/operation";
    }
}
