package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月05日 22:41
 */
@NoArgsConstructor
@Data
public class MdcResult {


    public String page;
    public List<DataBean> data;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        public String query_table;
        public String model;
        public String is_oper_type_adrg;
        public String oper_type;
        public String model_info;
        public String name;
        public String id;
        public String code;
        public String inserted_at;
        public String mdc;
        public String is_main_cc;
        public String updated_at;
        public List<String> logs;
        public List<String> f;
        public List<String> p_weight;
        public List<TabRuleBean> tab_rule;
        public List<String> icd10_a;
        public List<String> cc;
        public List<String> no_mcc;
        public List<String> drgs;
        public List<String> icd10_b;
        public List<String> icd10_c;
        public List<String> no_cc;
        public List<VersionsBean> versions;
        public List<String> in_days;
        public List<String> icd9_b;
        public List<InfoRuleBean> info_rule;
        public List<String> mcc;
        public List<String> out_result;
        public List<String> p_days;
        public List<String> icd9_a;
        public List<String> icd10_e;
        public List<String> icd10_d;

        @NoArgsConstructor
        @Data
        public static class TabRuleBean {
            /**
             * code : []
             * name : 主要诊断
             */

            public String name;
            public List<String> code;
        }

        @NoArgsConstructor
        @Data
        public static class VersionsBean {
            /**
             * code : RA1
             * is_main_cc : false
             * model_info : 要求主要诊断符合, 主要手术符合
             * name : 淋巴瘤、白血病等伴重大手术
             * version : BJ开发版(2019)
             */

            public String code;
            public boolean is_main_cc;
            public String model_info;
            public String name;
            public String version;
        }

        @NoArgsConstructor
        @Data
        public static class InfoRuleBean {
            /**
             * code : RA1
             * name : 编码
             */

            public String code;
            public String name;
        }
    }
}
