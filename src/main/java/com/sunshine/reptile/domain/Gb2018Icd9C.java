package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月24日 9:59
 */
@NoArgsConstructor
@Data
public class Gb2018Icd9C {
    public String code;
    public String id;
    public String inserted_at;
    public String is_qy;
    public String is_sub;
    public String name;
    public String p_type;
    public String query_table;
    public Object sub_code;
    public String updated_at;
    public String version;
    public List<String> adrg_a;
    public List<String> adrg_b;
    public List<String> codes;
    public List<String> logs;
}
