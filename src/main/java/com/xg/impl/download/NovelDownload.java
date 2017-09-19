package com.xg.impl;

import com.xg.config.Configuration;
import com.xg.entitys.Chapter;
import com.xg.entitys.ChapterDetail;
import com.xg.interfaces.IChapterCrawler;
import com.xg.interfaces.IChapterDetailCrawler;
import com.xg.interfaces.INovelDownload;
import com.xg.util.ChapterDetailCrawlerFactory;
import com.xg.util.CrawlerUtil;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.*;

import static com.xg.util.ChapterCrawlerFactory.*;

/**
 * Created by xg on 2017/9/13.
 */
public class NovelDownload implements INovelDownload {
    public String download(String url, Configuration config) {
        IChapterCrawler chapterCrawler = getChapterCrawler(url);
        List<Chapter> chapters = chapterCrawler.getChapter(url);
        int size = config.getSize();
        //Math.ceil(double) 10 -> 10 10.5->11 10.1 ->11 -10 -> -10 -10.1 -> 10 -10.5 -> -10
        int maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);
        Map<String, List<Chapter>> downloadTaskAlloc = new HashMap<String, List<Chapter>>();
        for (int i = 0; i < maxThreadSize; i++) {
            // i = 0 0-99
            // i = 1 100-199
            // i = 2 200-299
            // i = 3 300-399
            // ...
            // i = 19 1900-1999
            // i = 20 2000-2052
            int fromIndex = i * (config.getSize());
            if (i == maxThreadSize - 1) {
                int toIndex = chapters.size() - 1;
            }
            int toIndex = i == maxThreadSize - 1 ? chapters.size()  : i * (config.getSize()) + config.getSize();
            downloadTaskAlloc.put(fromIndex + "-" + toIndex, chapters.subList(fromIndex, toIndex));

        }
        ExecutorService service = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        List<Future<String>> tasks = new ArrayList<Future<String>>();
        String subPath=url.split("//")[1];
        //String savePath= config.getPath()+"/"+ SiteEnum.getEnumByUrl(url).getUrl();
        String savePath= config.getPath()+"/"+subPath;
        //new File(savePath).mkdir();
        new File(savePath).mkdirs();
        for (String key : keySet) {

            tasks.add( service.submit(new DownloadCallable(savePath + "/" + key + ".txt", downloadTaskAlloc.get(key),config.getTryTimes())));
        }
        service.shutdown();
        for (Future<String> future : tasks
                ) {
            try {
                System.out.println(future.get()+",download  finished");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CrawlerUtil.multiFileMerge(savePath,null,true);
        //CrawlerUtil.multiFileMerge("D:/1",null,true);
        return savePath+"/merge.txt";
    }

    class DownloadCallable implements Callable<String> {
        private List<Chapter> chapters;
        private String path;
        PrintWriter out = null;
        int tryTime;

        public DownloadCallable(String path, List<Chapter> chapters,int tryTime) {
            this.path = path;
            this.chapters = chapters;
            this.tryTime =tryTime;
        }

        public String call() throws Exception {
            try {
                out = new PrintWriter(new File(path));
                for (Chapter chapter : chapters
                        ) {
                    for (int i = 0; i< tryTime; i++) {
                        try {
                            IChapterDetailCrawler chapterDetailCrawler = ChapterDetailCrawlerFactory.getChapterDetailCrawler(chapter.getUrl());
                            ChapterDetail detail = chapterDetailCrawler.getChapterDetail(chapter.getUrl());
                            out.println(detail.getTitle());
                            out.println(detail.getContent());
                            break;
                        } catch (RuntimeException e){
                            System.err.println("try [" + (i + 1) + "/" + tryTime + "time  chapter="+chapter.getTitile()+"] download failed！");
                        }

                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (out != null)
                    out.close();
            }
            return path;
        }
    }

}
