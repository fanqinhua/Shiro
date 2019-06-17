package com.base.web.controller.system;

import com.base.pojo.basic.SysParamCategoryVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.IpUtil;
import com.base.utils.SystemJSONResult;
import com.base.web.service.ISysParamCategoryService;
import com.base.web.utils.OprLogUtil;
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
import java.util.Map;

@Controller
@RequestMapping("/sysParamCategory")
public class SysParamCategoryController extends SysBaseController{
    private Logger logger= LoggerFactory.getLogger(SysParamCategoryController.class);
    @Autowired
    private ISysParamCategoryService sysParamCategoryService;
    @RequestMapping("/main")
    public String login(HttpServletRequest request){
        return "system/sysParamCategory/main";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(HttpServletRequest request){
        Map<String,Object> map=sysParamCategoryService.getParamCategoryList();
        return map;
    }


    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(Model model, HttpServletRequest request){
        String id =request.getParameter("id");
        SysParamCategoryVO sysParamCategoryVO=sysParamCategoryService.findById(id);
        model.addAttribute("sysParamCategoryVO",sysParamCategoryVO);
        return "system/sysParamCategory/add";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String edit(Model model,HttpServletRequest request){
        String id =request.getParameter("id");
        SysParamCategoryVO childParamCategory=sysParamCategoryService.findById(id);
        SysParamCategoryVO parentParamCategory=sysParamCategoryService.findById(String.valueOf(childParamCategory.getIpid()));
        model.addAttribute("childParamCategory",childParamCategory);
        model.addAttribute("parentParamCategory",parentParamCategory);
        return "system/sysParamCategory/edit";
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult save(SysParamCategoryVO paramCategoryVO){
        if (paramCategoryVO.getId()!=null){
            int i=sysParamCategoryService.update(null,paramCategoryVO);
            if (i==1){
                OprLogUtil.insert("编辑系统分类:{"+paramCategoryVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("编辑成功");
            }else {
                OprLogUtil.insert("编辑系统分类:{"+paramCategoryVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("编辑失败");
            }
        }else {
            paramCategoryVO.setDcreateTime(new Date());
            paramCategoryVO.setDupdateTime(new Date());
            int i=sysParamCategoryService.insert(null,paramCategoryVO);
            if (i==1){
                OprLogUtil.insert("添加系统分类:{"+paramCategoryVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("添加成功");
            }else {
                OprLogUtil.insert("编辑系统分类:{"+paramCategoryVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("添加失败");
            }
        }
    }
}
