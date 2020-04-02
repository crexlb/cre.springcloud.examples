package consumer.feign.service;

import consumer.feign.hystrix.ErrorServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 创建者：忻龙彪
 * 时间：2020-4-2
 * Feign 采用的是基于接口的注解
 * Feign 整合了ribbon，具有负载均衡的能力
 * 整合了Hystrix，具有熔断的能力
 */
@FeignClient(value = "eureka.client.first.webapi",fallback = ErrorServiceImpl.class)
public interface IRemoteService {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String getHello(@RequestParam(value = "name") String name);
}
