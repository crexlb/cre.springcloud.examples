package routing.getway.response;

import com.alibaba.fastjson.JSON;
import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import routing.getway.models.BaseData;

import java.nio.charset.StandardCharsets;

public class ResDataDecorator extends ServerHttpResponseDecorator {

    private ServerHttpResponse originalResponse;
    public ResDataDecorator(ServerHttpResponse delegate) {
        super(delegate);
        originalResponse=delegate;
    }


    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        if (body instanceof Flux) {
            Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
            return super.writeWith(fluxBody.buffer().map(dataBuffer -> {
                DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                DataBuffer join = dataBufferFactory.join(dataBuffer);
                byte[] content = new byte[join.readableByteCount()];
                join.read(content);
                //释放掉内存
                DataBufferUtils.release(join);
                BaseData bdata = BaseData.result(200, "请求成功", new String(content, StandardCharsets.UTF_8));
                String bodyString = JSON.toJSON(bdata).toString();
                System.out.println();
                System.out.println(bodyString);
                byte[] uppedContent = new String(bodyString.getBytes(), StandardCharsets.UTF_8).getBytes();
                //如果不重新设置长度则收不到消息，或者收到的消息不全。
                originalResponse.getHeaders().setContentLength(uppedContent.length);
                return bufferFactory.wrap(uppedContent);
            }));
        }
        return super.writeWith(body);
    }
}
