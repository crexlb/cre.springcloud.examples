package breaker.hystrix.webapi;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import cre.example.common.utils.string.StringUtil;
import cre.example.common.web.api.ICommonGreeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HomeApi {

    @Value("${mywebapi.name}")
    private String mywebapi;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("hello")
    @HystrixCommand(fallbackMethod = "processHystrix")
    public String hello(String name) throws Exception {
        String webapiUrl=String.format("http://%s/hello?name=%s",mywebapi,name);
        System.out.println("webapiUrl="+webapiUrl);
        return  restTemplate.getForEntity(webapiUrl,String.class).getBody();
    }


    @PostMapping("/hello")
    @HystrixCommand(fallbackMethod = "processHystrix")
    public String postword(String name) throws Exception {
        System.out.println("post.name="+name);
        return  restTemplate.postForObject(String.format("http://%s/hello",mywebapi),name,String.class);
    }

    @GetMapping("test")
    @HystrixCommand(fallbackMethod = "processHystrix")
    public String testhello(String name) throws Exception {
        throw new Exception("异常测试");
    }

    @RequestMapping("/time")
    public String getTime(String name) {
        String s = StringUtil.toDateString();
        if (!StringUtil.isEmpty(name)) {
            s = s + "->" + name;
        }
        return s;
    }

    private String processHystrix(String name, Throwable e) {
        String s = "【断路器】服务异常，请稍后重试。。。";
        System.out.println(s);
        System.out.println("异常信息：" + e.getMessage());
        return s;
    }
}

