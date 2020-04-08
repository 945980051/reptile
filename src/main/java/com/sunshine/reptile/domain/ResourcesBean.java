package com.sunshine.reptile.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// FIXME generate failure  field _$Payload19
/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月06日 6:00
 */
@NoArgsConstructor
@Data
public class ResourcesBean {

    /**
     * data : [{"season_time":"3","error_log":null,"department_code2":"-1","gender":"女","opers_code":["胎心监测","会阴产科裂伤缝合术"],"acctual_days":"2","expense_yj":"706.0","mcc_bj":false,"pay_type":"8","opers_qy_cc":null,"icdc":"O","org_id":"11048","oper_code_cc":null,"total_expense":"2987.06","error_gb":false,"adrg_bj":"OC1","error_log_gb":null,"mcc_gb":false,"case_id":"406540","adrg_test":null,"expense_yl":"640.0","log_gb":"判断1: DRG分组范围--2天/2987.06元;判断2: 主要诊断--O42.0;判断3: 主要诊断MDC--O;判断4: 其他诊断--O70.1,Z37.0;判断5: 手术/操作--75.34,75.69;判断6: MDCA先期分组--;判断7: MDCP新生儿先期分组--;判断8: MDCY先期分组--;判断9: MDCZ先期分组--;判断10: 是否手术室手术--是,手术室手术编码--75.69;判断11: 外科分组--OC1;判断12: 内科分组--OC1,OR1,OU1;判断13: ADRG分组--OC1;判断14: MCC/CC主诊排除判断--;判断15: 是否MCC--否;判断16: 是否CC--否;判断17: DRG分组--OC15;","error_log_cc":null,"opers_adrg_bj":["75.69001"],"org_name":" ","oper_code_gb":"75.69","log_test":null,"error_cc":false,"year_time":"2016","error_log_test":null,"adrg_cc":null,"opers_adrg_test":null,"opers_qy_bj":[],"org_level":" ","log":["判断1: DRG分组范围--2天/2987.06元","判断2: 主要诊断--O42.001","判断3: 其他诊断--O70.101,Z37.001","判断4: MCC/CC主诊排除判断--","判断5: 是否MCC--否","判断6: 是否CC--否","判断7: 主要诊断MDC--O","判断8: 手术/操作--75.34001,75.69001","判断9: MDCA先期分组--","判断10: 是否手术室手术--是,手术室手术编码--75.69001","判断11: MDCP新生儿先期分组--","判断12: MDCY先期分组--","判断13: MDCZ先期分组--","判断14: 外科分组--OC1","判断15: 内科分组--OC1,OR1,OZ1","判断16: ADRG分组--OC1","判断17: DRG分组--OC15",""],"drg_bj":"OC15","org_code":"-1","departiment_code2":"-1","error_bj":false,"opers_adrg_gb":["75.69"],"diags_code":["分娩伴会阴裂伤II度","单胎活产"],"drg_test":null,"drg_cc":null,"out_date":"2016-8-17","disease_code":"足月胎膜早破(在24小时之内产程开始)","expense_yp_hc":"0.0","mdc_test":null,"org_class_code":" ","mdcs_main_cc":null,"month_time":"8","oper_code_bj":"75.69001","expense_yp":"1387.06","mdcs_main_bj":["O"],"mdcs_main_gb":["O"],"id":"928022","qy":false,"sf0100":"-1","log_bj":"判断1: DRG分组范围--2天/2987.06元;判断2: 主要诊断--O42.001;判断3: 其他诊断--O70.101,Z37.001;判断4: MCC/CC主诊排除判断--;判断5: 是否MCC--否;判断6: 是否CC--否;判断7: 主要诊断MDC--O;判断8: 手术/操作--75.34001,75.69001;判断9: MDCA先期分组--;判断10: 是否手术室手术--是,手术室手术编码--75.69001;判断11: MDCP新生儿先期分组--;判断12: MDCY先期分组--;判断13: MDCZ先期分组--;判断14: 外科分组--OC1;判断15: 内科分组--OC1,OR1,OZ1;判断16: ADRG分组--OC1;判断17: DRG分组--OC15;","mcc_cc":false,"disease_name":"足月胎膜早破(在24小时之内产程开始)","age":"33","opers_qy_gb":[],"mdc_gb":"O","org_zone":" ","sf0108":"医嘱离院","half_year":"2","expense_hl":"65.5","drg_death":true,"total2":null,"error_test":false,"inserted_at":"2017-07-29T20:42:21","sf0102":"-1","date_inhospital":"2016-8-15","org_catalog_code":" ","mdc_bj":"O","oper_code":"75.69001","mcc_test":false,"error_log_bj":null,"drg":"OC15","sf0104":"-1","mdc_cc":null,"drg_gb":"OC15","mdcs_main_test":null,"b_wt4_v1_id":"58265227","opers_adrg_cc":null,"adrg_gb":"OC1","sf0101":"","oper_code_test":null,"log_cc":null,"expense_gl":"477.5","updated_at":"2017-07-29T20:42:21","director_doctor":null,"opers_qy_test":null}]
     * info : []
     * page : 0
     * page_list : []
     */

