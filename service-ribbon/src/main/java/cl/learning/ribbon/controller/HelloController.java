package cl.learning.ribbon.controller;

import cl.learning.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : 常亮
 * @Date : 5:21 PM 2018/12/7
 * @Description :
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hi")
    public String sayHello(@RequestParam(defaultValue = "changliang") String name) {
        return helloService.sayHello(name);

    }

    @GetMapping(value = "/bye")
    public String sayBye(@RequestParam(defaultValue = "changliang") String name) {
        return helloService.goodBye(name);
    }

}
