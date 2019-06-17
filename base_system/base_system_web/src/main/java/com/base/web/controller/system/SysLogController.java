package com.base.web.controller.system;


import com.base.utils.JsonResVo;
import com.base.web.service.ISysLoginLogService;
import com.base.web.service.ISysOprLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.Map;

@Controller
@RequestMapping("/log")
public class SysLogController {
    private Logger logger= LoggerFactory.getLogger(SysLogController.class);
    @Autowired
    private ISysLoginLogService sysLoginLogService;
    @Autowired
    private ISysOprLogService sysOprLogService;

    @RequestMapping(value = "/login/main",method = RequestMethod.GET)
    public ModelAndView loginMain(){
        return new ModelAndView("system/log/loginlog/main",null);
    }
    @RequestMapping(value = "/opr/main",method = RequestMethod.GET)
    public ModelAndView oprMain(){
        return new ModelAndView("system/log/oprlog/main",null);
    }

    @RequestMapping(value = "/login/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> loginlist(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                   @RequestParam(value = "cname" ,required = false) String cname,
                                   @RequestParam(value = "startTime",required = false)String startTime,
                                   @RequestParam(value = "endTime",required = false)String endTime)throws Exception{
        Map<String,Object> map=sysLoginLogService.getLoginLogList(page,limit,cname,startTime,endTime);
        return map;
    }

    @RequestMapping(value = "/login/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResVo logindelete(@RequestParam("ids") String ids)throws Exception{
        JsonResVo result=new JsonResVo();
        Serializable[] logIds=StringUtils.split(ids,",");
        for (Serializable id:logIds){
            sysLoginLogService.deleteById(null,id);
        }
        result.setSuccess(true);
        result.setMsg("删除成功");
        return result;
    }

    @RequestMapping(value = "/opr/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> oprlist(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                        @RequestParam(value = "cname" ,required = false) String cname,
                                        @RequestParam(value = "startTime",required = false)String startTime,
                                        @RequestParam(value = "endTime",required = false)String endTime)throws Exception{
        Map<String,Object> map=sysOprLogService.getOprLogList(page,limit,cname,startTime,endTime);
        return map;
    }

    @RequestMapping(value = "/opr/delete",method = RequestMethod.POST)
    @ResponseBody
    public JsonResVo oprdelete(@RequestParam("ids") String ids)throws Exception{
        JsonResVo result=new JsonResVo();
        Serializable[] logIds=StringUtils.split(ids,",");
        for (Serializable id:logIds){
            sysOprLogService.deleteById(null,id);
        }
        result.setSuccess(true);
        result.setMsg("删除成功");
        return result;
    }
}
