package com.sunshine.reptile.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 18:09
 */

@Data
@Entity
@Table(name = "rule_gb2018_icd10az_list")
public class Gb2018Icd10Az {
    public String code;
    public String icdc_az;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String inserted_at;
    public String name;
    public String query_table;
    public String updated_at;
    public String version;
}