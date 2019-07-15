package com.springcloud.pro.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${myww}") // git配置文件里的key
     String myww;

    @RequestMapping(value = "/hi")
    public String hi(){
        return myww;
    }
}
