package com.ribbon.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import cre.example.common.web.api.ICommonGreeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 创建者：忻龙彪
 * 时间：2020-4-2
 */
@RestController
public class HomeApi implements ICommonGreeting {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${webapi}")
    private String webapi;

    @Override
    @RequestMapping("/hello")
    @HystrixCommand(fallbackMethod = "error")
    public String hello(@RequestParam(value = "name", defaultValue = "ribbonweb") String name) {
        String url = String.format("http://%s/hello?name=%s", webapi, name);
        System.out.println("尝试访问：" + url);
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    @HystrixCommand(fallbackMethod = "error")
    public String postword(String name) {
        String url = String.format("http://%s/hello", webapi);
        System.out.println("尝试访问：" + url);
        return restTemplate.postForObject(url, name,String.class);
    }

    @Override
    public String getTime(String name) {
        String url = String.format("http://%s/time", webapi);
        return restTemplate.getForObject(url,String.class);
    }

    /**
     * 熔断
     */
    public String error(String name) {
        return String.format("抱歉 %s ,以ribbon调用服务没有响应，请稍后重试。", name);
    }
}
