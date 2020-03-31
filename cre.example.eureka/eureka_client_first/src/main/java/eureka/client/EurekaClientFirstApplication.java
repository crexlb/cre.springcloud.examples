package eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaClientFirstApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientFirstApplication.class, args);
    }

}
