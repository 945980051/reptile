package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 18:40
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "rule_gb2018_icd10c_list")
public  class Gb2018Icd10c {

    public String code;
    public String diag_main_mcc;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String inserted_at;
    public String is_cc;
    public String is_main;
    public String is_mcc;
    public String is_sub;
    public String mdc_z;
    public String name;
    public String p_main;
    public String p_other;
    public String query_table;
    public String sub_code;
    public String updated_at;
    public String version;
    @ElementCollection
    public List<String> adrg_a;
    @ElementCollection
    public List<String> adrg_ab;
    @ElementCollection
    public List<String> adrg_b;
    @ElementCollection
    public List<String> logs;
    @ElementCollection
    public List<String> mdcs;
    @ElementCollection
    public List<String> no_cc;
}
