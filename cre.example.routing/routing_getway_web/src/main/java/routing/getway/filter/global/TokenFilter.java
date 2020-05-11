package routing.getway.filter.global;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import routing.getway.models.BaseData;

import java.nio.charset.StandardCharsets;

/**
 * 全局网关路由过滤器
 * token验证
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println();
        System.out.println("自定义全局网关过滤器TokenFilter之token验证");
       // String token = exchange.getRequest().getHeaders().getFirst("token");
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token == null || token.isEmpty()) {
            System.out.println( "token is empty..." );
            ServerHttpResponse response = exchange.getResponse();
            Object message =JSON.toJSON(BaseData.result(401,"鉴权失败","鉴权失败"));
            byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //指定编码，否则在浏览器中会中文乱码
           // response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
    /**
     * @过滤器设定优先级别的，值越大则优先级越低
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
