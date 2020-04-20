//package com.xktime.crawler.task.impl;
//
//import org.apache.commons.lang3.StringUtils;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Component;
//import us.codecraft.webmagic.Page;
//import us.codecraft.webmagic.Site;
//import us.codecraft.webmagic.processor.PageProcessor;
//
//import java.util.Date;
//import java.util.Iterator;
//
//@Component
//public class SegmentfaultTask implements PageProcessor {
//
//    private Site site = Site.me().setRetryTimes(1).setSleepTime(1000);
//
//    @Override
//    public void process(Page page) {
//        //抽取文章的超链接
//        page.addTargetRequests(page.getHtml().css(".news-list").links().
//                                    regex("https://segmentfault.com/a/.*").all());
//        if (!page.getUrl().regex("https://segmentfault.com/a/.*").match()) {
//            page.setSkip(true);
//            return;
//        }
//        //获取标题
//        Elements title = page.getHtml().getDocument().select("title");
//        if (title != null) {
//            String[] split = splitTitle(title.first().text());
//            page.putField("title", split[0]);
//            page.putField("channelName", split[1]);
//            page.putField("origin", split[2]);
//        }
//        //内容格式化
//        Elements content = page.getHtml().getDocument().select("article");
//        if (content != null) {
//            Elements contents = content.first().children();
//            Iterator<Element> itr = contents.iterator();
//            while (itr.hasNext()) {
//                Element next = itr.next();
//                if (StringUtils.isBlank(next.text())) {
//                    next.remove();
//                }
//            }
//            page.putField("content", contents.toString());
//        }
//        //获取标签
//        Elements lables = page.getHtml().getDocument().select("[name = keywords]");
//        if (lables != null) {
//            page.putField("lables", lables.attr("content"));
//        }
//        //获取作者名字
//        Elements authorName = page.getHtml().getDocument().select("strong");
//        if (authorName != null) {
//            page.putField("authorName", authorName.first().text());
//        }
//        page.putField("publishTime", new Date());
//    }
//
//    @Override
//    public Site getSite() {
//        return site;
//    }
//
//    /**
//     * SegmentFault标题解析格式为 【标题】 - 【标签】 - SegmentFault 思否
//     * split[0]为文章标题 split[1]为标签 split[2]为文章来源
//     * @param title
//     * @return
//     */
//    private String[] splitTitle(String title) {
//        String[] s = new String[3];
//        if (title == null) {
//            return s;
//        }
//        String[] split = title.split(" - ");
//        if (split.length == 3) {
//            return split;
//        } else if (split.length > 3) {
//            //如果长度大于3 那么前面都为文章标题
//            int titleLength = split.length - 2;
//            StringBuilder articleTitle = new StringBuilder();
//            for (int i = 0; i < titleLength; i++) {
//                articleTitle.append(split[i]);
//                if (i != titleLength - 1) {
//                    articleTitle.append(" - ");
//                }
//            }
//            s[0] = articleTitle.toString();
//            s[1] = split[titleLength];
//            s[2] = split[titleLength + 1];
//        } else {
//            for (int i = 0; i < split.length; i++) {
//                s[i] = split[i];
//            }
//        }
//        return s;
//    }
//}
