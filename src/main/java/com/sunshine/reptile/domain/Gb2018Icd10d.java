package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 18:40
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "rule_gb2018_icd10c_list_a")
public  class Gb2018Icd10d {

    public String code;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String is_cc;
    public String is_mcc;
    public String name;
}
