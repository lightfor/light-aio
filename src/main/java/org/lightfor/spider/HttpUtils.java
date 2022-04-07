package org.lightfor.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public enum HttpUtils {
    ;

    public static Document get(String url) throws IOException {
        return Jsoup.connect(url)
                .timeout(10000)
                .userAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1")
                .referrer(url)
                .ignoreContentType(true)
                .get();
    }
}
