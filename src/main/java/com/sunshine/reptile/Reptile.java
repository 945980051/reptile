package com.sunshine.reptile;

import com.sunshine.reptile.dao.Icd10Repository;
import com.sunshine.reptile.domain.*;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.IdWorker;
import com.sunshine.reptile.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月28日 12:20
 */
@Component
public class Reptile {
    @Resource
    public Icd10Repository icd10Repository;
    @Autowired
    private IdWorker idWorker;

    /**
     * 采集ICD-10
     */
    //@PostConstruct
    public void cj() {
        String url = "https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=";
        String table = "rule_gb2018_icd10az";
        char uc = 'A';
        List<Icd10a.DataBean> az = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            String respBody = HttpClient4.doGet(url + table + "&code=" + (char) (uc + i));
            az.addAll(JsonUtils.toBean(respBody, Icd10a.class).getData());
        }
        for (Icd10a.DataBean dataBean : az) {
            String respBody = HttpClient4.doGet(url + dataBean.getQuery_table() + "&code=" + dataBean.getCode());
            for (Icd10 bean : JsonUtils.toBean(respBody, Icd10b.class).getData()) {
                System.out.println(bean.getCode());
                Icd10savaData(dataBean, bean);
                respBody = HttpClient4.doGet(url + bean.getQuery_table() + "&code=" + bean.getCode());
                Icd10b icd10c = JsonUtils.toBean(respBody, Icd10b.class);
                if (icd10c.getData().size() > 0) {
                    Icd10 dataBeanc = icd10c.getData().get(0);
                    Icd10savaData(dataBean, dataBeanc);
                }
            }
        }
    }

    private void Icd10savaData(Icd10a.DataBean dataBean, Icd10 dataBeanc) {
        dataBeanc.setTitleName(dataBean.getName());
        dataBeanc.setTitleCode(dataBean.getCode());
        dataBeanc.setId(String.valueOf(idWorker.nextId()));
        icd10Repository.save(dataBeanc);
    }


    private static String version = "BJ开发版(2019)";

    /**
     * 分组测试
     * @param map
     * @param diags_code
     * @param opers_code
     */
     @PostConstruct
    public static void fzcs(Map<String, Object> map, List<String> diags_code, List<String> opers_code) {



        //天才第一步 先拿现成结果
        Result result = JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/comp_drg", map), Result.class);
        System.out.println("东华分组结果:" + result.drg);
        //获取主要诊断相关基本信息
        MainIcd10.RuleBean mainIcd10 = getMainIcd10(map.get("DISEASE_CODE").toString()).getRule().get(0);
        //主要诊断Adrg计算模型是否符合 用于确定MDC
        List<String> mainAdrgA = mainIcd10.getAdrg_a();
        List<String> mdcs = mainIcd10.getMdcs();
        //过滤主要诊断里的 adrg 用于确认mdc列表
        filterADrg(map, mainIcd10, mainAdrgA, mdcs);
        //确定了mdc列表为 mainAdrgA 每个成员的首字母
        List<String> mdc = new ArrayList<>();
        for (String str : mainAdrgA) {
            mdc.add(str.substring(0, 1).toUpperCase());
        }
        //  List<String> aDrg = new ArrayList<>();
        Set<String> aDrgSet = new HashSet<>();
        aDrgSet.addAll(mainAdrgA);

        List<String> cc = new ArrayList<>();
        List<String> mcc = new ArrayList<>();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", "icd10");
        hashMap.put("code", rulePackString(diags_code));
        hashMap.put("version", version);
        for (Diags.RuleBean ruleBean : JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/get_rule", hashMap), Diags.class).getRule()) {
            if (ruleBean.is_cc)
                cc.add(ruleBean.getName());
            if (ruleBean.is_mcc)
                mcc.add(ruleBean.getName());
            for (String adrg : ruleBean.getAdrg_a()) {
                for (String str : mdc) {
                    if (str.equals(adrg.substring(0, 1).toUpperCase()))
                        aDrgSet.add(adrg);
                }
            }
        }
        hashMap.put("type", "icd9");
        hashMap.put("code", rulePackString(opers_code));
        for (Opers.RuleBean ruleBean : JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/get_rule", hashMap), Opers.class).getRule()) {
            for (String adrg : ruleBean.getAdrg_a()) {
                for (String str : mdc) {
                    if (str.equals(adrg.substring(0, 1).toUpperCase()))
                        aDrgSet.add(adrg);
                }
            }
        }
        List<String> aDrgs = aDrgSet.stream().collect(Collectors.toList());
        //再次过滤所有不符合的ADRG
        filterADrg(map, mainIcd10, aDrgs, mdcs);
        List<String> drgList = new ArrayList<>();

        for (String aDrg : aDrgs) {
            Drgs drgs = JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_bj2019_adrg&code=" + aDrg), Drgs.class);
            drgList.addAll(drgs.getData().stream().map(Drgs.DataBean::getCode).collect(Collectors.toList()));
        }
        hashMap.put("type", "drg");
        hashMap.put("version", version);
        hashMap.put("code", rulePackString(drgList));
        Drgs drgs = JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/get_rule", hashMap), Drgs.class);
        for (int i = drgs.getRule().size() - 1; i >= 0; i--) {
            Drgs.RuleBean ruleBean = drgs.getRule().get(i);
            if (ruleBean.getIn_days().size() == 2) {
                Integer acctual_days = Integer.valueOf(String.valueOf(map.get("ACCTUAL_DAYS")));
                if (acctual_days < ruleBean.getIn_days().get(0) || acctual_days > ruleBean.getIn_days().get(1)) {
                    drgs.getRule().remove(i);
                }
            }
        }
        List<String> list = new ArrayList<>(drgs.getRule().stream().map(Drgs.RuleBean::getCode).collect(Collectors.toList()));
        //获取排序列表
        OrderBean orderBean = JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/drg_order?table=rule_bj2019_order"), OrderBean.class);
        //老实讲 这一长串我现在自己也看不懂了  作用是筛选出符合的几个分组 然后利用treeMap排序
        Map<Integer, String> resultMap = orderBean.getData().stream().collect(Collectors.toMap(OrderBean.DataBean::getOrder_at, OrderBean.DataBean::getCode))
                .entrySet().stream().filter(entry -> list.contains(entry.getValue())).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        TreeMap<Integer, String> treeMap = new TreeMap<>(resultMap);
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            if (entry != null) {
                System.out.println("本人分组结果为:" + entry.getValue());
                break;
            }
        }
       // System.out.println("123");
    }

    private static void filterADrg(Map<String, Object> map, MainIcd10.RuleBean mainIcd10, List<String> mainAdrgA, List<String> mdcs) {
        List<String> removeMainAdrgA = new ArrayList<>();
        for (String adrgA : mainAdrgA) {
            for (String mdc : mdcs) {
                for (MdcResult.DataBean dataBean : JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_bj2019_mdc&code=" + mdc), MdcResult.class).getData()) {
                    if (dataBean.getCode().equals(adrgA)) {
                        for (String computationalModel : dataBean.getModel_info().split(",")) {
                            if (computationalModel.indexOf("主要诊断") == -1 && computationalModel.indexOf("其他诊断") == -1 && computationalModel.indexOf("主要手术") == -1)
                                System.err.println("警告!! 主要诊断ADRG:" + dataBean.getName() + "中计算模型有遗漏 遗漏要求:" + computationalModel);
                        }
                        for (MdcResult.DataBean.TabRuleBean tabRuleBean : dataBean.getTab_rule()) {
                            if ("主要诊断".equals(tabRuleBean.getName())) {
                                if (tabRuleBean.getCode().stream().filter(name -> name.indexOf("_" + mainIcd10.getName()) != -1).collect(Collectors.toList()).size() == 0)
                                    removeMainAdrgA.add(dataBean.getCode());
                            } else if ("其他诊断".equals(tabRuleBean.getName())) {
                                boolean skip = false;
                                for (String diags : (List<String>) map.get("diags_code")) {
                                    for (String rule : tabRuleBean.getCode()) {
                                        if (rule.indexOf("_" + diags) != -1) {
                                            skip = true;
                                            break;
                                        }
                                    }
                                    if (skip)
                                        break;
                                }
                                if (!skip)
                                    removeMainAdrgA.add(dataBean.getCode());
                            } else if ("主要手术".equals(tabRuleBean.getName())) {
                                boolean skip = false;
                                for (String opers : (List<String>) map.get("opers_code")) {
                                    for (String rule : tabRuleBean.getCode()) {
                                        if (rule.indexOf("_" + opers) != -1) {
                                            skip = true;
                                            break;
                                        }
                                    }
                                    if (skip)
                                        break;
                                }
                                if (!skip)
                                    removeMainAdrgA.add(dataBean.getCode());
                            }
                        }
                    }
                }
            }
        }
        for (String rName : removeMainAdrgA) {
            mainAdrgA.remove(rName);
        }
    }

    private static String rulePackString(List<String> code) {
        String diagsCodeStr = "「";
        for (String str : code) {
            diagsCodeStr = diagsCodeStr + str + ",";
        }
        diagsCodeStr = diagsCodeStr.substring(0, diagsCodeStr.length() - 1) + "」";
        return diagsCodeStr;
    }


    private static MainIcd10 getMainIcd10(String disease_code) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "icd10");
        map.put("code", disease_code);
        map.put("version", version);
        String s = HttpClient4.doPost("https://www.jiankanglaifu.com/servers/get_rule", map);
        return JsonUtils.toBean(s, MainIcd10.class);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        //  map.put("B_WT4_V1_ID","62707572");
        map.put("DISEASE_CODE", "恶性肿瘤支持治疗");
        map.put("AGE", "6");
        map.put("GENDER", "9");
        map.put("SF0100", "1");
        map.put("SF0108", "1");
        map.put("ACCTUAL_DAYS", "1");
        map.put("TOTAL_EXPENSE", "2358.55");
        map.put("version", version);
        map.put("comp_type", "normal");
        //其他诊断编码
        List<String> diags_code = new ArrayList<>();
        diags_code.add("纵隔恶性肿瘤");
        map.put("diags_code", diags_code);
        //手术编码
        List<String> opers_code = new ArrayList<>();
        opers_code.add("静脉注射化疗药物");
        map.put("opers_code", opers_code);
        fzcs(map,diags_code,opers_code);
    }
}
