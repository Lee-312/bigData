package com.lee.bigdata.controller.xzqh;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lee.bigdata.model.District;

import cn.hutool.http.HttpUtil;

public class XzqhTest {

    final static String url = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/";

    public static void main1(String[] args) {
        //String result = httpGet(url);
        //System.out.println(result);
        //Map<String, String> districtMap = parseDocument(result);

        Map<String, String> jiangsu = Maps.newHashMap();
        jiangsu.put("江苏省", "32.html");

        List<District> list = Lists.newArrayList();
        for (Map.Entry<String, String> entry : jiangsu.entrySet()) {
            System.out.println(url + entry.getValue());
            String resultCity = httpGet(url + entry.getValue());
            Map<String, String> cityMap = parseDocument(resultCity);
            for (Map.Entry<String, String> city : cityMap.entrySet()) {
                System.out.println(url + city.getValue());
                String resultRegion = httpGet(url + city.getValue());
                Map<String, String> regionMap = parseDocument(resultRegion);
                for (Map.Entry<String, String> region : regionMap.entrySet()) {
                    String resultStreet = httpGet(url + region.getValue());
                    Map<String, String> streetMap = parseDocument(resultStreet);
                    for (Map.Entry<String, String> street : streetMap.entrySet()) {
                        District district = new District();
                        district.setDistrict(entry.getKey());
                        district.setCity(city.getKey());
                        district.setRegion(region.getKey());
                        district.setStreet(street.getKey());
                        String resultCommunity = HttpUtil.get(url + city.getValue(), 100);
                        Map<String, String> communityMap = parseDocument(resultCommunity);
                        for (Map.Entry<String, String> community : communityMap.entrySet()) {
                            district.setCommunity(community.getKey());
                            list.add(district);
                        }
                    }
                }
            }
        }
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        String result = httpGet("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2020/32/3207.html");
        System.out.println(result);

    }

    private static Map<String, String> parseDocument(String xmlString) {
        Map<String, String> map = Maps.newHashMap();
        try {
            int i = 0;
            if (xmlString.contains("href")) {
                String[] split = xmlString.split("<td>");
                for (String s : split) {
                    if (s.contains("href") && s.contains("<a")) {
                        String[] split1 = s.split("'");
                        String href = split1[1];
                        StringBuilder cityName = new StringBuilder();
                        String regex = "([\u4e00-\u9fa5]+)";
                        Matcher matcher = Pattern.compile(regex).matcher(s);
                        while (matcher.find()) {
                            cityName.append(matcher.group(0));
                        }
                        if (cityName.length() != 0) {
                            map.put(cityName.toString(), href);
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    private static String httpGet(String url) {
        int count = 5;
        while (count >= 0) {
            try {
                Map<String, Object> paramMap = Maps.newHashMap();
                paramMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
                return HttpUtil.get(url, 5000);
            } catch (Exception e) {
                count--;
                e.printStackTrace();
            }
        }
        return "";
    }

}
