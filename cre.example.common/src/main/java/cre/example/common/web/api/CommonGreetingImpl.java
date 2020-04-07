package cre.example.common.web.api;

import cre.example.common.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class CommonGreetingImpl implements ICommonGreeting {
    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String appName;

    @Override
    @GetMapping("hello")
    public String hello(@RequestParam(value = "name", defaultValue = "xxx") String name) {
        return String.format("%s->你好！ %s,我是来自于get服务%s:%s 的响应结果。", StringUtil.toDateString(), name, appName, port);
    }

    @Override
    @PostMapping("/hello")
    public String postword(String name) {
        return String.format("%s->你好！ %s,我是来自于post服务%s:%s 的响应结果。", StringUtil.toDateString(), name, appName, port);
    }

    @Override
    @RequestMapping("/time")
    public String getTime(String name) {
        String s = StringUtil.toDateString();
        if (!StringUtil.isEmpty(name)) {
            s = s + "->" + name;
        }
        return s;
    }
}
