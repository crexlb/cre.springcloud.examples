package eureka.client.web.api;

import cre.example.common.web.api.CommonGreetingImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeApi extends CommonGreetingImpl {

}
