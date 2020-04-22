package ir.ac.sbu.data_mining.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdditionalMediaInfo {
    @JsonProperty("monetizable")
    private boolean monetizable;
    @JsonProperty("source_user")
    private User sourceUser;
    @JsonProperty("description")
    private String description;
    @JsonProperty("embeddable")
    private boolean embeddable;
    @JsonProperty("title")
    private String title;
    @JsonProperty("call_to_actions")
    private CallToActions callToActions;
}
