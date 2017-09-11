package com.xg.interfaces;

import com.xg.entitys.ChapterDetail;

/**
 * Created by xg on 2017/9/10.
 */
public interface IChapterDetailCrawler {
    /**
     *get chapter detail by url
     */
    public ChapterDetail getChapterDetail(String url);
}
