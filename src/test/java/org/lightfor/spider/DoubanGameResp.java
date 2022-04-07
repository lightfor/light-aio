package org.lightfor.spider;

import java.util.List;

public class DoubanGameResp {

    private List<Game> games;

    static class Game{
        private String genres;
        private List<String> genreList;
        private int n_ratings;
        private String platforms;
        private List<String> platformList;
        private float rating;
        private String title;
        private long id;

        public String getGenres() {
            return genres;
        }

        public void setGenres(String genres) {
            this.genres = genres;
        }

        public List<String> getGenreList() {
            return genreList;
        }

        public void setGenreList(List<String> genreList) {
            this.genreList = genreList;
        }

        public int getN_ratings() {
            return n_ratings;
        }

        public void setN_ratings(int n_ratings) {
            this.n_ratings = n_ratings;
        }

        public String getPlatforms() {
            return platforms;
        }

        public void setPlatforms(String platforms) {
            this.platforms = platforms;
        }

        public List<String> getPlatformList() {
            return platformList;
        }

        public void setPlatformList(List<String> platformList) {
            this.platformList = platformList;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}