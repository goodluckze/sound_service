package com.cmb.startup.model;


/*
   声波承载内容实体
   1.商户id
   2.商户url（图片或者url)
   3.商户叠加mp3
 */
public class SoundRegistry {
    private long bUserId;
    private String contentType;
    private String content;
    private String addMusic;

    public long getbUserId() {
        return bUserId;
    }

    public void setbUserId(long bUserId) {
        this.bUserId = bUserId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddMusic() {
        return addMusic;
    }

    public void setAddMusic(String addMusic) {
        this.addMusic = addMusic;
    }
}
