package com.eden.agent.domain;

import java.io.Serializable;

public class ResponseEntity implements Serializable {
    private int code;
    private String msg;

    public ResponseEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseEntity success() {
        return new ResponseEntity(0, "success response");
    }
}
