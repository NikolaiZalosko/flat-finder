package com.nickz.flatfinder.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApartmentKufar {

    @JsonProperty("ad_id")
    private Long id;

    @JsonProperty("ad_link")
    private String url;
}
