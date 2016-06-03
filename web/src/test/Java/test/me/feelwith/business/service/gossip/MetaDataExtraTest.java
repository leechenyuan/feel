package test.me.feelwith.business.service.gossip;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
}
