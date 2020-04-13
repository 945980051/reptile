package com.sunshine.reptile;

import com.sunshine.reptile.domain.*;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月28日 12:20
 */
@Component
public class Reptile {


    //private static String version = "CN-B-DRG(2018)";
    private static String version = "整合开发版(2019)";
    //  private static String version_order="rule_gb2018_order";
    private static String version_order = "rule_bj2019_order";
    //  private static String version_mdc ="rule_gb2018_mdc";
    private static String version_mdc = "rule_bj2019_mdc";
    //   private static String version_adrg ="rule_gb2018_adrg";
    private static String version_adrg = "rule_bj2019_adrg";

    /**
     * 分组测试
     *
     * @param map
     * @param diags_code
     * @param opers_code
     */
    // @PostConstruct
    public static String fzcs(Map<String, Object> map) {
        List<String> diags_code = (List<String>) map.get("diags_code");
        List<String> opers_code = (List<String>) map.get("opers_code");
        //天才第一步 先拿现成结果
        //标准版
        map.put("comp_type", "normal");
        Result result = JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/comp_drg", map), Result.class);
        //智能分组版
        map.put("comp_type", "smart");
        Result result2 = JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/comp_drg", map), Result.class);
        //获取主要诊断相关基本信息
        List<MainIcd10.RuleBean> mainIcd10List = getMainIcd10(map.get("DISEASE_CODE").toString()).getRule();
        if (mainIcd10List.size() == 0) {
            throw new RuntimeException("主要诊断相关数据获取失败,分组异常");
        }
        MainIcd10.RuleBean mainIcd10 = mainIcd10List.get(0);
        //主要诊断Adrg计算模型是否符合 用于确定MDC
        List<String> mainAdrgA = mainIcd10.getAdrg_a();
        List<String> mdcs = mainIcd10.getMdcs();
        //获取CC和Mcc
        List<String> cc = new ArrayList<>();
        List<String> mcc = new ArrayList<>();
        getCcOrMcc(diags_code, cc, mcc);
        //获取手术相关数据
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", "icd9");
        hashMap.put("code", rulePackString(opers_code));
        hashMap.put("version", version);
        List<Opers.RuleBean> Icd9 = JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/get_rule", hashMap), Opers.class).getRule();
        //过滤主要诊断里的 adrg 用于确认mdc列表
        filterADrg(map, mainIcd10, mainAdrgA, mdcs, cc, Icd9);
        //确定了mdc列表为 mainAdrgA 每个成员的首字母
        List<String> mdc = new ArrayList<>();
        for (String str : mainAdrgA) {
            mdc.add(str.substring(0, 1).toUpperCase());
        }
        //  List<String> aDrg = new ArrayList<>();
        Set<String> aDrgSet = new HashSet<>();
        aDrgSet.addAll(mainAdrgA);


        hashMap.put("type", "icd10");
        hashMap.put("code", rulePackString(diags_code));

        for (Diags.RuleBean ruleBean : JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/get_rule", hashMap), Diags.class).getRule()) {
            for (String adrg : ruleBean.getAdrg_a()) {
                for (String str : mdc) {
                    if (str.equals(adrg.substring(0, 1).toUpperCase()))
                        aDrgSet.add(adrg);
                }
            }
        }

        for (Opers.RuleBean ruleBean : Icd9) {
            for (String adrg : ruleBean.getAdrg_a()) {
                for (String str : mdc) {
                    if (str.equals(adrg.substring(0, 1).toUpperCase()))
                        aDrgSet.add(adrg);
                }
            }
        }
        List<String> aDrgs = aDrgSet.stream().collect(Collectors.toList());
        //再次过滤所有不符合的ADRG
        filterADrg(map, mainIcd10, aDrgs, mdcs, cc, Icd9);
        List<String> drgList = new ArrayList<>();

        for (String aDrg : aDrgs) {
            Drgs drgs = JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=" + version_adrg + "&code=" + aDrg), Drgs.class);
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
            if (ruleBean.is_cc() && cc.size() == 0) {
                drgs.getRule().remove(i);
            }
            if (ruleBean.is_mcc() && mcc.size() == 0) {
                drgs.getRule().remove(i);
            }
        }
        List<String> list = new ArrayList<>(drgs.getRule().stream().map(Drgs.RuleBean::getCode).collect(Collectors.toList()));
        //获取排序列表
        OrderBean orderBean = JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/drg_order?table=" + version_order), OrderBean.class);
        //老实讲 这一长串我现在自己也看不懂了  作用是筛选出符合的几个分组 然后利用treeMap排序
        Map<Integer, String> resultMap = orderBean.getData().stream().collect(Collectors.toMap(OrderBean.DataBean::getOrder_at, OrderBean.DataBean::getCode))
                .entrySet().stream().filter(entry -> list.contains(entry.getValue())).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        //结果集排序
        TreeMap<Integer, String> treeMap = new TreeMap<>(new MyComparator());
        treeMap.putAll(resultMap);
        System.out.println(treeMap);
        treeMap.entrySet().stream().limit(1).forEach(entry -> System.out.println("本人分组结果为:" + entry.getValue()));
        System.out.println(result.getLogs().get(19).getLog());
        System.out.println("东华标准分组结果:" + result.drg);
        System.out.println(result2.getLogs().get(19).getLog());
        System.out.println("东华智能分组结果:" + result2.drg);
        System.out.println("分组完成");
        return treeMap.values().stream().limit(1).collect(Collectors.joining());
    }

