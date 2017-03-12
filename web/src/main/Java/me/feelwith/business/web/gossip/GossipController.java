package me.feelwith.business.web.gossip;

import me.feelwith.business.data.entity.Gossip;
import me.feelwith.business.data.entity.User;
import me.feelwith.business.service.gossip.GossipService;
import me.feelwith.business.service.user.UserIdService;
import me.feelwith.common.web.servlet.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/22.
 */
@Controller
@RequestMapping("gossip")
public class GossipController {
    @Autowired
    GossipService gossipService;

    UserIdService userIdService = new UserIdService();

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request,Gossip gossip,BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView();
        }
        gossip.setUserId(ServletUtil.getUserId(request));
        gossip.setGossipId(userIdService.createUserId());
        gossip.setContent("hehe");
        gossip.setCreateTime(new Date());
        gossipService.insert(gossip);
        return new ModelAndView().addObject("name","a");
    }

    @RequestMapping(value={"{gossipId:\\d+}"},method = RequestMethod.GET)
    public @ResponseBody Gossip getGossip(@PathVariable long gossipId){
        return  new Gossip();
    }
}
