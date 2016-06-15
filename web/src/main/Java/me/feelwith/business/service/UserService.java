package me.feelwith.business.service;

import me.feelwith.business.data.dao.UserMapper;
import me.feelwith.business.data.dao.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;

/**
 * Created by lideda on 2016/5/11.
 */
@Component("userService")
@Transactional
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User addUser(User user){
        userMapper.insert(user);
        int id = userMapper.selectLastInsertId();
        return userMapper.selectByPrimaryKey(id);
    }

    public User getUserById(int userId){
        return this.userMapper.selectByPrimaryKey(userId);
    }
    public int increaseScore(int aUserId,int bUserId,int score,boolean ifThrow){
        this.userMapper.increaseScore(aUserId,score);
        if(ifThrow){
            throw new IllegalArgumentException("this is an excep");
        }
        return this.userMapper.increaseScore(bUserId,-score);
    }
}
