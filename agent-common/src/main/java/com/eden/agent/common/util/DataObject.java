package com.eden.agent.common.util;

import java.util.Date;

/**
 * Create by zhaoxianghui on 2017/11/21.
 */
public class DataObject {
    private Date recordTime;
    private String businessLine;
    private String resourceName;
    public Date getRecordTime() {
        return recordTime;
    }
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
    public String getBusinessLine() {
        return businessLine;
    }
    public void setBusinessLine(String businessLine) {
        this.businessLine = businessLine;
    }
    public String getResourceName() {
        return resourceName;
    }
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
