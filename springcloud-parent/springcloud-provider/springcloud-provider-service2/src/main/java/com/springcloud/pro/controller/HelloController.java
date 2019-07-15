package com.springcloud.pro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        logger.info("request two name is "+name);
        try{
            //测试路由重试
            Thread.sleep(1000000);
        }catch ( Exception e){
            logger.error(" hello two error",e);
        }
        return "hello "+name+"，this is two messge";
    }

    @RequestMapping("/helloFeign")
    public String helloFeign(@RequestParam String name) {
        return "helloFeign "+name+"，this is two messge";
    }
}
