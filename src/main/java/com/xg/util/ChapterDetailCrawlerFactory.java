package com.xg.util;

import com.xg.enums.SiteEnum;
import com.xg.impl.chapter.ChapterDetailCrawler;
import com.xg.interfaces.IChapterDetailCrawler;

/**
 * Created by xg on 2017/9/13.
 */
public final  class ChapterDetailCrawlerFactory {
    /*
    returns the implementation class that implements the IChapterCrawler interface
     */
    private ChapterDetailCrawlerFactory(){};
    public static IChapterDetailCrawler getChapterDetailCrawler(String url){
        SiteEnum siteEnum=SiteEnum.getEnumByUrl(url);
        IChapterDetailCrawler chapterDetailCrawler=null;
        switch (siteEnum){
            case XS:
            case WX:
            case BXWX:
                chapterDetailCrawler=new ChapterDetailCrawler();
        }
        return chapterDetailCrawler;
    }

}
