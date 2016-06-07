package me.feelwith.business.service;

import me.feelwith.business.data.dao.ActivityMapper;
import me.feelwith.business.data.dao.MediaMapper;
import me.feelwith.business.data.dao.entity.Activity;
import me.feelwith.business.data.dao.entity.Media;
import me.feelwith.business.data.dao.entity.MediaExample;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by L on 2016/5/22 11:36.
 */
@Component("activityService")
@Transactional
public class ActivityService {
    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private MediaMapper mediaMapper;

    public int addOrJoinActivity(int userId,int activityId,String description,int mediaId){
       if(activityId == 0) {
           return createActivity(userId,description,mediaId);
       }
        return joinActivity(userId,activityId,description,mediaId);
    }

    //参与活动
    public int joinActivity(int userId,int activityId,String description,int mediaId){
        Media recoder = mediaMapper.selectByPrimaryKey(mediaId);
        MediaExample example = new MediaExample();
        example.createCriteria().andMediaIdEqualTo(mediaId);
        mediaMapper.updateByExampleSelective(recoder, example);
        return activityId;
    }
    /**
     * 创建活动
     * @param description
     * @param mediaId
     * @return
     */
    public int createActivity(int userId,String description,int mediaId){
        Activity activity = new Activity();
        activity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        activity.setDescription(description);
        activityMapper.insert(activity);
        int actId = activityMapper.selectLastId();
        joinActivity(userId,actId,description,mediaId);
        return actId;
    }
}
