package com.springcloud.pro.commons.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * Created by Michael on 2017/10/30.
 */
@Component
public class RedisClient {

    private Logger logger = LoggerFactory.getLogger(RedisClient.class);
    //token 失效时间
    public  final  static  Integer EXPIRE_TIME=1800;//半小时
    //短信失效时间
    public  final static  Integer SMS_EXPIRE_TIME=600;//十分钟

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key) {
        return (T)redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, int timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    public void expire(String key, int timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }
    //JedisPool方法
    @Autowired
    private JedisPool jedisPool;

    public Jedis getResource() {
        return jedisPool.getResource();
    }

    public void returnResource(Jedis jedis) {
        if(jedis != null){
            jedisPool.close();
        }
    }

    public void setValue(String key, String value) {
        setValue(key,value,EXPIRE_TIME);
    }

    public void setValue(String key,String value,Integer times){
        Jedis jedis=null;
        try{
            jedis = getResource();
            jedis.setex(key,times,value);
            logger.info("Redis set success - " + key + ", value:" + value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + value);
        }finally{
            returnResource(jedis);
        }
    }

    public String getValue(String key) {
        String result = null;
        Jedis jedis=null;
        try{
            jedis = getResource();
            result = jedis.get(key);
            logger.info("Redis get success - " + key + ", value:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Redis set error: "+ e.getMessage() +" - " + key + ", value:" + result);
        }finally{
            returnResource(jedis);
        }
        return result;
    }

}
