package net.tusdasa.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//@EnableFeignClients
//@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class FirstKpiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstKpiApplication.class, args);
	}

}
