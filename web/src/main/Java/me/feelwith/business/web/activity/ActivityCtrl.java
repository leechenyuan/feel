package me.feelwith.business.web.activity;

import me.feelwith.business.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by L on 2016/5/22 11:33.
 */
@Controller
@RequestMapping("activities")
public class ActivityCtrl {
    @Autowired
    ActivityService activityService ;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@RequestParam("type") int type,
                             @RequestParam("starId")int starId){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(
            @RequestParam(value = "activityId",defaultValue = "0")int activityId
            ,@RequestParam("description")String description
            ,@RequestParam("mediaId")int mediaId){
        int userId = -1;
        int actId = activityService.addOrJoinActivity(userId,activityId,description,mediaId);
        return new ModelAndView().addObject("activityId",actId);
    }
//    public ModelAndView add(
//            @RequestParam(value = "activityId",defaultValue = "0")int activityId
//            ,@RequestParam("description")String description
//            ,@RequestParam("mediaId")int mediaId){
//        int userId = -1;
//        int actId = activityService.addOrJoinActivity(userId,activityId,description,mediaId);
//        return new ModelAndView().addObject("activityId",actId);
//    }
}
