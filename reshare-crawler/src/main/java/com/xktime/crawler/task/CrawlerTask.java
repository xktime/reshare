package com.xktime.crawler.task;

import com.sun.istack.internal.NotNull;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;
import java.util.List;

public abstract class CrawlerTask implements PageProcessor {
    public abstract List<String> getTargetRequests(Page page);

    public abstract boolean checkSkipCondition(Page page);

    public abstract String getTitle(Page page);

    public abstract String getChannelName(Page page);

    public abstract String getOrigin(Page page);

    public abstract String getContent(@NotNull Page page);

    public abstract String getLables(Page page);

    public abstract String getAuthorName(Page page);

    public abstract Date getPublishTime(Page page);

    @Override
    public void process(Page page) {
        page.addTargetRequests(getTargetRequests(page));
        if (checkSkipCondition(page)) {
            page.setSkip(true);
            return;
        }
        page.putField("title", getTitle(page));
        page.putField("channelName", getChannelName(page));
        page.putField("origin", getOrigin(page));
        page.putField("content", getContent(page));
        page.putField("lables", getLables(page));
        page.putField("authorName", getAuthorName(page));
        page.putField("publishTime", getPublishTime(page));
    }
}
