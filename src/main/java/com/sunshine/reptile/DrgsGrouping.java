package com.sunshine.reptile;

import com.sunshine.reptile.domain.MdcResult;
import com.sunshine.reptile.entity.*;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.JsonUtils;
import com.sunshine.reptile.utils.ObjectUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author : zhengwenyao
 * @Description: CN-B-DRG(2018)分组模型
 * @date Date : 2020年04月14日 0:04
 */
public class DrgsGrouping {

    private static final String version = "CN-B-DRG(2018)";
    private static final String version_mdc = "rule_gb2018_mdc";
    private static final String ruleUrl = "https://www.jiankanglaifu.com/servers/get_rule";
    private static ExecutorContext executorContext = new ExecutorContext();

    public static void main(String[] args) throws IllegalAccessException, IOException {

        int num = (int) Math.floor(Math.random() * 1000000) + 1;
        num = 263404;
        //获取基本并按资料
        MedicalRecordInformation medicalRecordInformation =
                JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/edit/wt4_2016_id?id=" + num), MedicalRecordInformationList.class)
                        .getData().get(0);
        //病案资料转MAP
        Map<String, Object> map = MedicalRecordInformation2Map(medicalRecordInformation);
        //初始化基本数据
        getData(map);
        //这个mdc来自于主要诊断,且要符合mdc计算模型及ADrg计算模型(比方:mdc计算模型符合,Adrg计算模型不符合 =不符合)
        List<String> mdc = getMdc();

        System.out.println("1");
    }


    private static String getPost(String url, Map<String, String> parm) throws IOException {
        HttpPost httpPost2 = new HttpPost(url);
        httpPost2.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
        List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
        for (Map.Entry<String, String> entry : parm.entrySet()) {
            pairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        // pairList.add(new BasicNameValuePair("icdId", icdId));
        httpPost2.setEntity(new UrlEncodedFormEntity(pairList, "utf-8"));
        CloseableHttpResponse response2 = getHttpClient().execute(httpPost2);
        return EntityUtils.toString(response2.getEntity());
    }

    private static DefaultHttpClient getHttpClient() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
        PoolingClientConnectionManager cm = new PoolingClientConnectionManager(schemeRegistry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(2);
        HttpHost googleResearch = new HttpHost("research.google.com", 80);
        HttpHost wikipediaEn = new HttpHost("en.wikipedia.org", 80);
        cm.setMaxPerRoute(new HttpRoute(googleResearch), 30);
        cm.setMaxPerRoute(new HttpRoute(wikipediaEn), 50);
        return new DefaultHttpClient(cm);
    }


    private static List<String> getMdc() {

        for (String mdc : executorContext.getMdc()) {
            //mdc计算模型过滤 待处理 不符合的mdc不进入下一步
            for (MdcResult.DataBean datum : JsonUtils.toBean(HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=" + version_mdc + "&code=" + mdc), MdcResult.class).getData()) {
                for (String adrg : executorContext.getADrg()) {
                    if (datum.getCode().equals(adrg)) {
                        //是否主诊算入并发症 	true
                        if ("true".equals(datum.getIs_main_cc()) && executorContext.getCc().size() == 0)
                            executorContext.getCc().add(executorContext.getMainIcd10().getName());
                        //ADrg计算模型过滤
                        System.out.println(datum.getCode());
                    }
                }
            }
        }
        return executorContext.getMdc();
    }

    //获取icd10,icd9
    private static void getData(Map<String, Object> map) {
        //调接口三次获取主要诊断,其他诊断,手术等相关数据
        executorContext.setMainIcd10(getMainIcd10((String) map.get("DISEASE_CODE")));
        executorContext.setIcd10s(getIcd10((List<String>) map.get("diags_code")));
        executorContext.setIcd9s(getIcd9((List<String>) map.get("opers_code")));
        //本地数据处理,统计 mdc adrg cc mcc
        executorContext.setMdc(executorContext.getMainIcd10().getMdcs());
        List<String> aDrgs = new ArrayList<>();
        aDrgs.addAll(executorContext.getMainIcd10().getAdrg_a());
        ArrayList<String> mcc = new ArrayList<>();
        ArrayList<String> cc = new ArrayList<>();
        executorContext.getIcd10s().forEach(icd10 -> {
            if (icd10.is_mcc())
                mcc.add(icd10.getName());
            if (icd10.is_cc())
                cc.add(icd10.getName());
            aDrgs.addAll(icd10.getAdrg_a());
        });
        executorContext.getIcd9s().stream().filter(icd9 -> aDrgs.addAll(icd9.getAdrg_a()));
        executorContext.setADrg(aDrgs);
        executorContext.setMcc(mcc);
        executorContext.setCc(cc);
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
        map.put("version", version);
        String s = HttpClient4.doPost(ruleUrl, map);
        return JsonUtils.toBean(s, Icd10List.class).getRule();
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
        map.put("version", version);
        String s = HttpClient4.doPost(ruleUrl, map);
        return JsonUtils.toBean(HttpClient4.doPost(ruleUrl, map), Icd9List.class).getRule();
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
        map.put("version", version);
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
