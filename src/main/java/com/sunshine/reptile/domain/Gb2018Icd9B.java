package com.sunshine.reptile.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月22日 13:38
 */
@Data
@Entity
@Table(name = "rule_gb2018_icd9b_list")
public class Gb2018Icd9B {
    public String code;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String inserted_at;
    public String is_qy;
    public String is_sub;
    public String name;
    public String p_type;
    public String query_table;
    public String sub_code;
    public String updated_at;
    public String version;
    @ElementCollection
    public List<String> adrg_a;
    @ElementCollection
    public List<String> adrg_b;
    @ElementCollection
    public List<String> logs;
}
