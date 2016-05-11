package me.leechenyuan.business.service;

import me.leechenyuan.business.data.dao.UserMapper;
import me.leechenyuan.business.data.dao.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lideda on 2016/5/11.
 */
@Service("userMapperService")
public class UserMapperService {
    @Resource
    private UserMapper userMapper;
    public User getUserById(int userId){
        return this.userMapper.selectByPrimaryKey(userId);
    }
}
