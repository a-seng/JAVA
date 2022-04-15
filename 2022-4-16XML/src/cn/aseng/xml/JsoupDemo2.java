package cn.aseng.xml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class JsoupDemo2 {
    public static void main(String[] args) throws IOException {
        //2.1获取student.xml的path
        String path = JsoupDemo1.class.getClassLoader().getResource("student.xml").getPath();
        //2.2解析xml文档，加载文档进内存，获取dom树--->Document
        System.out.println(path);
        Document document = Jsoup.parse(new File("D:\\Program Files (x86)\\Code\\JAVA\\2022-4-16XML\\src\\student.xml"), "utf-8");
        //3.获取元素对象 Element
//        Elements student = document.getElementsByTag("student");
////        System.out.println(student);d
//        Elements name = document.getElementsByTag("name");
//        System.out.println(name.size());
//        System.out.println("---------------");
//
//        Element element = document.getElementsByTag("student").get(0);
//        Elements name1 = element.getElementsByTag("name");
//        System.out.println(name1.size());
//

        Elements name = document.select("name");
        System.out.println(name);

        Elements select = document.select("#itcast");
        System.out.println(select);

    }
}
