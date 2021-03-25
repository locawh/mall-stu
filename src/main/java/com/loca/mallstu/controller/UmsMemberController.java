package com.loca.mallstu.controller;

import com.loca.mallstu.common.CommonResult;
import com.loca.mallstu.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangHeng
 * @date  2021-03-24 16:51
 */
@RestController
@RequestMapping("/api/member")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @RequestMapping("/getCode")
    public CommonResult<String> getVerificationCode(@RequestParam String telephone) {
        return umsMemberService.getVerificationCode(telephone);
    }

    @RequestMapping("/checkCode")
    public CommonResult<String> checkVerificationCode(@RequestParam String telephone, @RequestParam String code) {
        return umsMemberService.checkVerificationCode(telephone, code);
    }
}
