package com.demo.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: vinod.rawat
 * Date: 03/02/20
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${msg}")
    private String msg;
    @Value("${msg1:cannot load form anywhere}")
    private String msg1;

    @RequestMapping("/msg")
    public String test(){
        return msg;
    }
    @RequestMapping("/msg1")
    public String tes2t(){
        return msg1;
    }
}
