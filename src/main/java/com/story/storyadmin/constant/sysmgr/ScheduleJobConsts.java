package com.story.storyadmin.constant.sysmgr;

/**
 * 系统定时任务常量
 */
public final class ScheduleJobConsts {

	private ScheduleJobConsts() {
		throw new IllegalAccessError("ScheduleJobConsts 常量类，不能实例化！");
	}
	
	/**
	 * 定时任务Job所在包
	 */
	public final static String JOB_PKG = "com.story.storyadmin.scheduler";
	
}
