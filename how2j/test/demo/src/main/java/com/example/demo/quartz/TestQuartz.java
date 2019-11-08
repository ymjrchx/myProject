package com.example.demo.quartz;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author chenxin
 * @date 2019/9/26 18:56
 */
public class TestQuartz {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                .startNow().withSchedule( SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(10)).build();

        JobDetail job = JobBuilder.newJob(MailJob.class)
                .withIdentity("mailjob1","mailgroup")
                .usingJobData("email","admin@10086.com")
                .build();
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
        System.out.println("dddddd");
        Thread.sleep(20000);
        scheduler.shutdown(true);






    }













































}
