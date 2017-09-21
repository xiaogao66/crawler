package com.xg.impl.novel;

import com.xg.entitys.Novel;
import com.xg.enums.SiteEnum;
import com.xg.impl.AbstractCrawler;
import com.xg.interfaces.INovelCrawler;
import com.xg.util.CrawlerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Map;

/**
 * Created by xg on 2017/9/20.
 */
public abstract class AbstractNovelCrawler extends AbstractCrawler implements INovelCrawler {

    public Elements getsTr(String url) throws Exception {
        return getsTr(url, INovelCrawler.MAX_TRY_TIMES);

    }

    protected Elements getsTr(String url, Integer maxTryTime) throws Exception {
        maxTryTime = maxTryTime == null ? INovelCrawler.MAX_TRY_TIMES : maxTryTime;
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
                return elements;
            } catch (Exception e) {

            }

        }
        throw new RuntimeException(url + ",tried " + maxTryTime + "times and download failed");
    }

}
