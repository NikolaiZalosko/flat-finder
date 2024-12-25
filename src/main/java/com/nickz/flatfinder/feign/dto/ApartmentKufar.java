package com.nickz.flatfinder.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApartmentKufar {

    @JsonProperty("ad_id")
    private String id;

    @JsonProperty("ad_link")
    private String url;
}
