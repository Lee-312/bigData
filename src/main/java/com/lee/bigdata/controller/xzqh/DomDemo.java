package com.lee.bigdata.controller.xzqh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DomDemo {


    //用Element方式
    public static void element(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            NodeList childNodes = element.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    //获取节点
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    //获取节点值
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
        }
    }

    public static void node(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            NodeList childNodes = node.getChildNodes();
            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    System.out.print(childNodes.item(j).getNodeName() + ":");
                    System.out.println(childNodes.item(j).getFirstChild().getNodeValue());
                }
            }
        }
    }

    public static void main(String[] args) {
       /* //1.创建DocumentBuilderFactory对象
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //2.创建DocumentBuilder对象
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse("C:\\Users\\lee\\Desktop\\2.txt");
            NodeList sList = d.getElementsByTagName("student");
            //element(sList);
            node(sList);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try (FileInputStream fis = new FileInputStream("C:\\Users\\lee\\Desktop\\1.txt");
             InputStreamReader reader = new InputStreamReader(fis, "GBK"); //最后的"GBK"根据文件属性而定，如果不行，改成"UTF-8"试试
             BufferedReader stream = new BufferedReader(reader);) {
            int i = 0;
            boolean flag = false;
            while ((stream.readLine()) != null) {
                String str = stream.readLine();
                if (str.contains("href")) {
                    String[] split = str.split("<td>");
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
                            System.out.println(href + ":" + cityName.toString());
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
