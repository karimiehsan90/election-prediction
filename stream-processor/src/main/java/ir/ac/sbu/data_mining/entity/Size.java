package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Size {
    @JsonProperty("h")
    private int h;
    @JsonProperty("resize")
    private String resize;
    @JsonProperty("w")
    private int w;
}