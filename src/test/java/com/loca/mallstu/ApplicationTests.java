package com.loca.mallstu;

import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.service.TestService;
import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private TestService testService;


    @Test
    public void addUserTest() {
        UserPO userPO = new UserPO();
        userPO.setName("loca");
        userPO.setAge(25);
        Boolean aBoolean = testService.addUser(userPO);
        System.out.println(aBoolean);
    }
}
