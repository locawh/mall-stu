package com.loca.mallstu.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CustomEventListener extends AnalysisEventListener<Map<String,Object>> {
    private List<Map<String,Object>> dataList;
    private Map<Integer,String> headMap;
    private String sheetName;

    public CustomEventListener(List<Map<String,Object>> dataList){
        this.dataList = dataList;
    }

    @Override
    public void invoke(Map<String, Object> valueMap, AnalysisContext analysisContext) {
        Map<String, Object> dataMap = new HashMap<>();
        for(int i=0;i<valueMap.size();i++){
            String key=headMap.get(i);
            Object value=valueMap.get(i);
            dataMap.put(key,value);
        }
        dataList.add(dataMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headMap=headMap;
        this.sheetName = context.readSheetHolder().getSheetName();
    }
}
