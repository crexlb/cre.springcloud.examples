package breaker.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableEurekaClient // 配置本应用将使用服务注册和服务发现
@EnableHystrixDashboard
@EnableTurbine
public class BreakerTurbineWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreakerTurbineWebApplication.class, args);
    }

}
