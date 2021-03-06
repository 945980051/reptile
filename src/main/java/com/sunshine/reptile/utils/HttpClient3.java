package com.sunshine.reptile.utils;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年03月28日 12:25
 */
public class HttpClient3 {
    public static String doGet(String url) {
        // 输入流
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
        // 创建httpClient实例
        HttpClient httpClient = new HttpClient();
        // 设置http连接主机服务超时时间：15000毫秒
        // 先获取连接管理器对象，再获取参数对象,再进行参数的赋值
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(1500000);
        // 创建一个Get方法实例对象
        GetMethod getMethod = new GetMethod(url);
        // 设置get请求超时为60000毫秒
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 6000000);
        // 设置请求重试机制，默认重试次数：3次，参数设置为true，重试机制可用，false相反
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, true));
        getMethod.setRequestHeader("Cookie","_hitb_key=SFMyNTY.g3QAAAABbQAAAAR1c2VydAAAAANkAAVsb2dpbmQABHRydWVkAA91cGRhdGVfcGFzc3dvcmRkAAVmYWxzZWQACHVzZXJJbmZvdAAAACBkAAVhZG1pbmQABWZhbHNlZAACYnBhZGQAC2NsaXBhbG1faWNkbQAAAAM2LjBkAAtjbGlwYWxtX21kY2QAA25pbGQAD2NsaXBhbG1fdmVyc2lvbmQAA25pbGQADGNsaXBhbG1feWVhcm0AAAAEMjAxOWQACmRlcGFydG1lbnRkAANuaWxkAAVlbWFpbG0AAAAQOTQ1OTgwMDUxQHFxLmNvbWQABmdlbmRlcmQAA25pbGQACmhlYWRpbWd1cmxtAAAAAGQAAmlkYWNkAAppbWFnZV9uYW1lbQAAAABkAAtpbnNlcnRlZF9hdHQAAAANZAAKX19zdHJ1Y3RfX2QAD0VsaXhpci5EYXRlVGltZWQACGNhbGVuZGFyZAATRWxpeGlyLkNhbGVuZGFyLklTT2QAA2RheWEdZAAEaG91cmEGZAALbWljcm9zZWNvbmRoAmEAYQBkAAZtaW51dGVhF2QABW1vbnRoYQpkAAZzZWNvbmRhI2QACnN0ZF9vZmZzZXRhAGQACXRpbWVfem9uZW0AAAAHRXRjL1VUQ2QACnV0Y19vZmZzZXRhAGQABHllYXJiAAAH42QACXpvbmVfYWJicm0AAAADVVRDZAARaXN1cGRhdGVfcGFzc3dvcmRkAAR0cnVlZAADbWRjamQABm1vYmlsZWQABWZhbHNlZAALbW9iaWxlX2luZm9kAANuaWxkAAptb2JpbGVfbWRjZAADbmlsZAAEbmFtZWQAA25pbGQAA29yZ2QAA25pbGQACnJ1bGVfb3JkZXJkAAVmYWxzZWQACnN1YmplY3RfYnBhAGQAA3RlbGQAA25pbGQABXRpdGxlZAADbmlsZAAEdHlwZW0AAAAM5Liq5Lq655So5oi3ZAAKdXBkYXRlZF9hdHQAAAANZAAKX19zdHJ1Y3RfX2QAD0VsaXhpci5EYXRlVGltZWQACGNhbGVuZGFyZAATRWxpeGlyLkNhbGVuZGFyLklTT2QAA2RheWEdZAAEaG91cmEGZAALbWljcm9zZWNvbmRoAmEAYQBkAAZtaW51dGVhF2QABW1vbnRoYQpkAAZzZWNvbmRhI2QACnN0ZF9vZmZzZXRhAGQACXRpbWVfem9uZW0AAAAHRXRjL1VUQ2QACnV0Y19vZmZzZXRhAGQABHllYXJiAAAH42QACXpvbmVfYWJicm0AAAADVVRDZAAIdXNlcm5hbWVtAAAACTk0NTk4MDA1MWQAB3ZlcnNpb25sAAAAAW0AAAACQkpqZAADd2ViZAAFZmFsc2VkAAp3ZWJfd2VjaGF0bQAAAABkABN3ZWJfd2VjaGF0X25pY2tuYW1lbQAAAABkAAN3dDRtAAAAAkJK.6HcHScVmX4qgu3lgD0YiSzeIJBQqYRvMPG5lpx9L74Y");
        try {
            // 执行Get方法
            int statusCode = httpClient.executeMethod(getMethod);
            // 判断返回码
            if (statusCode != HttpStatus.SC_OK) {
                // 如果状态码返回的不是ok,说明失败了,打印错误信息
                System.err.println("Method faild: " + getMethod.getStatusLine());
            } else {
                // 通过getMethod实例，获取远程的一个输入流
                is = getMethod.getResponseBodyAsStream();
                // 包装输入流
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                // 读取封装的输入流
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp).append("\r\n");
                }

                result = sbf.toString();
            }

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
            // 释放连接
            getMethod.releaseConnection();
        }
        return result;
    }

    public static String doPost(String url, Map<String, Object> paramMap) {
        // 获取输入流
        InputStream is = null;
        BufferedReader br = null;
        String result = null;
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(url);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);

        NameValuePair[] nvp = null;
        // 判断参数map集合paramMap是否为空
        if (null != paramMap && paramMap.size() > 0) {// 不为空
            // 创建键值参数对象数组，大小为参数的个数
            nvp = new NameValuePair[paramMap.size()];
            // 循环遍历参数集合map
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();

            int index = 0;
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                // 从mapEntry中获取key和value创建键值对象存放到数组中
                try {
                    nvp[index] = new NameValuePair(mapEntry.getKey(),
                            new String(mapEntry.getValue().toString().getBytes("UTF-8"), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                index++;
            }
        }
        // 判断nvp数组是否为空
        if (null != nvp && nvp.length > 0) {
            // 将参数存放到requestBody对象中
            postMethod.setRequestBody(nvp);
        }
        // 执行POST方法
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            // 判断是否成功
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method faild: " + postMethod.getStatusLine());
            }
            // 获取远程返回的数据
            is = postMethod.getResponseBodyAsStream();
            // 封装输入流
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp).append("\r\n");
            }

            result = sbf.toString();
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
            // 释放连接
            postMethod.releaseConnection();
        }
        return result;
    }
}
