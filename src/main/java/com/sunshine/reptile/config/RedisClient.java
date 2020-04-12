/**
 * 
 */
package com.sunshine.reptile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author 郑文耀
 * @Description redis 操作类
 * @date:   2019年1月3日 下午5:26:23
 */
@Configuration
public class RedisClient<K extends Serializable> {

    @Autowired
    private RedisTemplate<K, Object> redisTemplate;
    
    /**
     * @author 郑文耀
     * @Description: 切换链接db
     * @param @param db
     * @return void
     * @throws Exception 
     */
    public void switchDb(int db) {
        LettuceConnectionFactory factory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        factory.setDatabase(db);
        redisTemplate.setConnectionFactory(factory);
        factory.resetConnection();
    }
    
    /**
     * 判断K是否在redis中
     * @param K
     * @return
     */
    public boolean exists(final K K) {
        assertIsNull(K);
        return redisTemplate.hasKey(K);
    }

    /**
     * 从redis中获取数据
     * @param K
     * @return
     */
    public Object get(final K K) {
        assertIsNull(K);
        ValueOperations<K, Object> operations = redisTemplate.opsForValue();
        return operations.get(K);
    }

    /**
     * 讲数据插入redis中
     * @param k
     * @param v
     */
    public void set(final K k, Object v) {
        assertIsNull(k, v);
        ValueOperations<K, Object> operations = redisTemplate.opsForValue();
        operations.set(k, v);
    }

    /**
     * 将数据插入redis中，设置缓存失效时间
     * @param k
     * @param v
     * @param expire 失效时间，秒
     */
    public void set(final K k, Object v, long expire) {
        assertIsNull(k, v);
        ValueOperations<K, Object> operations = redisTemplate.opsForValue();
        operations.set(k, v);
        redisTemplate.expire(k, expire, TimeUnit.SECONDS);
    }

    /**
     * 删除
     * @param k
     */
    public void remove(final K k) {
        assertIsNull(k);
        if (exists(k)) {
            redisTemplate.delete(k);
        }
    }
    
    /**
     * 添加单个
     * 默认过期时间为两小时
     * @param key    key
     * @param hashKey  hash的key
     * @param 值 
     */
    public void hset(final K key,String hashKey,Object value){
        redisTemplate.opsForHash().put(key, hashKey, value);
    }
    
    /**
     * @author 郑文耀 
     * @Description: 删除hash中的值
     * @param @param key
     * @param @param hashKey
     * @return void
     * @throws Exception 
     */
    public void hdel(final K key, Object... hashKey){
        redisTemplate.opsForHash().delete(key,hashKey);  
    }
    
    /**
     * 添加单个
     * @param key    key
     * @param filed  hash的key
     * @param value 值 
     * @param expire 过期时间（秒）
     */
    public void hset(final K key,String hashKey,Object value,Integer expire){
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, expire,TimeUnit.SECONDS);
    }
    
    /**
     * 添加HashMap
     *
     * @param key    key
     * @param hm    要存入的hash表
     */
    public void hset(final K key, HashMap<String,Object> hm){
        redisTemplate.opsForHash().putAll(key,hm);
    }
    
    /**
     * @author 郑文耀
     * @Description:添加HashMap 失效时间
     * @param @param key
     * @param @param hm
     * @param @param expire
     * @return void
     * @throws Exception 
     */
    public void hset(final K key, HashMap<String,Object> hm, Integer expire){
        redisTemplate.opsForHash().putAll(key,hm);
        redisTemplate.expire(key, expire,TimeUnit.SECONDS);
    }
    
    /**
     * 如果key存在就不覆盖
     * @param key
     * @param hashKey
     * @param value
     */
    public void hsetAbsent(final K key,String hashKey,Object value){
        redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }
    
    /**
     * 查询key和field所确定的值
     *
     * @param key 查询的key
     * @param hashKey 查询hash的hashKey
     * @return HV
     */
    public Object hget(final K key,String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }
    
    /**
     * 查询该key下所有值
     *
     * @param key 查询的key
     * @return Map<HK, HV>
     */
    public Object hget(final K key) {
        return redisTemplate.opsForHash().entries(key);
    }
    
    /**
     * 删除key下所有值
     *
     * @param key 查询的key
     */
    public void deleteKey(final K key) {
        redisTemplate.opsForHash().getOperations().delete(key);
    }
    
    /**
     * 判断key和field下是否有值
     *
     * @param key 判断的key
     * @param hashKey 判断hash的hashKey
     */
    public Boolean hasKey(final K key,String hashKey) {
        return redisTemplate.opsForHash().hasKey(key,hashKey);
    }
    
    /**
     * 判断key下是否有值
     *
     * @param key 判断的key
     */
    public Boolean hasKey(final K key) {
        return redisTemplate.opsForHash().getOperations().hasKey(key);
    }

    /**
     * 批量删除
     * @param Ks
     */
    public void remove(final K[] Ks) {
        assertIsNull(Ks);
        redisTemplate.delete(Arrays.asList(Ks));
    }
    
    private void assertIsNull(K k) {
        Assert.state(k != null, "redis Key must not be null.");
    }

    private void assertIsNull(K[] Ks) {
        Assert.state(Ks != null, "redis Key must not be null.");
    }

    private void assertIsNull(K k, Object v) {
        Assert.state(k != null && v != null, "redis Key or value must not be null.");
    }
}
