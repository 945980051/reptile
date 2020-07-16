package com.sunshine.reptile;

import com.sunshine.reptile.config.RedisClient;
import com.sunshine.reptile.dao.*;
import com.sunshine.reptile.domain.*;
import com.sunshine.reptile.utils.BeanUtils;
import com.sunshine.reptile.utils.HttpClient;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.JsonUtils;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月12日 2:45
 */
public class ReptileTest extends BaseTest {
    @Resource
    private MdcListRepository mdcListRepository;
    @Resource
    private AdrgListRepository adrgListRepository;
    @Resource
    private DrgListRepository drgListRepository;
    @Resource
    private Icd10AzListRepository icd10AzListRepository;
    @Resource
    private Icd10cListRepository icd10cListRepository;
    @Resource
    private Icd10cListARepository icd10cListARepository;
    @Autowired
    private RedisClient<String> redisClient;

    @Test
    public void reptileMdcList() {
        //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_gb2018_mdc_list");
        //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_2019_mdc_list");
        //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_chs_mdc_list");
        //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_bj2019_mdc_list");
        // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_gb2019_mdc_list_v2");
        //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_gb2019_mdc_list");
        // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_cc2018_mdc_list");
        // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_bj2017_mdc_list");
        //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_bj2016_mdc_list");
        // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_bj2015_mdc_list");
        String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_cc2015_mdc_list");
        List<Gb2018Mdc> gb2018MdcList = JsonUtils.toBean(s, Gb2018MdcList.class).getData();
        mdcListRepository.deleteAll();
        for (Gb2018Mdc gb2018Mdc : gb2018MdcList) {
            System.out.println(gb2018Mdc.getMdc());
            mdcListRepository.save(gb2018Mdc);
        }
        reptileAdrgList();
    }

    @Test
    public void reptileAdrgList() {
        adrgListRepository.deleteAll();
        List<Gb2018Adrg> list = new ArrayList<>();
        for (Gb2018Mdc gb2018Mdc : mdcListRepository.findAll()) {
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_gb2018_mdc&code=" + gb2018Mdc.getMdc());
            //String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_2019_mdc&code=" + gb2018Mdc.getMdc());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_chs_mdc&code=" + gb2018Mdc.getMdc());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_bj2019_mdc&code=" + gb2018Mdc.getMdc());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_gb2019_mdc_v2&code=" + gb2018Mdc.getMdc());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_gb2019_mdc&code=" + gb2018Mdc.getMdc());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_cc2018_mdc&code=" + gb2018Mdc.getMdc());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_bj2017_mdc&code=" + gb2018Mdc.getMdc());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_bj2016_mdc&code=" + gb2018Mdc.getMdc());
            //   String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_bj2015_mdc&code=" + gb2018Mdc.getMdc());
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_cc2015_mdc&code=" + gb2018Mdc.getMdc());
            if ("\"Server internal error\"".equals(s))
                continue;
            List<Gb2018Adrg> gb2018AdrgList = JsonUtils.toBean(s, Gb2018AdrgList.class).getData();
            list.addAll(gb2018AdrgList);
        }
        for (Gb2018Adrg gb2018Adrg : list) {
            adrgListRepository.save(gb2018Adrg);
        }
        reptileDrgList();
    }

    @Test
    public void reptileDrgList() {
        drgListRepository.deleteAll();
        List<Gb2018Drg> list = new ArrayList<>();
        for (Gb2018Adrg gb2018Adrg : adrgListRepository.findAll()) {
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_gb2018_adrg&code=" + gb2018Adrg.getCode());
            //String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_drg?plat=client&table=rule_2019_adrg&code=" + gb2018Adrg.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_chs_adrg&code=" + gb2018Adrg.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_bj2019_adrg&code=" + gb2018Adrg.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_gb2019_adrg_v2&code=" + gb2018Adrg.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_gb2019_adrg&code=" + gb2018Adrg.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_cc2018_adrg&code=" + gb2018Adrg.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_bj2017_adrg&code=" + gb2018Adrg.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_bj2016_adrg&code=" + gb2018Adrg.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_bj2015_adrg&code=" + gb2018Adrg.getCode());
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_cc2015_adrg&code=" + gb2018Adrg.getCode());
            if ("\"Server internal error\"".equals(s))
                continue;
            List<Gb2018Drg> gb2018DrgList = JsonUtils.toBean(s, Gb2018DrgList.class).getData();
            list.addAll(gb2018DrgList);
        }
        for (Gb2018Drg gb2018Drg : list) {
            drgListRepository.save(gb2018Drg);
        }
    }


