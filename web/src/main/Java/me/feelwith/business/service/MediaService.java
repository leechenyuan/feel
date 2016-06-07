package me.feelwith.business.service;

import me.feelwith.business.data.dao.MediaMapper;
import me.feelwith.business.data.dao.entity.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/6/6.
 */
@Component("mediaService")
@Transactional
public class MediaService {
    @Resource
    private MediaMapper mediaMapper;

    public int insert(int userId,String url){
        Media media = new Media();
        media.setUserId(userId);
        media.setUrl(url);
        mediaMapper.insertSelective(media);
        return mediaMapper.selectLastId();
    }
}
