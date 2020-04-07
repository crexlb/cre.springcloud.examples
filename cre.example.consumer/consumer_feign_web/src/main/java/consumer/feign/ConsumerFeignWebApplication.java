package consumer.feign;

import consumer.feign.configs.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
////指定配置文件
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguration.class)
public class ConsumerFeignWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerFeignWebApplication.class, args);
    }
}
