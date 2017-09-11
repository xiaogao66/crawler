package com.xg.interfaces;

import com.xg.entitys.Chapter;

import java.util.List;

/**
 * Created by xg on 2017/9/8.
 */
public interface IChapterCrawler {
    /**
     *get chapter list by url
     */
    public List<Chapter> getChapter(String url);
}
