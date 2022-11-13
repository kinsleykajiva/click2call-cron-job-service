package zihub.click2call.cronjobsservices.util;


import org.quartz.*;
import zihub.click2call.cronjobsservices.info.TimerInfo;

import java.util.Date;
import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public final class TimerUtils {
    private TimerUtils() {}

    public static JobDetail buildJobDetail(final Class jobClass, final TimerInfo info) {
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), info);

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class jobClass, final TimerInfo info) {
        SimpleScheduleBuilder builder = simpleSchedule().withIntervalInMilliseconds(info.getRepeatIntervalMs());

        if (info.isRunForever()) {
            builder = builder.repeatForever();
        } else {
            builder = builder.withRepeatCount(info.getTotalFireCount() - 1);
        }

        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis() + info.getInitialOffsetMs()))
                .build();
    }
    public static Trigger buildTrigger2(final Class jobClass, final TimerInfo info) {

        return TriggerBuilder
                .newTrigger()
                .startNow()
                .withIdentity(jobClass.getSimpleName())
                //.withSchedule(cronSchedule(info.getCronExpression()))
                .withSchedule(simpleSchedule().withIntervalInHours(6).repeatForever())

                .build();
    }
}
