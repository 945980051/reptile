package com.sunshine.reptile.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 18:40
 */

@Data
@Entity
@Table(name = "rule_gb2018_icd9a_list")
public class Gb2018Icd9A {
    private String code;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String inserted_at;
    private String name;
    private String query_table;
    private String updated_at;
    private String version;
}

