package com.loca.mallstu.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangHeng
 * @date  2021-03-23 16:33
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @RequestMapping("test")
    public String test() {
        return "loca";
    }
}
