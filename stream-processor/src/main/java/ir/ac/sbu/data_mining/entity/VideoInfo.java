package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoInfo {
    @JsonProperty("aspect_ratio")
    private List<Integer> aspectRatio;
    @JsonProperty("variants")
    private List<Variant> variants;
    @JsonProperty("duration_millis")
    private int durationMillis;
}