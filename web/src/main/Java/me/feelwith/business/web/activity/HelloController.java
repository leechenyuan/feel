package me.feelwith.business.web.activity;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2016/6/1.
 */
@RequestMapping("hello")
public class HelloController {
    @RequestMapping("")
    public String index(){
        return "hello";
    }
}
