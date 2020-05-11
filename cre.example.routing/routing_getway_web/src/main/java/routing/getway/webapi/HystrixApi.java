package routing.getway.webapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HystrixApi {
    private SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @RequestMapping("/error")
    public Mono<String> fallback() {
        StringBuilder builder=new StringBuilder();
        String strTime=dateFormat.format(new Date());
        builder.append(strTime);
        builder.append(" > ");
        builder.append("Please try again later for a program exception");
        return Mono.just(builder.toString());
    }
}
