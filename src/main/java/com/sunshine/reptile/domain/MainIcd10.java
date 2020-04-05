package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月05日 21:59
 */
@NoArgsConstructor
@Data
public class MainIcd10 {

    private List<RuleBean> rule;

    @NoArgsConstructor
    @Data
    public static class RuleBean {


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
