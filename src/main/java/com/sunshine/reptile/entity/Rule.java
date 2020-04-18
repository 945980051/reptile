package com.sunshine.reptile.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月17日 13:36
 */
@Data
public class Rule {
    /**
     * adrg : RU1
     * age : []
     * code : RU10
     * in_days : []
     * is_cc : null
     * is_mcc : null
     * mdc : R
     * model : drg_m07
     * name : 恶性增生性疾病的支持性治疗（30天≤住院时间＜61天）
     * order_at : 736
     * out_result : []
     */

    private String adrg;
    private String code;
    private boolean is_cc;
    private boolean is_mcc;
    private String mdc;
    private String model;
    private String name;
    private int order_at;
    private List<Integer> age;
    private List<Integer> in_days;
    private List<?> out_result;
}
