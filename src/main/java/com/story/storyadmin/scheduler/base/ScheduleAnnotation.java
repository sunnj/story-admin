package com.story.storyadmin.scheduler.base;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface ScheduleAnnotation {

	/**
	 * 任务编号，全局必须唯一
	 * @return
	 */
	String jobId() default "";

	/**
	 * 任务名称
	 * 
	 * @return
	 */
	String jobName() default "";

	/**
	 * 任务是否废弃
	 * 
	 * @return
	 */
	boolean discard() default false;
}
