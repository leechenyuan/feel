package me.feelwith.business.service.user;

import me.feelwith.business.data.dao.UserAccountMapper;
import me.feelwith.business.data.dao.UserMapper;
import me.feelwith.business.data.entity.User;
import me.feelwith.business.data.entity.UserAccount;
import me.feelwith.common.spring.DataSourceS;
import me.feelwith.common.text.DigestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by L on 2017/2/11.
 */
@Service
@DataSourceS(dataSource = "user",readOnly = false)
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserAccountMapper userAccountMapper;

    @Transactional(readOnly = false)
    public long createUser(User user,String password){
        user.setUserId(createUserId());
        user.setCreateTime(new Date());
        userMapper.insertSelective(user);
        UserAccount account = new UserAccount();
        account.setUserId(user.getUserId());
        account.setPassword(DigestUtil.dm5Hex(password));
        userAccountMapper.insertSelective(account);
        return user.getUserId();
    }

    public User getUser(long userId){
        return userMapper.selectByPrimaryKey(Long.valueOf(userId));//默认id等于userId
    }

    public long createUserId(){
        return UserIdService.createUserId();
    }
}
