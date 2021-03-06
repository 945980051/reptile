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
    private String code;
    private String icdc_az;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String inserted_at;
    private String name;
    private String query_table;
    private String updated_at;
    private String version;
}