package cl.learning.fegin.controller;

import cl.learning.fegin.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : 常亮
 * @Date : 13:48 2019-01-09
 * @Description :
 */
@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/hi")
    public String sayHello(@RequestParam(defaultValue = "changliang") String name) {
        return helloService.sayHello(name);

    }
}
