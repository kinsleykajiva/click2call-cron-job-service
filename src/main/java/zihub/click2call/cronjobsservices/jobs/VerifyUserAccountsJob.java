package zihub.click2call.cronjobsservices.jobs;


import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import zihub.click2call.cronjobsservices.info.TimerInfo;
import zihub.click2call.cronjobsservices.util.Utils;

import java.io.IOException;

import static zihub.click2call.cronjobsservices.util.Utils.makeRequestServices;

@Component
@Slf4j
public class VerifyUserAccountsJob  implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        TimerInfo info = (TimerInfo) jobDataMap.get(VerifyUserAccountsJob.class.getSimpleName());
        String jsonPost = new JSONObject()
                .put("cron", VerifyUserAccountsJob.class.getSimpleName())
                .toString();
        log.error("VerifyUserAccountsJob is  '{}'", info.getCronExpression());

        try {
            makeRequestServices(Utils.SERVICES_NAMES.AUTH_SERVICE + "auth/api/v1/users/cron-job/notify-users-to-activate-verify-account", jsonPost);
        } catch (IOException e) {
            e.printStackTrace();
            log.error( "Error: " + e.getMessage());
        }



    }
}
