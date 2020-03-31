package eureka.client.second.web.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeApi {
    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String appName;
    @RequestMapping("/hi")
    public String help(@RequestParam(value = "name", defaultValue = "xxx") String name) {
        return String.format("你好！ %s,我是来自于服务%s:%s 的响应结果。",appName,name,port);
    }
}
