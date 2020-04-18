package com.sunshine.reptile;

import com.sunshine.reptile.domain.MdcResult;
import com.sunshine.reptile.domain.Result;
import com.sunshine.reptile.entity.*;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.JsonUtils;
import com.sunshine.reptile.utils.ObjectUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author : zhengwenyao
 * @Description: CN-B-DRG(2018)分组模型
 * @date Date : 2020年04月14日 0:04
 */

public class DrgsGrouping {

    private static final String VERSION = "CN-B-DRG(2018)";
    private static final String VERSION_MDC = "rule_gb2018_mdc";
    private static final String VERSION_ADRG = "rule_gb2018_adrg";
    private static final String version_order = "rule_gb2018_order";
    private static final String ruleurl = "https://www.jiankanglaifu.com/servers/get_rule";
    private static ExecutorContext EXECUTORCONTEXT = new ExecutorContext();

    private static int number = 0;

    private static void getModelInfos() {
        HashSet<String> set = new HashSet<>();
        char uc = 'A';
        for (int i = 0; i < 26; i++) {
            for (MdcResult.DataBean bean : JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=" + VERSION_MDC + "&code=" + (char) (uc + i)), MdcResult.class).getData()) {
                set.add(bean.getModel_info());
            }
        }
        HashSet<String> hashSet = new HashSet<>();
        for (String s : set) {
            s = s.replace("要求", "");
            for (String s1 : s.split(",")) {
                hashSet.add(s1.trim());
            }
        }
        for (String s : hashSet) {
            System.out.println(s);
        }
        System.out.println(hashSet);
    }

    public static void main(String[] args) throws IllegalAccessException, IOException, InterruptedException {
        for (int i = 0; i < 1000; i++) {
            number = i;
            EXECUTORCONTEXT = new ExecutorContext();
            if (!grouping())
                break;
            Thread.sleep(10000);
        }
        //   grouping();
    }

    public static boolean grouping() throws IllegalAccessException {
        //getModelInfos();
        int num = (int) Math.floor(Math.random() * 1000000) + 1;
        //   num = 474861;
        //获取基本并按资料
        MedicalRecordInformation medicalRecordInformation =
                JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/edit/wt4_2016_id?id=" + num), MedicalRecordInformationList.class)
                        .getData().get(0);
        //病案资料转MAP
        Map<String, Object> map = MedicalRecordInformation2Map(medicalRecordInformation);
        map.put("num", num);
        //初始化基本数据
        getData(map);
        //这个mdc来自于主要诊断,且要符合mdc计算模型及ADrg计算模型(比方:mdc计算模型符合,Adrg计算模型不符合 =不符合)
        filterADrg(true);
        //获取mdc
        Set<String> mdc = getMdc();
        //根据主诊合格mdc过滤所有Adrg,再用计算模型过滤所有Adrg
        List<String> aDrg = filterADrg(false, getAdrg(false, mdc));
        //获取所有Adrg下的drg信息
        getDrg(aDrg);
        //获取drg的所有计算模型 如cc,mcc,住院天数  注意,这步事取计算模型,数据第一步已获取
        getRule();
        //过滤所有Drg
        List<Drg> drgs = filterDrg();
        //获取drg入组顺序
        TreeMap<Integer, String> order = orderDrg(drgs);

        //取东华分组结果
        //标准版
        map.put("comp_type", "normal");
        Result result = JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/comp_drg", map), Result.class);
        //智能分组版
        map.put("comp_type", "smart");
        Result result2 = JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/comp_drg", map), Result.class);

        System.out.println(num);
        System.out.println(order);
        //     String drgCode ="";
        String drgCode = order.entrySet().stream().limit(1).collect(Collectors.toList()).get(0).getValue();
        //    order.entrySet().stream().limit(1).forEach(entry -> drgCode = entry.getValue());
        System.out.println("当前测试第" + (number + 1) + "个病案");
        System.out.println("本人分组结果为:" + drgCode);
        System.out.println(result.getLogs().get(19).getLog());
        System.out.println("东华标准分组结果:" + result.drg);
        System.out.println(result2.getLogs().get(19).getLog());
        System.out.println("东华智能分组结果:" + result2.drg);
        System.out.println("分组完成");
        String log = result2.getLogs().get(19).getLog();

        return result.drg.equals(drgCode) && log.substring(log.indexOf("「") + 1, log.indexOf("」")).split(",").length == order.size();
    }

