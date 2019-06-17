package com.base.helper;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class WebHelper {
    public WebHelper() {
    }

    public static String getCookie(String name, String defaultValue) {
        Cookie cookie = getCookieObject(name);
        return cookie != null ? cookie.getValue() : defaultValue;
    }

    public static String getCookie(String name) {
        return getCookie(name, (String)null);
    }

    public static Integer getCookieToInt(String name) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : null;
    }

    public static Integer getCookieToInt(String name, Integer defaultValue) {
        String result = getCookie(name);
        return result != null ? Integer.parseInt(result) : defaultValue;
    }

    public static Long getCookieToLong(String name) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : null;
    }

    public static Long getCookieToLong(String name, Long defaultValue) {
        String result = getCookie(name);
        return result != null ? Long.parseLong(result) : defaultValue;
    }

    public static Cookie getCookieObject(String name) {
        Cookie[] cookies = getServletRequest().getCookies();
        if (cookies != null) {
            Cookie[] var2 = cookies;
            int var3 = cookies.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Cookie cookie = var2[var4];
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }

    public static Cookie[] getCookieObjects() {
        Cookie[] result = getServletRequest().getCookies();
        return result != null ? result : new Cookie[0];
    }

    public static void setCookie(String name, String value, int maxAgeInSeconds, boolean isHttpOnly) {
        doSetCookie(name, value, maxAgeInSeconds, (String)null, (String)null, isHttpOnly);
    }

    public static void setCookie(String name, String value, int maxAgeInSeconds) {
        doSetCookie(name, value, maxAgeInSeconds, (String)null, (String)null, (Boolean)null);
    }

    public static void setCookie(Cookie cookie) {
        getServletResponse().addCookie(cookie);
    }

    public static void setCookie(String name, String value, int maxAgeInSeconds, String path, boolean isHttpOnly) {
        doSetCookie(name, value, maxAgeInSeconds, path, (String)null, isHttpOnly);
    }

    public static void setCookie(String name, String value, int maxAgeInSeconds, String path) {
        doSetCookie(name, value, maxAgeInSeconds, path, (String)null, (Boolean)null);
    }

    public static void setCookie(String name, String value, int maxAgeInSeconds, String path, String domain, boolean isHttpOnly) {
        doSetCookie(name, value, maxAgeInSeconds, path, domain, isHttpOnly);
    }

    public static void removeCookie(String name) {
        doSetCookie(name, (String)null, 0, (String)null, (String)null, (Boolean)null);
    }

    public static void removeCookie(String name, String path) {
        doSetCookie(name, (String)null, 0, path, (String)null, (Boolean)null);
    }

    public static void removeCookie(String name, String path, String domain) {
        doSetCookie(name, (String)null, 0, path, domain, (Boolean)null);
    }

    private static void doSetCookie(String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAgeInSeconds);
        if (path == null) {
            path = "/";
        }

        cookie.setPath(path);
        if (domain != null) {
            cookie.setDomain(domain);
        }

        getServletResponse().addCookie(cookie);
    }

    public static String getUserIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            return index != -1 ? ip.substring(0, index) : ip;
        } else {
            ip = request.getHeader("X-Real-IP");
            return StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip) ? ip : request.getRemoteAddr();
        }
    }

    public static HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static ServletContext getServletContext() {
        HttpServletRequest request = getServletRequest();
        return request != null ? request.getSession().getServletContext() : null;
    }

    public static HttpServletResponse getServletResponse() {
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    public static void out(String s) throws IOException {
        getServletResponse().setContentType("text/html; charset=utf-8");
        OutputStream stream = getServletResponse().getOutputStream();
        stream.write(s.getBytes("utf-8"));
        stream.flush();
        stream.close();
    }

    public static boolean isAjax(ServletRequest req) {
        HttpServletRequest request = (HttpServletRequest)req;
        return request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString());
    }

    public static void out(ServletResponse response, Map<String, String> resultMap) {
        PrintWriter out = null;

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(resultMap));
        } catch (Exception var7) {
            var7.printStackTrace();
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }

        }

    }

    public static Map<String, String> getParameterMap(HttpServletRequest request) throws Exception {
        Map<String, String> resultMap = new HashMap();
        Map<String, String[]> tempMap = request.getParameterMap();
        Set<String> keys = tempMap.keySet();
        Iterator var4 = keys.iterator();

        while(var4.hasNext()) {
            String key = (String)var4.next();
            String modelname = request.getParameter(key);
            resultMap.put(key, modelname);
        }

        return resultMap;
    }

    public static String toJSONString(HttpServletRequest request) throws Exception {
        Map<String, String> resultMap = getParameterMap(request);
        return JSON.toJSONString(resultMap);
    }
}
