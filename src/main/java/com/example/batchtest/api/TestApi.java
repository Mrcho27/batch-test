package com.example.batchtest.api;

import com.example.batchtest.batch.BookRegisterJobConfig;
import com.example.batchtest.service.AladinApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
public class TestApi {
    private final AladinApiService aladinApiService;

    private final JobLauncher jobLauncher;
    private final BookRegisterJobConfig bookRegisterJobConfig;

    @GetMapping("/api")
    public void apiTest(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        try {
//            JobLauncher객체의 run을 통해 Job을 실행하여 배치 처리를 할 수 있다.
//            파라미터로 Job객체와 JobPatameters 객체가 필요하다.
//            Job객체는 배치 처리할 작업을 가지고 있고
//            JobParameters는 Job을 여러 번 실행하는 상황에서 각 실행을 구분하기 위한 값이다.
//            중복되는 값이 들어가면 오류가 날 수 있기 때문에 보통 중복되지 않는 값을 넣기 위해
//            시간을 이용하는 경우가 많다.
            jobLauncher.run(bookRegisterJobConfig.apiJob(), jobParameters);
        } catch (Exception e) {
            log.error("job error!!!!!!!!!!");
        }

    }
}
