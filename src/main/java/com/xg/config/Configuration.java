package com.xg.config;

/**
 * Created by xg on 2017/9/13.
 */
public class Configuration {
    public static final int DEFAULT_SIZE = 100;
    public static final int DEFAULT_TRY_TIMES = 3;
    private String path;
    private int sizes;
    private int tryTimes;

    public Configuration() {

        this.sizes = DEFAULT_SIZE;
        this.tryTimes=DEFAULT_TRY_TIMES;
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

    public int getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(int tryTimes) {
        this.tryTimes = tryTimes;
    }
}