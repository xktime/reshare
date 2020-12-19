package com.xktime.crawler.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

public class FormatUtil {

    public static Elements contentFormat(Elements elements) {
        if (elements != null && elements.first() != null) {
            Elements contents = elements.first().children();
            Iterator<Element> itr = contents.iterator();
            while (itr.hasNext()) {
                Element next = itr.next();
                if (StringUtils.isBlank(next.text())) {
                    next.remove();
                }
            }
            return contents;
        }
        return null;

    }
}
