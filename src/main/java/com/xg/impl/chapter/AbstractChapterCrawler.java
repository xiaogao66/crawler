package com.xg.impl.chapter;

import com.xg.entitys.Chapter;
import com.xg.enums.SiteEnum;
import com.xg.impl.AbstractCrawler;
import com.xg.interfaces.IChapterCrawler;
import com.xg.util.CrawlerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xg on 2017/9/8.
 */
public class AbstractChapterCrawler extends AbstractCrawler implements IChapterCrawler {


    public List<Chapter> getChapter(String url) {
        try {
            String result = crawl(url);
            //System.out.println(result);
           Document document= Jsoup.parse(result);
           document.setBaseUri(url); //automatically match absolute and relative paths
          //Elements es= document.select("#list dd a");


            Elements es= document.select(CrawlerUtil.getParseText(SiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
          List<Chapter> list=new ArrayList<Chapter>();
          for (Element e:es){
              //System.out.println(e);
              Chapter chapter=new Chapter();
              chapter.setTitile(e.text());
              //chapter.setUrl("http://www.xs.la/"+e.attr("href"));
              chapter.setUrl(e.absUrl("href"));
              list.add(chapter);
          }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
