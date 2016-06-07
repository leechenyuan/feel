package me.feelwith.business.web.upload;

import me.feelwith.business.data.dao.entity.Media;
import me.feelwith.business.service.IFileUploadService;
import me.feelwith.business.service.MediaService;
import me.feelwith.business.web.result.UploadResult;
import me.feelwith.common.bean.MyBeanUtil;
import me.feelwith.common.constants.WebSiteConstants;
import me.feelwith.exception.UploadException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by Administrator on 2016/6/5.
 */
@Controller
@RequestMapping("upload")
public class UploadController {
    @Autowired
    IFileUploadService uploadService;
    @Autowired
    MediaService mediaService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file){
        try {
            //@FIXME
            int userId = 0;

            String path = uploadService.uploadFile(file.getInputStream(),name);
            path = WebSiteConstants.FEEL_WITH_ADDR + "/"+ path;
            int mediaId = mediaService.insert(userId,path);
            UploadResult result = new UploadResult();
            result.setFilePath(path);
            result.setMediaId(mediaId);
            return new ModelAndView().addObject(result);
        } catch (UploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "photo",method = RequestMethod.POST)
     public ModelAndView uploadPhoto(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file){
        try {
            //@FIXME
            int userId = 0;

            String path = uploadService.uploadFile(file.getInputStream(),name);
            if(StringUtils.isBlank(path)){
                throw new RuntimeException("can't upload files ");
            }
            path = WebSiteConstants.PIC_ADDR_PREFIX + "/"+ path;
            int mediaId = mediaService.insert(userId,path);
            UploadResult result = new UploadResult();
            result.setFilePath(path);
            result.setMediaId(mediaId);
            return new ModelAndView().addObject(result);
//            String path = uploadService.uploadFile(file.getInputStream(),name);
//            UploadResult result = new UploadResult();
//            result.setFilePath(path);
//            return new ModelAndView().addObject(result);
        } catch (UploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
