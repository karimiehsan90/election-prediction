package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMention {
    @JsonProperty("id")
    private long id;

    @JsonProperty("id_str")
    private String idStr;

    @JsonProperty("indices")
    private List<Integer> indices;

    @JsonProperty("name")
    private String name;

    @JsonProperty("screen_name")
    private String screenName;
}