package com.xg.impl.novel;

import com.xg.entitys.Novel;
import com.xg.enums.SiteEnum;
import com.xg.util.CrawlerUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xg on 2017/9/20.
 */
public class BxwxNovelCrawler extends AbstractNovelCrawler {
    public List<Novel> getsNovel(String url,Integer maxTryTime) {

        List<Novel> novelList=new ArrayList<Novel>();
        try {
            Elements trs = super.getsTr(url, 2);
            for (int index = 1, size = trs.size(); index < size; index++) {
                Element tr = trs.get(index);
                Elements tds = tr.getElementsByTag("td");
                Novel novel = new Novel();
                novel.setName(tds.first().text());
                String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href").replace(".htm", "/").replace("/binfo/", "/b/");
                novel.setUrl(novelUrl);
                novel.setLastUpdateChapter(tds.get(1).text());
                novel.setLastUpdateChapterUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                novel.setAuthor(tds.get(2).text());
                novel.setLastUpdateTime(CrawlerUtil.getDate(tds.get(4).text(), "yy-MM-dd"));//2017-9-20 yyyy-MM-dd
                novel.setStatus(CrawlerUtil.getNovelStatus(tds.get(5).text()));
                novel.setPlatformId(SiteEnum.getEnumByUrl(url).getId());
                novelList.add(novel);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return novelList;
    }
}
