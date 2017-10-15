package com.eden.agent.common.web;

import java.io.Serializable;

/**
 * Created by liyuanhang on 2017/4/22.
 */
public class IdResponse extends BaseResponse<IdResponse.IdHolder> implements Serializable {

    public IdResponse(long id) {
        super(new IdHolder(id));
    }

    public static class IdHolder {
        public long id;

        public IdHolder(long id) {
            this.id = id;
        }
    }
}
