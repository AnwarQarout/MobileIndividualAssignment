package com.example.fullfit.Models;

public class NewsModel {
    private String newsTitle;
    private String newsBody;

    public NewsModel() {
    }

    public NewsModel(String newsTitle, String newsBody) {
        this.newsTitle = newsTitle;
        this.newsBody = newsBody;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    public String toString(){
        return this.newsTitle;
    }
}
