package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月06日 3:23
 */
@NoArgsConstructor
@Data
public class Opers {

    public List<RuleBean> rule;

    @NoArgsConstructor
    @Data
    public static class RuleBean {
        /**
         * adrg_a : ["RB1","RE1","RF1"]
         * adrg_b : []
         * code : 99.25020
         * is_qy : false
         * is_sub : false
         * name : 静脉注射化疗药物
         * p_type : -1
         * sub_code : null
         */

        public String code;
        public boolean is_qy;
        public boolean is_sub;
        public String name;
        public int p_type;
        public String sub_code;
        public List<String> adrg_a;
        public List<String> adrg_b;
    }
}
