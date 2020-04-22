package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Variant {
    @JsonProperty("bitrate")
    private int bitrate;
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("url")
    private String url;
}