package com.xg.impl;

import com.xg.config.Configuration;
import com.xg.entitys.Chapter;
import com.xg.enums.SiteEnum;
import com.xg.interfaces.IChapterCrawler;
import com.xg.interfaces.IChapterDetailCrawler;
import com.xg.interfaces.INovelDownload;
import com.xg.util.ChapterDetailCrawlerFactory;
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
        //System.out.println(chapterDetailCrawler.getChapterDetail("http://www.bxwx9.org/b/70/70093/11969974.html"));
        System.out.println(ChapterDetailCrawlerFactory.getChapterDetailCrawler("http://www.bxwx9.org/b/70/70093/11969974.html").getChapterDetail("http://www.bxwx9.org/b/70/70093/11969974.html"));

    }


    @Test
    public void testDownload() {
        INovelDownload download = new NovelDownload();
        Configuration config = new Configuration();
        config.setPath("D:/1");
        config.setSize(50);
        //download.download("http://www.bxwx9.org/b/70/70093/", config);
        download.download("http://www.23wx.cc/du/41/41316/", config);
    }

    @Test
    public void testMultFileMerge(){
        ChapterUtil.multiFileMerge("D:/1",null,true);
    }
}

