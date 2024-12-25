package com.nickz.flatfinder.feign;

import com.nickz.flatfinder.feign.dto.OnlinerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "OnlinerFeign", url = "${client.onliner.host}")
public interface OnlinerFeignClient {

    @GetMapping("${client.onliner.search}")
    OnlinerResponse searchApartments();
}
