package com.example.batchtest.schedule;

import com.example.batchtest.batch.BookRegisterJobConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiBatchSchedule {
    private final JobLauncher jobLauncher;
    private final BookRegisterJobConfig bookRegisterJobConfig;

    @Scheduled(cron = "0/10 * * * * ?")
    public void BookRegister(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
            jobLauncher.run(bookRegisterJobConfig.apiJob(), jobParameters);
        } catch (Exception e) {
            log.info("error!!");
        }
    }
}
