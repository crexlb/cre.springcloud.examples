package consumer.feign.hystrix;

import consumer.feign.service.IRemoteService;
import cre.example.common.web.api.ICommonGreeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class ErrorServiceImpl implements IRemoteService {
    @Override
    public String getHello(@RequestParam(value = "name", defaultValue = "匿名") String name) {
        return String.format("抱歉 %s ,通过Feign调用服务没有响应，请稍后重试。", name);
    }

    @Override
    public ResponseEntity<byte[]> postword(String name) {
        String str = String.format("抱歉 %s ,通过Feign调用服务没有响应，请稍后重试。", name);
        return new ResponseEntity<byte[]>(str.getBytes(), HttpStatus.NOT_FOUND);
    }

    @Override
    public String getTime(String name) {
        return "未取到系统时间";
    }
}
