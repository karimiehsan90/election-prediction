package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEntityURL {
    @JsonProperty("urls")
    private List<URL> urls;
}
