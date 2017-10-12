package com.eden.agent.domain;

import java.io.Serializable;

public class RequestEntity implements Serializable {
    private String keyWord;
    private int pageNum;

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
}
