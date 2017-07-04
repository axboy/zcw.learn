package cn.wazitang.demo.springcloud.server.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zcw on 2017/7/4.
 */
@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //返回一个字符串代表过滤器类型，在zuul中定义了四种不同生命周期的过滤器类型
        //pre          路由之前
        //routing
        //post         路由之后
        //error
        return "pre";
    }

    @Override
    public int filterOrder() {
        //过滤的顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //判断逻辑
        return false;
    }

    @Override
    public Object run() {
        //过滤的具体逻辑
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //TODO
        return null;
    }
}
