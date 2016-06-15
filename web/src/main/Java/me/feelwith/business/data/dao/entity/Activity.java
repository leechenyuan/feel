package me.feelwith.business.data.dao.entity;

import java.util.Date;

public class Activity {
    private Integer activityId;

    private Integer userId;

    private String description;

    private Date createTime;

    private Integer likes;

    private Integer recentLikes;

    private Integer comments;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getRecentLikes() {
        return recentLikes;
    }

    public void setRecentLikes(Integer recentLikes) {
        this.recentLikes = recentLikes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }
}