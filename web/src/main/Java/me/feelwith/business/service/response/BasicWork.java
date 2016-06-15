package me.feelwith.business.service.response;

import me.feelwith.business.data.dao.entity.Work;
import me.feelwith.business.data.dao.entity.Workcomment;

import javax.swing.plaf.basic.BasicIconFactory;
import java.util.List;

/**
 * Created by Administrator on 2016/6/9.
 */
public class BasicWork extends Work{
    List<BasicWorkComment> hotComments;
    List<BasicWorkComment> newComments;

    public BasicWork(Work w){
        setWorkId(w.getWorkId());
        setDescription(w.getDescription());
        setUrl(w.getUrl());
        setCreateTime(w.getCreateTime());
        setActivityId(w.getActivityId());
        setLikes(w.getLikes());
        setRecentLikes(w.getRecentLikes());
    }

    public List<BasicWorkComment> getHotComments() {
        return hotComments;
    }

    public void setHotComments(List<BasicWorkComment> hotComments) {
        this.hotComments = hotComments;
    }

    public List<BasicWorkComment> getNewComments() {
        return newComments;
    }

    public void setNewComments(List<BasicWorkComment> newComments) {
        this.newComments = newComments;
    }
}
