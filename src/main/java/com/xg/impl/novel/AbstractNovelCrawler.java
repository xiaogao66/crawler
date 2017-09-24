package com.xg.impl.novel;

import com.xg.entitys.Novel;
import com.xg.enums.SiteEnum;
import com.xg.impl.AbstractCrawler;
import com.xg.interfaces.INovelCrawler;
import com.xg.util.CrawlerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xg on 2017/9/20.
 */
public abstract class AbstractNovelCrawler extends AbstractCrawler implements INovelCrawler {

    protected Element nextPageElement;
    protected String nextPage;

    public Elements getsTr(String url) throws Exception {
        return getsTr(url, INovelCrawler.MAX_TRY_TIME);

    }

    protected Elements getsTr(String url, Integer maxTryTime) throws Exception {
        maxTryTime = maxTryTime == null ? INovelCrawler.MAX_TRY_TIME : maxTryTime;
        Elements elements = null;
        for (int i = 0; i < maxTryTime; i++) {
            try {
                String result = super.crawl(url);
                Map<String, String> context = CrawlerUtil.getParseText(SiteEnum.getEnumByUrl(url));
                String novelSelector = context.get("novel-selector");
                if (novelSelector == null)
                    throw new RuntimeException(SiteEnum.getEnumByUrl(url).getUrl() + ",url=" + url + "unsupported novel list");
                Document document = Jsoup.parse(result);
                document.setBaseUri(url);
                elements = document.select(novelSelector);
                String nextPageSelector = context.get("novel-next-page-selector");
                if (nextPageSelector != null) {
                    Elements nextPageElements = document.select(nextPageSelector);
                    nextPageElement = nextPageElements == null ? null : nextPageElements.first();

                    if (nextPageElement != null) {
                        nextPage = nextPageElement.absUrl("href");
                    } else {
                        nextPage = "";
                    }
                }

                return elements;
            } catch (Exception e) {

            }

        }
        throw new RuntimeException(url + ",tried " + maxTryTime + "times and download failed");
    }

    public boolean hasNext() {

        return !nextPage.isEmpty();
}

    public String next() {
        return nextPage;
    }

    public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTime) {
        nextPage = firstPage;
        return new NovelIterator(maxTryTime);
    }

/*    private class NovelIterator implements Iterator<List<Novel>> {

    }*/

   private class NovelIterator implements  Iterator<List<Novel>>{
       private Integer maxTryTime;
       public NovelIterator(Integer maxTryTime) {
           this.maxTryTime = maxTryTime;
       }

       @Override
       public boolean hasNext() {
           return AbstractNovelCrawler.this.hasNext();
       }

       @Override
       public List<Novel> next() {
           List<Novel> novels = getsNovel(nextPage, maxTryTime);
           return novels;
       }


       @Override
       public void remove() {

       }
   }

}
