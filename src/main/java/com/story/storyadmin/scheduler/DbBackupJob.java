package com.story.storyadmin.scheduler;

import com.story.storyadmin.scheduler.base.BaseJob;
import com.story.storyadmin.scheduler.base.ScheduleAnnotation;
import com.story.storyadmin.service.sysmgr.BackupService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@ScheduleAnnotation(jobId = "DbBackupJob", jobName = "数据库备份Job")
public class DbBackupJob extends BaseJob {

	@Autowired
	private BackupService backupService;

	/**
	 * 数据备份任务
	 */
	@Override
	public void execute(JobExecutionContext context) {
		log.info("DbBackupJob备份任务执行开始：");
		backupService.backup();
		log.info("DbBackupJob备份任务执行完毕！");
	}

}
