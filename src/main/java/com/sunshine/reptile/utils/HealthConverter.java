/**
 * 
 */
package com.sunshine.reptile.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cglib.core.Converter;

/**
 * @author 张梓枫
 * @Description
 * @date: 2019年1月14日 上午10:01:56
 */
public class HealthConverter implements Converter {
    
    private static ObjectMapper objectMapperEx = new ObjectMapperEx();

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Object convert(Object value, Class target, Object context) {
        if ((value == null) || (!StringUtils.hasText(value.toString()))) {
            return null;
        }
        if (target.getTypeName().equals("java.lang.Object")) {
            return value;
        }
        if (target.isAssignableFrom(JsonNode.class)) {
            try {
                return objectMapperEx.readTree(value.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        if (((value instanceof JsonNode)) && (target.isAssignableFrom(String.class))) {
            return value.toString();
        }
        if ((!value.getClass().isAssignableFrom(target)) && (!target.isAssignableFrom(value.getClass()))) {
            return null;
        }
        return value;
    }

}
