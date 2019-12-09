package com.lxp.pro.spider.pojos;

public class UrlTaskPojo {
    private String url;
    private String fromUrl;

    public UrlTaskPojo(String url) {
        this.url = url;
    }

    public UrlTaskPojo(String url, String fromUrl) {
        this.url = url;
        this.fromUrl = fromUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }
}
