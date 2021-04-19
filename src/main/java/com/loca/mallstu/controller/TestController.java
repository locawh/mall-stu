package com.loca.mallstu.controller;

import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.common.CommonResult;
import com.loca.mallstu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangHeng
 * @date  2021-03-23 16:33
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public CommonResult<String> test() {
        return CommonResult.success("hello! loca");
    }

    @RequestMapping("addUser")
    public Boolean addUser() {
        System.out.println("sdfsd");
        UserPO userPO = new UserPO();
        userPO.setName("loca");
        userPO.setAge(26);
        return userService.addUser(userPO);
    }
}
