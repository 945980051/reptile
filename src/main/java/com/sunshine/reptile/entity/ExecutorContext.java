package com.sunshine.reptile.entity;

import lombok.Data;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: 上下文执行器
 * @date Date : 2020年04月14日 2:19
 */
@Data
public class ExecutorContext {
    private Icd10 mainIcd10;
    private List<Icd10> icd10s;
    private List<Icd9> icd9s;
    private List<String> mdc;
    private List<String> aDrg;
    private List<String> cc;
    private List<String> mcc;
}
