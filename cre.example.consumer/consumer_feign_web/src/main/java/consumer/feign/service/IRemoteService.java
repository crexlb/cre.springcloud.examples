package consumer.feign.service;

import consumer.feign.hystrix.ErrorServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 创建者：xlb
 * 时间：2020-4-2
 * Feign 采用的是基于接口的注解
 * Feign 整合了ribbon，具有负载均衡的能力
 * 整合了Hystrix，具有熔断的能力
 */
@FeignClient(value = "EUREKA.CLIENT.FIRST.WEBAPI",fallback = ErrorServiceImpl.class)
public interface IRemoteService {
    @GetMapping("/hello")
    String getHello(@RequestParam(value = "name", defaultValue = "匿名") String name);

    @PostMapping("/hello")
    ResponseEntity<byte[]> postword(@PathVariable("name") String name);

    @RequestMapping(value = "/time",method = RequestMethod.GET)
    String getTime(String name);
}
