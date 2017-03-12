package me.feelwith.business.data.dao;

import java.util.List;
import me.feelwith.business.data.entity.AuthToken;
import me.feelwith.business.data.entity.AuthTokenExample;
import org.apache.ibatis.annotations.Param;

public interface AuthTokenMapper {
    int countByExample(AuthTokenExample example);

    int deleteByExample(AuthTokenExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(AuthToken record);

    int insertSelective(AuthToken record);

    List<AuthToken> selectByExample(AuthTokenExample example);

    AuthToken selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") AuthToken record, @Param("example") AuthTokenExample example);

    int updateByExample(@Param("record") AuthToken record, @Param("example") AuthTokenExample example);

    int updateByPrimaryKeySelective(AuthToken record);

    int updateByPrimaryKey(AuthToken record);
}