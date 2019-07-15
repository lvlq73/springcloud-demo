package com.springcloud.pro.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class HelloRemoteHystrix implements HelloRemote {

    @Override
    public String helloFeign(@RequestParam(value = "name") String name) {
        return "hello" +name+", this messge send failed ";
    }
}
