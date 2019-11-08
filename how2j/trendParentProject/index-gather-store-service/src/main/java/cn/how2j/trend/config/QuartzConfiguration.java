package cn.how2j.trend.config;

import cn.how2j.trend.job.IndexDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {
    private static final int interval = 1;
    @Bean
    public JobDetail weatherDataSyncJonDetail(){
        return JobBuilder.newJob(IndexDataSyncJob.class).withIdentity("indexDataSyncJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger weatherDataSyncTrigger(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(interval).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJonDetail())
                .withIdentity("indexDataSyncTrigger").withSchedule(scheduleBuilder).build();

    }





















}
