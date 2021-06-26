package com.example.informationapp.api;

public class NewsURL {
    /**
     * top(推荐,默认)guonei(国内)guoji(国际)yule(娱乐)
     * tiyu(体育)junshi(军事)keji(科技)caijing(财经)
     * shishang(时尚)youxi(游戏)qiche(汽车)jiankang(健康)
     */

    public static String newsKey = "152fbb392a348c362aa771c77479736b";
    public static String news_url = "http://v.juhe.cn/toutiao/index?key=" + newsKey + "&page=&page_size=&is_filter=&type=";

    public static String top_url = news_url + "top";
    public static String guonei_url = news_url + "guonei";
    public static String guoji_url = news_url + "guoji";
    public static String yule_url = news_url + "yule";
    public static String tiyu_url = news_url + "tiyu";
    public static String junshi_url = news_url + "junshi";
    public static String keji_url = news_url + "keji";
    public static String caijing_url = news_url + "caijing";
    public static String shishang_url = news_url + "shishang";
    public static String youxi_url = news_url + "youxi";
    public static String qiche_url = news_url + "qiche";
    public static String jiankang_url = news_url + "jiankang";
}
