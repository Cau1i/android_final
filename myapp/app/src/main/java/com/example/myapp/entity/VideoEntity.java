package com.example.myapp.entity;

import java.io.Serializable;

/**
 * @author: wei
 * @date: 2020-06-27
 **/
public class VideoEntity implements Serializable {
    /**
     * vid : 1
     * vtitle : 青龙战甲搭配机动兵，P城上空肆意1V4
     * author : 狙击手麦克
     * coverurl : http://sf3-xgcdn-tos.pstatp.com/img/tos-cn-i-0004/527d013205a74eb0a77202d7a9d5b511~tplv-crop-center:1041:582.jpg
     * headurl : https://sf1-ttcdn-tos.pstatp.com/img/pgc-image/c783a73368fa4666b7842a635c63a8bf~360x360.image
     * playurl : http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4
     * createTime : 2020-07-14 11:21:45
     * updateTime : 2020-07-19 12:05:33
     * categoryId : 1
     * categoryName : 游戏
     * videoSocialEntity : {"commentnum":103,"likenum":121,"collectnum":220}
     */

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

