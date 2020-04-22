package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BoundingBox {
    @JsonProperty("coordinates")
    private List<List<List<Integer>>> coordinates;

    @JsonProperty("type")
    private String type;
}