package me.feelwith.business.service.gossip;

import me.feelwith.business.data.dao.GossipMapper;
import me.feelwith.business.data.entity.Gossip;
import me.feelwith.common.spring.DataSourceS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/11/22.
 */
@Service
@DataSourceS(dataSource = "gossip",readOnly = false)
public class GossipService {
    @Autowired
    GossipMapper gossipMapper;

    @DataSourceS(dataSource = "gossip",readOnly = false)
    public int insert(Gossip gossip){
        return gossipMapper.insertSelective(gossip);
    }
}
