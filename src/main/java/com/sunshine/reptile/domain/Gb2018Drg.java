package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 4:12
 */


@NoArgsConstructor
@Data
@Entity
@Table(name = "rule_gb2018_drg_list")
public class Gb2018Drg implements Serializable {
    private String adrg;
    private String code;
    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private Gb2018DrgModels drg_models;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private String inserted_at;
    private Boolean is_cc;
    private Boolean is_mcc;
    private String mdc;
    //private String model;

    //@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  //  private Gb2018DrgModelInfo model_info;
    private String name;
    private int order_at;
   // private String query_table;
   // private String updated_at;
   // @ElementCollection
   // private List<Integer> age;
   // @ElementCollection
   // private List<Integer> in_days;
  //  @OneToMany(targetEntity = Gb2018DrgInfoRule.class)
  //  @NotFound(action = NotFoundAction.IGNORE)
  //  private List<Gb2018DrgInfoRule> info_rule;
   // @ElementCollection
   // private List<String> logs;
   // @ElementCollection
   // private List<Integer> out_result;
   // @ElementCollection
  //  private List<String> tab_rule;
}

