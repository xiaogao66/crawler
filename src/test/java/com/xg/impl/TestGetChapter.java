package com.xg.impl;

import com.xg.config.Configuration;
import com.xg.entitys.Chapter;
import com.xg.entitys.Novel;
import com.xg.enums.SiteEnum;
import com.xg.impl.chapter.BXWXChapterCrawler;
import com.xg.impl.chapter.ChapterCrawler;
import com.xg.impl.chapter.ChapterDetailCrawler;
import com.xg.impl.download.NovelDownload;
import com.xg.interfaces.IChapterCrawler;
import com.xg.interfaces.IChapterDetailCrawler;
import com.xg.interfaces.INovelCrawler;
import com.xg.interfaces.INovelDownload;
import com.xg.util.ChapterDetailCrawlerFactory;
import com.xg.util.CrawlerUtil;
import com.xg.util.NovelCrawlerFactory;
import org.junit.Test;

import java.util.Iterator;
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
        CrawlerUtil.getParseText(SiteEnum.getEnumByUrl("http://www.23wx.cc/du/83/83293/"));
        System.out.println(CrawlerUtil.getParseText(SiteEnum.getEnumByUrl("http://www.23wx.cc/du/83/83293/")));
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
        CrawlerUtil.multiFileMerge("D:/1",null,true);
    }

    @Test
    public void testBXWXGetsNovel() {
        INovelCrawler spider = NovelCrawlerFactory.getNovelSpider("http://www.bxwx9.org/binitialE/0/1.htm");
        List<Novel> novels = spider.getsNovel("http://www.bxwx9.org/binitialE/0/1.htm",3);
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }

    @Test
    public void testKanShuZhongGetsNovel() {
        INovelCrawler spider = NovelCrawlerFactory.getNovelSpider("http://www.kanshuzhong.com/toplist/allvisit/1/");
        List<Novel> novels = spider.getsNovel("http://www.kanshuzhong.com/toplist/allvisit/1/",3);
        for (Novel novel : novels) {
            System.out.println(novel);
        }
    }
    @Test
    public void testKanShuZhongIterator() {
        INovelCrawler crawler = NovelCrawlerFactory.getNovelSpider("http://www.kanshuzhong.com/map/A/1/");
        Iterator<List<Novel>> iterator = crawler.iterator("http://www.kanshuzhong.com/map/A/1/", 10);
        while (iterator.hasNext()) {
            List<Novel> novels = iterator.next();
            System.err.println("URL：" + crawler.next());
//			for (Novel novel : novels) {
//				System.out.println(novel);
//			}
        }
    }
    @Test
    public void testBxwxIterator() {
        INovelCrawler spider = NovelCrawlerFactory.getNovelSpider("http://www.bxwx9.org/binitialO/0/1.htm");
        Iterator<List<Novel>> iterator = spider.iterator("http://www.bxwx9.org/binitialO/0/1.htm", 10);
        while (iterator.hasNext()) {
            List<Novel> novels = iterator.next();
            System.err.println("URL：" + spider.next());
//			for (Novel novel : novels) {
//				System.out.println(novel);
//			}
        }
    }

}

