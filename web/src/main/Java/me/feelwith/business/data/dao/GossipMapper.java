package me.feelwith.business.data.dao;

import java.util.List;
import me.feelwith.business.data.entity.Gossip;
import me.feelwith.business.data.entity.GossipExample;
import org.apache.ibatis.annotations.Param;

public interface GossipMapper {
    int countByExample(GossipExample example);

    int deleteByExample(GossipExample example);

    int deleteByPrimaryKey(Long gossipId);

    int insert(Gossip record);

    int insertSelective(Gossip record);

    List<Gossip> selectByExample(GossipExample example);

    Gossip selectByPrimaryKey(Long gossipId);

    int updateByExampleSelective(@Param("record") Gossip record, @Param("example") GossipExample example);

    int updateByExample(@Param("record") Gossip record, @Param("example") GossipExample example);

    int updateByPrimaryKeySelective(Gossip record);

    int updateByPrimaryKey(Gossip record);
}