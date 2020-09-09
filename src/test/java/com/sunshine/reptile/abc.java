package com.sunshine.reptile;

import com.sunshine.reptile.config.RedisClient;
import com.sunshine.reptile.utils.HttpClient4;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年09月09日 23:29
 */
public class abc extends BaseTest {
    @Resource
    private RedisClient<String> redisClient;
 @Test
    public void t1() throws InterruptedException {
   int  n=0;
     for (int i = 1; i < 999; i++) {
//https://www.fuli7.lv/category/default/
         String url = "https://www.fuli7.lv/category/xlzl/" + i + "/";
         String body = HttpClient4.doGet(url);
         Matcher matcher = Pattern.compile("</style>(.*?)</h2>").matcher(body);
         while (matcher.find()) {
             String group1 = matcher.group(1);
             System.out.println(group1);
             redisClient.set(group1,0);
         }
         matcher = Pattern.compile("<h2.*?>(.*?)</h2>").matcher(body);
         while (matcher.find()) {
             String group1 = matcher.group(1);
             System.out.println(group1);
             redisClient.set(group1,0);
         }
         Thread.sleep(2000);
     }
 }
}
