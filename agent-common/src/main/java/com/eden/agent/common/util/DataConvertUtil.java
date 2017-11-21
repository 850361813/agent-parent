package com.eden.agent.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by zhaoxianghui on 2017/11/21.
 */
public class DataConvertUtil {
    /**
     * 将对象的属性名称与值映射为MAP
     * @param o 对象
     * @return  Map<key,value> key为属性名称，value为名称、类型和值组成的对象
     */
    public static Map<String,FieldsEntity> convertObjectToMap(Object o){
        Class oClass = o.getClass();
        Field[] fields = oClass.getDeclaredFields();   //获取类中的所有声明的属性
        Map<String,FieldsEntity> map = new HashMap<String, FieldsEntity>();
        try{
            for(int i=0;i<fields.length;i++){
                //                不对序列化ID进行映射
                if(fields[i].getName().equals("serialVersionUID")){
                    continue;
                }
                Object valueObject = getFieldValue(o,fields[i].getName());
                map.put(fields[i].getName(), new FieldsEntity(fields[i].getName(), valueObject, fields[i].getType()));
            }
            return map;
        }catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 通过对象的getter方法获取属性值
     * @param o 对象
     * @param name 属性名称
     * @return 相应属性的值
     */
    public static Object getFieldValue(Object o,String name) throws SecurityException, NoSuchMethodException,IllegalArgumentException, IllegalAccessException,
                                                                            InvocationTargetException {
        Class owner = o.getClass();
        Method mothed = owner.getMethod(createGetter(name));
        Object object = mothed.invoke(o);
        return object;
    }

    /**
     * 通过属性名称拼凑getter方法
     * @param fieldName
     * @return
     */
    public static String createGetter(String fieldName){
        if(fieldName == null || fieldName.length() == 0 ){
            return null;
        }
        StringBuffer sb = new StringBuffer("get");
        sb.append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1));
        return sb.toString();
    }
}
