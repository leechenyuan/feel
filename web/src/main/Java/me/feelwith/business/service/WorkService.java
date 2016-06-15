package me.feelwith.business.service;

import com.google.common.collect.Lists;
import me.feelwith.business.data.dao.WorkMapper;
import me.feelwith.business.data.dao.WorkcommentMapper;
import me.feelwith.business.data.dao.constant.OrderClause;
import me.feelwith.business.data.dao.entity.Work;
import me.feelwith.business.data.dao.entity.Workcomment;
import me.feelwith.business.data.dao.entity.WorkcommentExample;
import me.feelwith.business.service.response.BasicWork;
import me.feelwith.business.service.response.BasicWorkComment;
import me.feelwith.business.web.common.WebUtil;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2016/6/6.
 */
@Component("workService")
@Transactional
public class WorkService {
    @Resource
    private WorkMapper workMapper;
    @Resource
    WorkcommentMapper workcommentMapper;
    @Resource
    UserService userService;

    //返回workId
    public int insert(Work work){
        work.setCreateTime(WebUtil.getSqlDateTime());
        workMapper.insertSelective(work);
        return workMapper.selectLastInsertId();
    }

    public Work like(int workId){
        Work work = workMapper.selectByPrimaryKey(workId);
        work.setLikes(work.getLikes()+1);
        workMapper.updateByPrimaryKeySelective(work);
        return work;
    }

    public Work selectById(int workId){
        return workMapper.selectByPrimaryKey(workId);
    }

    public BasicWork getWorkDetail(int workId){
        Work w = selectById(workId);
        BasicWork bw = new BasicWork(w) ;
        fillLastAndHotComments(bw);
        return bw;
    }

    public Workcomment addWorkComment(Workcomment workcomment){
        workcomment.setCreateTime(WebUtil.getSqlDateTime());
        workcommentMapper.insertSelective(workcomment);
        int id = workcommentMapper.selectLastInsertId();
        return workcommentMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void fillLastAndHotComments(BasicWork bw){
        WorkcommentExample example = new WorkcommentExample();
        example.setLimit(3);
        example.setOrderByClause(OrderClause.LIKES_DESC);
        example.createCriteria().andWorkIdEqualTo(bw.getWorkId());
        bw.setHotComments(wrapBasicWorkComment(ListUtils.emptyIfNull(workcommentMapper.selectByExample(example))));
        example.setLimit(7);
        example.setOrderByClause(OrderClause.CREATE_TIME_DESC);
        bw.setNewComments(wrapBasicWorkComment(ListUtils.emptyIfNull(workcommentMapper.selectByExample(example))));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<BasicWorkComment> wrapBasicWorkComment(List<Workcomment> workcomments){
        List<BasicWorkComment> basicWorkComments = Lists.newArrayListWithExpectedSize(workcomments.size());
        for(Workcomment e : workcomments){
            BasicWorkComment b = new BasicWorkComment(e);
            b.setUser(userService.getUserById(e.getUserId()));
            if(e.getRefUserId() != null){
                b.setRefUser(userService.getUserById(e.getRefUserId()));
            }
            basicWorkComments.add(b);
        }
        return basicWorkComments;
    }
}
