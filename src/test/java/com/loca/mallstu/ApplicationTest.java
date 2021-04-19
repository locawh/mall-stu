package com.loca.mallstu;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.loca.mallstu.bean.dto.BatchOperateResultDTO;
import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.common.CommonResult;
import com.loca.mallstu.service.MultithreadingService;
import com.loca.mallstu.service.UserService;
import com.loca.mallstu.service.UmsMemberService;
import com.loca.mallstu.utils.RandInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UmsMemberService umsMemberService;

    @Autowired
    private MultithreadingService multithreadingService;


    @Test
    public void addUserTest() {
        UserPO userPO = new UserPO();
        userPO.setName("loca");
        userPO.setAge(211);
        Boolean aBoolean = userService.addUser(userPO);
        System.out.println(aBoolean);
    }

    @Test
    public void updateUserTest() {
        UserPO userPO = new UserPO();
        userPO.setId(1);
        userPO.setName("loca");
        userPO.setAge(111);
        Boolean aBoolean = userService.updateUser(userPO);
        System.out.println(aBoolean);
    }

     @Test
    public void deleteUserTest() {
        Boolean aBoolean = userService.deleteUser(1);
        System.out.println(aBoolean);
    }



    @Test
    public void batchCreateUser() {
        ArrayList<UserPO> users = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            UserPO userPO = new UserPO();
            userPO.setName(RandInfoUtils.randFamilyName());
            userPO.setAge(RandInfoUtils.randAge());
            users.add(userPO);
        }
        //CommonResult<List<BatchOperateResultDTO>> resultCallable = multithreadingService.createUserBatchCallable(users);
        CommonResult<List<BatchOperateResultDTO>> resultCompletableFuture = multithreadingService.createUserBatchCompletableFuture(users);
        log.info("返回结果集={}", JSON.toJSONString(resultCompletableFuture));
    }
}
