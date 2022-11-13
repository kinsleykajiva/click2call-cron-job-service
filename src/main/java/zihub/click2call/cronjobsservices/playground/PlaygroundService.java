package zihub.click2call.cronjobsservices.playground;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zihub.click2call.cronjobsservices.info.TimerInfo;
import zihub.click2call.cronjobsservices.jobs.HelloWorldJob;
import zihub.click2call.cronjobsservices.jobs.VerifyUserAccountsJob;
import zihub.click2call.cronjobsservices.timerservice.SchedulerService;

import java.util.List;

@Service
@Slf4j
public class PlaygroundService {
    private final SchedulerService scheduler;

    @Autowired
    public PlaygroundService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runVerifyUserAccountsJob() {
        final TimerInfo info = new TimerInfo();


// get a crojob explainer

        //info.setCronExpression("0 0/2 8-17 * * ?"); //fire every other minute, between 8am and 5pm, every day:
       // info.setCronExpression("0 0/5 * * * ?"); //fire every At every 5th minute:
      //  info.setCronExpression("0 0/5 17 * * ?"); //fire every At every 5th minute:
//
        scheduler.schedule2(VerifyUserAccountsJob.class, info);
    }
    public void runHelloWorldJob() {
        final TimerInfo info = new TimerInfo();
        info.setTotalFireCount(5);
        info.setRemainingFireCount(info.getTotalFireCount());
        info.setRepeatIntervalMs(5000);
        info.setInitialOffsetMs(1000);

        info.setCallbackData("My callback data");

        scheduler.schedule(HelloWorldJob.class, info);
    }

    public Boolean deleteTimer(final String timerId) {
        return scheduler.deleteTimer(timerId);
    }

    public List<TimerInfo> getAllRunningTimers() {
        return scheduler.getAllRunningTimers();
    }

    public TimerInfo getRunningTimer(final String timerId) {
        return scheduler.getRunningTimer(timerId);
    }
}
