package com.xg.enums;

/**
 * Created by xg on 2017/9/9.
 */
public enum SiteEnum {
    WX(1, "http://www.23wx.cc"),
    XS(2, "http://www.xs.la"),
    BXWX(3,"http://www.bxwx9.org"),
    KSZ(4,"http://www.kanshuzhong.com");

    private int id;
    private String url;

    SiteEnum(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static SiteEnum getEnumById(int id) {
        switch (id) {
            case 1:
                return WX;
            case 2:
                return XS;
            default:
                throw new RuntimeException("id=" + id + "This site is not supported");
        }
    }
    public static SiteEnum getEnumByUrl(String url){
        for (SiteEnum s:values()
             ) {
            if(url.contains(s.getUrl())){
                return s;
            }
        }
        throw  new RuntimeException("url="+url+"This site is not supported");
    }
}
