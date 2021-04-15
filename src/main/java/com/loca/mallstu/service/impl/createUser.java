package com.loca.mallstu.service.impl;

import com.loca.mallstu.bean.po.UserPO;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author wangHeng
 * @date  2021-04-15 17:56
 */
public class createUser implements Callable<Boolean> {

    public createUser(List<UserPO> users) {

    }

    @Override
    public Boolean call() throws Exception {
        return null;
    }
}
