package com.eden.agent.common.constants;

/**
 * Create by zhaoxianghui on 2017/11/8.
 */
public enum TaskStatus {
    RUNNING(1, "正在运行"),
    NOT_RUNNING(0, "已停止");

    private int code;
    private String desc;

    TaskStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDesc(int code) {
        for (TaskStatus status : TaskStatus.values()) {
            if (code == status.code) {
                return status.desc;
            }
        }
        return "";
    }
}
