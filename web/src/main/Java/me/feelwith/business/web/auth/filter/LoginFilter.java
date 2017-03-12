package me.feelwith.business.web.auth.filter;

import me.feelwith.business.data.entity.User;
import me.feelwith.business.service.auth.AuthSerivce;
import me.feelwith.business.service.auth.AuthTokenService;
import me.feelwith.business.service.user.UserAccountService;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.plexus.logging.LogEnabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by L on 2017/2/11.
 */
public class LoginFilter implements Filter{
    private FilterConfig filterConfig ;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //不需要权限的
        HttpServletRequest httpServletRequest = HttpServletRequest.class.cast(request) ;
        String uri = httpServletRequest.getRequestURI();
        if(httpServletRequest.getMethod().equalsIgnoreCase("post")
                && uri.contains("users")){
            chain.doFilter(request,response);
            return;
        }

        String token = request.getParameter("token");
        boolean hasAuth = false;
        User user = null;
        if(StringUtils.isNoneBlank(token)){
            user = getUserByToken(token,httpServletRequest);
        }else{
            String userIdStr  = request.getParameter("userId");
            long userId = -1;
            if(StringUtils.isNumeric(userIdStr)){
                userId = Long.valueOf(userIdStr);
            }
            String password = request.getParameter("password");
            if(StringUtils.isNoneBlank(password)){
                user = getUserByAccount(userId,password,httpServletRequest);
            }
        }
//        String wechatToken = request.getParameter("wechatToken");
//        if(StringUtils.isNoneBlank(wechatToken)){
//            user = getUserByWeChatToken(wechatToken);
//        }

        if(user != null){
            request.setAttribute("user",user);
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }

    private User getUserByToken(String token,HttpServletRequest request){
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        AuthSerivce authSerivce = context.getBean(AuthSerivce.class);
        return authSerivce.getUserByToken(token);
    }

    private User getUserByAccount(long userId,String password,HttpServletRequest request){
            WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            AuthSerivce authSerivce = context.getBean(AuthSerivce.class);
            return authSerivce.getUserByAccount(userId, password);
    }


    private User getUserByWeChatToken(String weChatToken){
        //FIXME
        return null;
    }
}
