package me.feelwith.business.web.result;

/**
 * Created by Administrator on 2016/6/5.
 */
public class UploadResult {
    private String filePath;

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
    }

    private int mediaId;
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
