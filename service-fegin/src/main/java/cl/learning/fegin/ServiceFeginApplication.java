package cl.learning.fegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author changliang
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication

public class ServiceFeginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeginApplication.class, args);
    }

}

