package com.eden.agent.common.constants;

/**
 * Create by zhaoxianghui on 2017/11/20.
 */
public enum SearchTypeEnum {

    SEARCH_BY_KEYWORD(1),
    SEARCH_BY_CHANNEL(2);

    private int code;

    SearchTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
