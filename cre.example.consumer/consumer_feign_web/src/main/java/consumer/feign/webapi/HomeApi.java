package consumer.feign.webapi;

import consumer.feign.service.IRemoteService;
import cre.example.common.web.api.ICommonGreeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 创建者：忻龙彪
 * 时间：2020-4-2
 */
@RestController
public class HomeApi implements ICommonGreeting {

    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    IRemoteService remoteService;

    @Override
    @GetMapping(value = "/hello")
    public String hello(String name) {
        return remoteService.getHello(name);
    }
}
