package com.sunshine.reptile;

import com.sunshine.reptile.config.RedisClient;
import com.sunshine.reptile.dao.*;
import com.sunshine.reptile.domain.*;
import com.sunshine.reptile.utils.BeanUtils;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.JsonUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
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
        String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_2019_mdc_list");
        List<Gb2018Mdc> gb2018MdcList = JsonUtils.toBean(s, Gb2018MdcList.class).getData();
        mdcListRepository.deleteAll();
        for (Gb2018Mdc gb2018Mdc : gb2018MdcList) {
            System.out.println(gb2018Mdc.getMdc());
            mdcListRepository.save(gb2018Mdc);
        }
    }

    @Test
    public void reptileAdrgList() {
        adrgListRepository.deleteAll();
        List<Gb2018Adrg> list = new ArrayList<>();
        for (Gb2018Mdc gb2018Mdc : mdcListRepository.findAll()) {
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_gb2018_mdc&code=" + gb2018Mdc.getMdc());
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_2019_mdc&code=" + gb2018Mdc.getMdc());
            List<Gb2018Adrg> gb2018AdrgList = JsonUtils.toBean(s, Gb2018AdrgList.class).getData();
            list.addAll(gb2018AdrgList);
        }
        for (Gb2018Adrg gb2018Adrg : list) {
            adrgListRepository.save(gb2018Adrg);
        }
    }

    @Test
    public void reptileDrgList() {
        drgListRepository.deleteAll();
        List<Gb2018Drg> list = new ArrayList<>();
        for (Gb2018Adrg gb2018Adrg : adrgListRepository.findAll()) {
            // String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_gb2018_adrg&code=" + gb2018Adrg.getCode());
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_drg?plat=client&table=rule_2019_adrg&code=" + gb2018Adrg.getCode());
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
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10az&code=" + (char) (uc + i));
            List<Gb2018Icd10Az> gb2018Icd10List = JsonUtils.toBean(s, Gb2018Icd10AzList.class).getData();
            list.addAll(gb2018Icd10List);
        }
        for (Gb2018Icd10Az gb2018Icd10Az : list) {
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10c&code=" + gb2018Icd10Az.getCode());
            for (Gb2018Icd10c gb2018Icd10c : JsonUtils.toBean(s, Gb2018Icd10cList.class).getData()
                    .stream().filter(gb2018Icd10c -> gb2018Icd10c.getCode() != null && gb2018Icd10c.getCode().startsWith(gb2018Icd10Az.getCode()))
                    .collect(Collectors.toList())) {
                map.put(gb2018Icd10c.getName(), gb2018Icd10c);
            }
            System.out.println(gb2018Icd10Az.getCode() + "_" + map.size());
          //  if (map.size() == 7639) break;
        }
        for (Gb2018Icd10c value : map.values()) {
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_2019_icd10cc&code=" + value.getCode());
            value.setSubattribute(JsonUtils.toBean(s, Gb2018Icd10FList.class).getData());
        }
        for (Gb2018Icd10c value : map.values()) {
            Gb2018Icd10c gb2018Icd10c = value;
            mongoTemplate.save(gb2018Icd10c);
        }
    }

    @Test
    public void reptileIcd9() {
        List<Gb2018Icd9A> list = new ArrayList<>();
        HashMap<String, Gb2018Icd9B> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_2019_icd9_09&code=" + i);
            List<Gb2018Icd9A> data = JsonUtils.toBean(s, Gb2018Icd9AList.class).getData();
            list.addAll(data);
        }
        for (Gb2018Icd9A gb2018Icd9A : list) {
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_2019_icd9c&code=" + gb2018Icd9A.getCode());
            List<Gb2018Icd9B> data = JsonUtils.toBean(s, Gb2018Icd9BList.class).getData().stream().filter(gb2018Icd9B -> gb2018Icd9B.getCode() != null).collect(Collectors.toList());
            for (Gb2018Icd9B datum : data) {
                map.put(datum.getName(),datum);
            }
            System.out.println(gb2018Icd9A.getCode() + "_" + map.size());
        }
        mongoTemplate.dropCollection(Gb2018Icd9C.class);
        for (Gb2018Icd9B value : map.values()) {
            String s = HttpClient4.doGet("https://jiankanglaifu.com/library/rule_icd9?plat=client&table=rule_2019_icd9cc&code=" + value.getCode());
            for (Gb2018Icd9C datum : JsonUtils.toBean(s, Gb2018Icd9CList.class).getData()) {
                mongoTemplate.save(datum);
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
}
