package com.xg.interfaces;

import com.xg.entitys.Chapter;

import java.util.List;

/**
 * Created by xg on 2017/9/8.
 */
public interface IChapterCrawler {
    /**
     * 根据url获取完整的章节列表
     *
     */
    public List<Chapter> getChapter(String url);
}
