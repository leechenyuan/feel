package me.feelwith.business.data.entity;

import java.util.Date;

public class Gossip {
    private Long gossipId;

    private Long replyId;

    private Long transmitId;

    private Long userId;

    private String content;

    private String img;

    private Date createTime;

    public Long getGossipId() {
        return gossipId;
    }

    public void setGossipId(Long gossipId) {
        this.gossipId = gossipId;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getTransmitId() {
        return transmitId;
    }

    public void setTransmitId(Long transmitId) {
        this.transmitId = transmitId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}