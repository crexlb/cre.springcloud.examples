package consumer.feign.webapi;

import consumer.feign.service.IRemoteService;
import cre.example.common.web.api.ICommonGreeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建者：xlb
 * 时间：2020-4-2
 */
@RestController
public class HomeApi {

    //编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
    @Autowired
    IRemoteService remoteService;


    @GetMapping(value = "/hello")
    public String hello(String name) {
        return remoteService.getHello(name);
    }

    @PostMapping(value = "/hello")
    public ResponseEntity<byte[]> postword(String name) {
        System.out.println("post.hell.name=" + name);
        return remoteService.postword(name);
    }


    public String getTime(String name) {
        return remoteService.getTime(name);
    }
}
