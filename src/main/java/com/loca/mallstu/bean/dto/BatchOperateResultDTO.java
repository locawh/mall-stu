package com.loca.mallstu.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangHeng
 * @date  2021-04-16 10:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchOperateResultDTO {

    private String userName;
    private String message;

}
