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
@Table(name = "rule_gb2018_icd10e_list_a")
public class Gb2018Icd10e {

    public String code;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @ElementCollection
    public List<String> codes;
    public String is_cc;
    public String is_mcc;
    public String name;
}
