package cl.learning.hi.service;

import org.springframework.stereotype.Service;

/**
 * @Author : 常亮
 * @Date : 4:58 PM 2018/12/7
 * @Description :
 */
@Service
public class HelloService {
    public String seyHello(String name, String port) {
        return "hi," + name + ":" + port;
    }

    public String sayBye(String name, String port) {
        return "bye," + name + ":" + port;
    }
}
