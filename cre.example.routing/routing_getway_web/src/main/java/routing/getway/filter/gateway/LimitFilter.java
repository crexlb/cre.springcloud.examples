package routing.getway.filter.gateway;

import com.alibaba.fastjson.JSON;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
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
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @限流
 *
 */
public class LimitFilter implements GatewayFilter, Ordered {
    /**
     * @令牌桶总数
     */
    private int capacity;
    /**
     * @生产令牌桶数量
     */
    private int refillTokens ;
    /**
     * @生产令牌桶的时间间隔
     */
    private Duration refillDuration;

    /**
     * @param capacity       令牌桶总数
     * @param refillTokens   生产令牌桶数量
     * @param refillDuration 生产令牌桶的时间间隔
     */
    public LimitFilter(int capacity, int refillTokens, Duration refillDuration) {
        this.capacity = capacity;
        this.refillTokens = refillTokens;
        this.refillDuration = refillDuration;
    }

    private static final Map<String, Bucket> CACHE = new ConcurrentHashMap<>();

    private Bucket createNewBucket() {
        Refill refill = Refill.of(refillTokens, refillDuration);
        Bandwidth limit = Bandwidth.classic(capacity, refill);
        return Bucket4j.builder().addLimit(limit).build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse response = exchange.getResponse();
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = CACHE.computeIfAbsent(ip, k -> createNewBucket());
        System.out.println("IP: " + ip + ", available tokens :" + bucket.getAvailableTokens());
        if (bucket.tryConsume(1L)) {
            return chain.filter(exchange);
        }
        BaseData bdata = BaseData.result(401, "单个限流", "访问太频繁了，请稍后。。。");
        System.out.println("IP: " + ip + ", available tokens :" + bucket.getAvailableTokens() + " too many requests");

        Object message = JSON.toJSON(bdata);
        byte[] bits = message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
