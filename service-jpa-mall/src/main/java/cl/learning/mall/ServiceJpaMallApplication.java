package cl.learning.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceJpaMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceJpaMallApplication.class, args);
    }

}

