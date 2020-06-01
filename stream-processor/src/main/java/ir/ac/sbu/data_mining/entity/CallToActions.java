package ir.ac.sbu.data_mining.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallToActions {
    @JsonProperty("visit_site")
    private URL visitSite;
    @JsonProperty("watch_now")
    private URL watchNow;
}
