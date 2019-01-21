package cl.learning.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author changliang
 */
@EnableDiscoveryClient
@EnableApolloConfig
@SpringBootApplication
public class ServiceApolloApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApolloApplication.class, args);
    }

}

