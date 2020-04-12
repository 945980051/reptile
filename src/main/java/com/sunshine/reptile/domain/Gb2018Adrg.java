package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 2:59
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "rule_gb2018_adrg_list")
public class Gb2018Adrg implements Serializable {
    public String query_table;
    public String model;
    public String is_oper_type_adrg;
    public String oper_type;
    public String model_info;
    public String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String code;
    public String inserted_at;
    public String mdc;
    public String is_main_cc;
    public String updated_at;
    @ElementCollection
    public List<String> logs;
    @ElementCollection
    public List<String> f;
    @ElementCollection
    public List<String> p_weight;
    @OneToMany(targetEntity = Gb2018AdrgTabRule.class)
    @NotFound(action = NotFoundAction.IGNORE)
    public List<Gb2018AdrgTabRule> tab_rule;
    @ElementCollection
    public List<String> icd10_a;
    @ElementCollection
    public List<String> cc;
    @ElementCollection
    public List<String> no_mcc;
    @ElementCollection
    public List<String> drgs;
    @ElementCollection
    public List<String> icd10_b;
    @ElementCollection
    public List<String> icd10_c;
    @ElementCollection
    public List<String> no_cc;
    @ElementCollection
    public List<Integer> in_days;
    @ElementCollection
    public List<String> icd9_b;
    @OneToMany(targetEntity = Gb2018AdrgInfoRule.class)
    @NotFound(action = NotFoundAction.IGNORE)
    public List<Gb2018AdrgInfoRule> info_rule;
    @ElementCollection
    public List<String> mcc;
    @ElementCollection
    public List<Integer> out_result;
    @ElementCollection
    public List<Integer> p_days;
    @ElementCollection
    public List<String> icd9_a;
    @ElementCollection
    public List<String> icd10_e;
    @ElementCollection
    public List<String> icd10_d;


}
