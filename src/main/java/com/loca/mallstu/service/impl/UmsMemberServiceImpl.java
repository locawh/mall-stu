package com.loca.mallstu.service.impl;

import com.loca.mallstu.common.CommonResult;
import com.loca.mallstu.service.RedisService;
import com.loca.mallstu.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author wangHeng
 * @date  2021-03-24 18:08
 */
@Slf4j
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Autowired
    private RedisService redisService;

    @Override
    public CommonResult<String> getVerificationCode(String telephone) {
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, telephone + new Random(6));
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(telephone + new Random(6),"获取验证码成功!");
    }

    @Override
    public CommonResult<Boolean> checkVerificationCode(String telephone, String verification) {
        return null;
    }
}
