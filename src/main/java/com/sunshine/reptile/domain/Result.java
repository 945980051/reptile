package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月05日 21:08
 */
@NoArgsConstructor
@Data
public class Result {


    public int B_WT4_V1_ID;
    public String DISEASE_CODE;
    public String DISEASE_NAME;
    public boolean cc;
    public String code_version;
    public String drg;
    public String log;
    public boolean mcc;
    public Object oper_code;
    public String oper_name;
    public String version;
    public List<?> adrgs_diag;
    public List<?> adrgs_oper;
    public List<?> diags_code;
    public List<?> diags_name;
    public List<LogsBean> logs;
    public List<String> mdcs_main;
    public List<?> opers_adrg;
    public List<?> opers_code;
    public List<?> opers_name;
    public List<?> opers_qy;

    @NoArgsConstructor
    @Data
    public static class LogsBean {

        public String log;
        public String type;
    }
}
