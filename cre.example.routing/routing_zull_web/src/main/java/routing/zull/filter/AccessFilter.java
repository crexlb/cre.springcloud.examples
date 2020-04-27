package routing.zull.filter;



import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import routing.zull.models.BaseData;
import javax.servlet.http.HttpServletRequest;

/**
 * 限流
 */
@Component
public class AccessFilter extends MyFilter {
    // 每秒放多少个令牌 (100个)
    private static final RateLimiter rateLimiter = RateLimiter.create(1);

    public AccessFilter() {
        super(FilterType.PRE, 0);
    }
    @Override
    public Object execute(HttpServletRequest request, RequestContext ctx) throws Exception {
        String msg = String.format("AccessFilter 流量限制>>%s: %s", request.getMethod(), request.getRequestURL().toString());
        System.out.println(msg);
        //限流、限速
        if (!rateLimiter.tryAcquire()) {
            String msgs="访问太频繁了，请稍后。。。";
            System.out.println(msgs);
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.getResponse().setContentType("application/json; charset=UTF-8");
            BaseData bdata = BaseData.result(401, "访问限制", msgs);
            // 内容重新写入
            ctx.setResponseBody(JSON.toJSONString(bdata));
            return null;
        }
        return null;
    }
}
