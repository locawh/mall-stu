package com.loca.mallstu.utils.excel;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Data
@Builder
public class Excel {
    private List<Map<String,Object>> dataList;
    private Map<Integer,String> headMap;
    private String sheetName;

    public boolean isEmpty(){
        return MapUtils.isEmpty(headMap) && isBlank(sheetName) && CollectionUtils.isEmpty(dataList);
    }
}
