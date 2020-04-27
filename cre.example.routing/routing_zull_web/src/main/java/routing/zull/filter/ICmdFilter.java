package routing.zull.filter;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ICmdFilter {
    Object execute(HttpServletRequest request, RequestContext ctx) throws Exception;
}
