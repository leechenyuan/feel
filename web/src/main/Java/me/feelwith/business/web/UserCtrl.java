package me.feelwith.business.web;

import me.feelwith.business.data.dao.entity.User;
import me.feelwith.business.service.UserService;
import me.feelwith.business.web.common.WebUtil;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/11.
 */
@Controller
@RequestMapping("users")
public class UserCtrl {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView add(
        HttpServletRequest request
    ){
        return new ModelAndView().addObject("user",userService.addUser(buildUser(WebUtil.getSimpleParams(request))));
    }

    private static User buildUser(Map data){
        User user = new User();
        user.setCreateTime(WebUtil.getSqlDateTime());
        user.setAvatar(MapUtils.getString(data, "avatar"));
        user.setGender(MapUtils.getInteger(data, "gender"));
        user.setArea(MapUtils.getString(data, "area"));
        user.setWeChatId(MapUtils.getString(data, "wechatId"));
        return user;
    }
}
