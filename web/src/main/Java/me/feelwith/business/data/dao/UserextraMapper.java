package me.feelwith.business.data.dao;

import java.util.List;
import me.feelwith.business.data.dao.entity.Userextra;
import me.feelwith.business.data.dao.entity.UserextraExample;
import org.apache.ibatis.annotations.Param;

public interface UserextraMapper {
    int countByExample(UserextraExample example);

    int deleteByExample(UserextraExample example);

    int insert(Userextra record);

    int insertSelective(Userextra record);

    List<Userextra> selectByExample(UserextraExample example);

    int updateByExampleSelective(@Param("record") Userextra record, @Param("example") UserextraExample example);

    int updateByExample(@Param("record") Userextra record, @Param("example") UserextraExample example);
}