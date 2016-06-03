package me.feelwith.business.web.activity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by L on 2016/5/22 11:33.
 */
@Controller
@RequestMapping("activities")
public class ActivityCtrl {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@RequestParam("type") int type,
                             @RequestParam("starId")int starId){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(@RequestParam Map<String,String> requestParams){
        System.out.println(requestParams);
        return null;
    }
}
