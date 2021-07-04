package com.lee.bigdata.controller.Weather;

import java.io.IOException;
import java.io.InputStream;
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
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.lee.bigdata.model.CommonResult;

@Controller
@RequestMapping("/weather")
public class WeatherTest {


    @GetMapping("/today")
    public CommonResult getTodayWeather() {
        Map<String, String> map = Maps.newHashMap();

        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(getReponse("http://wthrcdn.etouch.cn/WeatherApi?city=%E9%95%BF%E6%98%A5"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            map.put("date", weatherNode.elementTextTrim("date"));
            map.put("high", weatherNode.elementTextTrim("high"));
            map.put("low", weatherNode.elementTextTrim("low"));
            Iterator weatherNodeChild = weatherNode.elementIterator("day");
            Element dayNode = (Element) weatherNodeChild.next();

            map.put("type", dayNode.elementTextTrim("type"));
            map.put("fengxiang", dayNode.elementTextTrim("fengxiang"));
            map.put("fengli", dayNode.elementTextTrim("fengli"));
        }
        Iterator zhishus = root.elementIterator("zhishus");
        while (zhishus.hasNext()) {
            Element zhishusNode = (Element) zhishus.next();
            Iterator zhishu = zhishusNode.elementIterator("zhishu");
            while (zhishu.hasNext()) {
                Element zhishuNode = (Element) zhishu.next();
                map.put("name", zhishuNode.elementTextTrim("name"));
                map.put("value", zhishuNode.elementTextTrim("value"));
                map.put("detail", zhishuNode.elementTextTrim("detail"));
            }
        }

        return new CommonResult(200, JSONArray.toJSONString(map));
    }

    public GZIPInputStream getReponse(String _url) throws ClientProtocolException, IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(_url);
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        InputStream inputs = entity.getContent();
        GZIPInputStream in = new GZIPInputStream(inputs);
        return in;

    }


}
