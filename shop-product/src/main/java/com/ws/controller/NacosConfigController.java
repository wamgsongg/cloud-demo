package com.ws.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RequestMapping("test")
@RestController
@Slf4j
@RefreshScope //动态刷新
public class NacosConfigController {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Value("${config.appName}")
    private String appName;

    @RequestMapping("/test1")
    public String testConfig1(){
        return applicationContext.getEnvironment().getProperty("config.appName");
    }

    @RequestMapping("/test2")
    public String testConfig2(){
        return appName;
    }

}
