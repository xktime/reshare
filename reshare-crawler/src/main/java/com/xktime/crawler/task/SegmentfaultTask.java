package com.xktime.crawler.task;

import com.xktime.crawler.util.HttpUtils;
import com.xktime.model.article.pojos.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class SegmentfaultTask {

    @Autowired
    HttpUtils httpUtils;

    @Scheduled(fixedDelay = 100 * 1000)
    public void crawling() {
        String url = "https://segmentfault.com";
        String html = httpUtils.doGetHtml(url);
        parseHtml(html);
    }

    private List<Article> parseHtml(String html) {
        if (html == null) {
            return null;
        }
        Document doc = Jsoup.parse(html);
        Element element = doc.getElementsByClass("news-list").first();
        Elements elements = element.children();
        List<Article> articles = new ArrayList<>();
        for (Element e : elements) {
            Attributes attributes = e.attributes();
            String dataId = attributes.get("data-id");
            articles.add(parseArticle("https://segmentfault.com/a/" + dataId));
        }
        return articles;
    }

    private Article parseArticle(String url) {
        String html = httpUtils.doGetHtml(url);
        return new Article();
    }
}
