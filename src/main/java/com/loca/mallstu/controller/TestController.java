package com.loca.mallstu.controller;

import com.alibaba.excel.EasyExcel;
import com.loca.mallstu.bean.dto.ExcelDataTestDTO;
import com.loca.mallstu.bean.po.UserPO;
import com.loca.mallstu.common.CommonResult;
import com.loca.mallstu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangHeng
 * @date  2021-03-23 16:33
 */
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public void test(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        OutputStream outputStream=response.getOutputStream();

        List<ExcelDataTestDTO> ExcelDataTestDTOList = new ArrayList<>();
        ExcelDataTestDTOList.add(new ExcelDataTestDTO("Alice", 1, "男", LocalDateTime.now().toString()));
        ExcelDataTestDTOList.add(new ExcelDataTestDTO("sdfs", 2, "女", LocalDateTime.now().minusDays(1).toString()));
        ExcelDataTestDTOList.add(new ExcelDataTestDTO("sdfwer", 3, "男", LocalDateTime.now().minusDays(2).toString()));

        // 使用 EasyExcel 写 Excel 文件
        EasyExcel.write(outputStream, ExcelDataTestDTO.class)
                .password("123456")
                .sheet("sheet_data")  // 设置 Excel 页签名
                .doWrite(ExcelDataTestDTOList);  // 写入数据
        outputStream.flush();
        outputStream.close();
    }

    @RequestMapping("addUser")
    public Boolean addUser() {
        System.out.println("sdfsd");
        UserPO userPO = new UserPO();
        userPO.setName("loca");
        userPO.setAge(26);
        return userService.addUser(userPO);
    }

}
