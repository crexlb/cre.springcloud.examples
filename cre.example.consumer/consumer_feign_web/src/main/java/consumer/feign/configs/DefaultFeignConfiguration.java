package consumer.feign.configs;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign通用配置
 */
/*@Configuration*/
public class DefaultFeignConfiguration {
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(1000,3000,3);
    }
}
