package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geo {
    @JsonProperty("coordinates")
    private List<Double> coordinates;

    @JsonProperty("type")
    private String type;
}