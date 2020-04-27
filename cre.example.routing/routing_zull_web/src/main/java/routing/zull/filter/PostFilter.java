package routing.zull.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import routing.zull.models.BaseData;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @路由之后执行
 * 对服务接果进行统一处理
 */
@Component
public class PostFilter extends MyFilter {
    public PostFilter() {

        super(FilterType.POST, 2);
    }

    @Override
    public Object execute(HttpServletRequest request, RequestContext ctx) throws Exception {
        String msg = String.format("PostFilter 返回结果>>%s: %s", request.getMethod(), request.getRequestURL().toString());
        System.out.println(msg);
        // 获取返回值内容，加以处理
        InputStream stream = ctx.getResponseDataStream();
        String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
        BaseData bdata = BaseData.result(ctx.getResponseStatusCode(), "请求成功", body);
        // 内容重新写入
        ctx.setResponseBody(JSON.toJSONString(bdata));
        return null;
    }
}
