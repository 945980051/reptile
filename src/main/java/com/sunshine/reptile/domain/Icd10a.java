package com.sunshine.reptile.domain;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月29日 3:23
 */
@Data
public class Icd10a {
    private List<DataBean> data;

    @Data
    public class DataBean {
        private String code;
        private String icdc_az;
        private String id;
        private String inserted_at;
        private String name;
        private String query_table;
        private String updated_at;
        private String version;
    }
}
