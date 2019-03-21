###服务注册：
* maven依赖
    ```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            <version>2.0.0.RELEASE</version>
        </dependency>
    ```
**这里以'service-hi'为例**
1. @EnableEurekaClient 使用在启动类上
    ```java
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
    ```
    - 注意：这里把rest接口写在启动类里是不规范的，最好写单独的controller里写所有的接口。
    
    
###服务消费：
#### Fegin方式：
* maven依赖
    ```xml
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-feign</artifactId>
              <version>1.4.6.RELEASE</version>
          </dependency>

    ```
**这里以消费service-hi为例**

1. @EnableFeignClients使用在启动类上开启fegin功能
    
    代码示例：
    ```java
    @EnableFeignClients
    @EnableEurekaClient
    @SpringBootApplication
    
    public class ServiceFeginApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ServiceFeginApplication.class, args);
        }
    
    }
    ```
2. @FeignClient 使用在接口类上，消费对应的服务
    
    代码示例：
    ```java
    @Service
    @FeignClient(value = "service-hi")
    public interface HelloService {
        @RequestMapping(value = "/hi")
        String sayHello(@RequestParam(value = "name") String name);
    
    }
    ```
    * 注意：fegin方式，一个服务需要写一个接口类来消费服务
#### Ribbon方式：

* maven依赖
    ```xml
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
              <version>2.0.0.RELEASE</version>
          </dependency>
    ```
1.  @RibbonClients或者@RibbonClient使用在启动类上

    代码示例：
    ```java
    @EnableEurekaClient
    @SpringBootApplication
    @RibbonClients(value = {@RibbonClient(configuration = MyLoadBalanced.class)})
    public class ServiceRibbonApplication {
    
        public static void main(String[] args) {
            SpringApplication.run(ServiceRibbonApplication.class, args);
        }
    
        @Bean
        @LoadBalanced
        RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
    ```
    @LoadBalanced 开启负载均衡


2.  使用RestTemplate,有对应的get,post等请求方式。

    代码示例：
    ```java
    @Service
    public class HelloService {
        @Autowired
        RestTemplate restTemplate;
    
        public String sayHello(String name) {
            return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
        }
    
        public String goodBye(String name) {
            return restTemplate.getForObject("http://SERVICE-HI/bye?name=" + name, String.class);
        }
    }
   
    ```
    比起fegin的方式，ribbon需要区拼完整的url，但这样会更灵活。