package com.loca.mallstu.service;

import com.loca.mallstu.common.CommonResult;

/**
 * @author wangHeng
 * @date  2021-03-24 16:52
 */
public interface UmsMemberService {

    /**
     * <h2>获取验证码</h2>
     *
     * @param telephone
     * @return com.loca.mallstu.common.CommonResult<java.lang.String>
     * @author wangheng
     * @date 2021/3/24 18:04
     */
    CommonResult<String> getVerificationCode(String telephone);

    /**
     * <h2>校验手机号是否匹配验证码</h2>
     *
     * @param telephone
     * @param verificationCode
     * @return com.loca.mallstu.common.CommonResult<java.lang.String>
     * @author wangheng
     * @date 2021/3/24 18:07
     */
    CommonResult<String> checkVerificationCode(String telephone, String verificationCode);


}
