package com.Httb.bookManage.util;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    public static List<Object> excelToList(InputStream inputStream) {
        List<Object> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            //工作表对象
            Sheet sheet = workbook.getSheetAt(0);
            //总行数
            int rowLength = sheet.getLastRowNum();
            //工作表的列
            Row row = sheet.getRow(0);
            //总列数
            int colLength = row.getLastCellNum();
            //得到指定的单元格
            Cell cell = row.getCell(0);
            for (int i = 0; i < rowLength+1; i++) {
                row = sheet.getRow(i);
                List<Object> rowList = new ArrayList<>();
                for (int j = 0; j < colLength; j++) {
                    cell = row.getCell(j);
                    //设置单元格类型
                    cell.setCellType(CellType.STRING);
                    //获取单元格数据
                    String cellValue = cell.getStringCellValue();
                    rowList.add(cellValue);
                    System.out.println(rowList);
                }
                list.add(rowList);
            }
        } catch (Exception e) {
            LOGGER.error("parse excel file error :", e);
        }
        return list;
    }


}