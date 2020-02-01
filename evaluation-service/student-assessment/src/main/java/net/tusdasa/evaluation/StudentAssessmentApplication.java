package net.tusdasa.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StudentAssessmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentAssessmentApplication.class, args);
    }
}
