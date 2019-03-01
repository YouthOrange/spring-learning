package cl.learning.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@EnableCaching
public class ServiceEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEsApplication.class, args);
    }

}

