package eureka.client.second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaClientSecondApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientSecondApplication.class, args);
    }

}