    /**
     * 获取Drg入组顺序
     *
     * @param drgs
     * @return
     */
    private static TreeMap<Integer, String> orderDrg(List<Drg> drgs) {
        //获取排序列表
        List<Order> orders = JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/drg_order?table=" + version_order), OrderList.class).getData();
        Map<Integer, String> collect = orders.stream().filter(order -> drgs.stream().map(Drg::getCode).collect(Collectors.toList()).contains(order.getCode())).collect(Collectors.toMap(Order::getId, Order::getCode));
        return new TreeMap<>(collect);
    }

    /**
     * 根据计算模型过滤drg信息
     */
    private static List<Drg> filterDrg() {
        for (Rule rule : EXECUTORCONTEXT.getRules()) {
            if (rule.getIn_days().size() == 2) {
                Integer acctual_days = Integer.valueOf(String.valueOf(EXECUTORCONTEXT.getInitialData().get("ACCTUAL_DAYS")));
                if (acctual_days < rule.getIn_days().get(0) || acctual_days > rule.getIn_days().get(1)) {
                    EXECUTORCONTEXT.getRemoveDrg().put(rule.getName(), "住院天数不符合");
                }
            }
            if (rule.getAge().size() == 2) {
                Integer acctual_days = Integer.valueOf(String.valueOf(EXECUTORCONTEXT.getInitialData().get("AGE")));
                if (acctual_days < rule.getAge().get(0) || acctual_days > rule.getAge().get(1)) {
                    EXECUTORCONTEXT.getRemoveDrg().put(rule.getName(), "年龄不符合");
                }
            }
            if (rule.is_cc() && EXECUTORCONTEXT.getCc().size() == 0) {
                EXECUTORCONTEXT.getRemoveDrg().put(rule.getName(), "cc不符合");
            }
            if (rule.is_mcc() && EXECUTORCONTEXT.getMcc().size() == 0) {
                EXECUTORCONTEXT.getRemoveDrg().put(rule.getName(), "mcc不符合");
            }
        }
        return EXECUTORCONTEXT.getDrgs().stream().filter(drg -> !EXECUTORCONTEXT.getRemoveDrg().keySet().contains(drg.getName())).collect(Collectors.toList());
    }

