package com.xg.interfaces;

import com.xg.entitys.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * Created by xg on 2017/9/20.
 */
public interface INovelCrawler {

    public static final int MAX_TRY_TIME = 3;

    public List<Novel> getsNovel(String url,Integer maxTryTime);

    public boolean hasNext();
    public String next();
    public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTime);
}
