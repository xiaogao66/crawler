package com.xg.impl;

import com.xg.entitys.Chapter;
import com.xg.enums.SiteEnum;
import com.xg.interfaces.IChapterCrawler;
import com.xg.impl.ChapterCrawler;
import com.xg.interfaces.IChapterDetailCrawler;
import com.xg.util.ChapterUtil;
import org.junit.Test;

import java.util.List;

/**
 * Created by xg on 2017/9/8.
 */
public class TestGetChapter {
    @Test
    public void test() {
        IChapterCrawler crawler = new ChapterCrawler();
        //List<Chapter> chapters= crawler.getChapter("http://www.xs.la/0_5/");
        //List<Chapter> chapters = crawler.getChapter("http://www.23wx.cc/du/83/83293/");
        List<Chapter> chapters = crawler.getChapter("http://www.bxwx9.org/b/70/70093/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter.toString());
        }
    }
    //bxwx
    @Test
    public void testGetBXWXChapter() {
        IChapterCrawler crawler = new BXWXChapterCrawler();
        //List<Chapter> chapters= crawler.getChapter("http://www.xs.la/0_5/");
        //List<Chapter> chapters = crawler.getChapter("http://www.23wx.cc/du/83/83293/");
        List<Chapter> chapters = crawler.getChapter("http://www.bxwx9.org/b/70/70093/");
        for (Chapter chapter : chapters) {
            System.out.println(chapter.toString());
        }
    }


    @Test
    public void testGetSiteRule() {
        ChapterUtil.getParseText(SiteEnum.getEnumByUrl("http://www.23wx.cc/du/83/83293/"));
        System.out.println(ChapterUtil.getParseText(SiteEnum.getEnumByUrl("http://www.23wx.cc/du/83/83293/")));
    }

    @Test
    public void testGetChapterDetail() {
        IChapterDetailCrawler chapterDetailCrawler = new ChapterDetailCrawler();
        //System.out.println(chapterDetailCrawler.getChapterDetail("http://www.23wx.cc/du/103/103839/21481218.html"));
        //System.out.println(chapterDetailCrawler.getChapterDetail("http://www.xs.la/46_46358/2436876.html"));
        System.out.println(chapterDetailCrawler.getChapterDetail("http://www.bxwx9.org/b/70/70093/11969974.html"));
    }
}

