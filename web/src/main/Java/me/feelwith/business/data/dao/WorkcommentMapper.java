package me.feelwith.business.data.dao;

import java.util.List;
import me.feelwith.business.data.dao.entity.Workcomment;
import me.feelwith.business.data.dao.entity.WorkcommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface WorkcommentMapper {
    int countByExample(WorkcommentExample example);

    int deleteByExample(WorkcommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(Workcomment record);

    int insertSelective(Workcomment record);

    List<Workcomment> selectByExample(WorkcommentExample example);

    Workcomment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") Workcomment record, @Param("example") WorkcommentExample example);

    int updateByExample(@Param("record") Workcomment record, @Param("example") WorkcommentExample example);

    int updateByPrimaryKeySelective(Workcomment record);

    int updateByPrimaryKey(Workcomment record);

    @Select("SELECT  LAST_INSERT_ID()")
    int selectLastInsertId();
}