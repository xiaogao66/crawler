package com.xg.util;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import com.xg.enums.SiteEnum;
import javafx.scene.shape.ClosePath;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xg on 2017/9/9.
 */
public final class ChapterUtil {
    private static final Map<SiteEnum, Map<String, String>> CHAPTER_MAP = new HashMap<SiteEnum, Map<String, String>>();

    static {
         init();
    }
    public static void init(){
        SAXReader reader= new SAXReader();
        try {
            //Document doc =sr.read("./site.xml");
            Document doc =reader.read("src/main/java/com/xg/config/site.xml");

            Element element=doc.getRootElement();
            List<Element> sites= element.elements("site");
            for (Element e:sites
                 ) {
                List<Element> secondItem=e.elements();
                Map<String,String> secondMap = new HashMap<String, String>();
                for (Element se:secondItem
                     ) {
                    String name=se.getName();
                    String text=se.getText();
                    secondMap.put(name,text);
                }
                CHAPTER_MAP.put(SiteEnum.getEnumByUrl(secondMap.get("url")), secondMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    //get parse role
    public  static Map<String,String> getParseText( SiteEnum siteEnum){
        return CHAPTER_MAP.get(siteEnum);
    }
}
