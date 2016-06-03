package me.feelwith.common.utils.image;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * * Created by L on 2016/5/12 17:04.
 */
public class ImageUtil {
    public static InputStream resize(InputStream in,String formateName,Integer width,Integer heigh) throws IOException {
        BufferedImage src = ImageIO.read(in);
        BufferedImage desc = ImageScalerWrapper.resize(src,width,heigh);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(desc,formateName,baos);
        InputStream is = new ByteArrayInputStream(baos.toByteArray());
        return is;
    }
}
