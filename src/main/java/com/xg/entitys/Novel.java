package com.xg.entitys;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xg on 2017/9/20.
 */
public class Novel implements Serializable {
    /* novel name */
    private String name;
    /* author */
    private String author;
    /* url */
    private String url;
    /*category */
    private String type;
    private String lastUpdateChapter;

    private String lastUpdateChapterUrl;

    private Date lastUpdateTime;

    private int status;

    private char firstLetter;

    private int platformId;

    private Date addTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastUpdateChapter() {
        return lastUpdateChapter;
    }

    public void setLastUpdateChapter(String lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter;
    }

    public String getLastUpdateChapterUrl() {
        return lastUpdateChapterUrl;
    }

    public void setLastUpdateChapterUrl(String lastUpdateChapterUrl) {
        this.lastUpdateChapterUrl = lastUpdateChapterUrl;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", lastUpdateChapter='" + lastUpdateChapter + '\'' +
                ", lastUpdateChapterUrl='" + lastUpdateChapterUrl + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", status=" + status +
                ", firstLetter=" + firstLetter +
                ", platformId=" + platformId +
                ", addTime=" + addTime +
                '}';
    }
}
