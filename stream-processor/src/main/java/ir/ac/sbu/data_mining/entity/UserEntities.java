package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEntities {
    @JsonProperty("description")
    private UserDescription description;
    @JsonProperty("url")
    private UserEntityURL url;
}