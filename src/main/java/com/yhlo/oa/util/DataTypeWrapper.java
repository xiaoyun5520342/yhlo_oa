package com.yhlo.oa.util;

import com.yhlo.oa.entity.KeyList;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @create 2022-04-11 15:05
 */
public class DataTypeWrapper {

    public static List<KeyList> getKeyList(Class clazz) {
        List list = new ArrayList();
        Map<String, Field> fieldMap = new HashMap<>();
        List<Field> allField = new ArrayList<>();

        while (clazz != null) {
            allField.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        for (Field field : allField) {
            if (field.getAnnotation(ApiModelProperty.class) != null && !StringUtils.isEmpty(field.getAnnotation(ApiModelProperty.class).value())) {
                String fieldName = field.getName();
                if (!fieldMap.containsKey(fieldName)) fieldMap.put(fieldName, field);
            }
        }
        for (Field field : fieldMap.values()) {
            KeyList keyList = new KeyList();
            keyList.setKey(field.getName());
            keyList.setLabel(field.getAnnotation(ApiModelProperty.class).value());
            list.add(keyList);
        }
        return list;
    }
}
