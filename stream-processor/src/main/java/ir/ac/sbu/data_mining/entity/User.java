package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("contributors_enabled")
    private boolean contributorsEnabled;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("default_profile")
    private boolean defaultProfile;
    @JsonProperty("default_profile_image")
    private boolean defaultProfileImage;
    @JsonProperty("description")
    private String description;
    @JsonProperty("entities")
    private UserEntities entities;
    @JsonProperty("favourites_count")
    private long favouritesCount;
    @JsonProperty("follow_request_sent")
    private boolean followRequestSent;
    @JsonProperty("followers_count")
    private int followersCount;
    @JsonProperty("following")
    private boolean following;
    @JsonProperty("friends_count")
    private int friendsCount;
    @JsonProperty("geo_enabled")
    private boolean geoEnabled;
    @JsonProperty("has_extended_profile")
    private boolean hasExtendedProfile;
    @JsonProperty("id")
    private long id;
    @JsonProperty("id_str")
    private String idStr;
    @JsonProperty("is_translation_enabled")
    private boolean isTranslationEnabled;
    @JsonProperty("is_translator")
    private boolean isTranslator;
    @JsonProperty("lang")
    private String lang;
    @JsonProperty("listed_count")
    private long listedCount;
    @JsonProperty("location")
    private String location;
    @JsonProperty("name")
    private String name;
    @JsonProperty("notifications")
    private boolean notifications;
    @JsonProperty("profile_background_color")
    private String profileBackgroundColor;
    @JsonProperty("profile_background_image_url")
    private String profileBackgroundImageHttpURL;
    @JsonProperty("profile_background_image_url_https")
    private String profileBackgroundImageHttpsURL;
    @JsonProperty("profile_background_tile")
    private String profileBackgroundTile;
    @JsonProperty("profile_banner_url")
    private String profileBannerURL;
    @JsonProperty("profile_image_url")
    private String profileImageHttpURL;
    @JsonProperty("profile_image_url_https")
    private String profileImageHttpsURL;
    @JsonProperty("profile_link_color")
    private String profileLinkColor;
    @JsonProperty("profile_sidebar_border_color")
    private String profileSidebarBorderColor;
    @JsonProperty("profile_sidebar_fill_color")
    private String profileSidebarFillColor;
    @JsonProperty("profile_text_color")
    private String profileTextColor;
    @JsonProperty("profile_use_background_image")
    private boolean profileUseBackgroundImage;
    @JsonProperty("protected")
    private boolean isProtected;
    @JsonProperty("screen_name")
    private String screenName;
    @JsonProperty("statuses_count")
    private long statusesCount;
    @JsonProperty("time_zone")
    private String timezone;
    @JsonProperty("translator_type")
    private String translatorType;
    @JsonProperty("url")
    private String url;
    @JsonProperty("utc_offset")
    private int utcOffset;
    @JsonProperty("verified")
    private boolean verified;
}