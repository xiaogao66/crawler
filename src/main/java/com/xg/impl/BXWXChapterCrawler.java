package com.xg.impl;

import com.xg.entitys.Chapter;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xg on 2017/9/12.
 */
public class BXWXChapterCrawler extends AbstractChapterCrawler {
    public List<Chapter> getChapter(String url) {
        List<Chapter> chapters = super.getChapter(url);
        Collections.sort(chapters, new Comparator<Chapter>() {
            public int compare(Chapter o1, Chapter o2) {
                String o1Url = o1.getUrl();
                String o2Url = o2.getUrl();
                String o1Index=o1Url.substring(o1Url.lastIndexOf("/")+1,o1Url.lastIndexOf("."));
                String o2Index=o2Url.substring(o2Url.lastIndexOf("/")+1,o2Url.lastIndexOf("."));
                return o1Index.compareTo(o2Index);
            }
        });
        return chapters;

    }
}
