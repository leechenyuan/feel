package me.feelwith.business.service;

import me.feelwith.exception.UploadException;

import java.io.File;
import java.io.InputStream;

/**
 * Created by lideda on 2016/5/12.
 */
public interface IFileUploadService {
    public String uploadFile(File f) throws UploadException;
    public String uploadFile(File f ,String fileName) throws UploadException;
    public String uploadFile(InputStream in ,String fileName) throws UploadException;
    /**
     * 图片,mime为转换后的格式
     * @param f
     * @param mime
     *      如果为null则使用源文件格式
     * @return
     */
    public String uploadImage(File f,String mime) throws UploadException;

    /**
     * 设置
     * @param f
     * @param mime
     * @param width 像素为单位
     * @param length 像素大小
     * @return
     */
    public String uploadImage(File f,String mime,int width,int length) throws UploadException;
}
