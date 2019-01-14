package cl.learning.hi;

import cl.learning.hi.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changliang
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
public class ServiceHiApplication {
    @Value("${server.port}")
    String port;

    @Autowired
    private HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
    }


    @RequestMapping(value = "/hi")
    public String home(@RequestParam(defaultValue = "changliang") String name) {
        return helloService.seyHello(name, port);

    }

    @RequestMapping(value = "/bye")
    public String bye(@RequestParam(defaultValue = "changliang") String name) {
        return helloService.sayBye(name, port);
    }

}
