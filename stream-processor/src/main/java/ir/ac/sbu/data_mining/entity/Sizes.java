package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sizes {
    @JsonProperty("large")
    private Size large;
    @JsonProperty("medium")
    private Size medium;
    @JsonProperty("small")
    private Size small;
    @JsonProperty("thumb")
    private Size thumb;
}