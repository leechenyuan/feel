package me.feelwith.exception;

/**
 * Created by lideda on 2016/5/12.
 */
public class UploadException extends Exception{
    public UploadException(String message, Throwable t) {
        super(t.getMessage(),t);
    }
}
