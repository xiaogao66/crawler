package com.xg.util;

import com.xg.enums.SiteEnum;
import com.xg.impl.chapter.BXWXChapterCrawler;
import com.xg.impl.chapter.ChapterCrawler;
import com.xg.interfaces.IChapterCrawler;

/**
 * Created by xg on 2017/9/13.
 */
public final class ChapterCrawlerFactory {
    /*
    *returns the implementation class that implements the IChapterCrawler interface

     */
    private  ChapterCrawlerFactory(){};

    public static IChapterCrawler getChapterCrawler(String url){
        SiteEnum siteEnum=SiteEnum.getEnumByUrl(url);
        IChapterCrawler chapterCrawler=null;
        switch (siteEnum){
            case BXWX:
                chapterCrawler=new BXWXChapterCrawler();break;
            case WX:
            case XS:
                chapterCrawler=new ChapterCrawler();
        }
       return chapterCrawler;
    }



}
