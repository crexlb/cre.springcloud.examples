package routing.zull.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import routing.zull.models.BaseData;

import javax.servlet.http.HttpServletRequest;
/**
 * @路由之前执行
 * token 身份验证
 */
@Component
public class AuthenticateFilter extends MyFilter{
    public AuthenticateFilter() {
        super(FilterType.PRE, 1);
    }

    @Override
    public Object execute(HttpServletRequest request, RequestContext ctx) throws Exception {
        String msg = String.format("PreFilter 权限验证>>%s: %s", request.getMethod(), request.getRequestURL().toString());
        System.out.println(msg);
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            System.out.println("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.getResponse().setContentType("application/json; charset=UTF-8");
            BaseData bdata = BaseData.result(401, "权限验证", "token is empty");
            ctx.setResponseBody(JSON.toJSONString(bdata));
        }
        return null;
    }
}
