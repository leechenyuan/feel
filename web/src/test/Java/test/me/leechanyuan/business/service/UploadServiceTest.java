package test.me.leechanyuan.business.service;

import me.leechenyuan.business.service.IFileUploadService;
import me.leechenyuan.common.bean.MyBeanUtil;
import me.leechenyuan.exception.UploadException;
import org.junit.Test;

import java.io.File;

/**
 * * Created by L on 2016/5/12 17:29.
 */
public class UploadServiceTest {
    @Test
    public void upload() throws UploadException {
        File file = new File("E:\\pictures\\小伙子记住，这是你人生的巅峰.gif");
        IFileUploadService service = MyBeanUtil.getBean("uploadService");
        System.out.println(service.uploadFile(file));
    }
}
