package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Symbol {
    @JsonProperty("indices")
    private List<Integer> indices;

    @JsonProperty("text")
    private String text;
}