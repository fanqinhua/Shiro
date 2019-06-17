package com.base.web.controller.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/datadict")
public class SysDatadictController {
    private Logger logger= LoggerFactory.getLogger(SysDatadictController.class);

    @RequestMapping("/main")
    public String login(HttpServletRequest request){
        return "system/datadict/main";
    }
}
