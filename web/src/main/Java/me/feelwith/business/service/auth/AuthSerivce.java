package me.feelwith.business.service.auth;

import me.feelwith.business.data.entity.User;
import me.feelwith.business.data.entity.UserAccount;
import me.feelwith.business.service.user.UserAccountService;
import me.feelwith.business.service.user.UserService;
import me.feelwith.common.spring.DataSourceS;
import me.feelwith.common.text.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by L on 2017/2/11.
 */
@Service
public class AuthSerivce {
    @Autowired
    UserService userService;
    @Autowired
    UserAccountService userAccountService;
    @Autowired
    AuthTokenService authTokenService;

    @DataSourceS(dataSource = "user",readOnly = false)
    public boolean isUserAccountValid(long userId,String password){
        User user = userService.getUser(userId);
        if(user == null){
            return false;
        }
        return userAccountService.isPasswordCorrect(userId,password);
    }

    @DataSourceS(dataSource = "auth",readOnly = true)
    public User getUserByToken(String token){
        Long userId = authTokenService.getUserIdByToken(token);
        if(userId == null){
            return null;
        }
        return userService.getUser(userId);
    }

    @DataSourceS(dataSource = "user",readOnly = false)
    public User getUserByAccount(long userId,String password){
        UserAccount userAccount = userAccountService.getUserAccount(userId);
        if((userAccount == null) ||(!userAccount.getPassword().equals(DigestUtil.dm5Hex(password)))){
            return null;
        }
        User user = userService.getUser(userId);
        return user;
    }
}
