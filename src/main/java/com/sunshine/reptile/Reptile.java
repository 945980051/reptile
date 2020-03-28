package com.sunshine.reptile;

import com.sunshine.reptile.utils.HttpClient;
import com.sunshine.reptile.utils.HttpClient3;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月28日 12:20
 */
@Component
public class Reptile {
    @PostConstruct
    public void cj() {
        String url ="https://www.jiankanglaifu.com/library/rule_icd10?plat=client&table=rule_gb2018_icd10az&code=";
        char uc = 'A';
        for (int i = 0; i < 26; i++) {
            String s = HttpClient3.doGet(url+ (char) (uc + i));
            System.out.println(s);
        }
    }
}
