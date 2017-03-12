package me.feelwith.business.web.upload;

/**
import me.feelwith.business.service.IFileUploadService;
import me.feelwith.business.service.WorkService;
import me.feelwith.business.web.common.WebUtil;
import me.feelwith.business.web.result.FileUploadResult;
import me.feelwith.business.web.result.WorkUploadResult;
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
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

 * Created by Administrator on 2016/6/5.
@Controller
@RequestMapping("upload")
public class UploadController {
    @Autowired
    IFileUploadService uploadService;
    @Autowired
    WorkService mediaService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView upload(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file){
        try {
            //@FIXME
            int userId = 0;
            String path = uploadService.uploadFile(file.getInputStream(),name);
            path = WebSiteConstants.getFileUploadAddrPrefix() + "/"+ path;
            FileUploadResult result = new FileUploadResult();
            result.setFilePath(path);
            return new ModelAndView().addObject(result);
        } catch (UploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    @RequestMapping(value = "work",method = RequestMethod.POST)
//     public ModelAndView uploadPhoto(
//            HttpServletRequest request,
//            @RequestParam("name") String name,
//			@RequestParam("file") MultipartFile file){
//        try {
//            //@FIXME
//            int userId = WebUtil.getUserId(request);
//            String path = uploadService.uploadFile(file.getInputStream(),name);
//            if(StringUtils.isBlank(path)){
//                throw new RuntimeException("can't upload files ");
//            }
//            path = WebSiteConstants.PIC_ADDR_PREFIX + "/"+ path;
//            int mediaId = mediaService.insert(userId,null,path);
//            WorkUploadResult result = new WorkUploadResult();
//            result.setFilePath(path);
//            result.setWorkId(mediaId);
//            return new ModelAndView().addObject(result);
////            String path = uploadService.uploadFile(file.getInputStream(),name);
////            UploadResult result = new UploadResult();
////            result.setFilePath(path);
////            return new ModelAndView().addObject(result);
//        } catch (UploadException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
 */
