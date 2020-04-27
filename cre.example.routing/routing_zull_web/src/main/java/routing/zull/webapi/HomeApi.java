package routing.zull.webapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeApi {
    @GetMapping(value = "/getserver")
    public String getServiceName() {
        System.out.println("Routing Zuul Applicetion Web");
        return "Routing Zuul Applicetion Web";
    }
}
