package com.xg.impl;

import com.xg.entitys.Chapter;
import com.xg.enums.SiteEnum;
import com.xg.interfaces.IChapterCrawler;
import com.xg.impl.ChapterCrawler;
import com.xg.util.ChapterUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */
public class TestGetChapter {
    @Test
    public void test() {
        IChapterCrawler crawler=new ChapterCrawler();
        //List<Chapter> chapters= crawler.getChapter("http://www.xs.la/0_5/");
        List<Chapter> chapters= crawler.getChapter("http://www.23wx.cc/du/83/83293/");
        for (Chapter chapter:chapters) {
            System.out.println( chapter.toString());
        }
    }

    @Test
    public void testGetSiteRule(){
        ChapterUtil.getParseText(SiteEnum.getEnumByUrl("http://www.23wx.cc/du/83/83293/"));
        System.out.println( ChapterUtil.getParseText(SiteEnum.getEnumByUrl("http://www.23wx.cc/du/83/83293/")));
    }
}

