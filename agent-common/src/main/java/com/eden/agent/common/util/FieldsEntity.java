package com.eden.agent.common.util;

/**
 * Create by zhaoxianghui on 2017/11/21.
 */
public class FieldsEntity {
    private String attributeName;     //属性变量名称
    private Object value;                //属性变量值
    private Class classType;          //属性类型

    public String getAttributeName() {
        return attributeName;
    }
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public Class getClassType() {
        return classType;
    }
    public void setClassType(Class classType) {
        this.classType = classType;
    }
    public FieldsEntity(String fieldName, Object o, Class classType){
        this.attributeName = fieldName;
        this.value = o;
        this.classType = classType;
    }
}
