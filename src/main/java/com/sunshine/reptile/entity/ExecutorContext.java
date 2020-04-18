package com.sunshine.reptile.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private List<Drg> drgs;
    private List<Rule> rules;
    private List<String> cc;
    private List<String> mcc;
    private Map<String,Object> initialData;
    //被过滤掉的Adrg k:名称 v:原因
    private Map<String, String> removeAdrg;
    //被过滤掉的Drg k:名称 v:原因
    private Map<String, String> removeDrg;

    public ExecutorContext() {
        this.drgs=new ArrayList<>();
        this.rules=new ArrayList<>();
        this.removeAdrg = new HashMap<>();
        this.removeDrg = new HashMap<>();
    }
}
