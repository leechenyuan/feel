package me.leechenyuan.business.service;

import me.leechenyuan.business.dao.UserDao;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by lideda on 2016/5/10.
 */
public class UserService {
    private static final UserDao userDao = new UserDao();
    @Transactional
    public static int incScore(){
        int inc = 10;
        userDao.incUserScore(1,inc);
        if(new Random().nextBoolean()){
            throw new IllegalArgumentException("dadsada");
        }
        userDao.incUserScore(2,-inc);
        return 0;
    }

    @Test
    public void test(){
       incScore();
    }
}
