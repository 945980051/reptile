package com.sunshine.reptile.utils;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 
 * @author 张梓枫
 * @Description xml与对象映射
 * @date:   2019年1月2日 上午10:01:00
 */
public abstract class XmlUtils {

    /**
     * 将javaBean序列化为xml
     * 
     * @param clz
     * @param obj
     * @param encoding
     * @return
     */
    public static String toXml(Class<?> clz, Object obj, String encoding) {
        try {
            StringWriter writer = new StringWriter();
            createMarshaller(clz, encoding).marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将Xml字符串序列化为javaBean
     * 
     * @param clz
     * @param xml
     * @return
     */
    public static Object fromXml(Class<?> clz, String xml) {
        try {
            StringReader reader = new StringReader(xml);
            JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { clz });
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将xml文件转为对象
     * 
     * @param clz
     * @param xmlPath
     *            xml文件路径
     * @return
     */
    public static Object xmlToBean(Class<?> clz, String xmlPath) {
        try {
            JAXBContext context = JAXBContext.newInstance(clz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object object = unmarshaller.unmarshal(new File(xmlPath));
            return object;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

    }

    private static Marshaller createMarshaller(Class<?> clz, String encoding) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(new Class[] { clz });

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty("jaxb.formatted.output", Boolean.TRUE);
            if (ObjectUtils.isNotEmpty(encoding)) {
                marshaller.setProperty("jaxb.encoding", encoding);
            }
            return marshaller;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
