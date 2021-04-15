package com.loca.mallstu.service.impl;

import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.common.CommonResult;
import com.loca.mallstu.service.RedisService;
import com.loca.mallstu.service.UmsMemberService;
import com.loca.mallstu.utils.AsyncThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author wangHeng
 * @date  2021-03-24 18:08
 */
@Slf4j
@Service
@SuppressWarnings("all")
public class UmsMemberServiceImpl implements UmsMemberService {

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Autowired
    private RedisService redisService;

    @Override
    public CommonResult<String> getVerificationCode(String telephone) {
        int code = new Random(6).nextInt(10);
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        String key = REDIS_KEY_PREFIX_AUTH_CODE + telephone;
        log.info("key={}", key);
        log.info("value={}", sb.toString());
        redisService.set(key, sb.toString());
        redisService.expire(key, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功!");
    }

    @Override
    public CommonResult<String> checkVerificationCode(String telephone, String verificationCode) {
        if (Objects.isNull(verificationCode)) {
            return CommonResult.failed("请输入验证码!");
        }
        String code = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        if (!Objects.equals(verificationCode, code)) {
            return CommonResult.failed("验证码已过期,请重新获取!");
        }
        return CommonResult.success(code, "有效的验证码!");
    }

    @Override
    public CommonResult<UserPO> createUserBatch(List<UserPO> users) {
        Future<Boolean> submit = (Future<Boolean>) AsyncThreadPoolUtil.BATCH_APPROVE_POOL.submit(new createUser(users));
        return null;
    }
}
