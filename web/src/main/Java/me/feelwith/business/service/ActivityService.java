package me.feelwith.business.service;

import me.feelwith.business.data.dao.ActivityMapper;
import me.feelwith.business.data.dao.WorkMapper;
import me.feelwith.business.data.dao.entity.Activity;
import me.feelwith.business.data.dao.entity.ActivityExample;
import me.feelwith.business.data.dao.entity.Work;
import me.feelwith.business.data.dao.entity.WorkExample;
import me.feelwith.business.service.response.BasicActivity;
import me.feelwith.business.web.common.DateTimeUtil;
import me.feelwith.business.web.common.WebUtil;
import org.apache.commons.collections4.ListUtils;
import org.apache.ibatis.javassist.CodeConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by L on 2016/5/22 11:36.
 */
@Component("activityService")
@Transactional
public class ActivityService {
    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private WorkMapper workMapper;

    //参与活动
    public int joinActivity(int userId,int activityId,int workId){
//        Work recoder = workMapper.selectByPrimaryKey(mediaId);
//        recoder.setActivityId(activityId);
//        WorkExample example = new WorkExample();
//        example.createCriteria().andWorkIdEqualTo(mediaId);
//        int rows = workMapper.updateByExampleSelective(recoder, example);
//        return activityId;
        Work work = new Work();
        work.setWorkId(workId);
        work.setActivityId(activityId);
        workMapper.updateByPrimaryKeySelective(work);
        return activityId;
    }

    public int createActivity(int userId,String description){
        Activity activity = new Activity();
        activity.setUserId(userId);
        activity.setCreateTime(WebUtil.getSqlDateTime());
        activity.setDescription(description);
        activityMapper.insertSelective(activity);
        int actId = activityMapper.selectLastInsertId();
        return actId;
    }

    public List<BasicActivity> listActivityByType(int type,int starId,int limit){
        //TODO 完善，缓存
        List<Activity> activities;
        ActivityExample example = new ActivityExample();
        example.setLimit(limit);
        if(type == ActivityType.LASTEST){
            example.setOrderByClause("activityId desc");
            example.createCriteria()
            .andActivityIdGreaterThan(starId)
            ;
        }else{
            example.setOrderByClause("recentlikes desc");
            example.createCriteria()
            ;
        }
        activities = activityMapper.selectByExample(example);
        List<BasicActivity> basicActivities = wrapActivity(activities);
        fillWork(basicActivities);
        return basicActivities;
    }
    public Activity like(int activityId){
        Activity activity = activityMapper.selectByPrimaryKey(activityId);
        activity.setLikes(activity.getLikes()+1);
        activityMapper.updateByPrimaryKeySelective(activity);
        return activity;
    }
    public List<BasicActivity> wrapActivity(List<Activity> activityList){
        List<BasicActivity> basicActivities = new ArrayList<>(activityList.size());
        for(Activity a : activityList){
            basicActivities.add(new BasicActivity(a));
        }
        return basicActivities;
    }

    public List<Work> getWorkByActivityId(int activityId){
        WorkExample example = new WorkExample();
        example.createCriteria().andActivityIdEqualTo(activityId);
        return ListUtils.emptyIfNull(workMapper.selectByExample(example));
    }

    public void fillWork(List<BasicActivity> activities){
        for (BasicActivity a : activities){
            a.setWorks(getWorkByActivityId(a.getActivityId()));
        }
    }


    public static final class ActivityType{
        public static final int LASTEST = 1;
        public static final int RECENT_HOT = 2;
        public static final int WEEK_HOT = 3;
        public static final int MONTH_HOT = 4;
    }
}
