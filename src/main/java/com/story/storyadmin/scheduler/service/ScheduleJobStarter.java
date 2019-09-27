package com.story.storyadmin.scheduler.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.story.storyadmin.constant.enumtype.YNFlagStatusEnum;
import com.story.storyadmin.domain.entity.sysmgr.ScheduleJob;
import com.story.storyadmin.service.sysmgr.ScheduleJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ScheduleJobStarter implements ApplicationRunner {

    @Autowired
    private StorySchedulerService storySchedulerService;

    @Autowired
    private ScheduleJobService scheduleJobService;

    /**
     * 会在服务启动完成后立即执行
     */
    @Override
    public void run(ApplicationArguments args) {
        log.info("================= 启动所有定时任务 ================");
        List<ScheduleJob> startJobs;
        QueryWrapper<ScheduleJob> query= new QueryWrapper<>();
        query.eq("yn_flag", YNFlagStatusEnum.VALID.getCode());
        query.eq("start_job",true);
        query.orderByAsc("start_time");
        startJobs = scheduleJobService.list(query);
        startJobs.stream().forEach(j -> {
            storySchedulerService.addJob(j.getJobId(), j.getJobClass(), j.getCron(), j.getStartTime());
        });
        storySchedulerService.startJobs();
    }
}