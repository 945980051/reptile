package com.sunshine.reptile;

import com.sunshine.reptile.dao.*;
import com.sunshine.reptile.domain.*;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.JsonUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void reptileMdcList() {
        String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_gb2018_mdc_list");
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
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_gb2018_mdc&code=" + gb2018Mdc.getMdc());
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
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_drg?plat=client&table=rule_gb2018_adrg&code=" + gb2018Adrg.getCode());
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
        char uc = 'A';
        ArrayList<Gb2018Icd10Az> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2018_icd10az&code=" + (char) (uc + i));
            List<Gb2018Icd10Az> gb2018Icd10List = JsonUtils.toBean(s, Gb2018Icd10AzList.class).getData();
            list.addAll(gb2018Icd10List);
        }
        for (Gb2018Icd10Az gb2018Icd10 : list) {
            icd10AzListRepository.save(gb2018Icd10);
        }
    }

    @Test
    public void reptileIcd10cList() {
        List<Gb2018Icd10c> list = new ArrayList<>();
        icd10cListRepository.deleteAll();
        for (Gb2018Icd10Az gb2018Icd10 : icd10AzListRepository.findAll()) {
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2018_icd10c&code=" + gb2018Icd10.getCode());
            List<Gb2018Icd10c> gb2018Icd10cList = JsonUtils.toBean(s, Gb2018Icd10cList.class).getData();
         //   list.addAll(gb2018Icd10cList);

            icd10cListRepository.saveAll(gb2018Icd10cList);
        }
        System.out.println(list.size());
        for (Gb2018Icd10c gb2018Icd10c : list) {
            icd10cListRepository.save(gb2018Icd10c);
        }
    }

    @Test
    public void reptileIcd10Info() {
        //icd10ListRepository.deleteAll();

    }
}
