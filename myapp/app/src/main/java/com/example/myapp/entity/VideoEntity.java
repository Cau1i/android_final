package com.example.myapp.entity;

import java.io.Serializable;

public class VideoEntity implements Serializable {
    private int vid;
    private String vtitle;
    private String author;
    private String coverurl;
    private String headurl;
    private String playurl;
    private int commentNum;
    private int likeNum;
    private int collectNum;

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVtitle() {
        return vtitle;
    }

    public void setVtitle(String vtitle) {
        this.vtitle = vtitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCoverurl() {
        return coverurl;
    }

    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public int getCommentnum() {
        return commentNum;
    }

    public void setCommentnum(int commentnum) {
        this.commentNum = commentnum;
    }

    public int getLikenum() {
        return likeNum;
    }

    public void setLikenum(int likenum) {
        this.likeNum = likenum;
    }

    public int getCollectnum() {
        return collectNum;
    }

    public void setCollectnum(int collectnum) {
        this.collectNum = collectnum;
    }
}

