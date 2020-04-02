package eureka.client.second.web.api;

import cre.example.common.web.api.CommonGreetingImpl;
import cre.example.common.web.api.ICommonGreeting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeApi extends CommonGreetingImpl {
 }
