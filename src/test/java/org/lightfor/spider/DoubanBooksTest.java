package org.lightfor.spider;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

public class DoubanBooksTest {

    @Test
    public void test() throws IOException {
        for(int i = 0; i < 100; i+=20){
            //Document doc = HttpUtils.get("https://book.douban.com/tag/%E4%BA%92%E8%81%94%E7%BD%91?start=" + i + "&type=T");
            //Document doc = HttpUtils.get("https://book.douban.com/tag/web?start=" + i + "&type=T");
            //Document doc = HttpUtils.get("https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?start=" + i + "&type=T");
            Document doc = HttpUtils.get("https://book.douban.com/tag/%E7%AE%97%E6%B3%95?start=" + i + "&type=T");
            Elements items = doc.select("li.subject-item");
            for (Element item : items) {
                System.out.println(item.select("h2").text());
                System.out.println(item.select("h2 a").attr("href"));
                System.out.println(item.select("div.pub").text());
                System.out.println(item.select("span.rating_nums").text());
                System.out.println(item.select("span.pl").text());
                System.out.println();
            }
        }
    }
}
