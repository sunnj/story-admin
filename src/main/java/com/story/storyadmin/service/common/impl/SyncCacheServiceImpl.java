package com.story.storyadmin.service.common.impl;


import com.story.storyadmin.config.shiro.security.JwtProperties;
import com.story.storyadmin.constant.Constants;
import com.story.storyadmin.service.common.SyncCacheService;
import com.story.storyadmin.utils.JedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SyncCacheServiceImpl implements SyncCacheService {

    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    JedisUtils jedisUtils;

    /**
     * 获取redis中key的锁，乐观锁实现
     * @param lockName
     * @param expireTime 锁的失效时间
     * @return
     */
    @Override
    public Boolean getLock(String lockName, int expireTime) {
        Boolean result = Boolean.FALSE;
        try {
            boolean isExist = jedisUtils.exists(lockName);
            if(!isExist){
                jedisUtils.getSeqNext(lockName,0);
                jedisUtils.expire(lockName,expireTime<=0? Constants.ExpireTime.ONE_HOUR:expireTime);
            }
            long reVal =  jedisUtils.getSeqNext(lockName,1);
            if(1l==reVal){
                //获取锁
                result = Boolean.TRUE;
                log.info("获取redis锁:"+lockName+",成功");
            }else {
                log.info("获取redis锁:"+lockName+",失败"+reVal);
            }
        } catch (Exception e) {
            log.error("获取redis锁失败:"+lockName, e);
        }
        return result;
    }

    /**
     * 释放锁，直接删除key(直接删除会导致任务重复执行，所以释放锁机制设为超时30s)
     * @param lockName
     * @return
     */
    @Override
    public Boolean releaseLock(String lockName) {
        Boolean result = Boolean.FALSE;
        try {
            jedisUtils.expire(lockName, Constants.ExpireTime.TEN_SEC);
            log.info("释放redis锁:"+lockName+",成功");
        } catch (Exception e) {
            log.error("释放redis锁失败:"+lockName, e);
        }
        return result;
    }
}
