package me.feelwith.business.web;

import me.feelwith.business.data.dao.entity.Work;
import me.feelwith.business.data.dao.entity.Workcomment;
import me.feelwith.business.service.WorkService;
import me.feelwith.business.web.common.WebUtil;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/9.
 */
@Controller
@RequestMapping("works")
public class WorkCtrl {
    @Autowired
    private WorkService workService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(
            HttpServletRequest request,
            @RequestParam("description") String description
            ,@RequestParam("url")String url){
        Work w = buildWork(WebUtil.getSimpleParams(request));
        w.setUserId(WebUtil.getUserId(request));
        int workId = workService.insert(w);
        return new ModelAndView().addObject("work",workService.selectById(workId));
    }

    @RequestMapping(path = {"{workId:\\d+}"},method = RequestMethod.PATCH)
    public ModelAndView like(
            HttpServletRequest request
            ,@PathVariable int workId
            ,@RequestParam("action")String action
            ,@RequestParam("field")String field
            ){
        if(action.equals("inc")&& field.equals("likes")){
            return new ModelAndView()
                    .addObject("work", workService.like(workId));
        }
        //FIXME
        return new ModelAndView().addObject("noSupportYet");
    }

    @RequestMapping(path = {"{workId:\\d+}"},method = RequestMethod.GET)
    public ModelAndView detail(
        HttpServletRequest request
    ,@PathVariable int workId){
        return new ModelAndView().addObject("work : ",workService.getWorkDetail(workId));
    }

    @RequestMapping(path = {"{workId:\\d+}/comments"},method = RequestMethod.POST)
    public ModelAndView addWorkComment(
           HttpServletRequest request
            ,@RequestParam("workId")int word
           ,@RequestParam("content")String content
            ,@RequestParam(value = "refCommentId",required = false) Integer refCommentId
            ,@RequestParam(value = "refUserId",required = false)Integer refUserId
    ){
        Workcomment w = buildWorkComment(WebUtil.getSimpleParams(request),WebUtil.getUserId(request));
        return new ModelAndView().addObject("comment",workService.addWorkComment(w));
    }

    private static Work buildWork(Map data){
        Work w = new Work();
        w.setDescription(MapUtils.getString(data,"description"));
        w.setUrl(MapUtils.getString(data,"url"));
        return w;
    }

    public static Workcomment buildWorkComment(Map data,int userId){
        Workcomment workcomment = new Workcomment();
        workcomment.setWorkId(MapUtils.getInteger(data, "workId"));
        workcomment.setContent(MapUtils.getString(data, "content"));
        workcomment.setUserId(userId);
        workcomment.setRefUserId(MapUtils.getInteger(data, "refUserId"));
        workcomment.setRefCommentId(MapUtils.getInteger(data, "refCommentId"));
        return workcomment;
    }
}
