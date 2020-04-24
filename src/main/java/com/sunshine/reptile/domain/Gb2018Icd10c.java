package com.sunshine.reptile.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 18:40
 */
@Data
@Entity
@Table(name = "rule_gb2018_icd10c_list")
public class Gb2018Icd10c {

    private String code;
    @ElementCollection
    private List<String> codes;
    private String diag_main_mcc;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String inserted_at;
    private String is_cc;
    private String is_main;
    private String is_mcc;
    private String is_sub;
    private String mdc_z;
    private String name;
    private String p_main;
    private String p_other;
    private String query_table;
    private String sub_code;
    private String updated_at;
    private String version;
    @ElementCollection
    private List<String> adrg_a;
    @ElementCollection
    private List<String> adrg_ab;
    @ElementCollection
    private List<String> adrg_b;
    @ElementCollection
    private List<String> logs;
    @ElementCollection
    private List<String> mdcs;
    @ElementCollection
    private List<String> no_cc;
    @Transient //jpa忽略该字段
    private List<Gb2018Icd10F> subattribute;
}
