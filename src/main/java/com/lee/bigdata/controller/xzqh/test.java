package com.lee.bigdata.controller.xzqh;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class test {


    public static InputStream getReponse(String _url) throws ClientProtocolException, IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(_url);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        System.out.println(entity.getContent());
        InputStream inputs = entity.getContent();
        return inputs;

    }


    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap();
        SAXReader reader = new SAXReader();
        Document document = reader.read(getReponse("C:\\Users\\lee\\Desktop\\2.txt"));
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        Iterator forecast = root.elementIterator("forecast");
        if (forecast.hasNext()) {
            Element recordEless = (Element) forecast.next();
            Iterator weather = recordEless.elementIterator("weather");
            Element weatherNode = (Element) weather.next();
            System.out.println();
            System.out.println("date" + ":" + weatherNode.elementTextTrim("date"));
            System.out.println("high" + ":" + weatherNode.elementTextTrim("high"));
            System.out.println("low" + ":" + weatherNode.elementTextTrim("low"));
            Iterator weatherNodeChild = weatherNode.elementIterator("day");
            Element dayNode = (Element) weatherNodeChild.next();
            System.out.println();
            System.out.println("type" + ":" + dayNode.elementTextTrim("type"));
            System.out.println("fengxiang" + ":" + dayNode.elementTextTrim("fengxiang"));
            System.out.println("fengli" + ":" + dayNode.elementTextTrim("fengli"));
        }
        Iterator zhishus = root.elementIterator("zhishus");
        while (zhishus.hasNext()) {
            Element zhishusNode = (Element) zhishus.next();
            Iterator zhishu = zhishusNode.elementIterator("zhishu");
            while (zhishu.hasNext()) {
                Element zhishuNode = (Element) zhishu.next();
                System.out.println();
                System.out.println("name" + ":" + zhishuNode.elementTextTrim("name"));
                System.out.println("value" + ":" + zhishuNode.elementTextTrim("value"));
                System.out.println("detail" + ":" + zhishuNode.elementTextTrim("detail"));
            }
        }
    }
}
