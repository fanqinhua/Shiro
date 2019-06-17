package com.base.web.controller.app;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/echarts")
public class SysEchartsController {

    @RequestMapping(value = "/reportrMan/main",method = RequestMethod.GET)
    public ModelAndView reportrMan(){
        return new ModelAndView("app/echarts/reportrMan/main",null);
    }
    @RequestMapping(value = "/billMan/main",method = RequestMethod.GET)
    public ModelAndView billMan(){
        return new ModelAndView("app/echarts/billMan/main",null);
    }
}
