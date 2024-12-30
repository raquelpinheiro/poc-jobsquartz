package com.poc.jobsquartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Job1 implements Job {

    static Logger _log = LoggerFactory.getLogger(Job1.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        _log.info("--- Executable: {} " + "\n" +
                "Params: {}" + "\n" +
                "Trigger: {}" + "\n" +
                "Job parent: {}",
                new Date(), jobExecutionContext.getMergedJobDataMap().getIntValue("Id"),
                jobExecutionContext.getTrigger().getKey().getName(),
                jobExecutionContext.getTrigger().getJobKey().getName());

        _log.info("-- Job Finished --");
    }
}
