package routing.zull.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;
import routing.zull.models.BaseData;

import javax.servlet.http.HttpServletResponse;

/**
 * @发送错误调用
 * 路由容错处理
 */
@Component
public class ErrorFilter extends MyFilter {
    public ErrorFilter() {
        super(FilterType.ERROR, -1);
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().containsKey("throwable");
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response = ctx.getResponse();
        String msg = String.format("ErrorFilter>>%s: %s", ctx.getRequest().getMethod(), ctx.getRequest().getRequestURL().toString());
        System.out.println(msg);
        Object e = ctx.get("throwable");
        if (e != null && e instanceof ZuulException) {
            ZuulException zuulException = (ZuulException) e;
            ctx.remove("throwable");
            ctx.setSendZuulResponse(false);
            ctx.getResponse().setContentType("application/json; charset=UTF-8");
            BaseData bdata = BaseData.result(401, "服务故障", zuulException.getMessage());
            ctx.setResponseBody(JSON.toJSONString(bdata));
        }

        return null;
    }
}
