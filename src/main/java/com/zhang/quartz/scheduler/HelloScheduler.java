package com.zhang.quartz.scheduler;

import com.zhang.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class )
                .withIdentity("myJob","zhang")
                .usingJobData("msg1","xxx")
                .usingJobData("msg2",3.12)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("myTrigger","zhang")
                .startNow().withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds( 2 ).repeatForever()
                ).build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob( jobDetail , trigger );
    }
}
