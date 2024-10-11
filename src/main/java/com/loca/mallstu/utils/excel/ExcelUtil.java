package com.loca.mallstu.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
public class ExcelUtil {

    public static Excel readExcel(MultipartFile multipartFile, Integer sheetNo,ExcelTypeEnum excelType) throws Exception {
        List<Map<String,Object>> dataList = new ArrayList<>();
        CustomEventListener customEventListener = new CustomEventListener(dataList);
        ExcelReaderBuilder builder = EasyExcel.read(multipartFile.getInputStream(), customEventListener).excelType(excelType);
        ExcelReaderSheetBuilder excelReaderSheetBuilder = isNull(sheetNo) ? builder.sheet() : builder.sheet(sheetNo);
        excelReaderSheetBuilder.doRead();
        return Excel.builder().dataList(dataList).headMap(customEventListener.getHeadMap()).sheetName(customEventListener.getSheetName()).build();
    }

    public static void writeExcel(Excel excel, File tmpFile){
        EasyExcel.write(tmpFile).sheet(excel.getSheetName()).doWrite(convertToCanWrite(excel.getHeadMap(),excel.getDataList()));
    }

    public static void exportExcel(List<List<Object>> dataList, File tmpFile,String sheetName, String[] columnNames, ExcelTypeEnum excelType) throws IOException {
        try (Workbook workbook = ExcelTypeEnum.XLS == excelType ? new HSSFWorkbook() : new XSSFWorkbook()) {


            Sheet sheet = workbook.createSheet(sheetName);
            // 创建表头行
            Row headerRow = sheet.createRow(0);



            if (nonNull(columnNames)) {
                // 添加列名
                for (int i = 0; i < columnNames.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columnNames[i]);
                }
            }

            int rowNum = 0;
            for (List<Object> rowData : dataList) {
                Row row = sheet.createRow(rowNum++);
                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);
                    cell.setCellValue(nonNull(field)?field.toString():"");
                }
            }
            try (FileOutputStream fos = new FileOutputStream(tmpFile)) {
                workbook.write(fos);
            }
        }
    }

    public static void exportCSV(List<List<Object>> dataList, File tmpFile) throws IOException {
        try (Writer writer = new FileWriter(tmpFile);
            CSVWriter csvWriter = new CSVWriter(writer)) {
            for (List<Object> rowData: dataList) {
                String[] line = new String[rowData.size()];
                for (int i = 0; i < rowData.size(); i++) {
                    Object data = rowData.get(i);
                    line[i] = nonNull(data)? data.toString() : null;
                }
                csvWriter.writeNext(line,false);
            }
        }
    }

    public static List<List<Object>> convertToCanWrite(Map<Integer,String> headMap,List<Map<String,Object>> dataList){
        List<List<Object>> sheetData = new ArrayList<>();
        Map<Integer,String> sortedHeadMap = new LinkedHashMap<>();
        headMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(e->sortedHeadMap.put(e.getKey(),e.getValue()));
        List<Object> headList = new ArrayList<>();
        sortedHeadMap.forEach(headList::add);
        sheetData.add(headList);
        Map<String, Integer> reverseHeadMap = sortedHeadMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (k1, k2) -> k1));
        for (Map<String, Object> dataMap : dataList) {
            List<Object> rowList = new ArrayList<>();
            TreeMap<Integer,Object> sortedValueMap = new TreeMap<>(Comparator.naturalOrder());
            dataMap.forEach((k,v)->{
                sortedValueMap.put(reverseHeadMap.get(k),v);
            });
            sortedValueMap.forEach(rowList::add);
            sheetData.add(rowList);
        }
        return sheetData;
    }

    public static ExcelTypeEnum getExcelType(String suffix){
        switch (suffix.toLowerCase()){
            case "xlsx" -> {
                return ExcelTypeEnum.XLSX;
            }
            case "xls" -> {
                return ExcelTypeEnum.XLS;
            }
            case "csv" ->{
                return ExcelTypeEnum.CSV;
            }
        }
        return null;
    }
}
