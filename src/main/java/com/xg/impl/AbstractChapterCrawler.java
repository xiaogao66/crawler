package com.xg.impl;

import com.xg.entitys.Chapter;
import com.xg.enums.SiteEnum;
import com.xg.interfaces.IChapterCrawler;
import com.xg.util.ChapterUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xg on 2017/9/8.
 */
public class AbstractChapterCrawler implements IChapterCrawler {
    protected String crawl(String url) throws Exception {
        CloseableHttpClient httpClient = null;
        String result = null;
        try {
            httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);


            try {
                //result = EntityUtils.toString(httpResponse.getEntity(),"gbk");
                result = EntityUtils.toString(httpResponse.getEntity(), ChapterUtil.getParseText(SiteEnum.getEnumByUrl(url)).get("charset"));

            } finally {
                httpResponse.close();
            }
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (httpClient != null)
                httpClient.close();
        }
    }

    public List<Chapter> getChapter(String url) {
        try {
            String result = crawl(url);
            //System.out.println(result);
           Document document= Jsoup.parse(result);
           document.setBaseUri(url); //automatically match absolute and relative paths
          //Elements es= document.select("#list dd a");
            Elements es= document.select(ChapterUtil.getParseText(SiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
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
