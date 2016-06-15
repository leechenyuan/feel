package me.feelwith.business.data.dao;

import java.util.List;
import me.feelwith.business.data.dao.entity.Workactionlog;
import me.feelwith.business.data.dao.entity.WorkactionlogExample;
import org.apache.ibatis.annotations.Param;

public interface WorkactionlogMapper {
    int countByExample(WorkactionlogExample example);

    int deleteByExample(WorkactionlogExample example);

    int deleteByPrimaryKey(Integer workId);

    int insert(Workactionlog record);

    int insertSelective(Workactionlog record);

    List<Workactionlog> selectByExample(WorkactionlogExample example);

    Workactionlog selectByPrimaryKey(Integer workId);

    int updateByExampleSelective(@Param("record") Workactionlog record, @Param("example") WorkactionlogExample example);

    int updateByExample(@Param("record") Workactionlog record, @Param("example") WorkactionlogExample example);

    int updateByPrimaryKeySelective(Workactionlog record);

    int updateByPrimaryKey(Workactionlog record);
}