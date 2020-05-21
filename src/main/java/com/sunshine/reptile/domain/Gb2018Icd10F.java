package com.sunshine.reptile.domain;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月23日 13:22
 */
@Data
public class Gb2018Icd10F {
   private String code;
 //  private String diag_main_mcc;
   private String id;
  // private String inserted_at;
   private String is_cc;
   private String is_main;
   private String is_mcc;
  // private String is_sub;
   private String mdc_z;
   private String name;
   private String p_main;
  // private String p_other;
  // private String query_table;
  // private String sub_code;
  // private String updated_at;
  // private String version;
   private List<String> adrg_a;
  // private List<String> adrg_ab;
  // private List<String> adrg_b;
   private List<String> codes;
 //  private List<String> logs;
   private List<String> mdcs;
 //  private List<String> no_cc;
}
