package com.story.storyadmin.config.shiro.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.story.storyadmin.config.shiro.security.JwtUtil;
import com.story.storyadmin.constant.SecurityConsts;
import com.story.storyadmin.utils.JedisUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 重写Shiro的Cache保存读取
 * @param <K>
 * @param <V>
 */

public class ShiroCache<K,V> implements Cache<K,V> {

    private JedisUtils jedisUtils;

    public ShiroCache(JedisUtils jedisUtils) {
        this.jedisUtils = jedisUtils;
    }

    /**
     * 获取缓存
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public Object get(Object key) throws CacheException{
        String tempKey= this.getKey(key);
        Object result=null;
        if(jedisUtils.exists(tempKey)){
            try {
                result = jedisUtils.getObject(tempKey,Object.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 保存缓存
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    @Override
    public Object put(Object key, Object value) throws CacheException {
        String tempLey= this.getKey(key);
        try {
            jedisUtils.saveObject(tempLey, value);
            return value;
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 移除缓存
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public Object remove(Object key) throws CacheException {
        String tempKey= this.getKey(key);
        if(jedisUtils.exists(tempKey)){
            jedisUtils.delKey(tempKey);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 20;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        Set keys = this.keys();
        List<V> values = new ArrayList<>();
        try {
            for (Object key : keys) {
                values.add((V)jedisUtils.getObject(this.getKey(key)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

    /**
     * 缓存的key名称获取为shiro:cache:account
     * @param key
     * @return
     */
    private String getKey(Object key) {
        return SecurityConsts.PREFIX_SHIRO_CACHE + JwtUtil.getClaim(key.toString(), SecurityConsts.ACCOUNT);
    }

}
