package com.lxp.pro.crawing;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ControllerImg {

    private static int count = 0;

    //将地址传入解析器
    public static Document getDom(String URL){
        try {
            URL url = new URL(URL);
            Connection connection = Jsoup.connect(URL);
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
    //获得下一页，然后递归调用地址
    public  void getNextPageInfor(Document document){
        //选择到下一页标签的class名   .bk
        String URL = null;
        Elements pageElement = document.select(".postlist").select(".nav-links").select("a");
        for (Element element : pageElement) {
            System.out.println(element.attr("href"));

            if("下一页»".equals(element.text())) {
                URL = element.attr("href");
                System.err.println(URL);
            }
        }

        if(URL==null){
            return;
        }

        //放入下一页
        Document nextPage = getDom(URL);
        List<String> list = getPageInfor(nextPage);
        System.out.println("---------------"+(++count)+"-------------");
        for (String jobBean : list) {
            System.out.println(jobBean);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        getNextPageInfor(nextPage);
    }

    public static List<String> getPageInfor(Document document){
        Random r = new Random();
        //伪装的游览器，这里越多越好
        String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
                "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};
        int i = r.nextInt(14);

        //输入输入流
        FileOutputStream outputStream  = null;
        InputStream inputStream = null;
        BufferedInputStream bis = null;


        List<String> list = new ArrayList<String>();
        Elements elements = document.select("#pins li");
        elements.remove(0);
        for (Element element : elements) {
            Elements needElements = element.select("a");

            //选取图片
            String messageUrl =  needElements.get(0).select("img[src]").attr("data-original");

            //每张图片生成UUID
            String outImage = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";

            try {
                //创建链接
                URL imgUrl = new URL(messageUrl);
                HttpURLConnection connection = (HttpURLConnection) imgUrl.openConnection();

                //伪装请求，绕过反爬
                connection.setRequestProperty("User-Agent", ua[i]);
                //添加来源
                connection.setRequestProperty("Referer","http://www.mzitu.com/");
                //添加地址解析
                connection.setRequestProperty("Host","i.meizitu.net");


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                //获取输入流
                inputStream = connection.getInputStream();

                //将输入流信息放入缓冲提升读写速度
                bis = new BufferedInputStream(inputStream);

                //获取字节数;
                byte[] buf = new byte[1024];

                //生产文件
                outputStream = new FileOutputStream("D:\\meizitu\\"+outImage); int size = 0;
                while ((size =bis.read(buf)) != -1) {
                    outputStream.write(buf, 0, size);
                }

                //刷新文件流
                outputStream.flush();
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    if(outputStream != null){
                        outputStream.close();
                    }
                    if(bis != null) {
                        bis.close();
                    }
                    if(inputStream != null) {
                        inputStream.close();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }

            //list.add(jobBean);
        }
        return list;
    }





    public static void main(String[] args){
        ControllerImg c = new ControllerImg();

        String URL = "https://www.mzitu.com/xinggan/";
        Document document = getDom(URL);
        List<String> list = getPageInfor(document);
        System.out.println("---------------"+(++count)+"-------------");
        for (String jobBean : list) {
            System.out.println(jobBean);

        }
        c.getNextPageInfor(document);
    }

}