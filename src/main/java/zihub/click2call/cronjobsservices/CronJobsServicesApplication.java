package zihub.click2call.cronjobsservices;

//import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import zihub.click2call.cronjobsservices.playground.PlaygroundService;
import zihub.click2call.cronjobsservices.util.Utils;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class CronJobsServicesApplication {
    @Autowired
    private final PlaygroundService service;

    public CronJobsServicesApplication(PlaygroundService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(CronJobsServicesApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(Environment environment) {
        return args -> {
            Utils.BaseUrl = environment.getProperty("app.baseurl");
            log.error("XXXXXXXX-Setup ENV <:::::::> " + environment.getProperty("message-from-application-properties"));
            log.error("XXXXXXXX-Setup ENV <:::::app.baseurl::::> " + environment.getProperty("app.baseurl"));

            service.runVerifyUserAccountsJob();

        };
    }

}
