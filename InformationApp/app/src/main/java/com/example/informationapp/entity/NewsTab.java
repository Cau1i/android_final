package com.example.informationapp.entity;

import com.example.informationapp.api.NewsURL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsTab implements Serializable {
    private int id;
    private String type;
    private String url;

    public NewsTab() {
    }

    public NewsTab(int id, String type, String url) {
        this.id = id;
        this.type = type;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static List<NewsTab> getNewsTabList() {
        List<NewsTab> mDatas = new ArrayList<>();
        NewsTab nt1 = new NewsTab(1, "头条", NewsURL.top_url);
        NewsTab nt2 = new NewsTab(2, "国内", NewsURL.guonei_url);
        NewsTab nt3 = new NewsTab(3, "国际", NewsURL.guoji_url);
        NewsTab nt4 = new NewsTab(4, "娱乐", NewsURL.yule_url);
        NewsTab nt5 = new NewsTab(5, "体育", NewsURL.tiyu_url);
        NewsTab nt6 = new NewsTab(6, "军事", NewsURL.junshi_url);
        NewsTab nt7 = new NewsTab(7, "科技", NewsURL.keji_url);
        NewsTab nt8 = new NewsTab(8, "财经", NewsURL.caijing_url);
        NewsTab nt9 = new NewsTab(9, "时尚", NewsURL.shishang_url);
        NewsTab nt10 = new NewsTab(10, "游戏", NewsURL.youxi_url);
        NewsTab nt11 = new NewsTab(11, "汽车", NewsURL.qiche_url);
        NewsTab nt12 = new NewsTab(12, "健康", NewsURL.jiankang_url);
        mDatas.add(nt1);
//        mDatas.add(nt2);
//        mDatas.add(nt3);
        mDatas.add(nt4);
        mDatas.add(nt5);
//        mDatas.add(nt6);
//        mDatas.add(nt7);
//        mDatas.add(nt8);
//        mDatas.add(nt9);
        mDatas.add(nt10);
//        mDatas.add(nt11);
//        mDatas.add(nt12);
        return mDatas;
    }
}