    public int page;
    public List<DataBean> data;
    public List<?> info;
    public List<?> page_list;

    @NoArgsConstructor
    @Data
    public static class DataBean {
        /**
         * season_time : 3
         * error_log : null
         * department_code2 : -1
         * gender : 女
         * opers_code : ["胎心监测","会阴产科裂伤缝合术"]
         * acctual_days : 2
         * expense_yj : 706.0
         * mcc_bj : false
         * pay_type : 8
         * opers_qy_cc : null
         * icdc : O
         * org_id : 11048
         * oper_code_cc : null
         * total_expense : 2987.06
         * error_gb : false
         * adrg_bj : OC1
         * error_log_gb : null
         * mcc_gb : false
         * case_id : 406540
         * adrg_test : null
         * expense_yl : 640.0
         * log_gb : 判断1: DRG分组范围--2天/2987.06元;判断2: 主要诊断--O42.0;判断3: 主要诊断MDC--O;判断4: 其他诊断--O70.1,Z37.0;判断5: 手术/操作--75.34,75.69;判断6: MDCA先期分组--;判断7: MDCP新生儿先期分组--;判断8: MDCY先期分组--;判断9: MDCZ先期分组--;判断10: 是否手术室手术--是,手术室手术编码--75.69;判断11: 外科分组--OC1;判断12: 内科分组--OC1,OR1,OU1;判断13: ADRG分组--OC1;判断14: MCC/CC主诊排除判断--;判断15: 是否MCC--否;判断16: 是否CC--否;判断17: DRG分组--OC15;
         * error_log_cc : null
         * opers_adrg_bj : ["75.69001"]
         * org_name :
         * oper_code_gb : 75.69
         * log_test : null
         * error_cc : false
         * year_time : 2016
         * error_log_test : null
         * adrg_cc : null
         * opers_adrg_test : null
         * opers_qy_bj : []
         * org_level :
         * log : ["判断1: DRG分组范围--2天/2987.06元","判断2: 主要诊断--O42.001","判断3: 其他诊断--O70.101,Z37.001","判断4: MCC/CC主诊排除判断--","判断5: 是否MCC--否","判断6: 是否CC--否","判断7: 主要诊断MDC--O","判断8: 手术/操作--75.34001,75.69001","判断9: MDCA先期分组--","判断10: 是否手术室手术--是,手术室手术编码--75.69001","判断11: MDCP新生儿先期分组--","判断12: MDCY先期分组--","判断13: MDCZ先期分组--","判断14: 外科分组--OC1","判断15: 内科分组--OC1,OR1,OZ1","判断16: ADRG分组--OC1","判断17: DRG分组--OC15",""]
         * drg_bj : OC15
         * org_code : -1
         * departiment_code2 : -1
         * error_bj : false
         * opers_adrg_gb : ["75.69"]
         * diags_code : ["分娩伴会阴裂伤II度","单胎活产"]
         * drg_test : null
         * drg_cc : null
         * out_date : 2016-8-17
         * disease_code : 足月胎膜早破(在24小时之内产程开始)
         * expense_yp_hc : 0.0
         * mdc_test : null
         * org_class_code :
         * mdcs_main_cc : null
         * month_time : 8
         * oper_code_bj : 75.69001
         * expense_yp : 1387.06
         * mdcs_main_bj : ["O"]
         * mdcs_main_gb : ["O"]
         * id : 928022
         * qy : false
         * sf0100 : -1
         * log_bj : 判断1: DRG分组范围--2天/2987.06元;判断2: 主要诊断--O42.001;判断3: 其他诊断--O70.101,Z37.001;判断4: MCC/CC主诊排除判断--;判断5: 是否MCC--否;判断6: 是否CC--否;判断7: 主要诊断MDC--O;判断8: 手术/操作--75.34001,75.69001;判断9: MDCA先期分组--;判断10: 是否手术室手术--是,手术室手术编码--75.69001;判断11: MDCP新生儿先期分组--;判断12: MDCY先期分组--;判断13: MDCZ先期分组--;判断14: 外科分组--OC1;判断15: 内科分组--OC1,OR1,OZ1;判断16: ADRG分组--OC1;判断17: DRG分组--OC15;
         * mcc_cc : false
         * disease_name : 足月胎膜早破(在24小时之内产程开始)
         * age : 33
         * opers_qy_gb : []
         * mdc_gb : O
         * org_zone :
         * sf0108 : 医嘱离院
         * half_year : 2
         * expense_hl : 65.5
         * drg_death : true
         * total2 : null
         * error_test : false
         * inserted_at : 2017-07-29T20:42:21
         * sf0102 : -1
         * date_inhospital : 2016-8-15
         * org_catalog_code :
         * mdc_bj : O
         * oper_code : 75.69001
         * mcc_test : false
         * error_log_bj : null
         * drg : OC15
         * sf0104 : -1
         * mdc_cc : null
         * drg_gb : OC15
         * mdcs_main_test : null
         * b_wt4_v1_id : 58265227
         * opers_adrg_cc : null
         * adrg_gb : OC1
         * sf0101 :
         * oper_code_test : null
         * log_cc : null
         * expense_gl : 477.5
         * updated_at : 2017-07-29T20:42:21
         * director_doctor : null
         * opers_qy_test : null
         */

