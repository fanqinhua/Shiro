package com.base.web.service.impl;

import com.base.web.service.ISysJedisClusterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
public class SysJedisClusterClientImpl implements ISysJedisClusterClient {
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public String get(String key) throws Exception{
        String string = jedisCluster.get(key);
        jedisCluster.close();
        return string;
    }

    @Override
    public String set(String key, String value) throws Exception{
        String string = jedisCluster.set(key, value);
        jedisCluster.close();
        return string;
    }

    @Override
    public String hget(String hkey, String key) throws Exception{
        String string = jedisCluster.hget(hkey, key);
        jedisCluster.close();
        return string;
    }

    @Override
    public long hset(String hkey, String key, String value)throws Exception {
        long result = jedisCluster.hset(hkey, key, value);
        jedisCluster.close();
        return result;
    }

    @Override
    public long incr(String key)throws Exception {
        long result = jedisCluster.incr(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public long expire(String key, int second)throws Exception {
        long result = jedisCluster.expire(key, second);
        jedisCluster.close();
        return result;
    }

    @Override
    public long ttl(String key) throws Exception{
        long result = jedisCluster.ttl(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public long del(String key)throws Exception {
        long result = jedisCluster.del(key);
        jedisCluster.close();
        return result;
    }

    @Override
    public long hdel(String hkey, String key) throws Exception{
        long result = jedisCluster.hdel(hkey, key);
        jedisCluster.close();
        return result;
    }
}
