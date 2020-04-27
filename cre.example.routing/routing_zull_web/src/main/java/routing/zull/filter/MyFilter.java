package routing.zull.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.function.Predicate;

public class MyFilter extends ZuulFilter implements  ICmdFilter {
    private FilterType stype = FilterType.PRE;
    private int order = 0;
    //private Predicate should;

    public MyFilter(FilterType ftype, int order) {
        this.stype = ftype;
        this.order = order;
    }


    @Override
    public String filterType() {
        return stype.name().toLowerCase();
    }

    @Override
    public int filterOrder() {
        return order;
    }

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().sendZuulResponse();
    }

    @Override
    public Object run() throws ZuulException {
        Object robj=null;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        try {
            String requestURI=request.getRequestURI();
            ///如果是反向代理则不进入过滤器
            if(!requestURI.contains("proxy")){
                robj=execute(request,ctx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return robj;
    }

    @Override
    public Object execute(HttpServletRequest request,RequestContext ctx) throws Exception {
        return null;
    }
}
