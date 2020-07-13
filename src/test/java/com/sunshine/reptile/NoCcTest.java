package com.sunshine.reptile;

import com.sunshine.reptile.utils.ExcelPoiReaderUtils;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.JsonUtils;
import lombok.Data;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年06月08日 11:00
 */
public class NoCcTest extends BaseTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void t1() throws InterruptedException {
        String url = "https://www.jiankanglaifu.com/servers/comp_drg";
        HashMap<String, Object> param = new HashMap<>();
        param.put("version", "BJ开发版(2019)");

        param.put("AGE", "35");
        param.put("ACCTUAL_DAYS", "4");
        param.put("TOTAL_EXPENSE", "6517.07");
        param.put("SF0100", "-1");
        param.put("SF0101", "-1");
        param.put("SF0102", "-1");
        param.put("SF0108", "1");
        param.put("GENDER", "女");
        param.put("comp_type", "normal");
        List<String> diagsCode = new ArrayList<>();
        param.put("diags_code", diagsCode);


        String[] columns = {"code", "name", "adrg_a", "codes", "is_cc", "is_main", "is_mcc", "mdc_z", "mdcs", "no_cc", "p_main", "p_other"};
        List<Map<String, String>> list = new ExcelPoiReaderUtils().readExcel("D:\\健康来福字段数据\\整合版开发板(2019)0514.xlsx", columns)
                .stream().filter(item -> "是".equals(item.get("is_cc"))).collect(Collectors.toList());
        ArrayList<mainOrCc> arrayList = new ArrayList<>();
        // arrayList.add(new mainOrCc("埃尔托生物型霍乱","痢疾志贺菌痢疾" ));
        for (Map<String, String> map : list) {
            List<String> noCc = (List<String>) JsonUtils.toList(map.get("no_cc"));
            for (String str : noCc) {
                arrayList.add(new mainOrCc(str, map.get("name")));
            }
        }
        //  List<mainOrCc> results = new ArrayList<>();
        for (mainOrCc mainOrCc : arrayList) {
            param.put("DISEASE_CODE", mainOrCc.getMain());//主诊
            diagsCode.add(mainOrCc.getCc());//并发症


            if (diagsCode.size() > 0) {
                for (int i = 0; i < diagsCode.size(); i++) {
                    param.put("diags_code[" + i + "]", diagsCode.get(i));
                }
            }


            String json = HttpClient4.doPost(url, param);
            result result = JsonUtils.toBean(json, result.class);
            String log = result.getLogs().get(16).getLog();
            if (log.indexOf("判断17: 是否CC--是") != -1) {
                try {
                    mongoTemplate.save(result, "noCc");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(200);
        }


    }

    @Data
    static class mainOrCc {
        private String main;
        private String cc;

        public mainOrCc(String main, String cc) {
            this.main = main;
            this.cc = cc;
        }
    }

    @Data
    static class result {
        private int B_WT4_V1_ID;
        private String DISEASE_CODE;
        private String DISEASE_NAME;
        private boolean cc;
        private String code_version;
        private String drg;
        private String log;
        private boolean mcc;
        private Object oper_code;
        private String oper_name;
        private String version;
        private List<String> adrgs_diag;
        private List<String> adrgs_oper;
        private List<String> diags_code;
        private List<String> diags_name;
        private List<LogsBean> logs;
        private List<String> mdcs_main;
        private List<String> opers_adrg;
        private List<String> opers_code;
        private List<String> opers_name;
        private List<String> opers_qy;


    }

    @Data
    public static class LogsBean {
        private String log;
        private String type;
    }
}
