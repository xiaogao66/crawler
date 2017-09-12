package com.xg.impl;

import com.xg.entitys.ChapterDetail;
import com.xg.enums.SiteEnum;
import com.xg.interfaces.IChapterDetailCrawler;
import com.xg.util.ChapterUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * Created by xg on 2017/9/10.
 */
public class AbstractChapterDetailCrawler extends AbstractCrawler implements IChapterDetailCrawler {
    public ChapterDetail getChapterDetail(String url) {
        try {

            String result = super.crawl(url);
            result = result.replace("&nbsp;", "  ").replace("<br />", "\n").replace("<br/>", "\n");
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            Map<String, String> contexts = ChapterUtil.getParseText(SiteEnum.getEnumByUrl(url));
            ChapterDetail chapterDetail = new ChapterDetail();
            //get title
            String titleSelector = contexts.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split("\\,");
            splits = this.parseSelector(splits);
            chapterDetail.setTitle(document.select(splits[0]).get(Integer.parseInt(splits[1])).text());
            //get content
            String contentSelector = contexts.get("chapter-detail-content-selector");
            chapterDetail.setContent(document.select(contentSelector).first().text());
            //get prev page address
            String prevSelector = contexts.get("chapter-detail-prev-selector");
            splits = prevSelector.split("\\,");
            splits = this.parseSelector(splits);
            chapterDetail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).attr("abs:href"));
            //get next page address
            String nextSelector = contexts.get("chapter-detail-next-selector");
            splits = nextSelector.split("\\,");
            splits = this.parseSelector(splits);
            chapterDetail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
            return chapterDetail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] parseSelector(String[] splits) {
        String[] subSpilts = new String[2];
        if (splits.length == 1) {
            subSpilts[0] = splits[0];
            subSpilts[1] ="0";
            return subSpilts;
        } else {
            return splits;
        }

    }
}
