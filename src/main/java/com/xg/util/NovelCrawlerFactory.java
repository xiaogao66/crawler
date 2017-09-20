package com.xg.util;

import com.xg.enums.SiteEnum;
import com.xg.impl.novel.BxwxNovelCrawler;
import com.xg.impl.novel.KanShuZhongNovelCrawler;
import com.xg.interfaces.INovelCrawler;

/**
 * Created by xg on 2017/9/20.
 */
public class NovelCrawlerFactory {
        private NovelCrawlerFactory() {}
        public static INovelCrawler getNovelSpider(String url) {
            SiteEnum novelSiteEnum = SiteEnum.getEnumByUrl(url);
            switch (novelSiteEnum) {
                case BXWX : return new BxwxNovelCrawler();
                case KSZ : return new KanShuZhongNovelCrawler();
                default : throw new RuntimeException(url + "unsupport");
            }
        }

}
