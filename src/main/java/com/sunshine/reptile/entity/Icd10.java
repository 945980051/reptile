package com.sunshine.reptile.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月14日 1:43
 */
@Data
public class Icd10 {
    private String code;
    private String diag_main_mcc;
    private boolean is_cc;
    private boolean is_main;
    private boolean is_mcc;
    private boolean is_sub;
    private String mdc_z;
    private String name;
    private boolean p_main;
    private boolean p_other;
    private String sub_code;
    private List<String> adrg_a;
    private List<String> adrg_ab;
    private List<String> adrg_b;
    private List<String> mdcs;
    private List<String> no_cc;
}
