package me.feelwith.business.service.response;

import me.feelwith.business.data.dao.entity.User;
import me.feelwith.business.data.dao.entity.Workcomment;

import java.util.Date;

/**
 * Created by Administrator on 2016/6/10.
 */
public class BasicWorkComment {
    String content;
    int likes;
    Date createTime;
    User user;
    User refUser;//回复的用户

    public BasicWorkComment(Workcomment workcomment){
        this.content = workcomment.getContent();
        this.likes = workcomment.getLikes();
        this.createTime = workcomment.getCreateTime();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRefUser() {
        return refUser;
    }

    public void setRefUser(User refUser) {
        this.refUser = refUser;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
