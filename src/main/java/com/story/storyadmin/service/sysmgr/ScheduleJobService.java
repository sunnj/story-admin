package com.story.storyadmin.service.sysmgr;

import com.story.storyadmin.domain.StoryServiceException;
import com.story.storyadmin.domain.entity.sysmgr.ScheduleJob;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 定时任务 服务类
 * </p>
 *
 * @author sunnj
 * @since 2019-08-18
 */
public interface ScheduleJobService extends IService<ScheduleJob> {

    /**
     * 检查 jobId 是否已经存在
     * @param id
     * @param jobId
     * @return
     * @throws StoryServiceException
     */
    boolean checkExistJob(Long id, String jobId) throws StoryServiceException;

    /**
     * 获取系统的Job
     *
     * @return
     */
    List<ScheduleJob> findScheduleJobCombo();

    /**
     * 更新运行时Job信息
     * @param jobId
     * @param fireTime
     * @param previousFireTime
     * @param nextFireTime
     * @param failReason
     * @throws StoryServiceException
     */
    void updateRuntimeJob(String jobId, Date fireTime, Date previousFireTime, Date nextFireTime, String failReason) throws StoryServiceException;

}
