package com.poc.jobsquartz;

import com.poc.jobsquartz.jobs.Job1;
import org.quartz.*;

import java.util.Calendar;

public class QuartzTest {

    public static void main(String[] args) {

        try {

            Scheduler scheduler = org.quartz.impl.StdSchedulerFactory.getDefaultScheduler();

            JobDetail job = JobBuilder.newJob(Job1.class).withIdentity("Job1", "Group1")
                    .usingJobData("Id", 12345)
                    .storeDurably(true)
                    .requestRecovery(true)
                    .build();

            scheduler.addJob(job, true);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis() + 5000);

            SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("Trigger1", "Group1")
                    .forJob(job).startAt(calendar.getTime()).build();

            // Se o Job da trigger foi interrompido durante a execução, então a trigger ainda existe e não deve criar a trigger novamente
            if (!scheduler.checkExists(trigger.getKey())){
                scheduler.scheduleJob(trigger);
            }

            scheduler.start();

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
