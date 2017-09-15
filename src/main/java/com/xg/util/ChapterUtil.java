package com.xg.util;

import com.sun.org.apache.bcel.internal.util.ClassPath;
import com.xg.enums.SiteEnum;
import javafx.scene.shape.ClosePath;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;


import java.io.*;
import java.util.*;

/**
 * Created by xg on 2017/9/9.
 */
public final class ChapterUtil {
    private static final Map<SiteEnum, Map<String, String>> CHAPTER_MAP = new HashMap<SiteEnum, Map<String, String>>();

    static {
        init();
    }

    public static void init() {
        SAXReader reader = new SAXReader();
        try {
            //Document doc =sr.read("./site.xml");
            Document doc = reader.read("src/main/java/com/xg/config/site.xml");

            Element element = doc.getRootElement();
            List<Element> sites = element.elements("site");
            for (Element e : sites
                    ) {
                List<Element> secondItem = e.elements();
                Map<String, String> secondMap = new HashMap<String, String>();
                for (Element se : secondItem
                        ) {
                    String name = se.getName();
                    String text = se.getText();
                    secondMap.put(name, text);
                }
                CHAPTER_MAP.put(SiteEnum.getEnumByUrl(secondMap.get("url")), secondMap);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    //get parse role
    public static Map<String, String> getParseText(SiteEnum siteEnum) {
        return CHAPTER_MAP.get(siteEnum);
    }

    //Multi-file merge
    public static void multiFileMerge(String path, String mergeToFile, boolean dleteSubFile) {
        mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
        File[] files = new File(path).listFiles(new FilenameFilter() {
            //Filter  file type
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        //sort
        Arrays.sort(files, new Comparator<File>() {
            public int compare(File o1, File o2) {
                int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
                int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
                if (o1Index > o2Index) {
                    return 1;
                } else if (o1Index == o2Index) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        PrintWriter out=null;

        try {
            out=new PrintWriter(new File(mergeToFile),"UTF-8");
            for (File file:files
                 ) {
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
                String line=null;
                while ((line=bufferedReader.readLine())!=null){
                    out.println(line);
                }
                bufferedReader.close();
                if(dleteSubFile){
                    file.delete();
                }
            }

        } catch (IOException e) {
            throw  new RuntimeException(e);
        }finally {
            out.close();
        }
    }
}
