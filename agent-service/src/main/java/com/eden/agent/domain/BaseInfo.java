package com.eden.agent.domain;

import java.sql.Timestamp;

public class BaseInfo {
    private long id;
    private String keyWord;
    private int pageNum;
    private String pageUrl;
    private String inputUrl;
    private String inputTag;
    private String videoLinks;
    private int valid;
    private Timestamp collectTime;
    private Timestamp publishTime;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String videoId;
    private int crawStatus;
    private int fetchStatus;
    private int publishStatus;
    private String videoTitle;
    private String videoInfo;
    private String videoItemId;
    private int postStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getInputUrl() {
        return inputUrl;
    }

    public void setInputUrl(String inputUrl) {
        this.inputUrl = inputUrl;
    }

    public String getInputTag() {
        return inputTag;
    }

    public void setInputTag(String inputTag) {
        this.inputTag = inputTag;
    }

    public String getVideoLinks() {
        return videoLinks;
    }

    public void setVideoLinks(String videoLinks) {
        this.videoLinks = videoLinks;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public Timestamp getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Timestamp collectTime) {
        this.collectTime = collectTime;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getCrawStatus() {
        return crawStatus;
    }

    public void setCrawStatus(int crawStatus) {
        this.crawStatus = crawStatus;
    }

    public int getFetchStatus() {
        return fetchStatus;
    }

    public void setFetchStatus(int fetchStatus) {
        this.fetchStatus = fetchStatus;
    }

    public int getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(String videoInfo) {
        this.videoInfo = videoInfo;
    }

    public String getVideoItemId() {
        return videoItemId;
    }

    public void setVideoItemId(String videoItemId) {
        this.videoItemId = videoItemId;
    }

    public int getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(int postStatus) {
        this.postStatus = postStatus;
    }
}
