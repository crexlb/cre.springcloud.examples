package routing.getway.filter.global;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import routing.getway.response.ResDataDecorator;

/**
 * 接口返回值统一封装
 */
@Component
public class DataFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("自定义全局网关过滤器DataFilter");
        ResDataDecorator resDataDecorator=new ResDataDecorator(exchange.getResponse());
        ServerWebExchange webex=exchange.mutate().response(resDataDecorator).build();
        return chain.filter(webex);
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
