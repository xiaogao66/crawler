package com.xg.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * Created by xg on 2017/9/15.
 */
public class CrawlerHttpGet extends HttpGet{
    public CrawlerHttpGet() {

    }

    public CrawlerHttpGet(URI uri) {
        super(uri);
    }

    public CrawlerHttpGet(String uri) {
        super(uri);
        setDefaultConfig();
    }
    /**
     *
     */
    private void setDefaultConfig() {
        this.setConfig(RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build());
        //this.setHeader("User-Agent", "");
        System.out.println(this.getConfig().getConnectTimeout());
    }
}
