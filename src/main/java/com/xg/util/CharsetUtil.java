package com.xg.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.net.URL;
import java.util.Iterator;
import java.util.regex.*;


/**
 * Created by xg on 2017/9/12.
 */
public class CharsetUtil {
    //get charset
    public static String getCharset(String url) throws Exception {
        URL u = new URL(url);
        Document doc = Jsoup.parse(u, 6 * 1000);
        Elements eles = doc.select("meta[http-equiv=Content-Type]");
        Iterator<Element> itor;
        itor = eles.iterator();
        while (itor.hasNext())
            return matchCharset(itor.next().toString());
        return "gb2312";
    }

    //get page  charset
    public static String matchCharset(String content) {
        String chs = "gb2312";
        Pattern p = Pattern.compile("(?<=charset=)(.+)(?=\")");
        Matcher m = p.matcher(content);
        if (m.find())
            return m.group();
        return chs;
    }

}
