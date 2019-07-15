package com.springcloud.pro.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "springcloud-provider-service",fallback = HelloRemoteHystrix.class)
public interface HelloRemote {

    @RequestMapping(value = "/helloFeign")
    public String helloFeign(@RequestParam(value = "name") String name);
}
