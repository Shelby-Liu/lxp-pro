package com.lxp.pro.spider.download;

import com.lxp.pro.spider.utils.StaticValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *   将种子对应的内容下载下来
 */


public class WebDownload {
//    public static String getHtmlSource(String url, String charset) throws IOException {
//        Connection conn = Jsoup.connect(url);
//        Document doc = conn.get();
//        System.out.println(doc.outerHtml());
//        String text = doc.text();
//        return text;
//    }
//
//    public static void main(String[] args) throws IOException {
//        String url = "https://www.mzitu.com";
//        System.out.println(getHtmlSource(url,StaticValues.DEFAULT_CHARSET));
//    }
    public static Document getDom(String url){  //获取网页dom
        try {
            URL urlobj = new URL(url);
            Connection connection = Jsoup.connect(url);
            Document document = connection.get();
            return document;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public static void getInnerUrl(Document document){

        Map<String,String> map = new HashMap<>();



        Elements elements = document.select(".main2").select("a");
        for (Element element : elements) {
            String title = element.text();

            String innerUrl ="https://www.xzbu.com" + element.attr("href");
            System.out.println(title+"-->"+innerUrl);
            map.put(title,innerUrl);
            //System.out.println(element.text()+"-->"+element.attr("href"));
        }
//        System.out.println(map.toString());
//        String str = map.get("农业生产中作物栽培技术应用探讨");
//        System.out.println(str);
        for(Map.Entry<String,String> enyry: map.entrySet()){
            getInnerText(enyry.getValue());
        }
    }

    public static void getInnerText(String url){
        List<String> list = new ArrayList<>();
        Document document = getDom(url);
        Elements elements = document.select(".article_leftBox").select("p");
        for (Element element : elements) {
            System.out.println(element.text());
            list.add(element.text());
        }
    }

    public static void main(String[] args) {
        String url="https://www.xzbu.com/8/";
        getInnerUrl(getDom(url));
    }
}