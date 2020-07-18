package com.xspace.nacos;

import com.xspace.nacos.api.annotation.ResponseResult;
import com.xspace.nacos.api.exception.UserAuthenticationException;
import com.xspace.nacos.api.facade.HelloFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class AlibabaNacosDubboConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlibabaNacosDubboConsumerApplication.class, args);
  }

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  @LoadBalanced
  public WebClient.Builder loadBalancedWebClientBuilder() {
    return WebClient.builder();
  }

  @Slf4j
  @RestController
  @ResponseResult
  static class TestController {

    @Reference
    HelloFacade helloService;

    @GetMapping("/test")
    public String test() {
      return helloService.hello("xue.zeng");
    }

    @GetMapping("/user")
    public User getUser() {
      return new User("1", "xue.zeng");
    }

    @GetMapping("/exception")
    public User getException() throws UserAuthenticationException{
      return new User();
    }

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/LoadBalancerClient")
    public String LoadBalancerClient() {
      // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
      ServiceInstance serviceInstance = loadBalancerClient.choose("alibaba-nacos-dubbo-producer");
      String url = serviceInstance.getUri() + "/hello?name=" + "xue.zeng";
      RestTemplate restTemplate = new RestTemplate();
      String result = restTemplate.getForObject(url, String.class);
      return "Invoke : " + url + ", return : " + result;
    }


    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/restTemplate")
    public String restTemplate() {
      String result = restTemplate.getForObject("http://alibaba-nacos-dubbo-producer/hello?name=xue.zeng",
              String.class);
      return "Return : " + result;
    }

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/webClient")
    public Mono<String> webClient() {
      Mono<String> result = webClientBuilder.build()
              .get()
              .uri("http://alibaba-nacos-dubbo-producer/hello?name=xue.zeng")
              .retrieve()
              .bodyToMono(String.class);
      return result;
    }

    @Autowired
    Client client;

    @GetMapping("/feignClient")
    public String feignClient() {
      String result = client.hello("xue.zeng");
      return "Return : " + result;
    }
  }


  @FeignClient("alibaba-nacos-dubbo-producer")
  interface Client {

    @GetMapping("/hello")
    String hello(@RequestParam(name = "name") String name);

  }

  static class User {
    String id;
    String name;

    public User(String id, String name) {
      this.id = id;
      this.name = name;
    }

    public User() throws UserAuthenticationException {
      throw new UserAuthenticationException("用户异常", new Exception());
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
