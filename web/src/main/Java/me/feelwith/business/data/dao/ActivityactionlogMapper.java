package me.feelwith.business.data.dao;

import java.util.List;
import me.feelwith.business.data.dao.entity.Activityactionlog;
import me.feelwith.business.data.dao.entity.ActivityactionlogExample;
import org.apache.ibatis.annotations.Param;

public interface ActivityactionlogMapper {
    int countByExample(ActivityactionlogExample example);

    int deleteByExample(ActivityactionlogExample example);

    int deleteByPrimaryKey(Integer activityId);

    int insert(Activityactionlog record);

    int insertSelective(Activityactionlog record);

    List<Activityactionlog> selectByExample(ActivityactionlogExample example);

    Activityactionlog selectByPrimaryKey(Integer activityId);

    int updateByExampleSelective(@Param("record") Activityactionlog record, @Param("example") ActivityactionlogExample example);

    int updateByExample(@Param("record") Activityactionlog record, @Param("example") ActivityactionlogExample example);

    int updateByPrimaryKeySelective(Activityactionlog record);

    int updateByPrimaryKey(Activityactionlog record);
}