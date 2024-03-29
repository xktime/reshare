package com.xktime.crawler.task.impl;

import com.xktime.crawler.task.BaseCrawlerService;
import com.xktime.crawler.util.FormatUtil;
import com.xktime.crawler.webmagic.HttpClientDownloader;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Selectable;

import java.util.Date;
import java.util.List;

@Service
public class SegmentfaultCrawlerServiceImpl extends BaseCrawlerService {

    @Override
    public void run(Pipeline pipeline) {
        Spider.create(this).setDownloader(new HttpClientDownloader())//修复SSLExceptionBug
                .addUrl("https://segmentfault.com/hottest")
                .addUrl("https://segmentfault.com/newest")
                .addUrl("https://segmentfault.com/")
                .addPipeline(pipeline)
                .run();
    }

    @Override
    public List<String> getTargetRequests(Page page) {
        if (page == null) {
            return null;
        }
        Selectable list = page.getHtml().css(".news-list");
        if (list != null) {
            return list.links().regex("https://segmentfault.com/a/.*").all();
        }
        return null;
    }

    @Override
    public boolean checkSkipCondition(Page page) {
        if (page == null) {
            return false;
        }
        return !page.getUrl().regex("https://segmentfault.com/a/.*").match();
    }

    @Override
    public String getTitle(Page page) {
        Elements title = page.getHtml().getDocument().select("title");
        if (title != null) {
            String[] split = splitTitle(title.first().text());
            return split[0];
        }
        return null;
    }

    @Override
    public String getChannelName(Page page) {
        Elements title = page.getHtml().getDocument().select("title");
        if (title != null) {
            String[] split = splitTitle(title.first().text());
            return split[1];
        }
        return null;
    }

    @Override
    public String getOrigin(Page page) {
        return "SegmentFault 思否";
    }

    @Override
    public String getContent(Page page) {
        Elements elements = page.getHtml().getDocument().select("article");
        Elements content = FormatUtil.contentFormat(elements);
        if (content != null) {
            return content.toString();
        }
        return null;
    }

    @Override
    public String getLabels(Page page) {
        Elements labels = page.getHtml().getDocument().select("[name = keywords]");
        if (labels != null) {
            return labels.attr("content");
        }
        return null;
    }

    @Override
    public String getAuthorName(Page page) {
        Elements authorName = page.getHtml().getDocument().select("strong");
        if (authorName != null) {
            return authorName.first().text();
        }
        return null;
    }

    @Override
    public long getPublishTime(Page page) {
        return new Date().getTime();
    }

    private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);

    @Override
    public Site getSite() {
        return site;
    }


    /**
     * SegmentFault标题解析格式为 【标题】 - 【标签】 - SegmentFault 思否
     * split[0]为文章标题 split[1]为标签 split[2]为文章来源
     *
     * @param title
     * @return
     */
    private String[] splitTitle(String title) {
        String[] s = new String[3];
        if (title == null) {
            return s;
        }
        String[] split = title.split(" - ");
        if (split.length == 3) {
            return split;
        } else if (split.length > 3) {
            //如果长度大于3 那么前面都为文章标题
            int titleLength = split.length - 2;
            StringBuilder articleTitle = new StringBuilder();
            for (int i = 0; i < titleLength; i++) {
                articleTitle.append(split[i]);
                if (i != titleLength - 1) {
                    articleTitle.append(" - ");
                }
            }
            s[0] = articleTitle.toString();
            s[1] = split[titleLength];
            s[2] = split[titleLength + 1];
        } else {
            for (int i = 0; i < split.length; i++) {
                s[i] = split[i];
            }
        }
        return s;
    }
}