    /**
     * 过去所有drg计算模型
     */
    private static void getRule() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "drg");
        map.put("code", rulePackString(EXECUTORCONTEXT.getDrgs().stream().map(Drg::getCode).collect(Collectors.toList())));
        map.put("version", VERSION);
        List<Rule> rules = JsonUtils.toBean(HttpClient4.doPost("https://www.jiankanglaifu.com/servers/get_rule", map), RuleList.class).getRule();
        EXECUTORCONTEXT.getRules().addAll(rules);
    }

    /**
     * 获取所有Adrg下的drg信息
     *
     * @param aDrgs
     * @return
     */
    private static List<Drg> getDrg(List<String> aDrgs) {
        for (String aDrg : aDrgs) {
            DrgList drgs = JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=" + VERSION_ADRG + "&code=" + aDrg), DrgList.class);
            EXECUTORCONTEXT.getDrgs().addAll(drgs.getData());
        }
        return EXECUTORCONTEXT.getDrgs();
    }

    /**
     * 根据Adrg信息获取mdc
     *
     * @return
     */
    private static Set<String> getMdc() {
        List<String> aDrgs = getAdrg(true)
                .stream().filter(adrg -> !EXECUTORCONTEXT.getRemoveAdrg().keySet().contains(adrg))
                .collect(Collectors.toList());
        return aDrgs.stream().map(aDrg -> aDrg.substring(0, 1).toUpperCase()).collect(Collectors.toSet());
    }

    /**
     * 过滤Adrg 来确定mdc
     *
     * @param filterMain 是否只过滤主诊
     */
    private static List<String> filterADrg(boolean filterMain) {
        return filterADrg(filterMain, null);
    }

    /**
     * 过滤Adrg 来确定mdc
     *
     * @param filterMain 是否只过滤主诊
     * @param aDrgs      根据传入drg的mdc进行过滤
     * @return
     */
    private static List<String> filterADrg(boolean filterMain, Set<String> aDrgs) {
        if (aDrgs == null)
            aDrgs = getAdrg(filterMain);
        //取主要诊断adrg的mdc去重
        Set<String> mdcs = aDrgs.stream().map(aDrg -> aDrg.substring(0, 1).toUpperCase()).collect(Collectors.toSet());
        for (String mdc : mdcs) {
            //取每个mdc计算模型
            for (MdcResult.DataBean datum : JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=" + VERSION_MDC + "&code=" + mdc), MdcResult.class).getData()) {
                if (aDrgs.contains(datum.getCode())) {
                    //是否主诊算入并发症 	true
                    if ("true".equals(datum.getIs_main_cc()) && EXECUTORCONTEXT.getMainIcd10().is_cc())
                        EXECUTORCONTEXT.getCc().add(EXECUTORCONTEXT.getMainIcd10().getName());
                    //根据计算模型进行过滤
                    filterModelInfo(datum);
                }
            }
        }
        return aDrgs.stream().filter(adrg -> !EXECUTORCONTEXT.getRemoveAdrg().keySet().contains(adrg)).collect(Collectors.toList());
    }

    /**
     * 获取Adrg
     *
     * @param filterMain 是否只获取主诊
     * @return
     */
    private static Set<String> getAdrg(boolean filterMain) {
        Set<String> aDrgs = new HashSet<>();
        aDrgs.addAll(EXECUTORCONTEXT.getMainIcd10().getAdrg_a());
        if (!filterMain) {
            EXECUTORCONTEXT.getIcd10s().stream().forEach(icd10 -> aDrgs.addAll(icd10.getAdrg_a()));
            EXECUTORCONTEXT.getIcd9s().stream().forEach(icd9 -> aDrgs.addAll(icd9.getAdrg_a()));
        }
        return aDrgs;
    }

    /**
     * 获取Adrg
     *
     * @param filterMain 是否只获取主诊
     * @param mdcs       结果过滤
     * @return
     */
    private static Set<String> getAdrg(boolean filterMain, Set<String> mdcs) {
        return getAdrg(filterMain)
                .stream().filter(adrg -> mdcs.contains(adrg.substring(0, 1).toUpperCase()))
                .collect(Collectors.toSet());
    }

    /**
     * 计算模型处理方法
     *
     * @param datum mdc列表集合
     */
    private static void filterModelInfo(MdcResult.DataBean datum) {
        System.out.println(datum.getCode());
        //取当前adrg所有计算模型
        String[] rules = datum.getModel_info().replace("要求", "").split(",");
        for (String rule : rules) {
            switch (rule.trim()) {
                case "主要诊断符合":
                case "并且主要诊断符合":
                    if (getTableRuleCode("主要诊断", datum)
                            .stream().filter(code -> code.indexOf("_" + EXECUTORCONTEXT.getMainIcd10().getName()) != -1)
                            .collect(Collectors.toList()).size() == 0)
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "主要手术符合":
                    if (!getIntersection(getTableRuleCode("主要手术", datum)
                            , EXECUTORCONTEXT.getIcd9s().stream().map(Icd9::getName).collect(Collectors.toList())))
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "其他诊断符合":
                    if (!getIntersection(getTableRuleCode("其他诊断", datum),
                            EXECUTORCONTEXT.getIcd10s().stream().map(Icd10::getName).collect(Collectors.toList())))
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "其他手术符合":
                    if (!getIntersection(getTableRuleCode("其他手术", datum),
                            EXECUTORCONTEXT.getIcd9s().stream().map(Icd9::getName).collect(Collectors.toList())))
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "手术室手术符合":
                    if (EXECUTORCONTEXT.getIcd9s().stream()
                            .filter(icd9 -> icd9.getP_type() == 0).collect(Collectors.toList()).size() == 0)
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "新生儿天数符合":
                    Integer sf0100 = new Integer(EXECUTORCONTEXT.getInitialData().get("SF0100").toString());
                    if (!(new Integer(datum.getP_days().get(0)) <= sf0100 && sf0100 <= new Integer(datum.getP_days().get(1))))
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "新生儿体重符合":
                    Integer sf0102 = new Integer(EXECUTORCONTEXT.getInitialData().get("SF0102").toString());
                    if (!(new Integer(datum.getP_weight().get(0)) <= sf0102 && sf0102 <= new Integer(datum.getP_weight().get(1))))
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;

                case "主要诊断不在排除主要诊断列表内":
                    if (!getIntersection(getTableRuleCode("排除主要诊断", datum),
                            EXECUTORCONTEXT.getIcd9s().stream().map(Icd9::getName).collect(Collectors.toList())))
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "主要诊断或其他诊断符合其一":
                    if (getTableRuleCode("主要诊断", datum)
                            .stream().filter(code -> code.indexOf("_" + EXECUTORCONTEXT.getMainIcd10().getName()) != -1)
                            .collect(Collectors.toList()).size() != 0)
                        break;
                    if (!getIntersection(getTableRuleCode("其他诊断", datum),
                            EXECUTORCONTEXT.getIcd10s().stream().map(Icd10::getName).collect(Collectors.toList())))
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "出院转归符合":
                    if (datum.getOut_result().stream()
                            .filter(data -> data.equals(EXECUTORCONTEXT.getInitialData().get("SF0108").toString()))
                            .collect(Collectors.toList()).size() == 0)
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "住院日或出院转归满足其一":
                    Integer dayY = new Integer(EXECUTORCONTEXT.getInitialData().get("ACCTUAL_DAYS").toString());
                    List<String> daysY = datum.getIn_days();
                    if (new Integer(daysY.get(0)) <= dayY && dayY <= new Integer(daysY.get(1)))
                        break;
                    if (datum.getOut_result().stream().filter(data -> data.equals(EXECUTORCONTEXT.getInitialData().get("SF0108").toString())).collect(Collectors.toList()).size() == 0)
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "住院日和出院转归满足":
                    Integer dayT = new Integer(EXECUTORCONTEXT.getInitialData().get("ACCTUAL_DAYS").toString());
                    List<String> daysT = datum.getIn_days();
                    if (!(new Integer(daysT.get(0)) <= dayT && dayT <= new Integer(daysT.get(1))
                            || datum.getOut_result().stream().filter(data -> data.equals(EXECUTORCONTEXT.getInitialData().get("SF0108").toString())).collect(Collectors.toList()).size() == 0))
                        EXECUTORCONTEXT.getRemoveAdrg().put(datum.getCode(), rule.trim());
                    break;
                case "病例诊断符合ADRG诊断":

                    break;
                case "并且病历诊断符合ADRG诊断":

                    break;
                default:
                    System.err.println("警告!! 主要诊断ADRG:" + datum.getName() + "中计算模型有遗漏 遗漏要求:" + rule.trim());
                    break;
            }
        }
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
     * 根据type取出对应code
     *
     * @param type
     * @param dataBean
     * @return
     */
    private static List<String> getTableRuleCode(String type, MdcResult.DataBean dataBean) {
        return dataBean.getTab_rule()
                .stream().filter(tabRuleBean -> type.equals(tabRuleBean.getName()))
                .collect(Collectors.toList())
                .get(0).getCode();
    }

    /**
     * 获取icd10,icd9
     *
     * @param map
     */
    private static void getData(Map<String, Object> map) {
        EXECUTORCONTEXT.setInitialData(map);
        //调接口三次获取主要诊断,其他诊断,手术等相关数据
        EXECUTORCONTEXT.setMainIcd10(getMainIcd10((String) map.get("DISEASE_CODE")));
        EXECUTORCONTEXT.setIcd10s(getIcd10((List<String>) map.get("diags_code")));
        EXECUTORCONTEXT.setIcd9s(getIcd9((List<String>) map.get("opers_code")));
        //本地数据处理,统计 mdc adrg cc mcc
        EXECUTORCONTEXT.setMdc(EXECUTORCONTEXT.getMainIcd10().getMdcs());
        List<String> aDrgs = new ArrayList<>();
        aDrgs.addAll(EXECUTORCONTEXT.getMainIcd10().getAdrg_a());
        ArrayList<String> mcc = new ArrayList<>();
        ArrayList<String> cc = new ArrayList<>();
        EXECUTORCONTEXT.getIcd10s().forEach(icd10 -> {
            if (icd10.is_mcc())
                mcc.add(icd10.getName());
            if (icd10.is_cc())
                cc.add(icd10.getName());
            aDrgs.addAll(icd10.getAdrg_a());
        });
        EXECUTORCONTEXT.getIcd9s().stream().filter(icd9 -> aDrgs.addAll(icd9.getAdrg_a()));
        EXECUTORCONTEXT.setADrg(aDrgs);
        EXECUTORCONTEXT.setMcc(mcc);
        EXECUTORCONTEXT.setCc(cc);
    }

    /**
     * 获取主要诊断相关数据
     *
     * @param diseaseCode
     * @return
     */
    private static Icd10 getMainIcd10(String diseaseCode) {
        ArrayList<String> list = new ArrayList<>();
        list.add(diseaseCode);
        return getIcd10(list).get(0);
    }

    /**
     * 获取其他诊断相关数据
     *
     * @param diagsCode
     * @return
     */
    private static List<Icd10> getIcd10(List<String> diagsCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "icd10");
        map.put("code", rulePackString(diagsCode));
        map.put("version", VERSION);
        return JsonUtils.toBean(HttpClient4.doPost(ruleurl, map), Icd10List.class).getRule();
    }

    /**
     * 获取手术/操作编码相关数据
     *
     * @param opers_code
     * @return
     */
    private static List<Icd9> getIcd9(List<String> opers_code) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", "icd9");
        map.put("code", rulePackString(opers_code));
        map.put("version", VERSION);
        return JsonUtils.toBean(HttpClient4.doPost(ruleurl, map), Icd9List.class).getRule();
    }

    /**
     * 封装请求字符串
     *
     * @param code
     * @return
     */
    private static String rulePackString(List<String> code) {
        StringBuffer buffer = new StringBuffer("「");
        if (code != null) {
            for (String str : code) {
                buffer.append(str + ",");
            }
        } else {
            buffer.append(",");
        }
        return buffer.substring(0, buffer.length() - 1) + "」";
    }

    /**
     * 将病案资料转map存储
     *
     * @param dataBean
     * @return
     * @throws IllegalAccessException
     */
    private static Map<String, Object> MedicalRecordInformation2Map(MedicalRecordInformation dataBean) throws IllegalAccessException {
        Map<String, Object> map = ObjectUtils.objectToMapKeyUp(dataBean);
        //分组器版本
        map.put("version", VERSION);
        //其他诊断编码
        List<String> diags_code = dataBean.getDiags_code();
        //手术编码
        List<String> opers_code = dataBean.getOpers_code();
        //处理表单请求数组传参
        if (diags_code.size() > 0) {
            for (int i = 0; i < diags_code.size(); i++) {
                map.put("diags_code[" + i + "]", diags_code.get(i));
            }
        }
        if (opers_code.size() > 0) {
            for (int i = 0; i < opers_code.size(); i++) {
                map.put("opers_code[" + i + "]", opers_code.get(i));
            }
        }
        return map;
    }
}
