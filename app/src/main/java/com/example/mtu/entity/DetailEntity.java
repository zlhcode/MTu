package com.example.mtu.entity;

import java.util.List;

/**
 * 详情数据
 */
public class DetailEntity {

    private String title;

    private List<String> imagesList;

    private String currentUrl;

    private String nextUrl;

    private String totalPage;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

    public void setCurrentUrl(String currentUrl) {
        this.currentUrl = currentUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "DetailEntity{" +
                "title='" + title + '\'' +
                ", imagesList=" + imagesList +
                ", currentUrl='" + currentUrl + '\'' +
                ", nextUrl='" + nextUrl + '\'' +
                ", totalPage='" + totalPage + '\'' +
                '}';
    }
}
