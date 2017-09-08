package com.xg.entitys;

import java.io.Serializable;

/**
 * Created by xg on 2017/9/8.
 */
public class Chapter implements Serializable {
private String titile;
private  String url;

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "titile='" + titile + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
