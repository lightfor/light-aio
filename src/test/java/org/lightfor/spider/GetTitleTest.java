package org.lightfor.spider;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.junit.Test;

/**
 * get page title
 * Created by Light on 2017/7/21.
 */
public class GetTitleTest {

    @Test
    public void test() throws Exception{
        String urlStr = "http://www.qq.com";
        String[] urls = urlStr.split("\\s+");

        for(String url : urls){
            Elements title = HttpUtils.get(url).head().getElementsByTag("title");
            System.out.println(title.get(0).text());
            System.out.println(url);
        }
    }
}
