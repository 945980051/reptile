package com.sunshine.reptile;

import com.sunshine.reptile.dao.Icd10Repository;
import com.sunshine.reptile.domain.Icd10;
import com.sunshine.reptile.domain.Icd10a;
import com.sunshine.reptile.domain.Icd10b;
import com.sunshine.reptile.utils.HttpClient3;
import com.sunshine.reptile.utils.IdWorker;
import com.sunshine.reptile.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @PostConstruct
    public void cj() {
        String url = "https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=";
        String table = "rule_gb2018_icd10az";
        char uc = 'A';
        List<Icd10a.DataBean> az = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            String respBody = HttpClient3.doGet(url + table + "&code=" + (char) (uc + i));
            az.addAll(JsonUtils.toBean(respBody, Icd10a.class).getData());
        }
        for (Icd10a.DataBean dataBean : az) {
            String respBody = HttpClient3.doGet(url + dataBean.getQuery_table() + "&code=" + dataBean.getCode());
            for (Icd10 bean : JsonUtils.toBean(respBody, Icd10b.class).getData()) {
                System.out.println(bean.getCode());
                Icd10savaData(dataBean, bean);
                respBody = HttpClient3.doGet(url + bean.getQuery_table() + "&code=" + bean.getCode());
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
}
