package routing.getway.route;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import routing.getway.base.IBuildRouteLocator;
import routing.getway.filter.gateway.LimitFilter;
import routing.getway.filter.gateway.RequestTimeFilter;

import java.time.Duration;

@Configuration
public class ApiRoute implements IBuildRouteLocator {
    @Bean
    @Override
    public RouteLocator build(RouteLocatorBuilder builder) {
        String toUrl = "lb://EUREKA.CLIENT.FIRST.WEBAPI";
        //PrefixPathGatewayFilterFactory及StripPrefixGatewayFilterFactory是一对针对请求url前缀进行处理的filter工厂，前者添加prefix，后者去除prefix。
        return builder.routes()
                .route(p ->
                        p.path("/api/b/**")
                                .filters(f -> f
                                        .stripPrefix(2)
                                        .hystrix(c -> c
                                                .setName("myhystrix")
                                                .setFallbackUri("forward:/error"))
                                ).uri(toUrl))
                .route(p ->
                        //断言 访问http://localhost:8767/?/abc/**（？代表任何值） 地址的请求全部路由到 http://localhost:8761/** 地址。
                        p.predicate(pr -> pr.getRequest().getPath().toString().contains("abc"))
                                .filters(f -> f
                                        //去掉2项前缀（如：http://localhost:8767/?/abc/xx时,自动去掉“?/abc”2项就变成http://localhost:8767/xx）
                                        .stripPrefix(2)
                                        //设置熔断器，当请求的目标地址不能访问时，此请求被转发到“forward:/error”处理。
                                        .hystrix(c -> c
                                                .setName("myhystrix")
                                                .setFallbackUri("forward:/error")))
                                .uri(toUrl))
                .route(p ->
                        p.path("/api/c/**")
                                //自定义网关过滤器
                                .filters(f -> f.filter(new RequestTimeFilter())
                                        .filter(new LimitFilter(1,1, Duration.ofSeconds(1)))
                                        .stripPrefix(2)
                                ).uri(toUrl))
                .build();
    }
}
