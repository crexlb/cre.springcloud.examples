package routing.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient/*服务注册*/
@SpringBootApplication
public class RoutingGetwayWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutingGetwayWebApplication.class, args);
    }

}
