package com.loca.mallstu.service.impl;

import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.dao.TestMapper;
import com.loca.mallstu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangHeng
 * @date  2021-03-24 11:29
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public Boolean addUser(UserPO user) {
        int count = testMapper.insert(user);
        return count == 1;
    }
}
