package com.story.storyadmin.service.sysmgr;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.story.storyadmin.config.mongo.StoryMongoTemplate;
import com.story.storyadmin.dao.SysLogDao;
import com.story.storyadmin.domain.entity.sysmgr.SysLog;
import com.story.storyadmin.utils.DateUtils;
import com.story.storyadmin.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SysLogService extends StoryMongoTemplate<SysLog> {

    @Autowired
    private SysLogDao sysLogDao;

    public IPage<SysLog> findPage(Page<SysLog> page, SysLog sysLog){
        return super.findPage(page,sysLog, "visitTime","desc",SysLog.class);
    }

    /**
     * 记录系统日志
     * @param sysLog
     * @param id
     * @param spendTime
     */
    @Async("syslogAsync")
    public void recordLog(SysLog sysLog, String id, Long spendTime) {
        if (sysLog != null) {
            sysLogDao.save(sysLog);
        } else {
            Optional<SysLog> existSysLog = sysLogDao.findById(id);
            SysLog existLog= existSysLog.get();
            if (existLog != null) {
                existLog.setSpendTime(spendTime);
                sysLogDao.save(existLog);
            }
        }
    }

    public SysLog findById(String id){
        Optional<SysLog> existSysLog = sysLogDao.findById(id);
        SysLog existLog= existSysLog.get();
        return existLog;
    }

    @Override
    protected Criteria buildCriteria(SysLog filter) {
        Criteria criteria = new Criteria();
        String account = filter.getAccount();
        String ip = filter.getIp();
        if(StringUtils.isNotEmpty(account)) {
            criteria.and("account").regex(".*?"+account+".*");
        }
        if(StringUtils.isNotEmpty(ip)) {
            criteria.and("ip").regex(".*?"+ip+".*");
        }

        if(filter.getStartDate()!=null){
            criteria.and("visitTime").gte(filter.getStartDate());
        }
        if(filter.getEndDate()!=null){
            criteria.lte(filter.getEndDate());
        }
        return criteria;
    }

}
