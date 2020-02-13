package com.example.mtu.entity;
import java.util.List;

/**
 * 美图实体
 */
public class ItemEntity {

    /**
     * 图片路径
     */
    private String imageSrc;

    /**
     * 详情标题
     */
    private String title;

    private String desc;

    private List<String> tags;

    private String totalNum;

    private String detailUrl;

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "imageSrc='" + imageSrc + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", tags=" + tags +
                ", totalNum='" + totalNum + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }
}
