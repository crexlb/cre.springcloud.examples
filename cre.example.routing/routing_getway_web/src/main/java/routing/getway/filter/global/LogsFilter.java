package routing.getway.filter.global;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局网关路由过滤器
 * 系统日志
 */
@Component
public class LogsFilter  implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange e, GatewayFilterChain c) {
        System.out.println();
        System.out.println("自定义全局网关过滤器LogsFilter记录系统日志");
        System.out.println("请求地址："+e.getRequest().getPath());
        return c.filter(e);
    }
    /**
     * @过滤器设定优先级别的，值越大则优先级越低
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
