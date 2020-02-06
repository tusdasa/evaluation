package net.tusdasa.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableCaching
@SpringCloudApplication
public class AcademicYearApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcademicYearApplication.class, args);
    }
}
