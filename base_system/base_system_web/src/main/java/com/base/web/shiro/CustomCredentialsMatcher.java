package com.base.web.shiro;

import com.base.web.utils.CustomizedPropertyConfigurer;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomCredentialsMatcher extends HashedCredentialsMatcher {
    /**
     * 集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
     */
    private Cache<String, AtomicInteger> passwordRetryCache;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomCredentialsMatcher.class);
    private static final String RETRY_CACHE_NAME = "passwordRetryCache";
    private static final Integer MAX_RETRY_COUNT = 3;//登陆次数限制

    /**
     * cacheManager对象由外部注入
     * 可以是Ehcache的CacheManager
     * 也可以注入自定义的CacheManager
     *
     * @param cacheManager cacheManager
     */
    private CustomCredentialsMatcher(CacheManager cacheManager) {
        /**
         * 此处从CacheManager中获取缓存Cache对象
         * 本例中获取的缓存对象是从Ehcache.xml配置中获取
         * 如果是我们自定义CacheManager的话，
         * 可用下面的实现思路：
         * 先尝试从缓区池中获取名为RETRY_CACHE_NAME的缓存对象
         * 如果缓存池中没有名为RETRY_CACHE_NAME的缓存对象
         * 那么则创建名为RETRY_CACHE_NAME的缓存对象，并放入到缓存池中
         * 保证本类属性passwordRetryCache不为空
         */
        passwordRetryCache = cacheManager.getCache(RETRY_CACHE_NAME);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        final String clientUserName = (String) token.getPrincipal();
        //先查看是否系统中是否已有登录次数缓存
        AtomicInteger retryCount = passwordRetryCache.get(clientUserName);
        // 如果之前没有登录缓存，则创建一个登录次数缓存。
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
        }
        //将缓存记录的登录次数加1
        retryCount.incrementAndGet();
        //如果有且次数已经超过限制，则驳回本次登录请求。
        if (retryCount.get() > MAX_RETRY_COUNT) {
            LOGGER.error("登录次数超过限制");
            throw new ExcessiveAttemptsException("用户:" + clientUserName + "登录次数已经超过限制");
        }
        //并将其保存到缓存中
        passwordRetryCache.put(clientUserName, retryCount);
        //debug
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("用户:{},尝试登录次数:{}", clientUserName, retryCount.get());
        }
        //调用超类验证器，判断是否登录成功
        boolean isMatcher = super.doCredentialsMatch(token, info);
        //如果成功则清除缓存
        if (isMatcher) {
            passwordRetryCache.remove(clientUserName);
        }
        return isMatcher;
    }

    public Cache<String, AtomicInteger> getPasswordRetryCache() {
        return passwordRetryCache;
    }

    public void setPasswordRetryCache(Cache<String, AtomicInteger> passwordRetryCache) {
        this.passwordRetryCache = passwordRetryCache;
    }

}
