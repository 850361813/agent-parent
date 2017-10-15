package com.eden.agent.domain;

import java.io.Serializable;

public class RequestEntity implements Serializable {
    private String keyWord;
    private String pageNum;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}