    @Test
    public void reptileIcd10AzList() {
        icd10AzListRepository.deleteAll();
        icd10cListRepository.deleteAll();
        char uc = 'A';
        ArrayList<Gb2018Icd10Az> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2018_icd10az&code=" + (char) (uc + i));
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10az&code=" + (char) (uc + i));
            List<Gb2018Icd10Az> gb2018Icd10List = JsonUtils.toBean(s, Gb2018Icd10AzList.class).getData();
            list.addAll(gb2018Icd10List);
        }
        for (Gb2018Icd10Az gb2018Icd10 : list) {
            icd10AzListRepository.save(gb2018Icd10);
        }
    }

    @Test
    public void reptileIcd10cList() throws IllegalAccessException, InstantiationException {
        icd10cListRepository.deleteAll();


      /*  for (Gb2018Icd10Az gb2018Icd10 : icd10AzListRepository.findAll()) {
            System.out.println(gb2018Icd10.getCode());
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2018_icd10c&code=" + gb2018Icd10.getCode());
            List<Gb2018Icd10c> gb2018Icd10cList = JsonUtils.toBean(s, Gb2018Icd10cList.class).getData();
            List<Gb2018Icd10c> list = gb2018Icd10cList
                    .stream().filter(gb2018Icd10c -> gb2018Icd10c.getCode() != null)
                    .collect(Collectors.toList());
            for (Gb2018Icd10d gb2018Icd10d : BeanUtils.copy(list, Gb2018Icd10d.class)) {
                icd10cListARepository.save(gb2018Icd10d);
            }
        }*/
        HashMap<String, Gb2018Icd10d> map = new HashMap<>();
        icd10cListARepository.deleteAll();
        TreeSet<String> treeSet = new TreeSet<>(icd10AzListRepository.findAll().stream().map(Gb2018Icd10Az::getCode).collect(Collectors.toSet()));
        for (String str : treeSet) {
            System.out.println(str + "_" + map.size());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2018_icd10c&code=" + str);
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10c&code=" + str);
            List<Gb2018Icd10c> list = JsonUtils.toBean(s, Gb2018Icd10cList.class).getData()
                    .stream().filter(gb2018Icd10c -> gb2018Icd10c.getCode() != null)
                    .collect(Collectors.toList());
            for (Gb2018Icd10d gb2018Icd10d : BeanUtils.copy(list, Gb2018Icd10d.class)) {
                map.put(gb2018Icd10d.getCode(), gb2018Icd10d);
            }
           /* if (map.size() == 7641)
                break;*/
        }
        for (Gb2018Icd10d gb2018Icd10d : map.values()) {
            icd10cListARepository.save(gb2018Icd10d);
        }
          /*  for (Gb2018Icd10c gb2018Icd10c : list) {
                if (!redisClient.exists(gb2018Icd10c.getCode())) {
                    redisClient.set(gb2018Icd10c.getCode(), gb2018Icd10c.getName());
                    icd10cListRepository.save(gb2018Icd10c);
                }
            }*/

    }

    @Resource
    private Icd10ListERepository icd10ListERepository;

    @Test
    public void reptileIcd10Info() throws IllegalAccessException, InstantiationException {
        icd10ListERepository.deleteAll();
        HashMap<String, Gb2018Icd10e> map = new HashMap<>();
        for (Gb2018Icd10d gb2018Icd10d : icd10cListARepository.findAll()) {
            System.out.println(gb2018Icd10d.getCode() + "_" + map.size());
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10cc&code=" + gb2018Icd10d.getCode());
            List<Gb2018Icd10c> list = JsonUtils.toBean(s, Gb2018Icd10cList.class).getData()
                    .stream().filter(gb2018Icd10c -> gb2018Icd10c.getCode() != null)
                    .collect(Collectors.toList());
          /*  for (Gb2018Icd10e gb2018Icd10e : BeanUtils.copy(list, Gb2018Icd10e.class)) {
                map.put(gb2018Icd10e.getName(), gb2018Icd10e);
            }*/
        }
       /* for (Gb2018Icd10e gb2018Icd10e : map.values()) {
            icd10ListERepository.save(gb2018Icd10e);
        }*/
    }

    @Resource
    private Icd9ListARepository icd9ListARepository;
    @Resource
    private Icd9ListBRepository icd9ListBRepository;

    @Test
    public void reptileIcd9List() {
        icd9ListARepository.deleteAll();
        icd9ListBRepository.deleteAll();
        List<Gb2018Icd9A> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //  String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_gb2018_icd9_09&code=" + i);
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_2019_icd9_09&code=" + i);
            List<Gb2018Icd9A> data = JsonUtils.toBean(s, Gb2018Icd9AList.class).getData();
            list.addAll(data);
            icd9ListARepository.saveAll(data);
        }
        for (Gb2018Icd9A gb2018Icd9A : list) {
            // String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_gb2018_icd9c&code=" + gb2018Icd9A.getCode());
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_2019_icd9c&code=" + gb2018Icd9A.getCode());
            List<Gb2018Icd9B> data = JsonUtils.toBean(s, Gb2018Icd9BList.class).getData().stream().filter(gb2018Icd9B -> gb2018Icd9B.getCode() != null).collect(Collectors.toList());
            icd9ListBRepository.saveAll(data);
        }
    }

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void reptileIcd10() {
        char uc = 'A';
        List<Gb2018Icd10Az> list = new ArrayList<>();
        HashMap<String, Gb2018Icd10c> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            //   String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10az&code=" + (char) (uc + i));
            // String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_chs_icd10az&code=" + (char) (uc + i));
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_chs_icd10az&code=" + (char) (uc + i));
            //       String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2019_icd10az&code=" + (char) (uc + i));
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2019_icd10az_v2&code=" + (char) (uc + i));
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2019_icd10az&code=" + (char) (uc + i));
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_cc2018_icd10az&code=" + (char) (uc + i));
            //   String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2017_icd10az&code=" + (char) (uc + i));
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2016_icd10az&code=" + (char) (uc + i));
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2015_icd10az&code=" + (char) (uc + i));
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_cc2015_icd10az&code=" + (char) (uc + i));
            List<Gb2018Icd10Az> gb2018Icd10List = JsonUtils.toBean(s, Gb2018Icd10AzList.class).getData();
            list.addAll(gb2018Icd10List);
        }
        for (Gb2018Icd10Az gb2018Icd10Az : list) {
            //   String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10c&code=" + gb2018Icd10Az.getCode());
            //  String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_chs_icd10c&code=" + gb2018Icd10Az.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_chs_icd10c&code=" + gb2018Icd10Az.getCode());
            //    String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2019_icd10c&code=A00" + gb2018Icd10Az.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2019_icd10c_v2&code=" + gb2018Icd10Az.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2019_icd10c&code=" + gb2018Icd10Az.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_cc2018_icd10c&code=" + gb2018Icd10Az.getCode());
            //   String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2017_icd10c&code=" + gb2018Icd10Az.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2016_icd10c&code=" + gb2018Icd10Az.getCode());
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_cc2015_icd10c&code=" + gb2018Icd10Az.getCode());
            for (Gb2018Icd10c gb2018Icd10c : JsonUtils.toBean(s, Gb2018Icd10cList.class).getData()
                    .stream().filter(gb2018Icd10c -> gb2018Icd10c.getCode() != null && gb2018Icd10c.getCode().startsWith(gb2018Icd10Az.getCode()))
                    .collect(Collectors.toList())) {
                map.put(gb2018Icd10c.getName(), gb2018Icd10c);
            }
            System.out.println(gb2018Icd10Az.getCode() + "_" + map.size());
            //  if (map.size() == 7639) break;
        }
        for (Gb2018Icd10c value : map.values()) {
            //  String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10cc&code=" + value.getCode());
            //  String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_chs_icd10cc&code=" + value.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_chs_icd10cc&code=" + value.getCode());
            //     String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2019_icd10cc&code=" + value.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2019_icd10cc_v2&code=" + value.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2019_icd10cc&code=" + value.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_cc2018_icd10cc&code=" + value.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2017_icd10cc&code=" + value.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_bj2016_icd10cc&code=" + value.getCode());
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_cc2015_icd10cc&code=" + value.getCode());
            value.setSubattribute(JsonUtils.toBean(s, Gb2018Icd10FList.class).getData());
            if (value.getSubattribute().size() == 0) {
                List<Gb2018Icd10F> gb2018Icd10FS = new ArrayList<>();
                Gb2018Icd10F gb2018Icd10F = new Gb2018Icd10F();
                BeanUtils.copy(value, gb2018Icd10F);
                gb2018Icd10FS.add(gb2018Icd10F);
                value.setSubattribute(gb2018Icd10FS);
            }
        }
        mongoTemplate.dropCollection(Gb2018Icd10F.class);
        mongoTemplate.dropCollection(Gb2018Icd10c.class);
        List<Gb2018Icd10F> gb2018Icd10FS = new ArrayList<>();
        for (Gb2018Icd10c value : map.values()) {
            mongoTemplate.save(value);
            gb2018Icd10FS.addAll(value.getSubattribute());
        }
        for (Gb2018Icd10F gb2018Icd10F : gb2018Icd10FS) {
            mongoTemplate.save(gb2018Icd10F);
        }
        t1();
    }

    @Test
    public void reptileIcd9() {
        List<Gb2018Icd9A> list = new ArrayList<>();
        HashMap<String, Gb2018Icd9B> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            //String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_2019_icd9_09&code=" + i);
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_chs_icd9_09&code=" + i);
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2019_icd9_09&code=" + i);
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_gb2019_icd9_09_v2&code=" + i);
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_gb2019_icd9_09&code=" + i);
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_cc2018_icd9_09&code=" + i);
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2017_icd9_09&code=" + i);
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2016_icd9_09&code=" + i);
            //   String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2015_icd9_09&code=" + i);
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_cc2015_icd9_09&code=" + i);
            List<Gb2018Icd9A> data = JsonUtils.toBean(s, Gb2018Icd9AList.class).getData();
            list.addAll(data);
        }
        for (Gb2018Icd9A gb2018Icd9A : list) {
            // String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_2019_icd9c&code=" + gb2018Icd9A.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_chs_icd9c&code=" + gb2018Icd9A.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2019_icd9c&code=" + gb2018Icd9A.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_gb2019_icd9c_v2&code=" + gb2018Icd9A.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_gb2019_icd9c&code=" + gb2018Icd9A.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_cc2018_icd9c&code=" + gb2018Icd9A.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2017_icd9c&code=" + gb2018Icd9A.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2016_icd9c&code=" + gb2018Icd9A.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2015_icd9c&code=" + gb2018Icd9A.getCode());
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_cc2015_icd9c&code=" + gb2018Icd9A.getCode());
            List<Gb2018Icd9B> data = JsonUtils.toBean(s, Gb2018Icd9BList.class).getData().stream().filter(gb2018Icd9B -> gb2018Icd9B.getCode() != null).collect(Collectors.toList());
            for (Gb2018Icd9B datum : data) {
                map.put(datum.getName(), datum);
            }
            System.out.println(gb2018Icd9A.getCode() + "_" + map.size());
        }
        mongoTemplate.dropCollection(Gb2018Icd9C.class);
        for (Gb2018Icd9B value : map.values()) {
            //  String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_2019_icd9cc&code=" + value.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_chs_icd9cc&code=" + value.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2019_icd9cc&code=" + value.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_gb2019_icd9cc_v2&code=" + value.getCode());
            //String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_gb2019_icd9cc&code=" + value.getCode());
            //  String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2017_icd9cc&code=" + value.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2016_icd9cc&code=" + value.getCode());
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_bj2015_icd9cc&code=" + value.getCode());
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_cc2015_icd9cc&code=" + value.getCode());
            List<Gb2018Icd9C> data = JsonUtils.toBean(s, Gb2018Icd9CList.class).getData();
            if (data.size() == 0) {
                Gb2018Icd9C gb2018Icd9C = new Gb2018Icd9C();
                BeanUtils.copy(value, gb2018Icd9C);
                mongoTemplate.save(gb2018Icd9C);
            } else {
                for (Gb2018Icd9C datum : JsonUtils.toBean(s, Gb2018Icd9CList.class).getData()) {
                    mongoTemplate.save(datum);
                }
            }
        }
    }

    @Test
    public void t1() {
        List<Gb2018Icd10F> list = new ArrayList<>();
        for (Gb2018Icd10c gb2018Icd10c : mongoTemplate.findAll(Gb2018Icd10c.class)) {
            list.addAll(gb2018Icd10c.getSubattribute());
        }
        System.out.println(list.size());
        for (Gb2018Icd10F gb2018Icd10F : list) {
            mongoTemplate.save(gb2018Icd10F);
        }
    }

    @Test
    public void t2() throws InterruptedException {
        // List<Drugs> drugs = new ArrayList<>();
        mongoTemplate.dropCollection(Drugs.class);
        String s = HttpClient4.doGet("http://drugs.dxy.cn/index.htm");
        Matcher matcher = Pattern.compile("<h3><a href=\"(.*?)\">(.*?)</a></h3>").matcher(s);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            // System.out.println(matcher.group(1));
            // System.out.println(matcher.group(2));
            list.add(matcher.group(1));
        }
        // int page=1;
        for (String str : list) {
            //    System.out.println("http:" + str);
            String s2 = HttpClient4.doGet("http:" + str);
            Matcher matcher2 = Pattern.compile("page=([0-9]+).*?最后一页").matcher(s2);
            int page = 1;
            while (matcher2.find()) {
                // System.out.println(matcher2.group(1));
                page = Integer.parseInt(matcher2.group(1));
            }
            for (int i = 1; i <= page; i++) {
                String s1 = HttpClient4.doGet("http:" + str + "?page=" + i);
                //  Matcher matcher1 = Pattern.compile("<h3>.\\n.*<a href=\"(.*?)\">(.*?)</a>").matcher(s1);
                Matcher matcher1 = Pattern.compile("<h3>\\n.*<a href=\"(.*?)\">(.*?)</a>").matcher(s1);
                while (matcher1.find()) {
                    Drugs drug = new Drugs();
                    //    System.out.println(matcher1.group(1));
                    String s3 = HttpClient4.doGet("http:" + matcher1.group(1)).replace("\t", "");
                    // System.out.println(s2);
                    Matcher matcher3 = Pattern.compile("药品名称:</span></dt>\\n.*\\n(.*?)<br/>.*\\n(.*)\\n(.*)").matcher(s3);
                    while (matcher3.find()) {
                        System.out.println(matcher3.group(1));
                        System.out.println(matcher3.group(2));
                        System.out.println(matcher3.group(3));
                        drug.setCommonName(matcher3.group(1));
                        drug.setEnglishName(matcher3.group(2));
                        drug.setCommodityName(matcher3.group(3));
                    }
                    String group = "";
                    Matcher matcher4 = Pattern.compile("curr_drugId = ([0-9]+);").matcher(s3);
                    while (matcher4.find()) {
                        group = matcher4.group(1);
                    }
                    String syz = getBody(group, s3, "<dt><span class=\"fl\" id=\"3\">适应症:</span></dt>");
                    drug.setSyz(syz);
                    String jj = getBody(group, s3, "<dt><span class=\"fl\" id=\"12\">禁忌:</span></dt>");
                    drug.setJj(jj);
                    String pzwh = getBody(group, s3, "<dt><span class=\"fl\" id=\"39\">批准文号:</span></dt>");
                    drug.setPzwh(pzwh);
                    System.out.println(drug);
                    mongoTemplate.save(drug);
                    // drugs.add(drug);
                    Thread.sleep(1000);
                }
                Thread.sleep(1000);
            }
            Thread.sleep(1000);
        }
    }


    private static String getBody(String id, String str, String type) {
        String s4 = "";
        try {
            s4 = str.substring(str.indexOf(type));
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        s4 = s4.substring(s4.indexOf("<dd>") + 4, s4.indexOf("</dd>"))
                .replace("<p>", "")
                .replace("</p>", "")
                .replace("<li>", "")
                .replace("</li>", "")
                .replace("\n", "")
                .replace("\t", "")
                .replace(" ", "");
        if (s4.indexOf("<font color=\"red\">登录</font>") != -1) {
//id="([0-9+].)"

            String ss = "callCount=1\n" +
                    "page=/drug/89790/detail.htm\n" +
                    "httpSessionId=\n" +
                    "scriptSessionId=850AD29AFF157BB633956D6062C9CAFD491\n" +
                    "c0-scriptName=DrugUtils\n" +
                    "c0-methodName=showDetail\n" +
                    "c0-id=0\n" +
                    "c0-param0=number:89790\n" +
                    "c0-param1=number:14\n" +
                    "batchId=" + Pattern.compile("id=\"([0-9+].)\"").matcher(str).group(1) + "\n";
            ss = ss.replace("89790", id);
            String s5 = HttpClient.doPost("http://drugs.dxy.cn/dwr/call/plaincall/DrugUtils.showDetail.dwr", ss, id);
            System.out.println(s5);
            return s5;
        } else {
            return s4;
        }
    }

    public static void main(String[] args) throws Exception {
        String regex = "<p class=\"betbtn\" data-type=\"[a-z]*\" data-value=\"\\d\" data-sp=\"\\d*\\.\\d*\"><span>(\\d*\\.\\d*)</span></p><p class=\"betbtn\" data-type=\"[a-z]*\" data-value=\"\\d\" data-sp=\"\\d*\\.\\d*\"><span>(\\d*\\.\\d*)</span></p><p class=\"betbtn\" data-type=\"[a-z]*\" data-value=\"\\d\" data-sp=\"\\d*\\.\\d*\"><span>(\\d*\\.\\d*)</span></p>";
        Matcher matcher = Pattern.compile(regex).matcher(doGet("https://trade.500.com/jczq/"));
        Map<String, List<String>> treeMap = new TreeMap<>();
        while (matcher.find()) {
            List<String> arrayList = new ArrayList<>();
            arrayList.add(matcher.group(1));
            arrayList.add(matcher.group(2));
            arrayList.add(matcher.group(3));
            TreeSet<String> treeSet = new TreeSet<>();
            for (String str : arrayList) {
                treeSet.add(str);
            }
            treeMap.put(treeSet.first(), arrayList);
        }
        //treeMap
        String[] toArray = treeMap.keySet().toArray(new String[0]);
        String s1 = toArray[toArray.length - 1];
        String s2 = toArray[toArray.length - 2];
        List<String> ranksA = treeMap.get(s1);
        List<String> ranksB = treeMap.get(s2);
        Set<String> treeSet = new TreeSet<>();
        for (Map.Entry<String, List<String>> entry1 : treeMap.entrySet()) {
            for (Map.Entry<String, List<String>> entry2 : treeMap.entrySet()) {
                if (entry1.getKey().equals(entry2.getKey()))
                    break;
                List<RespDto> respDtos = getResult(entry1.getValue(), entry1.getValue());
                String s = respDtos.stream().map(RespDto::getCount).collect(Collectors.toList()).get(0);
                treeSet.add(s);
            }
        }


        //List<RespDto> respDtos = getResult(ranksA, ranksB);
        System.out.println(treeSet);
    }

    private static List<RespDto> getResult(List<String> ranksA, List<String> ranksB) throws InterruptedException {
        List<String> list = new ArrayList<>();
        for (String ra : ranksA) {
            for (String rb : ranksB) {
                list.add(set(ra, rb));
            }
        }
        int[] multiple = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        boolean ary = true;
        String count = "0";
        //int n = 0;
        while (ary) {
            String countt = count;
            for (int i = 1; i < list.size(); i++) {
                String[] strings = get(list.get(i));
                String money = getMoney(strings[0], strings[1], multiple[i]);
                count = getCount(multiple);
                if (new BigDecimal(money).compareTo(new BigDecimal(count)) == -1) {
                    multiple[i]++;
                    continue;
                }
            }
            /*System.out.println(list);
            System.out.println(getStr(getMultiple(multiple)));
            System.out.println(getStr(getRanks(multiple, list)));
            System.out.println(count);
            Thread.sleep(200);*/
            if (count.equals(countt) || Integer.valueOf(count) > 1000000)
                break;
        }
       /* System.out.println(list);
        System.out.println(getStr(getMultiple(multiple)));
        System.out.println(getStr(getRanks(multiple, list)));
        System.out.println(count);*/
        List<RespDto> respDtos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            RespDto respDto = new RespDto();
            respDto.setScores(list.get(i));
            respDto.setMultiple(multiple[i] + "");
            String[] strings = get(list.get(i));
            String money = getMoney(strings[0], strings[1], multiple[i]);
            respDto.setMoney(money);
            respDto.setCount(count);
            respDtos.add(respDto);
        }
        return respDtos;
    }

    /**
     * 获取每队金额
     *
     * @param multiple
     * @param list
     * @return
     */
    public static List<String> getRanks(int[] multiple, List<String> list) {
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String[] strings = get(list.get(i));
            arrayList.add(getMoney(strings[0], strings[1], multiple[i]));
        }
        return arrayList;
    }

    /**
     * 字符串拼接
     *
     * @param strs
     * @return
     */
    public static String getStr(List<String> strs) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < strs.size(); i++) {
            if (i != 0)
                buffer.append(":");
            buffer.append(strs.get(i));
        }
        return buffer.toString();
    }

    /**
     * 获取结果倍数
     *
     * @param multiple
     * @return
     */
    public static List<String> getMultiple(int[] multiple) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < multiple.length; i++) {
            list.add(multiple[i] + "");
        }
        return list;
    }

    /**
     * 取总金额
     *
     * @param multiple
     * @return
     */
    private static String getCount(int[] multiple) {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (int i = 0; i < multiple.length; i++) {
            if (multiple[i] != 0) {
                bigDecimal = bigDecimal.add(new BigDecimal("2").multiply(new BigDecimal(multiple[i] + "")));
            }
        }
        if (bigDecimal.compareTo(new BigDecimal("0")) == 0)
            return "2";
        else
            return bigDecimal.toString();
    }


    private static String set(String ranksA, String ranksB) {
        return ranksA + "-" + ranksB;
    }

    private static String[] get(String ranks) {
        return ranks.split("-");
    }

    /**
     * 计算两对金额
     *
     * @param ranksA
     * @param ranksB
     * @param multiple
     * @return
     */
    private static String getMoney(String ranksA, String ranksB, int multiple) {
        return new BigDecimal(ranksA)
                .multiply(new BigDecimal(ranksB))
                .multiply(new BigDecimal(multiple + ""))
                .multiply(new BigDecimal("2"))
                .multiply(new BigDecimal("1"))
                .setScale(2, BigDecimal.ROUND_DOWN)
                .toString();
    }

    private static String doGet(String url) throws IOException {
        URL connURL = new URL(url);
        HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
        httpConn.setRequestProperty("Accept", "*/*");
        httpConn.connect();
        //httpConn.getHeaderField("keyName");
        // 响应头部获取
        Map<String, List<String>> headers = httpConn.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : headers.keySet()) {
            //  System.out.println(key + ":  " + httpConn.getHeaderField(key));
        }
        // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
        BufferedReader in = null;

        in = new BufferedReader(new InputStreamReader(httpConn
                .getInputStream(), "gbk"));//通用编码格式为utf-8
        String line;
        StringBuffer buffer = new StringBuffer();
        // 读取返回的内容
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        String respBody = buffer.toString();
        httpConn.disconnect();//主动断开httpConn连接
        return respBody;
    }

    @Data
    public static class RespDto {
        //比例
        private String scores;
        //倍数
        private String multiple;
        //金额
        private String money;

        private String count;
    }
}
