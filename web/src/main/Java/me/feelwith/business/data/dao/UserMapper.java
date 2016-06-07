package me.feelwith.business.data.dao;

import java.util.List;
import me.feelwith.business.data.dao.entity.User;
import me.feelwith.business.data.dao.entity.UserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Update("update User set Score=Score+#{Score} where UserId=#{UserId}")
    int increaseScore(@Param("UserId") int userId, @Param("Score") int score);

    @Select("SELECT  LAST_INSERT_ID()")
    int selectLastId();
}