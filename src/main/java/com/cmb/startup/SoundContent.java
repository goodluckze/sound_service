package com.cmb.startup;

import java.util.Date;

public class SoundContent {
    private Long id;
    private String eventId;
    private long bUserId;
    private String bUserName;
    private String contentType;
    private String content;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public long getbUserId() {
        return bUserId;
    }

    public void setbUserId(long bUserId) {
        this.bUserId = bUserId;
    }

    public String getbUserName() {
        return bUserName;
    }

    public void setbUserName(String bUserName) {
        this.bUserName = bUserName;
    }

    public String getType() {
        return contentType;
    }

    public void setType(String type) {
        this.contentType = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
