package com.loca.mallstu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.loca.mallstu.bean.po.RolePO;
import com.loca.mallstu.bean.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangHeng
 * @date  2021-03-24 11:38
 */
@Mapper
public interface RoleMapper extends BaseMapper<RolePO> {

}
