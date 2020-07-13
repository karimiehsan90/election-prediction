package ir.ac.sbu.data_mining.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {
    @JsonProperty("favorite_count")
    private int favoriteCount;
    @JsonProperty("retweet_count")
    private int retweetCount;
    @JsonProperty("screen_name")
    private String screenName;
    @JsonProperty("text")
    private String text;

    public Tweet(){}

    public Tweet(int favoriteCount, int retweetCount, String screenName, String text) {
        this.favoriteCount = favoriteCount;
        this.retweetCount = retweetCount;
        this.screenName = screenName;
        this.text = text;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getText() {
        return text;
    }
}
