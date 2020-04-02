package consumer.feign.hystrix;

import cre.example.common.web.api.ICommonGreeting;
import org.springframework.stereotype.Component;

@Component
public class ErrorServiceImpl implements ICommonGreeting {

    @Override
    public String hello(String name) {
        return String.format("抱歉 %s ,服务没有响应，请稍后重试。",name);
    }
}
