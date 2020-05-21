package com.sunshine.reptile;

import com.sunshine.reptile.utils.HL7ToXmlConverter;
import org.dom4j.Document;
import org.dom4j.Node;
import org.junit.Test;

import java.util.List;

/**
 * @author : zhengwenyao
 * @Description: TODO
 * @date Date : 2020年04月24日 12:27
 */
public class Hl7 extends HL7ToXmlConverter {
    @Test
    public void t1(){
        String myHL7string="MSH|^~\\&|455755610_0100||0200||20110624160404|000|QRY^A19^QRY_A19|0123456001|P|2.6\nQRD|||||||||0001^郭靖^体检号^EQ^AND~0002^东一区^病区号^EQ^AND\nQRF||20110627|20110803";
        Document document = HL7ToXmlConverter.ConvertToXmlObject(myHL7string);
//获取事件
        String eventName = HL7ToXmlConverter.GetText(document, "MSH/MSH.9/MSH.9.3");
        System.out.println("eventName:"+eventName);
// List nodeValue = document.selectNodes("MSH.1");
        String nodeValue = document.selectSingleNode("HL7Message/MSH/MSH.1").getText();
        String nodeValue2 = document.selectSingleNode("HL7Message/MSH/MSH.3").getText();
// DocumentElement.SelectNodes(path);
        System.out.println(nodeValue+":"+nodeValue2);
        String value = HL7ToXmlConverter.GetText(document, "QRD/QRD.9/QRD.9.1",0);
        String value1 = HL7ToXmlConverter.GetText(document, "QRD/QRD.9/QRD.9.1",1);
        String value2 = HL7ToXmlConverter.GetText(document, "QRD/QRD.9/QRD.9.1");
        System.out.println(value+":"+value1+":"+value2);
        List<Node> list = HL7ToXmlConverter.GetTexts(document, "QRD/QRD.9/QRD.9.1");
        for(Node node : list)
        {
            System.out.println(":"+node.getText());
        }
        System.out.println(HL7ToXmlConverter.ConvertToXml(myHL7string));
    }
}
