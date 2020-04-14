package com.sunshine.reptile.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: 病案资料
 * @date Date : 2020年04月14日 0:42
 */
@Data
public class MedicalRecordInformation {
    //主要诊断编码
    private String disease_code;
    //年龄
    private String age;
    //性别 0.未知 1.男 2.女 9.未说明
    private String gender;
    //新生儿天数(天)
    private String sf0100;
    //新生儿出生体重(克)
    private String sf0101;
    //新生儿入院体重(克)
    private String sf0102;
    /* 出院转归代码
   1-医嘱离院
   2-医嘱转院
   3-医嘱转社区卫生服务机构/乡镇卫生院
   4-非医嘱离院
   5-死亡
   9-其他*/
    private String sf0108;
    //住院天数(天)
    private String acctual_days;
    //住院费用(元)
    private String total_expense;
    //其他诊断编码
    private List<String> diags_code;
    //手术编码
    private List<String> opers_code;

}
