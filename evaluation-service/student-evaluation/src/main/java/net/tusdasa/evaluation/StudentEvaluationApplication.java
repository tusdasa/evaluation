package net.tusdasa.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableFeignClients
@SpringCloudApplication
@EnableCaching
public class StudentEvaluationApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentEvaluationApplication.class, args);
    }
}
