package com.example.comicviewer;

public class VideoChapter {
    private String path;
    private String title;

    private int id;

    public VideoChapter(int id,String cid,String title){
        this.path = cid;
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String cid) {
        this.path = cid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
