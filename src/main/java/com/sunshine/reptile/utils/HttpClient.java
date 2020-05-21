package com.sunshine.reptile.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月28日 12:24
 */
public class HttpClient {
    private static String privateKey = "route=76fe8badbf970cea48a83fee22e3ae12; __utma=129582553.1644258210.1587885582.1587885582.1587885582.1; __utmb=129582553.2.10.1587885582; __utmc=129582553; __utmz=129582553.1587885582.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __utmt=1; Hm_lvt_d1780dad16c917088dd01980f5a2cfa7=1587885582; Hm_lpvt_d1780dad16c917088dd01980f5a2cfa7=1587885608; DRUGSSESSIONID=B8E9A68BCE28113D01B59009C982575C-n2; __asc=e836f496171b55b2e7619deb748; __auc=e836f496171b55b2e7619deb748; _ga=GA1.2.1876424587.1587885586; _gid=GA1.2.26952698.1587885586; _gat=1; JUTE_BBS_DATA=6bd6a7d1b3398ef651ee6c408c966552417bd9e6288edb0ff0afbb2232a8533658a45d0166b97d550f4396e09cd6c7667d425dda615e7fe7a9ca70a506c13539dad8269da28f8aa8874b339491d5f113; CLASS_CASTGC=68a9d14a532a87621b9a407aa8b18b8f0835574186080d2af30cadf088cbec3e360b3a39a277877a8e992b6ffa508e28cc41637391d3f93e8875c5df53e3bc61754d2faaa033173148c3f73b40d1bb840418ccdfec482efa75aeb7ccec6eb1c5084264f8ec8d392e28ec86d447054a6fe9fbd69d80c76503b5207238e71c7acd4c97c37e360512796f1d01364fe638df9e81c5bc2da2e06d5368bf0236400527991ea5fa97be90fc2a4821f54d9039371320cca606bf10e783f10d4d38341a4d16b2c024df91b8c53623c5faf07c99443cedd05d16d1e52ccda56f0a8048f0ce0710d8bfb76478741f589cd5955bd019d8047c248a302c44fa63fa8b0e045654";

    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    public static String doPost(String httpUrl, String param,String str) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Referer", "http://drugs.dxy.cn/drug/"+str+"/detail.htm");
            connection.setRequestProperty("Cache-Control", "no-cacheUser-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0");
            connection.setRequestProperty("Cookie", privateKey);
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(param.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }
}
