package me.leechenyuan.common.utils.image;

import org.imgscalr.Scalr;

import java.awt.image.BufferedImage;
import java.lang.management.OperatingSystemMXBean;

/**
 * * Created by L on 2016/5/13 16:19.
 */
public class ImageScalerWrapper {
    protected static BufferedImage resize(BufferedImage src,Integer width,Integer height){
        if(width == null && height == null ){
            throw new IllegalArgumentException("width and heigh must not be null");
        }
        if(width == null){
            return Scalr.resize(src, Scalr.Mode.FIT_TO_HEIGHT,height);
        }
        if(height == null ){
            return Scalr.resize(src, Scalr.Mode.FIT_TO_WIDTH,width);
        }
        return Scalr.resize(src, Scalr.Method.QUALITY,width,height);
    }
}
