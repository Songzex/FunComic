package com.scy.common.vos;

public class MusicInfo {
    private String title;
    private String fileUrl;
    private Integer image;
    private String epname;
    private String singer;
    private String webUrl;

    // 构造函数、getter 和 setter 方法

    public MusicInfo() {
    }

    public MusicInfo(String title, String fileUrl, Integer image, String epname, String singer, String webUrl) {
        this.title = title;
        this.fileUrl = fileUrl;
        this.image = image;
        this.epname = epname;
        this.singer = singer;
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getEpname() {
        return epname;
    }

    public void setEpname(String epname) {
        this.epname = epname;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}