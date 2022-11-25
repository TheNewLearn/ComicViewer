package com.example.comicviewer;

public class ComicChapter {
    private String cid;
    private String title;

    public ComicChapter(String cid,String title){
        this.cid = cid;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
