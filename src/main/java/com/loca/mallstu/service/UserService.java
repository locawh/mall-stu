package com.loca.mallstu.service;

import com.loca.mallstu.bean.po.UserPO;

/**
 * @author wangHeng
 * @date  2021-03-24 11:28
 */
public interface UserService {

    Boolean addUser(UserPO userPO);
    Boolean updateUser(UserPO userPO);
    Boolean deleteUser(Integer id);
}
