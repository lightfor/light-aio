package org.lightfor.spider;

import com.google.gson.Gson;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * IP Location Info
 * Created by Light on 2017/5/26.
 */

public class DoubanGamesTest {

    public static final String SPLITTER = " / ";

    @Test
    public void test() throws Exception {
        Gson gson = new Gson();
        String url = "https://www.douban.com/j/ilmen/game/search?genres=&platforms=&q=&sort=rating&more=";
        for(int i = 1; i < 100; i++){
            List<DoubanGameResp.Game> games = new ArrayList<>();
            Document doc = HttpUtils.get(url + i);
            DoubanGameResp doubanGameResp = getDoubanGameResp(gson, doc);
            if(doubanGameResp == null){
                continue;
            }
            for (DoubanGameResp.Game game : doubanGameResp.getGames()) {
                game.setPlatformList(Arrays.asList(game.getPlatforms().split(SPLITTER)));
                if(!game.getPlatformList().contains("PC")
                        && game.getPlatformList().contains("GBA")){
                    games.add(game);
                }
                game.setGenreList(Arrays.asList(game.getGenres().split(SPLITTER)));
            }
            games.stream().filter(g -> g.getN_ratings() > 1000)
                    .map(g -> g.getTitle()+"\t"+g.getN_ratings()+"\t"+g.getRating())
                    .forEach(System.out::println);
            //Thread.sleep(2000);
        }
    }

    private DoubanGameResp getDoubanGameResp(Gson gson, Document doc) {
        try {
            return gson.fromJson(doc.text(), DoubanGameResp.class);
        } catch (Exception e){
            System.out.println(doc.text());
        }
        return null;
    }
}
