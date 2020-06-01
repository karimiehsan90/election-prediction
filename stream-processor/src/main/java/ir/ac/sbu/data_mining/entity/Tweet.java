package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweet {
    @JsonProperty("contributors")
    private String contributors;

    @JsonProperty("coordinates")
    private Geo coordinates;

    @JsonProperty("created_at")
    private long createdAt;

    @JsonProperty("display_text_range")
    private List<Integer> displayTextRange;

    @JsonProperty("entities")
    private Entities entities;

    @JsonProperty("extended_entities")
    private Entities extendedEntities;

    @JsonProperty("favorite_count")
    private int favoriteCount;

    @JsonProperty("favorited")
    private boolean favorited;

    @JsonProperty("geo")
    private Geo geo;

    @JsonProperty("id")
    private long id;

    @JsonProperty("id_str")
    private String idStr;

    @JsonProperty("in_reply_to_screen_name")
    private String inReplyToScreenName;

    @JsonProperty("in_reply_to_status_id")
    private long inReplyToStatusId;

    @JsonProperty("in_reply_to_status_id_str")
    private String inReplyToStatusIdStr;

    @JsonProperty("in_reply_to_user_id")
    private long inReplyToUserId;

    @JsonProperty("in_reply_to_user_id_str")
    private String inReplyToUserIdStr;

    @JsonProperty("is_quote_status")
    private boolean isQuoteStatus;

    @JsonProperty("quoted_status_id")
    private long quotedStatusId;

    @JsonProperty("quoted_status_id_str")
    private String quotedStatusIdStr;

    @JsonProperty("lang")
    private String language;

    @JsonProperty("place")
    private Place place;

    @JsonProperty("retweet_count")
    private int retweetCount;

    @JsonProperty("retweeted")
    private boolean retweeted;

    @JsonProperty("screen_name")
    private String screenName;

    @JsonProperty("source")
    private String source;

    @JsonProperty("text")
    private String text;

    @JsonProperty("truncated")
    private boolean truncated;

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("possibly_sensitive")
    private boolean possiblySensitive;

    @JsonProperty("withheld_copyright")
    private boolean withheldCopyright;

    @JsonProperty("withheld_in_countries")
    private List<String> withheldInCountries;

    @JsonProperty("withheld_scope")
    private String withheldScope;

    public Entities getEntities() {
        return this.entities;
    }

    public Entities getExtendedEntities() {
        return this.extendedEntities;
    }

    public int getFavoriteCount() {
        return this.favoriteCount;
    }

    public int getRetweetCount() {
        return this.retweetCount;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public String getText() {
        return this.text;
    }
}
