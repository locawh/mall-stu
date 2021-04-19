package com.loca.mallstu.service.impl;

import com.loca.mallstu.bean.dto.BatchOperateResultDTO;
import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.common.ResultCode;

import java.util.concurrent.Callable;

/**
 * <h2>业务线程类</h2>
 *
 * @author wangHeng
 * @date  2021-04-15 17:56
 */
public class CreateUser implements Callable<BatchOperateResultDTO> {


    private UserPO user;

    public CreateUser(UserPO user) {
        this.user = user;
    }

    @Override
    public BatchOperateResultDTO call() throws Exception {
        /*int insert = testMapper.insert(user);
        return BatchOperateResultDTO.builder().message(insert==1 ? ResultCode.SUCCESS.getMessage() : ResultCode.FAILED.getMessage())
                .userName(user.getName()).build();*/
        return BatchOperateResultDTO.builder().message(ResultCode.SUCCESS.getMessage())
                .userName(user.getName()).build();
    }
}
