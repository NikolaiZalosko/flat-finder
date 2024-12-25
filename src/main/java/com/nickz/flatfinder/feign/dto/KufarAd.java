package com.nickz.flatfinder.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KufarAd (@JsonProperty("ad_id") Long id,
                       @JsonProperty("ad_link") String url) {}
