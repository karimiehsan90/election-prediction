package ir.ac.sbu.data_mining.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TweetRequest {
    @JsonProperty("hashtags")
    private List<String> hashtags;

    @JsonProperty("favorite_count")
    private int favoriteCount;

    @JsonProperty("retweet_count")
    private int retweetCount;

    @JsonProperty("screen_name")
    private String screenName;

    @JsonProperty("text")
    private String text;

    public TweetRequest(List<String> hashtags
        , int favoriteCount
        , int retweetCount
        , String screenName
        , String text) {
            this.hashtags = hashtags;
            this.favoriteCount = favoriteCount;
            this.retweetCount = retweetCount;
            this.screenName = screenName;
            this.text = text;
    }
}
