package com.sunshine.reptile.domain;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月11日 23:49
 */

@Entity
@Data
@Table(name = "rule_gb2018_mdc_list")
public class Gb2018Mdc implements Serializable {

    private boolean is_qy;
    private String query_table;
    private String model;
    private String oper_type;
    private String model_info;
    private String name;
    @ElementCollection
    private List<Integer> in_days;
    @Id
    @GeneratedValue
    private Integer id;
    @ElementCollection
    private List<Integer> out_result;
    private String code;
    private String no_DiseOper_adrg;
    private String inserted_at;
    private String oper_type_adrg;
    private String mdc;
    private String updated_at;
    @ElementCollection
    private List<String> oper_adrgs;
    @ElementCollection
    private List<String> gender;
    @ElementCollection
    private List<String> logs;
    @ElementCollection
    private List<String> z08;
    @ElementCollection
    private List<String> z03;
    @ElementCollection
    private List<String> p_age;
    @ElementCollection
    private List<String> p01;
    @ElementCollection
    private List<String> z07;
    @OneToMany(targetEntity = Gb2018MdcTabRule.class)
    @NotFound(action= NotFoundAction.IGNORE)
    private List<Gb2018MdcTabRule> tab_rule;
    @ElementCollection
    private List<String> icd10_a;
    @ElementCollection
    private List<String> z06;
    @ElementCollection
    private List<String> z05;
    @ElementCollection
    private List<String> p03;
    @ElementCollection
    private List<String> y01;
    @ElementCollection
    private List<String> y02;
    @OneToMany(targetEntity = Gb2018MdcInfoRule.class)
    @NotFound(action= NotFoundAction.IGNORE)
    private List<Gb2018MdcInfoRule> info_rule;
    @ElementCollection
    private List<String> z01;
    @ElementCollection
    private List<String> b01;
    @ElementCollection
    private List<Integer> p_days;
    @ElementCollection
    private List<String> z02;
    @ElementCollection
    private List<String> icd9_a;
    @ElementCollection
    private List<String> z04;
    @ElementCollection
    private List<String> diag_adrgs;
    @ElementCollection
    private List<String> p02;


}

