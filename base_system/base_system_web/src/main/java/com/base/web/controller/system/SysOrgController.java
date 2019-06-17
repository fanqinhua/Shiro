package com.base.web.controller.system;

import com.base.pojo.basic.SysOrgVO;
import com.base.pojo.constant.MainConstant;
import com.base.utils.IpUtil;
import com.base.utils.SystemJSONResult;
import com.base.web.service.ISysOrgService;
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
import org.springframework.web.servlet.ModelAndView;
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
@RequestMapping(value = "/org")
public class SysOrgController extends SysBaseController{
    private Logger logger = LoggerFactory.getLogger(SysOrgController.class);
    @Autowired
    private ISysOrgService sysOrgService;

    @RequestMapping("/main")
    public ModelAndView login(HttpServletRequest request) {
        return new ModelAndView("system/org/main", null);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        map = sysOrgService.getOrgList();
        return map;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(Model model, HttpServletRequest request)throws Exception {
        String id = request.getParameter("id");
        String ccname=request.getParameter("cname")==null?"":request.getParameter("cname");
        String cname =new String(ccname.getBytes("iso-8859-1"),"utf-8") ;
        if (!cname.equals("0")){
            model.addAttribute("id",id);
            model.addAttribute("cname",cname);
        }else {
            SysOrgVO orgVO=sysOrgService.findById(id);
            SysOrgVO porgVO=sysOrgService.findById(orgVO.getIpid());
            model.addAttribute("orgVO",orgVO);
            model.addAttribute("porgVO",porgVO);
        }
        return new ModelAndView("system/org/edit", null);
    }


    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult save(SysOrgVO orgVO){
        if (orgVO.getId()!=null){
            int i=sysOrgService.update(null,orgVO);
            if (i==1){
                OprLogUtil.insert("编辑机构:{"+orgVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("编辑成功");
            }else {
                OprLogUtil.insert("编辑机构:{"+orgVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("编辑失败");
            }
        }else {
            if (orgVO.getIpid()==null){
                orgVO.setIpid(0);
            }
            orgVO.setDcreateTime(new Date());
            orgVO.setCtype("01");
            int i=sysOrgService.insert(null,orgVO);
            if (i==1){
                OprLogUtil.insert("保存机构:{"+orgVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_OK);
                return SystemJSONResult.ok("添加成功");
            }else {
                OprLogUtil.insert("保存机构:{"+orgVO.getCname()+"}",new Date(),getCurrentUser().getId(),getCurrentUser().getCname(), IpUtil.getServerIP(), MainConstant.USER_OPR_STATUS_NO);
                return SystemJSONResult.errorMsg("添加失败");
            }
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public SystemJSONResult delete(HttpServletRequest request){
        String id=request.getParameter("id");
        List<SysOrgVO> list=sysOrgService.findByIpid(Integer.parseInt(id));
        if (list!=null&&list.size()>0){
            for (SysOrgVO vo:list){
                vo.setCstatus(MainConstant.ORG_STATUS_NO);
                sysOrgService.update(null,vo);
            }
        }
        SysOrgVO vo=sysOrgService.findById(id);
        vo.setCstatus(MainConstant.ORG_STATUS_NO);
        sysOrgService.update(null,vo);
        return SystemJSONResult.ok();
    }
}
