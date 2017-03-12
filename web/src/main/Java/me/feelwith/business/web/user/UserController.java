package me.feelwith.business.web.user;

import me.feelwith.business.data.entity.User;
import me.feelwith.business.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by L on 2017/2/11.
 */
@Controller
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody User createUser(HttpServletRequest request,User user,BindingResult result){
        String password = request.getParameter("password");
        userService.createUser(user,password);
        return userService.getUser(user.getUserId());
    }
}
