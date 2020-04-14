package com.sunshine.reptile.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月14日 1:53
 */
@Data
public class Icd9 {
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

    private String code;
    private boolean is_qy;
    private boolean is_sub;
    private String name;
    private int p_type;
    private String sub_code;
    private List<String> adrg_a;
    private List<String> adrg_b;
}
