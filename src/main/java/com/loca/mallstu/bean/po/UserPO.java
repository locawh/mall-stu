package com.loca.mallstu.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangHeng
 * @date  2021-03-24 11:30
 */
@Data
@TableName(value = "user", autoResultMap = true)
public class UserPO implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "age")
    private Integer age;
}
