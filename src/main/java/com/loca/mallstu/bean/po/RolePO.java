package com.loca.mallstu.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wangHeng
 * @date  2021-04-19 15:44
 */
@Data
@TableName(value = "ms_user", autoResultMap = true)
public class RolePO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "role_name")
    private String roleName;
}
