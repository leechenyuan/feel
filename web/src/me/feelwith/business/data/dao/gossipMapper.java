package me.feelwith.business.data.dao;

import java.util.List;
import me.feelwith.business.data.entity.gossip;
import me.feelwith.business.data.entity.gossipExample;
import org.apache.ibatis.annotations.Param;

public interface gossipMapper {
    int countByExample(gossipExample example);

    int deleteByExample(gossipExample example);

    int deleteByPrimaryKey(Long id);

    int insert(gossip record);

    int insertSelective(gossip record);

    List<gossip> selectByExample(gossipExample example);

    gossip selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") gossip record, @Param("example") gossipExample example);

    int updateByExample(@Param("record") gossip record, @Param("example") gossipExample example);

    int updateByPrimaryKeySelective(gossip record);

    int updateByPrimaryKey(gossip record);
}