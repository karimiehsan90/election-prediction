package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Place {
    @JsonProperty("attributes")
    private Attributes attributes;

    @JsonProperty("bounding_box")
    private BoundingBox boundingBox;

    @JsonProperty("contained_within")
    private List<String> containedWithin;

    @JsonProperty("country")
    private String country;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("place_type")
    private String placeType;

    @JsonProperty("url")
    private String url;

}