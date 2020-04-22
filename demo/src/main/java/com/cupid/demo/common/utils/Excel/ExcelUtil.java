package com.cupid.demo.common.utils.Excel;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    /**
     * Excel读取数据操作
     * @param inputStream 输入流
     * @return 二维列表对象，第一行为表头名称
     */
    public List<List<Object>> readExcel(InputStream inputStream) throws Exception{
            List<List<Object>> rows = new ArrayList();// 所有行
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(inputStream);
            Sheet sheet = wb.getSheetAt(0);// 默认取第一个sheet
            Object value = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化日期字符串
            int rowNum = sheet.getPhysicalNumberOfRows();
            //读取行数
            for (int i = sheet.getFirstRowNum(); i <= sheet
                    .getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    break;
                }
                List<Object> list = new ArrayList<Object>();
                for (int j = row.getFirstCellNum(); j <= row
                        .getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) {
                        continue;
                    }
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                value = sdf.format(DateUtil.getJavaDate(cell
                                        .getNumericCellValue()));
                            } else {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                String temp = cell.getStringCellValue();
                                if (temp.indexOf(".") != -1) {
                                    value = String.valueOf(new Double(temp))
                                            .trim();
                                } else {
                                    value = temp.trim();
                                }
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = cell.getBooleanCellValue();
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            value = cell.getCellFormula();
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            value = "";
                            break;
                        default:
                            value = cell.toString();
                    }
                    list.add(value);
                }
                rows.add(list);
            }
            return rows;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 更新指定列里面指定列内容
     * @param sheet
     */
    public static void updateColumns(XSSFSheet sheet, int column, String content) {
        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                break;
            }
            Cell cell = row.getCell(column);
            if (cell == null) {
                continue;
            }
            cell.setCellValue(content);
            row.removeCell(cell);
        }
    }
}