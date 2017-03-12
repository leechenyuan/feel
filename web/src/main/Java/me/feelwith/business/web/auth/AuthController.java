package me.feelwith.business.web.auth;

import me.feelwith.business.service.auth.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by L on 2017/2/11.
 */
@Controller
@RequestMapping("auths")
public class AuthController {
    @Autowired
    AuthTokenService authTokenService;

    @RequestMapping(value = "token",method = RequestMethod.PUT)
    public String updateToken(long userId){
        return authTokenService.updateToken(userId);
    }

    @RequestMapping(value = "token",method = RequestMethod.GET)
    public @ResponseBody String getToken(long userId){
       return authTokenService.getUserToken(userId);
    }
}
