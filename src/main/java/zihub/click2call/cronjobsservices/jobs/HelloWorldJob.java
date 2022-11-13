package zihub.click2call.cronjobsservices.jobs;


import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import zihub.click2call.cronjobsservices.info.TimerInfo;

@Component
@Slf4j
public class HelloWorldJob implements Job {
    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldJob.class);

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        TimerInfo info = (TimerInfo) jobDataMap.get(HelloWorldJob.class.getSimpleName());

        log.error("Name ::", info.getCallbackData());
        log.error("Remaining fire count is '{}'", info.getRemainingFireCount());
        LOG.info("Remaining fire count is '{}'", info.getRemainingFireCount());
    }
}
