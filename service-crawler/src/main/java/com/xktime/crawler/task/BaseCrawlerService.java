package com.xktime.crawler.task;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public abstract class BaseCrawlerService implements PageProcessor {

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
        page.putField("labels", getLabels(page));
        page.putField("authorName", getAuthorName(page));
        page.putField("publishTime", getPublishTime(page));
    }

    /**
     * 启动爬虫
     * @param pipeline
     */
    public abstract void run(Pipeline pipeline);

    /**
     * 跳转页面列表
     *
     * @param page
     * @return
     */
    public abstract List<String> getTargetRequests(Page page);

    /**
     * 检查是否要跳过该页面
     *
     * @param page
     * @return
     */
    public abstract boolean checkSkipCondition(Page page);

    public abstract String getTitle(Page page);

    public abstract String getChannelName(Page page);

    public abstract String getOrigin(Page page);

    public abstract String getContent(Page page);

    public abstract String getLabels(Page page);

    public abstract String getAuthorName(Page page);

    public abstract long getPublishTime(Page page);
}
