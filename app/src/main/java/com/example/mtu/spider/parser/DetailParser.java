package com.example.mtu.spider.parser;

import com.example.mtu.entity.DetailEntity;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DetailParser {


    public static DetailEntity parseOneDetail(String oneDetailHtml) {
        DetailEntity detailEntity = new DetailEntity();
        List<String> imageList = new ArrayList<>();
        Document document = DefaultParser.getHtmlResources(oneDetailHtml);
        Elements imageElements = document.select(".content center img");

        String currentNum = document.select("#pages").select("span").text();
        for (int j = 0; j < imageElements.size(); j++) {
            String imageUrl = imageElements.get(j).attr("src").trim();
//            System.out.println("当前第" + currentNum + "页，共有" + (imageElements.size()) + "张图片，当前是第" + (j + 1) + "张图片，，图片地址是：" + imageUrl);
            imageList.add(imageUrl);
        }
        detailEntity.setImagesList(imageList);
        return detailEntity;
    }
}
