package com.eden.agent.common.web;

import java.io.Serializable;

/**
 * Created by liyuanhang on 2017/4/22.
 */
public class IdRequest extends BaseRequest implements Serializable {

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
