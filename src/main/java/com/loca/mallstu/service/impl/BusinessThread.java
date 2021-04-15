package com.loca.mallstu.service.impl;

import com.loca.mallstu.bean.po.UserPO;

import java.util.concurrent.Callable;

/**
 * @author wangHeng
 * @date  2021-04-15 16:30
 */
public class BusinessThread implements Callable<UserPO> {

    @Override
    public UserPO call() throws Exception {
        //业务处理

        return null;
    }
}
