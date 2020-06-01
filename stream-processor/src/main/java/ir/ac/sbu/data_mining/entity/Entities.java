package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Entities {
    @JsonProperty("hashtags")
    private List<Hashtag> hashtags;

    @JsonProperty("symbols")
    private List<Symbol> symbols;

    @JsonProperty("urls")
    private List<URL> urls;

    @JsonProperty("user_mentions")
    private List<UserMention> userMentions;

    @JsonProperty("media")
    private List<Media> media;

    public List<Hashtag> getHashtags() {
        return this.hashtags;
    }
}
