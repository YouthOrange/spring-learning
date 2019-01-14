package cl.learning.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : 常亮
 * @Date : 5:19 PM 2018/12/7
 * @Description :
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    public String sayHello(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }
    public String goodBye(String name){
        return restTemplate.getForObject("http://SERVICE-HI/bye?name=" + name, String.class);
    }
}

