package com.xktime.crawler.task;

import com.xktime.crawler.util.HttpUtils;
import com.xktime.model.article.pojos.Article;
import com.xktime.model.mappers.article.ArticleMapper;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SegmentfaultTask {

    @Autowired
    HttpUtils httpUtils;

    @Scheduled(fixedDelay = 30 * 1000)
    public void crawling() {
        String url = "https://segmentfault.com/hottest";
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
            Article article = parseArticle("https://segmentfault.com/a/" + dataId);
            if (article != null) {
                articles.add(article);
            }
        }
        return articles;
    }

    private Article parseArticle(@NonNull String url) {
        String html = httpUtils.doGetHtml(url);
        if (StringUtils.isBlank(html)) {
            return null;
        }
        Document doc = Jsoup.parse(html);
        if (doc == null) {
            return null;
        }
        Article article = new Article();
        //获取标题
        String title = doc.getElementsByTag("title").first().text();
        if (title == null) {
            return null;
        }
        //SegmentFault标题解析格式为 【标题】 - 【标签】 - SegmentFault 思否
        //split[0]为文章标题 split[1]为标签 split[2]为文章来源
        String[] split = title.split(" - ");
        if (split.length > 3) {
            //如果长度大于3 那么前面都为文章标题
            int titleLength = split.length - 2;
            StringBuilder articleTitle = new StringBuilder();
            for (int i = 0; i < titleLength; i++) {
                articleTitle.append(split[i]);
                if (i != titleLength - 1) {
                    articleTitle.append(" - ");
                }
            }
            String[] temp = {articleTitle.toString(), split[titleLength], split[titleLength + 1]};
            split = temp;
        }
        article.setTitle(split[0]);
        article.setChannelName(split[1]);
        article.setOrigin(split[2]);
        //获取内容
        Element content = doc.getElementsByTag("article").first();
        if (content != null) {
            article.setContent(content.text());
        }
        //获取lables
        Element keywords = doc.select("[name = keywords]").first();
        Attributes lables = keywords.attributes();
        if (lables != null) {
            article.setLabels(lables.get("content"));
        }
        //获取作者
        Element autherName = doc.getElementsByTag("strong").first();
        if (autherName != null) {
            article.setAuthorName(autherName.text());
        }
        return article;
    }
}
