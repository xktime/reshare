package com.xktime.crawler.task.impl;

import com.xktime.crawler.task.CrawlerTask;
import com.xktime.crawler.util.FormatUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CsdnTaskImpl extends CrawlerTask {
    @Override
    public List<String> getTargetRequests(Page page) {
        if (page == null) {
            return null;
        }
        Selectable list = page.getHtml().css(".feedlist_mod").css("h2");
        if (list != null) {
            return list.links().regex("https://blog.csdn.net/.*/article/details/.*").all();
        }
        return null;
    }

    @Override
    public boolean checkSkipCondition(Page page) {
        if (page == null) {
            return false;
        }
        return !page.getUrl().regex("https://blog.csdn.net/.*/article/details/.*").match();
    }

    @Override
    public String getTitle(Page page) {
        Elements title = page.getHtml().getDocument().select(".title-article");
        if (title != null) {
            return title.text();
        }
        return null;
    }

    @Override
    public String getChannelName(Page page) {
        Elements elements = page.getHtml().getDocument().select(".tag-link");
        if (elements != null) {
            return elements.first().text();
        }
        return null;
    }

    @Override
    public String getOrigin(Page page) {
        return "CSDN博客";
    }

    @Override
    public String getContent(Page page) {
        Elements elements = page.getHtml().getDocument().select(".article_content");
        if (elements == null) {
            return null;
        }
        Elements content = elements.select("#content_views");
        Elements formatedContent = FormatUtil.contentFormat(content);
        if (formatedContent != null) {
            return formatedContent.toString();
        }
        return null;
    }

    @Override
    public String getLables(Page page) {
        Elements elements = page.getHtml().getDocument().select(".tag-link");
        if (elements != null) {
            return elements.nextAll().toString();
        }
        return null;
    }

    @Override
    public String getAuthorName(Page page) {
        Elements authorName = page.getHtml().getDocument().select(".follow-nickName");
        if (authorName != null) {
            return authorName.text();
        }
        return null;
    }

    @Override
    public Date getPublishTime(Page page) {
        Element publishTime = page.getHtml().getDocument().select(".time").first();
        if (publishTime != null) {
            String time = publishTime.text();
            StringBuilder builder = new StringBuilder(time);
            builder.delete(0, 5);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(builder.toString());
                return date;
            } catch (ParseException e) {
                return null;
            }
        }
        return null;
    }

    private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);

    @Override
    public Site getSite() {
        return site;
    }

}
