package com.sunshine.reptile.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月29日 7:15
 */
@Entity
@Data
@Table(name = "icd10")
public class Icd10 {
    @Id
    @Column(name = "id", nullable = true, length = 255)
    public String id;
    @Column(name = "title_code", nullable = true, length = 255)
    private String titleCode;
    @Column(name = "title_name", nullable = true, length = 255)
    private String titleName;
    @Column(name = "code", nullable = true, length = 255)
    public String code;
    @Column(name = "diag_main_mcc", nullable = true, length = 255)
    public String diag_main_mcc;
    @Column(name = "inserted_at", nullable = true, length = 255)
    public String inserted_at;
    @Column(name = "is_cc", nullable = true, length = 255)
    public String is_cc;
    @Column(name = "is_main", nullable = true, length = 255)
    public String is_main;
    @Column(name = "is_mcc", nullable = true, length = 255)
    public String is_mcc;
    @Column(name = "is_sub", nullable = true, length = 255)
    public String is_sub;
    @Column(name = "mdc_z", nullable = true, length = 255)
    public String mdc_z;
    @Column(name = "name", nullable = true, length = 255)
    public String name;
    @Column(name = "p_main", nullable = true, length = 255)
    public String p_main;
    @Column(name = "p_other", nullable = true, length = 255)
    public String p_other;
    @Column(name = "query_table", nullable = true, length = 255)
    public String query_table;
    @Column(name = "sub_code", nullable = true, length = 255)
    public String sub_code;
    @Column(name = "updated_at", nullable = true, length = 255)
    public String updated_at;
    @Column(name = "version", nullable = true, length = 255)
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
