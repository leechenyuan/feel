package me.feelwith.business.service;

import me.feelwith.business.data.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lideda on 2016/5/10.
 */
@Transactional
@Component("userService")
public class UserService {
    @Autowired
    private UserDao userDao ;
    public int incScore(boolean thr){
        int inc = 10;
        userDao.incUserScore(1,inc);
        if(thr){
            throw new IllegalArgumentException("dadsada");
        }
        userDao.incUserScore(2,-inc);
        return 0;
    }
}
