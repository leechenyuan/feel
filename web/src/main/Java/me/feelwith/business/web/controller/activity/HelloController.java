package me.feelwith.business.web.controller.activity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/6/1.
 */
@Controller
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("you")
    public ModelAndView index(){
        return new ModelAndView().addObject("name","hello you");
    }
}
