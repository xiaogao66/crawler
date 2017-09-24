package com.xg.impl.novel;

import com.xg.entitys.Novel;
import com.xg.enums.SiteEnum;
import com.xg.util.CrawlerUtil;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xg on 2017/9/20.
 */
public class KanShuZhongNovelCrawler  extends  AbstractNovelCrawler{
    public List<Novel> getsNovel(String url,Integer maxTryTime) {
        List<Novel> novelList=new ArrayList<Novel>();
        try {
            Elements trs =super.getsTr(url);
            for (int index=1,size=trs.size()-1;index<size;index++){
                Elements tds = trs.get(index).getElementsByTag("td");
                //				for
                // (Element td : tds) {
                //					System.out.println(td);
                // 				}
                Novel novel=new Novel();
                novel.setType(tds.first().text());
                novel.setName(tds.get(1).text());
                novel.setUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
                novel.setLastUpdateChapter(tds.get(2).text());
                novel.setLastUpdateChapterUrl(tds.get(2).getElementsByTag("a").first().absUrl("href"));
                novel.setAuthor(tds.get(3).text());
                novel.setLastUpdateTime(CrawlerUtil.getDate(tds.get(4).text(), "MM-dd"));
                novel.setStatus(CrawlerUtil.getNovelStatus(tds.get(5).text()));
                novel.setPlatformId(SiteEnum.getEnumByUrl(url).getId());
                novelList.add(novel);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return novelList;
    }
}
