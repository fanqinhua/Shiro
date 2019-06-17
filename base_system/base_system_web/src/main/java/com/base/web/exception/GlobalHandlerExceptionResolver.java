package com.base.web.exception;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver{
    private Logger logger= LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);
    /**
     * 在这里处理所有得异常信息
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object o, Exception ex) {
        logger.error("后台异常", ex);
        Map<String, Object> errorInfo = Maps.newHashMap();
        errorInfo.put("message", StringUtils.defaultIfBlank(ex.getMessage(), "系统异常。"));

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("error", errorInfo);
        ex.printStackTrace();
        if (ex instanceof KickOutException) {
            return new ModelAndView("error/kickout", model);
        }if (ex instanceof ValidateCodeOverdueException) {
            return new ModelAndView("error/kickout", model);
        }
        return new ModelAndView();
    }

    /**
     * 将错误信息添加到response中
     *
     * @param msg
     * @param response
     */
    public static void printWrite(String msg, HttpServletResponse response) {
        try {
            PrintWriter pw = response.getWriter();
            pw.write(msg);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
