package cre.example.common.web.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CommonGreetingImpl implements ICommonGreeting {
    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String appName;

    @Override
    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "xxx") String name) {
        return String.format("你好！ %s,我是来自于服务%s:%s 的响应结果。", name, appName, port);
    }
}
