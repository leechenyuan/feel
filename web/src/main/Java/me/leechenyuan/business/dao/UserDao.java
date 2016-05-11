package me.leechenyuan.business.dao;

import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

/**
  Created by lideda on 2016/5/10.
 */
@Component("userDao")
public class UserDao extends BasicDao {
    public int incUserScore(int userId,int score){
        int affectedRows =
                getJdbcTemplate().update("update User set Score=Score+? where UserId = ?"
        ,new Object[]{score,userId});
        return affectedRows;
    }
}
