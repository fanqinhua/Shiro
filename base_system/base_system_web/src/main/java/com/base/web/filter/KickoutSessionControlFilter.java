package com.base.web.filter;

import com.alibaba.fastjson.JSON;
import com.base.pojo.bean.ShiroUserBean;
import com.base.web.shiro.ShiroUtils;
import org.apache.ibatis.jdbc.Null;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author fqh
 * @version V1.0
 * @Title: KickoutSessionControlFilter.java
 * @Package com.agood.bejavagod.controller.filter
 * @Description: 同一用户后登陆踢出前面的用户
 * @date 2016年12月12日 下午7:25:40
 */
@Controller
public class KickoutSessionControlFilter extends AccessControlFilter {
    private static Logger logger = LoggerFactory.getLogger(KickoutSessionControlFilter.class);
    private String kickoutUrl; //踢出后到的地址
    private boolean kickoutAfter = false; //踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession = 1; //同一个帐号最大会话数 默认1
    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }
        Session session = subject.getSession();
        ShiroUserBean user = (ShiroUserBean) subject.getPrincipal();
        String username = user.getLoginName();
        Serializable sessionId = session.getId();
        // 同步控制
        Deque<Serializable> deque = cache.get(username);
        if (deque == null) {
            deque = new LinkedList<Serializable>();
            cache.put(username, deque);
        }
        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
        }
        //如果队列里的sessionId数超出最大会话数，开始踢人
        while (deque.size() > maxSession) {
            Serializable kickoutSessionId = null;
            if (kickoutAfter) { //如果踢出后者
                kickoutSessionId = deque.removeFirst();
                logger.debug("踢出后登录的，被踢出的sessionId为: " + kickoutSessionId);
            } else { //否则踢出前者
                kickoutSessionId = deque.removeLast();
                logger.debug("踢出先登录的，被踢出的sessionId为： " + kickoutSessionId);
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (kickoutSession != null) {
                    //设置会话的kickout属性表示踢出了
                    kickoutSession.setAttribute("kickout", true);
                }
            } catch (Exception e) {//ignore exception
            }
        }
        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null && (Boolean) session.getAttribute("kickout") == true) {
            //会话被踢出了
            try {
//                subject.logout();
                ShiroUtils.logout();
            } catch (Exception e) { //ignore
                e.getStackTrace();
            }
            saveRequest(request);
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            httpServletRequest.getSession().setAttribute("toLogin", "0");
            WebUtils.issueRedirect(httpServletRequest, response, kickoutUrl);
            return false;
        }
        return true;
    }

    /**
     * 判断是否是AJAX请求
     * @param request
     * @return
     */
    private boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("x-requested-with");
        if (null != header && "XMLHttpRequest".equalsIgnoreCase(header)) {
            return true;
        }
        return false;
    }
}