        public String season_time;
        public Object error_log;
        public String department_code2;
        public String gender;
        public String acctual_days;
        public String expense_yj;
        public boolean mcc_bj;
        public String pay_type;
        public Object opers_qy_cc;
        public String icdc;
        public String org_id;
        public Object oper_code_cc;
        public String total_expense;
        public boolean error_gb;
        public String adrg_bj;
        public Object error_log_gb;
        public boolean mcc_gb;
        public String case_id;
        public Object adrg_test;
        public String expense_yl;
        public String log_gb;
        public Object error_log_cc;
        public String org_name;
        public String oper_code_gb;
        public Object log_test;
        public boolean error_cc;
        public String year_time;
        public Object error_log_test;
        public Object adrg_cc;
        public Object opers_adrg_test;
        public String org_level;
        public String drg_bj;
        public String org_code;
        public String departiment_code2;
        public boolean error_bj;
        public Object drg_test;
        public Object drg_cc;
        public String out_date;
        public String disease_code;
        public String expense_yp_hc;
        public Object mdc_test;
        public String org_class_code;
        public Object mdcs_main_cc;
        public String month_time;
        public String oper_code_bj;
        public String expense_yp;
        public String id;
        public boolean qy;
        public String sf0100;
        public String log_bj;
        public boolean mcc_cc;
        public String disease_name;
        public String age;
        public String mdc_gb;
        public String org_zone;
        public String sf0108;
        public String half_year;
        public String expense_hl;
        public boolean drg_death;
        public Object total2;
        public boolean error_test;
        public String inserted_at;
        public String sf0102;
        public String date_inhospital;
        public String org_catalog_code;
        public String mdc_bj;
        public String oper_code;
        public boolean mcc_test;
        public Object error_log_bj;
        public String drg;
        public String sf0104;
        public Object mdc_cc;
        public String drg_gb;
        public Object mdcs_main_test;
        public String b_wt4_v1_id;
        public Object opers_adrg_cc;
        public String adrg_gb;
        public String sf0101;
        public Object oper_code_test;
        public Object log_cc;
        public String expense_gl;
        public String updated_at;
        public Object director_doctor;
        public Object opers_qy_test;
        public List<String> opers_code;
        public List<String> opers_adrg_bj;
        public List<?> opers_qy_bj;
        public List<String> log;
        public List<String> opers_adrg_gb;
        public List<String> diags_code;
        public List<String> mdcs_main_bj;
        public List<String> mdcs_main_gb;
        public List<?> opers_qy_gb;
    }
}
