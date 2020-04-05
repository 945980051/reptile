package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月06日 3:48
 */
@NoArgsConstructor
@Data
public class Diags {

    public List<RuleBean> rule;

    @NoArgsConstructor
    @Data
    public static class RuleBean {
        /**
         * adrg_a : ["ER1"]
         * adrg_ab : []
         * adrg_b : []
         * code : C38.301
         * diag_main_mcc : null
         * is_cc : true
         * is_main : true
         * is_mcc : false
         * is_sub : false
         * mdc_z : 无
         * mdcs : ["E"]
         * name : 纵隔恶性肿瘤
         * no_cc : ["前纵隔恶性肿瘤"]
         * p_main : false
         * p_other : false
         * sub_code : null
         */

        public String code;
        public String diag_main_mcc;
        public boolean is_cc;
        public boolean is_main;
        public boolean is_mcc;
        public boolean is_sub;
        public String mdc_z;
        public String name;
        public boolean p_main;
        public boolean p_other;
        public String sub_code;
        public List<String> adrg_a;
        public List<String> adrg_ab;
        public List<String> adrg_b;
        public List<String> mdcs;
        public List<String> no_cc;
    }
}
