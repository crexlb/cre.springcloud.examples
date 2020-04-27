package routing.zull.filter;

import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletRequest;

/**
 * @路由之时执行
 */

public class RoutingFilter extends MyFilter{
    public RoutingFilter() {
        super(FilterType.ROUTING, 3);
    }

    @Override
    public Object execute(HttpServletRequest request, RequestContext ctx) throws Exception {
        String msg=String.format("RoutingFilter >>>%s: %s", request.getMethod(), request.getRequestURL().toString());
        System.out.println(msg);

        return null;
    }
}
