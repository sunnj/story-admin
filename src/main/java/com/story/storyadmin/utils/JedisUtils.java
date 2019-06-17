package com.story.storyadmin.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Redis 访问操作工具类
 * 
 * @author 
 *
 */
@Component
public class JedisUtils {

	@Autowired  
    private RedisTemplate<String, Object> redisTemplate; 
	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valOpsStr;
	@Resource(name = "redisTemplate")
	private SetOperations<String, String> valOpsSet;
	@Resource(name = "redisTemplate")
	private ZSetOperations<String, String> valOpsZSet;
	@Resource(name = "redisTemplate")
	ListOperations<String, String> valOpsList;
	@Resource(name = "redisTemplate")
	private HashOperations<String, String, Object> valOpsHash;
	
    private static final ObjectMapper mapper = new ObjectMapper();

    /** 
     * 将数据存入缓存 
     * 
     * @param key 
     * @param val 
     * @return 
     */  
    public void saveString(String key, String val) {  
        valOpsStr.set(key, val);  
    }  
    
    /** 
     * 将数据存入缓存的集合中 
     * 
     * @param key 
     * @param val 
     * @return 
     */  
    public void saveToSet(String key, String val) {  
    	valOpsSet.add(key, val);  
    }  
  
    /** 
     * 从Set中获取数据
     * 
     * @param key 
     * @return keyValue 
     */  
    public String getFromSet(String key) {  
        return valOpsSet.pop(key);  
    } 
    
    /** 
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET 
     * if Not eXists』(如果不存在，则 SET)的简写。 <br> 
     * 保存成功，返回 true <br> 
     * 保存失败，返回 false 
     */
    public boolean saveNX(String key, String val) {  
    	return valOpsStr.setIfAbsent(key, val);
    }  
    
