package com.nickz.flatfinder.feign;

import com.nickz.flatfinder.feign.dto.KufarResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "KufarFeign", url = "${client.kufar.host}")
public interface KufarFeignClient {

    @GetMapping("${client.kufar.search}")
    KufarResponse searchApartments();
}
