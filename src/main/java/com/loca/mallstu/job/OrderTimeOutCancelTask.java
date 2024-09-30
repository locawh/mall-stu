package com.loca.mallstu.job;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.dao.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author wangheng
 * @version 1.0
 * @date 2021-04-05 15:49
 */
@Slf4j
@Component
public class OrderTimeOutCancelTask {

    @Autowired
    private UserMapper userMapper;


    //@Scheduled(cron = "*/5 * * * * ?")
    public void cancelTimeOutOrder() {
       log.info("执行定时任务，时间={}", LocalDateTime.now());
        userMapper.selectList(null);
        List<UserPO> users = userMapper.selectList(Wrappers.lambdaQuery());
        users.forEach(userPO -> log.info("user info =={}", JSON.toJSONString(userPO)));
        System.out.println("test");
    }
}
