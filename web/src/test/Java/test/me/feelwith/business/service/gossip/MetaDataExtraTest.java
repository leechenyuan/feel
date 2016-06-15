package test.me.feelwith.business.service.gossip;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.google.common.collect.Maps;
import me.feelwith.business.data.dao.entity.User;
import me.feelwith.business.web.common.WebUtil;
import me.feelwith.common.bean.MyBeanUtil;
import me.feelwith.common.utils.GsonUtil;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

/**
 * * Created by L on 2016/5/12 17:14.
 */
public class MetaDataExtraTest {
    @Test
    public void extra() throws ImageProcessingException, IOException {
        File file = new File("E:\\temp\\test1.jpg");
        Metadata metadata = ImageMetadataReader.readMetadata(file);

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s",
                        directory.getName(), tag.getTagName(), tag.getDescription());
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
    }

    @Test
    public void uuid(){
        String uuid = UUID.randomUUID().toString();
        System.err.format("ERROR: %s", uuid);
    }

    @Test
    public void bean() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        User u = new User();
        u.setCreateTime(WebUtil.getSqlDateTime());
        u.setUserId(9);
        Map data = Maps.newHashMap();
        data = MyBeanUtil.toMap(u);
//        BeanUtils.copyProperties(u,data);
        System.err.println("ERROR: %s"+data);
    }
}
