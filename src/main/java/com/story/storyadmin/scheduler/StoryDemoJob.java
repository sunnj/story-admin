package com.story.storyadmin.scheduler;

import com.story.storyadmin.scheduler.base.ScheduleAnnotation;
import com.story.storyadmin.scheduler.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

@Slf4j
@ScheduleAnnotation(jobId = "StoryDemoJob", jobName = "框架演示Job")
public class StoryDemoJob extends BaseJob {

	@Override
	public void execute(JobExecutionContext context){
		log.info("STORY-ADMIN： 你好！");
	}

}
