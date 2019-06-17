package com.base.web.controller.system;

import com.base.pojo.basic.SysModuleSourceVO;
import com.base.pojo.constant.MainConstant;
import com.base.pojo.constant.ModuleConstant;
import com.base.utils.IpUtil;
import com.base.utils.SystemJSONResult;
import com.base.web.service.ISysModuleSourceService;
import com.base.web.utils.OprLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/moduleSource")
public class SysModuleSourceController extends SysBaseController{

    private Logger logger= LoggerFactory.getLogger(SysModuleSourceController.class);

    @Autowired
    private ISysModuleSourceService sysModuleSourceService;


    @RequestMapping(value = "/moduleSourceDel",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult moduleSourceDel(Model model, HttpServletRequest request){
        String moduleSourceId=request.getParameter("moduleSourceId");
        String id=request.getParameter("id");
        int i=sysModuleSourceService.moduleSourceDel(Integer.parseInt(moduleSourceId));
        if (i==1){
            OprLogUtil.insert("删除菜单资源",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
            List<SysModuleSourceVO> list=sysModuleSourceService.getModuleSourceList(Integer.parseInt(id));
            return SystemJSONResult.ok(list);
        }else {
            OprLogUtil.insert("删除菜单资源",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
            return SystemJSONResult.errorMsg("删除失败");
        }

    }

    @RequestMapping(value="/moduleSourceSave/{moduleId}",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult moduleSourceSave(@PathVariable String moduleId, @RequestBody List<SysModuleSourceVO> sourceList, HttpServletRequest request)throws Exception{
         if (sourceList!=null&&sourceList.size()>0){
             for (SysModuleSourceVO vo:sourceList){
                 if (vo.getId()==null){
                     vo.setCmoduleId(Integer.parseInt(moduleId));
                     vo.setDcreateTime(new Date());
                     vo.setCoprType(ModuleConstant.MODULE_OPR_TYPE_OTHER);
                     vo.setCdesc(vo.getCname());
                     sysModuleSourceService.insert(null,vo);
                 }else {
                     sysModuleSourceService.update(null,vo);
                 }
             }
            List<SysModuleSourceVO> list=sysModuleSourceService.getModuleSourceList(Integer.parseInt(moduleId));
             OprLogUtil.insert("保存菜单资源",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
             return SystemJSONResult.ok(list);
        }else {
             OprLogUtil.insert("保存菜单资源",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
             return SystemJSONResult.errorMsg("保存失败");
        }

    }
}
