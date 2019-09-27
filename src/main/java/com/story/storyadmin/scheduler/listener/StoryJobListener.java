package com.story.storyadmin.scheduler.listener;

import com.story.storyadmin.domain.StoryServiceException;
import com.story.storyadmin.service.sysmgr.ScheduleJobService;
import com.story.storyadmin.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoryJobListener implements JobListener {
	public static final String LISTENER_NAME = "StoryJobListener";
	
	@Autowired
	private ScheduleJobService scheduleJobService;

	public StoryJobListener(){}

	@Override
	public String getName() {
		return LISTENER_NAME;
	}

	/**
	 * 任务执行前
	 * @param context
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		System.out.println("jobToBeExecuted");
		System.out.println("Job : " + jobName + " is going to start...");
	}

	/**
	 * 任务被否决
	 * @param context
	 */
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		System.out.println("jobExecutionVetoed");
	}

	/**
	 * 任务被调度后
	 * @param context
	 * @param jobException
	 */
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String jobId = context.getJobDetail().getKey().getName();
		String failReason = null;
		if(jobException != null) {
			failReason = ExceptionUtils.getStackMsg(jobException);
		}
		try {
			scheduleJobService.updateRuntimeJob(jobId, context.getFireTime(), context.getPreviousFireTime(), context.getNextFireTime(), failReason);
		} catch (StoryServiceException e) {
			log.error("updateRuntimeJob {} error", jobId, e);
		}
	}
}
