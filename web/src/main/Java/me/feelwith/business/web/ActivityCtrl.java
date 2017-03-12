package me.feelwith.business.web;

/**
import me.feelwith.business.data.dao.entity.Activity;
import me.feelwith.business.service.ActivityService;
import me.feelwith.business.service.WorkService;
import me.feelwith.business.web.common.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

 * Created by L on 2016/5/22 11:33.
@Controller
@RequestMapping("activities")
public class ActivityCtrl {
    @Autowired
    ActivityService activityService ;
    @Autowired
    WorkService workService;

   @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list(@RequestParam("type") int type,
                             @RequestParam("starId")int starId){
        return new ModelAndView()
                .addObject("activies", activityService.listActivityByType(type, starId, 10))
                ;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request,@RequestParam("description")String description){
        int userId = WebUtil.getUserId(request);
        int actId = activityService.createActivity(userId,description);
        Activity act = activityService.getById(actId);
        return new ModelAndView().addObject("activity",act);
    }
   @RequestMapping(path = {"{activityId:\\d+}"},method = RequestMethod.PATCH)
    public ModelAndView like(
            HttpServletRequest request
            ,@PathVariable int activityId
            ,@RequestParam("action")String action
            ,@RequestParam("field")String field
            ){
        if(action.contains("inc") && field.equals("likes")){
            return new ModelAndView()
                    .addObject("activity",activityService.like(activityId));
        }
       //FIXME
        return new ModelAndView().addObject("res","no support yet");
    }

    @RequestMapping(path = {"{activityId:\\d+}/works"},method = RequestMethod.POST)
    public ModelAndView joinActivity(HttpServletRequest request
            ,@PathVariable int activityId
            ,@RequestParam("workId")int workId) {
        int actId = activityService.joinActivity(WebUtil.getUserId(request),activityId,workId);
        return new ModelAndView().addObject("work",workService.selectById(workId));
    }
}
 */
