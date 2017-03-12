package me.feelwith.business.service.user;

import me.feelwith.business.data.dao.UserAccountMapper;
import me.feelwith.business.data.entity.UserAccount;
import me.feelwith.common.spring.DataSourceS;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by L on 2017/2/11.
 */
@Service
@DataSourceS(dataSource = "user",readOnly = false)
public class UserAccountService {
    @Autowired
    UserAccountMapper userAccountMapper;

    public boolean isPasswordCorrect(long userId,String password){
        if(userId < 1){
            return false;
        }
        if(StringUtils.isBlank(password)){
            return false;
        }

        UserAccount account = userAccountMapper.selectByPrimaryKey(userId);
        if(account == null){
            return false;
        }
        return password.equals(account.getPassword());
    }

    public UserAccount getUserAccount(long userId){
        return userAccountMapper.selectByPrimaryKey(userId) ;
    }
}