    private static void getCcOrMcc(List<String> diags_code, List<String> cc, List<String> mcc) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("type", "icd10");
        hashMap.put("code", rulePackString(diags_code));
        hashMap.put("version", version);
        for (Diags.RuleBean ruleBean : JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/get_rule", hashMap), Diags.class).getRule()) {
            if (ruleBean.is_cc)
                cc.add(ruleBean.getName());
            if (ruleBean.is_mcc)
                mcc.add(ruleBean.getName());

        }
    }

    private static void filterADrg(Map<String, Object> map, MainIcd10.RuleBean mainIcd10, List<String> adrgAs, List<String> mdcs, List<String> cc, List<Opers.RuleBean> icd9) {
        List<String> removeMainAdrgA = new ArrayList<>();
        for (String adrgA : adrgAs) {
            for (String mdc : mdcs) {
                for (MdcResult.DataBean dataBean : JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=" + version_mdc + "&code=" + mdc), MdcResult.class).getData()) {
                    if (dataBean.getCode().equals(adrgA)) {
                        //是否主诊算入并发症 	true
                        if ("true".equals(dataBean.getIs_main_cc()) && cc.size() > 0) {
                            removeMainAdrgA.add(dataBean.getCode());
                        }
                        ruleFilter(dataBean, map, adrgAs, mainIcd10, removeMainAdrgA, icd9);
                    }
                }
            }
        }
        for (String rName : removeMainAdrgA) {
            adrgAs.remove(rName);
        }
    }

    private static void ruleFilter(MdcResult.DataBean dataBean, Map<String, Object> map, List<String> adrgAs, MainIcd10.RuleBean mainIcd10, List<String> removeMainAdrgA, List<Opers.RuleBean> icd9) {
        //取当前adrg所有计算模型
        String[] rules = dataBean.getModel_info().replace("要求", "").split(",");
        for (String rule : rules) {
            boolean skip = false;
            switch (rule.trim()) {
                case "主要诊断符合":
                    if (getTableRuleCode("主要诊断", dataBean)
                            .stream().filter(name -> name.indexOf("" + mainIcd10.getName()) != -1)
                            .collect(Collectors.toList())
                            .size() == 0)
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "其他诊断符合":
                    if (!getIntersection(getTableRuleCode("其他诊断", dataBean), (List<String>) map.get("opers_code")))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "主要手术符合":
                    //如果两个集合没有交集就说明条件不符合 予以删除
                    if (!getIntersection(getTableRuleCode("主要手术", dataBean), (List<String>) map.get("diags_code")))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "其他手术符合":
                    if (!getIntersection(getTableRuleCode("其他手术", dataBean), (List<String>) map.get("diags_code")))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "手术室手术符合":
                    if (icd9.stream().filter(i -> i.getP_type() == 0).collect(Collectors.toList()).size() == 0)
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "住院日满足":
                    Integer day = new Integer(map.get("ACCTUAL_DAYS").toString());
                    List<String> days = dataBean.getIn_days();
                    if (!(new Integer(days.get(0)) <= day && day <= new Integer(days.get(1))))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "出院转归满足":
                case "出院转归符合":
                    if (dataBean.getOut_result().stream().filter(data -> data.equals(map.get("SF0108").toString())).collect(Collectors.toList()).size() == 0)
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
               /* case "并且病历诊断符合ADRG诊断":

                    break;*/
                case "主要诊断或其他诊断符合其一":
                    skip = false;
                    if (getTableRuleCode("主要诊断", dataBean)
                            .stream().filter(name -> name.indexOf("" + mainIcd10.getName()) != -1)
                            .collect(Collectors.toList())
                            .size() != 0)
                        break;
                    if (!getIntersection(getTableRuleCode("其他诊断", dataBean), (List<String>) map.get("opers_code")))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
             /*   case "病例诊断符合ADRG诊断":

                    break;*/
                case "要求新生儿天数符合":
                case "新生儿天数符合":
                    Integer newDay = new Integer(map.get("SF0100").toString());
                    List<String> newDays = dataBean.getP_days();
                    if (!(new Integer(newDays.get(0)) <= newDay && newDay <= new Integer(newDays.get(1))))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "病历诊断不在排除病历诊断列表内":
                    if (getIntersection(getTableRuleCode("病案排除诊断", dataBean), (List<String>) map.get("diags_code")))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "新生儿体重符合":
                    Integer weight = new Integer(map.get("SF0102").toString());
                    List<String> weights = dataBean.getP_weight();
                    if (!(new Integer(weights.get(0)) <= weight && weight <= new Integer(weights.get(1))))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "住院日和出院转归满足":
                    skip = false;
                    Integer dayT = new Integer(map.get("ACCTUAL_DAYS").toString());
                    List<String> daysT = dataBean.getIn_days();
                    if (!(new Integer(daysT.get(0)) <= dayT && dayT <= new Integer(daysT.get(1))))
                        skip = true;
                    if (dataBean.getOut_result().stream().filter(data -> data.equals(map.get("SF0108").toString())).collect(Collectors.toList()).size() == 0)
                        skip = true;
                    if (skip)
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "主要诊断不在排除主要诊断列表内":
                    if (getTableRuleCode("排除主要诊断", dataBean).contains(mainIcd10.getName()))
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                case "住院日或出院转归满足其一":
                    Integer dayY = new Integer(map.get("ACCTUAL_DAYS").toString());
                    List<String> daysY = dataBean.getIn_days();
                    if (new Integer(daysY.get(0)) <= dayY && dayY <= new Integer(daysY.get(1)))
                        break;
                    if (dataBean.getOut_result().stream().filter(data -> data.equals(map.get("SF0108").toString())).collect(Collectors.toList()).size() == 0)
                        removeMainAdrgA.add(dataBean.getCode());
                    break;
                default:
                    System.err.println("警告!! 主要诊断ADRG:" + dataBean.getName() + "中计算模型有遗漏 遗漏要求:" + rule.trim());
                    break;
            }

        }

    }

    private static List<String> getTableRuleCode(String type, MdcResult.DataBean dataBean) {
        return dataBean.getTab_rule()
                .stream().filter(tabRuleBean -> type.equals(tabRuleBean.getName()))
                .collect(Collectors.toList())
                .get(0).getCode();
    }

    /**
     * 取两个集合的交集 有交集返回true
     *
     * @param diags
     * @param diagsCodes
     * @return
     */
    private static boolean getIntersection(List<String> diags, List<String> diagsCodes) {
        if (diags.size() == 0)
            return true;
        boolean skip = false;
        for (String diag : diags) {
            for (String diagsCode : diagsCodes) {
                if (diag.indexOf("_" + diagsCode) != -1) {
                    skip = true;
                    break;
                }
            }
            if (skip)
                break;
        }
        return skip;
    }

    /**
     * 封装请求字符串
     *
     * @param code
     * @return
     */
    private static String rulePackString(List<String> code) {
        String diagsCodeStr = "「";
        if (code != null) {
            for (String str : code) {
                diagsCodeStr = diagsCodeStr + str + ",";
            }
        } else {
            diagsCodeStr = diagsCodeStr + ",";
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
        ResourcesBean resourcesBean = JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/edit/wt4_2016_id?id=759778"), ResourcesBean.class);
        ResourcesBean.DataBean dataBean = resourcesBean.getData().get(0);
        //主要诊断编码
        map.put("DISEASE_CODE", dataBean.getDisease_code());
        //年龄
        map.put("AGE", dataBean.getAge());
        //性别
        map.put("GENDER", dataBean.getGender());
        map.put("SF0100", dataBean.getSf0100());
        map.put("SF0101", dataBean.getSf0101());
        map.put("SF0102", dataBean.getSf0102());
        map.put("SF0108", dataBean.getSf0108());
        map.put("ACCTUAL_DAYS", dataBean.getAcctual_days());
        map.put("TOTAL_EXPENSE", dataBean.getTotal_expense());
        map.put("version", version);
        //其他诊断编码
        List<String> diags_code = dataBean.getDiags_code();
        //手术编码
        List<String> opers_code = dataBean.getOpers_code();
        if (diags_code.size() > 0) {
            for (int i = 0; i < diags_code.size(); i++) {
                map.put("diags_code[" + i + "]", diags_code.get(i));
            }
        }
        map.put("diags_code", diags_code);
        if (opers_code.size() > 0) {
            for (int i = 0; i < opers_code.size(); i++) {
                map.put("opers_code[" + i + "]", opers_code.get(i));
            }
        }
        map.put("opers_code", opers_code);
        String fzcs = fzcs(map);

    }

    //定义key的比较器，比较算法根据第一个参数o1,小于、等于或者大于o2分别返回负整数、0或者正整数，来决定二者存放的先后位置：返回负数则o1在前，正数则o2在前。
    private static class MyComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }
}
