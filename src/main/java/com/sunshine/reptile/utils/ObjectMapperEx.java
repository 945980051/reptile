/**
 * 
 */
package com.sunshine.reptile.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * @author 张梓枫
 * @Description
 * @date: 2019年1月14日 上午10:00:53
 */
public class ObjectMapperEx extends ObjectMapper {

    private static final long serialVersionUID = 9063519517507369864L;

    public ObjectMapperEx() {
        setSerializationInclusion(JsonInclude.Include.NON_NULL);

        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        setDateFormat(simpleDateFormat);
    }
}