    /** 
     * 将 key的值保存为 value ，当且仅当 key 不存在。 若给定的 key 已经存在，则 SETNX 不做任何动作。 SETNX 是『SET 
     * if Not eXists』(如果不存在，则 SET)的简写。 <br> 
     * 保存成功，返回 true <br> 
     * 保存失败，返回 false 
     * 
     * @param key 
     * @param val 
     * @param expire 单位：秒
     *            超时时间 
     * @return 保存成功，返回 true 否则返回 false 
     */  
    public boolean saveNX(String key, String val, int expire) {  
        boolean ret = saveNX(key, val);  
        if (ret) {  
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);  
        }  
        return ret;  
    }  
  
    /** 
     * 将数据存入缓存（并设置失效时间） 
     * 
     * @param key 
     * @param val 
     * @param seconds 
     * @return 
     */  
    public void saveString(String key, String val, int seconds) {  
    	valOpsStr.set(key, val, seconds, TimeUnit.SECONDS);  
    } 
    
    /** 
     * 将自增变量存入缓存 
     */  
    public void saveSeq(String key, long seqNo) {  
        redisTemplate.delete(key);  
        valOpsStr.increment(key, seqNo);  
    }  
  
    /** 
     * 将递增浮点数存入缓存 
     */  
    public void saveFloat(String key, float data) {  
        redisTemplate.delete(key);  
        valOpsStr.increment(key, data);  
    } 
    
    /** 
     * 保存复杂类型数据到缓存 
     * 
     * @param key 
     * @param obj 
     * @return 
     */
    public void saveObject(String key, Object obj){
        try {
            redisTemplate.opsForValue().set(key, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /** 
     * 保存复杂类型数据到缓存（并设置失效时间） 
     * 
     * @param key 
     * @param obj
     * @param seconds 
     * @return 
     * @throws JsonProcessingException 
     */  
    public void saveObject(String key, Object value, int seconds) {
        if(seconds>0){
            redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        }else{
            this.saveObject(key, value);
        }
    }
    
    /** 
     * 功能: 存到指定的队列中，不限制队列大小
     * 左进右出
     * 
     * @param key 
     * @param val 
     *            
     */  
    public void saveToQueue(String key, String val) {
    	saveToQueue(key, val, 0);
    }
    
    /** 
     * 功能: 存到指定的队列中
     * 左进右出
     * 
     * @param key 
     * @param val 
     * @param size 队列大小限制 0：不限制 
     *            
     */  
    public void saveToQueue(String key, String val, long size) {  
        if (size > 0 && valOpsList.size(key) >= size) {  
        	valOpsList.rightPop(key);  
        }  
        valOpsList.leftPush(key, val);  
    } 
    
    /** 
     * 保存到hash集合中 
     * 
     * @param hName 集合名 
     *           
     * @param key 
     * @param val 
     */  
    public void hashSet(String hName, String key, String value) {  
    	valOpsHash.put(hName, key, value);  
    }
    
    /** 
     * 保存到hash集合中 
     * 
     * @param hName 集合名 
     *           
     * @param key 
     * @param val 
     */  
    public void hashSet(String hName, Map<String, String> hashMap) {  
    	valOpsHash.putAll(hName, hashMap);
    }
    
    /** 
     * 根据key获取所以值 
     *  
     * @param key 
     * @return 
     */  
    public Map<String, Object> hGetAll(String key) {  
        return valOpsHash.entries(key);  
    }
    
    /** 
     * 保存到hash集合中 
     * 
     * @param <T> 
     * 
     * @param hName 集合名 
     * @param key 
     * @param val 
     * @throws JsonProcessingException 
     */  
    public <T> void hashSet(String hName, String key, T t) throws JsonProcessingException {  
        hashSet(hName, key, mapper.writeValueAsString(t));  
    }
    
    /** 
     * 保存到hash集合中 只在 key 指定的哈希集中不存在指定的字段时，设置字段的值。如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 
     * key 关联。如果字段已存在，该操作无效果。 
     * 
     * @param hName 
     *            集合名 
     * @param key 
     * @param val 
     */  
    public void hsetnx(String hName, String key, String value) {  
    	valOpsHash.putIfAbsent(hName, key, value);
    }  
  
    /** 
     * 保存到hash集合中 只在 key 指定的哈希集中不存在指定的字段时，设置字段的值。如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 
     * key 关联。如果字段已存在，该操作无效果。 
     * 
     * @param <T> 
     * 
     * @param hName 
     *            集合名 
     * @param key 
     * @param val 
     * @throws JsonProcessingException 
     */  
    public <T> void hsetnx(String hName, String key, T t) throws JsonProcessingException {  
        hsetnx(hName, key, mapper.writeValueAsString(t));  
    }
    
    /**
     * 删除Hash中的key项
     * 
     * @param hName
     * @param key
     */
    public void hdel(String hName, String key) {
    	valOpsHash.delete(hName, key);
    }

    /**
     * 取得复杂类型数据
     * @param key
     * @return
     */
    public Object getObject(String key) {
        return key==null?null:redisTemplate.opsForValue().get(key);
    }
    
//    /**
//     * 取得复杂类型数据
//     *
//     * @param key
//     * @param obj
//     * @param clazz
//     * @return
//     * @throws IOException
//     * @throws JsonMappingException
//     * @throws JsonParseException
//     */
//    public <T> T get(String key, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
//        String value = valOpsStr.get(key);
//        if (value == null) {
//            return null;
//        }
//        return mapper.readValue(value, clazz);
//    }
    
    /** 
     * 从缓存中取得字符串数据 
     * 
     * @param key 
     * @return 数据 
     */  
    public String get(String key) {
        return valOpsStr.get(key);  
    } 
    
    /** 
     * 
     * 功能: 从指定队列里取得数据
     * 
     * @param key 
     * @param size 数据长度 
     *            
     * @return 
     */  
    public List<String> getFromQueue(String key) {  
    	return getFromQueue(key, 0);
    }
    
    public List<String> getFromQueue(String key, long size) {  
        boolean flag = redisTemplate.execute((RedisCallback<Boolean>) connection -> {  
            return connection.exists(key.getBytes());  
        });  
        if (flag) {  
            return new ArrayList<>();  
        }  
        if (size > 0) {  
            return valOpsList.range(key, 0, size - 1);  
        } else {  
            return valOpsList.range(key, 0, valOpsList.size(key) - 1);  
        }  
    }  
    
    /** 
     * 功能: 从指定队列里取得数据
     * 
     * @param key 
     * @return 
     */  
    public String popQueue(String key) {  
        return valOpsList.rightPop(key);  
  
    }  
  
    /** 
     * 取得序列值的下一个 
     * 
     * @param key 
     * @return 
     */  
    public Long getSeqNext(String key) {  
        return redisTemplate.execute((RedisCallback<Long>) connection -> {  
            return connection.incr(key.getBytes());  
  
        });  
    }  
  
    /** 
     * 取得序列值的下一个，增加 value
     * 
     * @param key 
     * @param value
     * @return 
     */  
    public Long getSeqNext(String key, long value) {  
        return redisTemplate.execute((RedisCallback<Long>) connection -> {  
            return connection.incrBy(key.getBytes(), value);  
        });  
  
    }  
  
    /** 
     * 将序列值回退一个 
     * 
     * @param key 
     * @return 
     */  
    public void getSeqBack(String key) {  
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.decr(key.getBytes()));  
    } 
    
    /** 
     * 增加浮点数的值 
     * 
     * @param key 
     * @return 
     */  
    public Double incrFloat(String key, double incrBy) {  
        return redisTemplate.execute((RedisCallback<Double>) connection -> {  
            return connection.incrBy(key.getBytes(), incrBy);  
        });  
    } 
    
    /** 
     * 从hash集合里取得 
     * 
     * @param hName 
     * @param key 
     * @return 
     */  
    public Object hashGet(String hName, String key) {  
        return valOpsHash.get(hName, key);  
    }  
  
    public <T> T hashGet(String hName, String key, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {  
        return mapper.readValue((String) hashGet(hName, key), clazz);  
    }  
    
    /** 
     * 判断是否缓存了数据 
     * 
     * @param key 
     *            数据KEY 
     * @return 判断是否缓存了 
     */  
    public boolean exists(String key) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {  
            return connection.exists(key.getBytes());  
        });  
    }
    
    /** 
     * 判断hash集合中是否缓存了数据, 有问题
     * 
     * @param hName 
     * @param key 
     * @return 判断是否缓存了 
     */  
    public boolean hashExists(String hName, String key) {  
    	return valOpsHash.hasKey(hName, key);
    } 
    
    /** 
     * 判断是否缓存在指定的集合中 
     * 
     * @param key 
     * @param val 
     * 
     * @return 判断是否缓存了 
     */  
    public boolean isMember(String key, String val) {  
    	return valOpsSet.isMember(key, val);
    }
    
    /** 
     * 从缓存中删除数据 
     * 
     * @param string 
     * @return 
     */  
    public void delKey(String key) {  
    	redisTemplate.delete(key);
//        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(key.getBytes()));  
    }  
    
    /** 
     * 设置超时时间 
     * 
     * @param key 
     * @param seconds 
     */  
    public void expire(String key, int seconds) {  
    	redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }  
  
    /** 
     * 列出set中所有成员 
     * 
     * @param setName 
     * @return 
     */  
    public Set<String> listSet(String setName) {  
        return valOpsSet.members(setName);
    } 
    
    /** 
     * 向set中追加一个值 
     * 
     * @param setName 
     * @param value 
     */  
    public void appendSet(String setName, String value) {  
    	valOpsSet.add(setName, value);
    }  
    
    /** 
     * 向sorted set中追加一个值 
     * 
     * @param key 
     * @param score 
     * @param member 
     */  
    public void saveToSortedset(String key, String member, Double score) {  
    	valOpsZSet.add(key, member, score);
    } 
  
    /** 
     * 根据成员名取得sorted sort分数 
     * 
     * @param key 
     *            set名 
     * @param member 
     *            成员名 
     * @return 分数 
     */  
    public Double getMemberScore(String key, String member) {
    	return valOpsZSet.score(key, member);
    }  
  
    /** 
     * 从sorted set删除一个值 
     * 
     * @param key 
     * @param member 
     */  
    public  void delFromSortedset(String key, String member) {  
    	valOpsZSet.remove(key, member);
    }
    
    /** 
     * 逆序列出sorted set包括分数的set列表 
     * 
     * @param key 
     *            set名 
     * @param start 
     *            开始位置 
     * @param end 
     *            结束位置 
     * @return 列表 
     */  
    public Set<TypedTuple<String>> listSortedsetRev(String key, int start, int end) { 
    	return valOpsZSet.reverseRangeWithScores(key, start, end);
    }  
  
    /** 
     * 逆序取得sorted sort排名 
     * 
     * @param key 
     *            set名 
     * @param member 
     *            成员名 
     * @return 排名 
     */  
    public Long getReverseRank(String key, String member) {  
    	return valOpsZSet.reverseRank(key, member);
    } 
    
    /** 
     * 从hashmap中删除一个值 
     * 
     * @param key 
     * @param field 
     */  
    public void delFromMap(String key, String field) {  
    	valOpsHash.delete(key, field);
    }
    
    /** 
     * 将所有指定的值插入到存于 key 的列表的头部。如果 key 不存在，那么在进行 push 操作前会创建一个空列表 
     * 
     * @param <T> 
     * 
     * @param key 
     * @param value 
     * @return 
     * @throws JsonProcessingException 
     */  
    public <T> Long lpush(String key, T value) throws JsonProcessingException {  
    	return valOpsList.leftPush(key, mapper.writeValueAsString(value));
    }  
  
    /** 
     * 只有当 key 已经存在并且存着一个 list 的时候，在这个 key 下面的 list 的头部插入 value。 与 LPUSH 相反，当 
     * key 不存在的时候不会进行任何操作 
     * 
     * @param key 
     * @param value 
     * @return 
     * @throws JsonProcessingException 
     */  
    public <T> Long lpushx(String key, T value) throws JsonProcessingException {  
        return valOpsList.leftPushIfPresent(key, mapper.writeValueAsString(value));  
    }  
  
    /** 
     * 返回存储在 key 里的list的长度。 如果 key 不存在，那么就被看作是空list，并且返回长度为 0 
     * 
     * @param key 
     * @return 
     */  
    public Long llen(String key) {  
        return valOpsList.size(key);  
    }  
  
    /** 
     * 返回存储在 key 的列表里指定范围内的元素。 start 和 end 
     * 偏移量都是基于0的下标，即list的第一个元素下标是0（list的表头），第二个元素下标是1，以此类推 
     * 
     * @param key 
     * @return 
     */  
    public List<String> lrange(String key, long start, long end) {  
        return valOpsList.range(key, start, end);  
    }  
  
    /** 
     * 移除并且返回 key 对应的 list 的第一个元素 
     * 
     * @param key 
     * @return 
     */  
    public String lpop(String key) {  
        return valOpsList.leftPop(key);  
    } 
    
}
