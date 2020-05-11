package routing.zull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication/*服务启动*/
@EnableZuulProxy/*服务路由*/
@EnableEurekaClient/*服务注册*/
@EnableDiscoveryClient/*服务发现*/
public class RoutingZullWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoutingZullWebApplication.class, args);
    }
}
