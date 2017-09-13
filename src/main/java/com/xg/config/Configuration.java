package com.xg.config;

/**
 * Created by xg on 2017/9/13.
 */
public class Configuration {
    public  static final int DEFAULT_SIZE=100;
    private  String path;
    private  int sizes;


    public Configuration(){
        this.sizes=DEFAULT_SIZE;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return sizes;
    }

    public void setSize(int size) {
        this.sizes = size;
    }
}