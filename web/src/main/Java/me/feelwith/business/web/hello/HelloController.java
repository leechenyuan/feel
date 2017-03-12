package me.feelwith.business.web.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by L on 2016/12/23.
 */
@Controller
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("hi")
    public ModelAndView hi(HttpServletRequest request){
        return new ModelAndView().addObject("hi,what's your name?");
    }
}
