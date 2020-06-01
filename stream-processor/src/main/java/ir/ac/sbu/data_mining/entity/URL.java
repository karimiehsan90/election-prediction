package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class URL {
    @JsonProperty("display_url")
    private String displayURL;

    @JsonProperty("expanded_url")
    private String expandedURL;

    @JsonProperty("indices")
    private List<Integer> indices;

    @JsonProperty("url")
    private String url;
}