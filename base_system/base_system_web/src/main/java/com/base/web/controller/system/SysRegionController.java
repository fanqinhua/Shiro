package com.base.web.controller.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/regionMan")
public class SysRegionController {
    private Logger logger= LoggerFactory.getLogger(SysRegionController.class);

    @RequestMapping("/main")
    public String login(HttpServletRequest request){
        return "system/regionMan/main";
    }
}
