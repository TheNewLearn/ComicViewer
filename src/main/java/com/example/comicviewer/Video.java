package com.example.comicviewer;

import java.util.Date;
import java.util.List;

public class Video {
    private String id;
    private String title;
    private String director;
    private String videoType;
    private String actor;
    private String desc;
    private Date updatetime;
    private String imgpath;
    private String releaseTime;


    public Video(String id, String title, String director,String actor,String videoType,String desc,Date updatetime,String imgpath,String releaseTime){
        this.id = id;
        this.title = title;
        this.director = director;
        this.videoType = videoType;
        this.desc = desc;
        this.imgpath = imgpath;
        this.updatetime = updatetime;
        this.releaseTime = releaseTime;
        this.actor = actor;
    }



    public void setId(String id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public void setVideoType(String type){
        this.videoType = type;
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

    public void setActor(String actor){this.actor = actor;}

    public void setReleaseTime(String rt){this.releaseTime = rt;}

    public String getDirector() {
        return director;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public String getVideoType() {
        return videoType;
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

    public String getActor(){
        return actor;
    }

    public String getReleaseTime(){
        return releaseTime;
    }
}
