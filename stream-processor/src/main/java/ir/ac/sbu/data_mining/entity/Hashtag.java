package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hashtag {
    @JsonProperty("indices")
    private List<Integer> indices;

    @JsonProperty("text")
    private String text;

    public String getText() {
        return this.text;
    }
}
