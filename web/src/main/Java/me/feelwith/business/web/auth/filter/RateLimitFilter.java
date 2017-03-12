package me.feelwith.business.web.auth.filter;

import me.feelwith.common.web.servlet.ServletUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 访问速度限制
 * Created by L on 2017/2/11.
 */
public class RateLimitFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(request);
        String ip = ServletUtil.getIpAddress(httpServletRequest);
        //TODO 限制用户对于每个端口的访问速度
    }

    @Override
    public void destroy() {
    }
}
