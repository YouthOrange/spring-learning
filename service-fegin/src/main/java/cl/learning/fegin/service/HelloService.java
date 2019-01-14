package cl.learning.fegin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author : 常亮
 * @Date : 13:48 2019-01-09
 * @Description :
 */
@Service
@FeignClient(value = "service-hi")
public interface HelloService {
    @RequestMapping(value = "/hi")
    String sayHello(@RequestParam(value = "name") String name);

}
