package me.feelwith.business.service.impl;

import me.feelwith.business.service.IFileUploadService;
import me.feelwith.exception.UploadException;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * Created by lideda on 2016/5/12.
 */
@Service("uploadService")
public class FastDfsUploadServiceWrapper implements IFileUploadService {
    private static final Logger logger = LoggerFactory.getLogger(FastDfsUploadServiceWrapper.class);

    @Override
    public String uploadFile(File f) throws UploadException {
        return uploadFile(f,f.getName());
    }

    @Override
    public String uploadFile(File f, String fileName) throws UploadException {
        String [] urlParams = FastDfsWrapper.uploadFile(f,fileName,f.length());
        String url = StringUtils.defaultString(urlParams[0]);
        url += "/"+StringUtils.defaultString(urlParams[1]);
        return url;
    }

    @Override
    public String uploadFile(InputStream in, String fileName) throws UploadException {
        return null;
    }

    @Override
    public String uploadImage(File f, String mime) throws UploadException {
        return null;
    }

    @Override
    public String uploadImage(File f, String mime, int width, int length) throws UploadException {
        return null;
    }

    private static void transferException(Throwable t) throws UploadException {
        UploadException exception = new UploadException(t.getMessage(), t);
        throw exception;
    }

    private static class FastDfsWrapper {
       private static final String configFileName =
               "E:\\homework\\java\\workspace\\feels\\web\\src\\main\\Resource\\fdfs_client.conf";
//                "fdfs_client.conf";
       static {
           init();
       }
        private static void init(){
            try {
                ClientGlobal.init(configFileName);
            } catch (Throwable e) {
                logger.error(e.getMessage(),e);
            }
        }
        /**
         * 上传文件
         */
        public static String[] uploadFile(File file, String uploadFileName, long fileLength) throws UploadException {
            logger.debug("上传文件=======================");
            byte[] fileBuff = new byte[0];
            try {
                fileBuff = getFileBuffer(new FileInputStream(file), fileLength);
            } catch (IOException e) {
                transferException(e);
            }
            String[] files = null;
            String fileExtName = "";
            if (uploadFileName.contains(".")) {
                fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
            } else {
                logger.debug("Fail to upload file, because the format of filename is illegal.");
                return null;
            }

            // 建立连接
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = null;
            try {
                trackerServer = tracker.getConnection();
            } catch (IOException e) {
                transferException(e);
            }
            StorageServer storageServer = null;
            StorageClient client = new StorageClient(trackerServer, storageServer);

            // 设置元信息
            NameValuePair[] metaList = new NameValuePair[3];
            metaList[0] = new NameValuePair("fileName", uploadFileName);
            metaList[1] = new NameValuePair("fileExtName", fileExtName);
            metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));

            // 上传文件
            try {
                files = client.upload_file(fileBuff, fileExtName, metaList);
            } catch (Exception e) {
                logger.debug("Upload file \"" + uploadFileName + "\"fails");
            }
            try {
                trackerServer.close();
            } catch (IOException e) {
                transferException(e);
            }
            return files;
        }

        private static byte[] getFileBuffer(InputStream inStream, long fileLength) throws IOException {

            byte[] buffer = new byte[256 * 1024];
            byte[] fileBuffer = new byte[(int) fileLength];

            int count = 0;
            int length = 0;

            while ((length = inStream.read(buffer)) != -1) {
                for (int i = 0; i < length; ++i) {
                    fileBuffer[count + i] = buffer[i];
                }
                count += length;
            }
            return fileBuffer;
        }

        //下载文件
        public static void downloadFile(String groupName, String filepath) throws Exception {
            logger.debug("下载文件=======================");
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] b = storageClient.download_file(groupName, filepath);
            logger.debug("文件大小:" + b.length);
            String fileName = "e:\\temp\\test1.jpg";
            FileOutputStream out = new FileOutputStream(fileName);
            out.write(b);
            out.flush();
            out.close();
        }

        //查看文件信息
        public static void getFileInfo(String groupName, String filepath) throws Exception {
            logger.debug("获取文件信息=======================");
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            FileInfo fi = storageClient.get_file_info(groupName, filepath);
            logger.debug("所在服务器地址:" + fi.getSourceIpAddr());
            logger.debug("文件大小:" + fi.getFileSize());
            logger.debug("文件创建时间:" + fi.getCreateTimestamp());
            logger.debug("文件CRC32 signature:" + fi.getCrc32());
        }

        public static void getFileMate(String groupName, String filepath) throws Exception {
            logger.debug("获取文件Mate=======================");
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            NameValuePair nvps[] = storageClient.get_metadata(groupName, filepath);
            for (NameValuePair nvp : nvps) {
                logger.debug(nvp.getName() + ":" + nvp.getValue());
            }
        }

        public static void deleteFile(String groupName, String filepath) throws Exception {
            logger.debug("删除文件=======================");
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            int i = storageClient.delete_file(groupName, filepath);
            logger.debug(i == 0 ? "删除成功" : "删除失败:" + i);
        }
    }
}
