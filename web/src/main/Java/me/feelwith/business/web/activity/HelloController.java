package me.feelwith.business.web.activity;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
@Controller
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("")
    public @ResponseBody String index(){
        return "hello x";
    }
    @RequestMapping("list")
    public ModelAndView list(){
        int a = 10;
        return new ModelAndView().addObject("list", Lists.newArrayList("-1","0","1","2","3","4","5"));
    }
}
