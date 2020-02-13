package com.example.mtu.spider.parser;

import com.example.mtu.entity.ItemEntity;
import com.example.mtu.spider.utils.UserAgentUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListParser {


    public static String currentPage;

    public static String totalPage;


    /**
     * 解析列表数据
     *
     * @param listUrl
     * @return
     */
    public static List<ItemEntity> getListData(String listUrl, int currentPage) {
        List<ItemEntity> itemEntities;
        Document document = DefaultParser.getHtmlResources(listUrl);
        totalPage = document.select("#pages").select("a:nth-last-child(2)").text();
        if (currentPage == 1) {
            itemEntities = parseOneList(listUrl);
        } else {
            if (currentPage > Integer.parseInt(totalPage)) {
                currentPage = Integer.parseInt(totalPage);
            }
            listUrl = listUrl + currentPage + ".html";
            itemEntities = parseOneList(listUrl);
        }
        return itemEntities;
    }

    private static List<ItemEntity> parseOneList(String oneListHtml) {
        List<ItemEntity> itemEntities = new ArrayList<>();
        Document document = DefaultParser.getHtmlResources(oneListHtml);
        Elements imageListElements = document.select(".boxs .img li");
        for (Element imageListElement : imageListElements) {
            ItemEntity itemEntity = new ItemEntity();
            Elements aEl = imageListElement.select("a");
            String detailUrl = aEl.attr("href");
            String coverImage = aEl.select("img").attr("src");
            String numberInfo = imageListElement.select("p").first().text();
            String personInfo = imageListElement.select("p").get(2).text();
            String title = imageListElement.select("p").last().select("a").text();
            String totalNumber = numberInfo.split("：")[1].trim().split(" ")[0];
//            System.out.println("标题：" + title + " " + personInfo + ",封面路径:" + coverImage + " 数量:" + totalNumber + "张啊,详情路径：" + detailUrl);
            itemEntity.setDetailUrl(detailUrl);
            itemEntity.setImageSrc(coverImage);
            itemEntity.setTotalNum(totalNumber);
            itemEntity.setTitle(title);
            itemEntities.add(itemEntity);
        }
        return itemEntities;
    }


}
