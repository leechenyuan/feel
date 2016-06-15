package me.feelwith.business.service.response;

import me.feelwith.business.data.dao.entity.Activity;
import me.feelwith.business.data.dao.entity.Work;

import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicIconFactory;
import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class BasicActivity extends Activity{
    private List<Work> works;

    public BasicActivity(){

    }
    public BasicActivity(Activity activity){
        setActivityId(activity.getActivityId());
        setUserId(activity.getUserId());
        setDescription(activity.getDescription());
        setCreateTime(activity.getCreateTime());
        setLikes(activity.getLikes());
        setRecentLikes(activity.getRecentLikes());
        setComments(activity.getComments());
    }


    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
