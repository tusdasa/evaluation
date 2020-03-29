package net.tusdasa.evaluation;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Author: tusdasa
 * @Date: 2020-03-27 11:45 AM
 */

@EnableAdminServer
@SpringCloudApplication
public class OperationsApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperationsApplication.class, args);
    }
}
