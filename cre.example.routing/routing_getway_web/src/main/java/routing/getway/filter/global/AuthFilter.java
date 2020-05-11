package routing.getway.filter.global;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import routing.getway.models.BaseData;

import java.nio.charset.StandardCharsets;

/**
 * 登录验证
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String user = exchange.getRequest().getQueryParams().getFirst("user");
        System.out.println();
        System.out.println("登录验证");
        //重定向(redirect)到登录页面
        if (StringUtils.isBlank(user)) {
            String url = "http://localhost:8760";
            ServerHttpResponse response = exchange.getResponse();
            //303状态码表示由于请求对应的资源存在着另一个URI，应使用GET方法定向获取请求的资源
            response.setStatusCode(HttpStatus.SEE_OTHER);
            response.getHeaders().set(HttpHeaders.LOCATION, url);
/*
            Object message = JSON.toJSON(BaseData.result(401,"用户未登录","用户未登录"));
            byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.writeWith(Mono.just(buffer));
*/
            return response.setComplete();

        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
