package com.loca.mallstu.bean.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description
 * @ClassName ExcelDataTestDTO
 * @Author locawong
 * @Date 2024/10/11 17:10
 */
@Data
@AllArgsConstructor
public class ExcelDataTestDTO {

	@ExcelProperty("name")
	private String name;

	@ExcelProperty("age")
	private Integer age;

	@ExcelProperty("sex")
	private String sex;

	@ExcelProperty("date")
	private String date;

}
