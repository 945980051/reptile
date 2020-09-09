package com.sunshine.reptile;

import com.sunshine.reptile.utils.HttpClient4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年09月09日 23:06
 */
public class abc {
    public static void main(String[] args) throws InterruptedException {


        for (int i = 1; i < 999; i++) {
            String url = "https://www.fuli7.lv/page/" + i + "/";
            String body = HttpClient4.doGet(url);
            Matcher matcher = Pattern.compile("</style>(.*?)</h2>").matcher(body);
            while (matcher.find()) {
                String group1 = matcher.group(1);
                System.out.println(group1);
            }
            matcher = Pattern.compile("<h2.*?>(.*?)</h2>").matcher(body);
            while (matcher.find()) {
                String group1 = matcher.group(1);
                System.out.println(group1);
            }
            Thread.sleep(2000);
        }

    }
}
