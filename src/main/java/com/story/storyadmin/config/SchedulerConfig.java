package com.story.storyadmin.config;

import com.story.storyadmin.scheduler.factory.StoryJobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * 定时任务配置
 * @author sunnj
 *
 */
@Configuration
public class SchedulerConfig {
	
	@Autowired
	@Qualifier("storyJobFactory")
    private StoryJobFactory storyJobFactory;
	
	@Bean(name="storySchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setQuartzProperties(quartzProperties());
        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(storyJobFactory);
        return factory;
    }
	
	@Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactory = new PropertiesFactoryBean();
        propertiesFactory.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactory.afterPropertiesSet(); // 必须手动触发
        return propertiesFactory.getObject();
    }
}
