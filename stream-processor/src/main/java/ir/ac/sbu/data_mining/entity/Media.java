package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Media {
    @JsonProperty("display_url")
    private String displayURL;
    @JsonProperty("expanded_url")
    private String expandedURL;
    @JsonProperty("id")
    private long id;
    @JsonProperty("id_str")
    private String idStr;
    @JsonProperty("indices")
    private List<Integer> indices;
    @JsonProperty("media_url")
    private String mediaHttpURL;
    @JsonProperty("media_url_https")
    private String mediaHttpsURL;
    @JsonProperty("sizes")
    private Sizes sizes;
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("source_status_id")
    private long sourceStatusId;
    @JsonProperty("source_status_id_str")
    private String sourceStatusIdStr;
    @JsonProperty("source_user_id")
    private long sourceUserId;
    @JsonProperty("source_user_id_str")
    private String sourceUserIdStr;
    @JsonProperty("video_info")
    private VideoInfo videoInfo;
    @JsonProperty("additional_media_info")
    private AdditionalMediaInfo additionalMediaInfo;
}