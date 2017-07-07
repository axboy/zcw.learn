package cn.wazitang.demo.javaweb.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 作者 zcw
 * 时间 2017/7/6 上午12:15
 * 描述 copy on MyFilter
 */
//FIXME 测试结果，多个filter，按类名（文件名不一致情况下未测试）字母顺序先后执行
@WebFilter(urlPatterns = {"/home"})
public class HomeFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("HomeFilter ==> doFilter() ==> before");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("HomeFilter ==> doFilter() ==> after");
    }

    public void destroy() {
        System.out.println("HomeFilter ==> destroy()");
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("HomeFilter ==> init()");
    }
}
