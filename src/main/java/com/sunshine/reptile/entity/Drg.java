package com.sunshine.reptile.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月17日 13:24
 */
@Data
public class Drg {
    private String adrg;
    private String code;
    private DrgModelsBean drg_models;
    private int id;
    private String inserted_at;
    private boolean is_cc;
    private boolean is_mcc;
    private String mdc;
    private String model;
    private ModelInfoBean model_info;
    private String name;
    private int order_at;
    private String query_table;
    private String updated_at;
    private List<String> age;
    private List<String> in_days;
    private List<InfoRuleBean> info_rule;
    private List<String> logs;
    private List<String> out_result;
    private List<String> tab_rule;
    private List<VersionsBean> versions;

    @NoArgsConstructor
    @Data
    private static class DrgModelsBean {
        /**
         * model_field : []
         * model_info : 无条件限制
         */

        private String model_info;
        private List<String> model_field;
    }

    @NoArgsConstructor
    @Data
    private static class ModelInfoBean {
        /**
         * model_field : []
         * model_info : 无条件限制
         */

        private String model_info;
        private List<String> model_field;
    }

    @NoArgsConstructor
    @Data
    private static class InfoRuleBean {
        /**
         * code : RU10
         * name : 编码
         */

        private String code;
        private String name;
    }

    @NoArgsConstructor
    @Data
    private static class VersionsBean {
        /**
         * code : RU10
         * model_info : 无条件限制
         * name : 恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）
         * version : 整合开发版(2019)
         */

        private String code;
        private String model_info;
        private String name;
        private String version;
    }
}

