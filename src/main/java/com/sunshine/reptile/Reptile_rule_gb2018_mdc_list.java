package com.sunshine.reptile;

import com.sunshine.reptile.dao.MdcListRepository;
import com.sunshine.reptile.domain.Gb2018AdrgList;
import com.sunshine.reptile.domain.Gb2018Mdc;
import com.sunshine.reptile.domain.Gb2018MdcList;
import com.sunshine.reptile.utils.HttpClient4;
import com.sunshine.reptile.utils.JsonUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月11日 23:47
 */
@RestController
public class Reptile_rule_gb2018_mdc_list {

    @Resource
    private MdcListRepository mdcListRepository;

    @GetMapping("/createmdc")
    public void reptileMdcList() throws IllegalAccessException, InstantiationException {
        String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_mdc?plat=client&table=rule_gb2018_mdc_list");
        List<Gb2018Mdc> gb2018MdcList = JsonUtils.toBean(s, Gb2018MdcList.class).getData();
        mdcListRepository.deleteAll();
        for (Gb2018Mdc gb2018Mdc : gb2018MdcList) {
            mdcListRepository.save(gb2018Mdc);
        }
    }

    @GetMapping("/createadrg")
    public void reptileAdrgList() throws IllegalAccessException, InstantiationException {
        for (Gb2018Mdc gb2018Mdc : mdcListRepository.findAll()) {
            String s = HttpClient4.doGet("https://www.jiankanglaifu.com/library/rule_adrg?plat=client&table=rule_gb2018_mdc&code=" + gb2018Mdc.getMdc());
            Gb2018AdrgList gb2018AdrgList = JsonUtils.toBean(s, Gb2018AdrgList.class);
            System.out.println(gb2018AdrgList);
            System.out.println(s);

        }
    }

}
