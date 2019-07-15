package com.springcloud.pro.controller;

import com.springcloud.pro.service.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    //--------------使用 Restful调用服务------------------------
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return restTemplate.getForEntity("http://springcloud-provider-service/hello?name=llq", String.class).getBody();
    }

    //--------------使用 Feign调用服务------------------------
    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping("/helloFeign/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.helloFeign(name);
    }
}
