package me.feelwith.common.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by L on 2016/11/26.
 */
public class MyExceptionHandler extends DefaultHandlerExceptionResolver{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyExceptionHandler.class);
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ex.printStackTrace();
        LOGGER.error(ex.getMessage(),ex);
        return super.doResolveException(request, response, handler, ex);
    }
}
