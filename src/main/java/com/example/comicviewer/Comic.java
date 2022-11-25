package com.example.comicviewer;

import java.util.Date;
import java.util.List;

public class Comic {

    private String id;
    private String title;
    private String author;
    private String comicType;
    private String desc;
    private Date updatetime;
    private String imgpath;

    private List<ComicChapter> comicchapters;

    private List<String> comic_content_path;

    public Comic(String id, String title, String author,String comicType,String desc,Date updatetime,String imgpath){
        this.id = id;
        this.title = title;
        this.author = author;
        this.comicType = comicType;
        this.desc = desc;
        this.imgpath = imgpath;
        this.updatetime = updatetime;
    }



    public void setId(String id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setComicType(String type){
        this.comicType = type;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public void setUpdatetime(Date date){
        this.updatetime = date;
    }

    public void setImgpath(String path){
        this.imgpath = path;
    }

    public String getAuthor() {
        return author;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public String getComicType() {
        return comicType;
    }

    public String getDesc() {
        return desc;
    }

    public String getId() {
        return id;
    }

    public String getImgpath() {
        return imgpath;
    }

    public String getTitle() {
        return title;
    }

}
