package routing.getway.filter.global;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalRequestTime implements GlobalFilter, Ordered {
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println();
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        String s=(System.currentTimeMillis() - startTime) + "ms";
                        System.out.println("(全局过滤器)请求耗时："+s);
                    }
                })
        );

    }

    /**
     * @过滤器设定优先级别的，值越大则优先级越低
     */
    @Override
    public int getOrder() {
        return 3;
    }
}

