package com.loca.mallstu.service.impl;

import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.dao.UserMapper;
import com.loca.mallstu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangHeng
 * @date  2021-03-24 11:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean addUser(UserPO user) {
        int count = userMapper.insert(user);
        return count == 1;
    }

    @Override
    public Boolean updateUser(UserPO userPO) {
        int count = userMapper.updateById(userPO);
        return count == 1;
    }

    @Override
    public Boolean deleteUser(Integer id) {
        int count = userMapper.deleteById(id);
        return count == 1;
    }
}
