package me.leechenyuan.business.dao;

import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by lideda on 2016/5/10.
 */
public class UserDao extends JdbcDaoSupport {
    public int incUserScore(int userId,int score){
        int affectedRows =
                getJdbcTemplate().update("update User set Score+=? where UserId = ?"
        ,new Object[]{score,userId});
        return affectedRows;
    }
}
