package com.base.web.service;

public interface ISysJedisClusterClient {
    String get(String key)throws Exception;

    String set(String key, String value)throws Exception;

    String hget(String hkey, String key)throws Exception;

    long hset(String hkey, String key, String value)throws Exception;

    long incr(String key)throws Exception;

    long expire(String key, int second)throws Exception;

    long ttl(String key)throws Exception;

    long del(String key)throws Exception;

    long hdel(String hkey, String key)throws Exception;
}
