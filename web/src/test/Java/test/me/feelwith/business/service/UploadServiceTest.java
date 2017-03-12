package test.me.feelwith.business.service;

//import me.feelwith.business.service.IFileUploadService;
import me.feelwith.common.bean.MyBeanUtil;
import me.feelwith.exception.UploadException;
import org.junit.Test;

import java.io.File;

/**
 * * Created by L on 2016/5/12 17:29.
 */
public class UploadServiceTest {
    @Test
    public void upload() throws UploadException {
        File file = new File("E:\\pictures\\小伙子记住，这是你人生的巅峰.gif");
//        IFileUploadService service = MyBeanUtil.getBean("uploadService");
//        System.out.println(service.uploadFile(file));
    }
}
