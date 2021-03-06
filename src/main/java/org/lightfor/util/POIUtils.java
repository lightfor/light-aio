package org.lightfor.util;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * POI工具类
 * @author Light
 */
public enum  POIUtils {
    INSTANCE;

    public static void writeToFile(OutputStream os, String sheetName, String[] titles, String[] cells, List<Map<String,String>> excelList){
        SXSSFWorkbook  wb = new SXSSFWorkbook(100);
        Sheet sheet = wb.createSheet(sheetName);
        CellStyle style = wb.createCellStyle();
        //style.setAlignment(SXSSFWorkbook.ALIGN_CENTER);
        Font font = wb.createFont();
        font.setFontName(HSSFFont.FONT_ARIAL);
        font.setFontHeightInPoints((short) 10);
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //font.setColor(HSSFColor.BLACK.index);
        style.setFont(font);
        Row titleRow = sheet.createRow(0);
        handleData(titles, cells, excelList, sheet, style, titleRow);
        sheet.setDefaultColumnWidth((short) 20);
        try {
            wb.write(os);
            os.close();
        } catch (Exception e) {
            LogUtils.error("写入异常", e);
        }
        try {
            wb.dispose();
        } catch (Exception e) {
            LogUtils.error("清理临时文件异常",e);
        }

    }

    private static void handleData(String[] titles, String[] cells, List<Map<String, String>> excelList, Sheet sheet, CellStyle style, Row titleRow) {
        for (int i = 0; i < titles.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(new XSSFRichTextString(titles[i]));
            cell.setCellStyle(style);
        }
        if(excelList != null){
            for (int i = 0; i < excelList.size(); i++) {
                int maxRow = SpreadsheetVersion.EXCEL2007.getLastRowIndex() - 1;
                if (i > maxRow){
                    return;
                }
                Row tmpRow = sheet.createRow(i + 1);
                for(int j = 0 ; j < cells.length ; j++){
                    tmpRow.createCell(j).setCellValue(excelList.get(i).get(cells[j]));
                }
            }
        }
    }

    public static void read(String fileName){
        try {
            DateUtils.start();
            LogUtils.info(DateUtils.count());
            FileInputStream excelFile = new FileInputStream(fileName);
            LogUtils.info(DateUtils.count());
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)
                    .bufferSize(4096)
                    .open(excelFile);
            LogUtils.info(DateUtils.count());
            Sheet sheet = workbook.getSheetAt(0);
            LogUtils.info(DateUtils.count());
            for (Row currentRow : sheet) {
                for (Cell currentCell : currentRow) {
                    if(currentCell.getCellType() == CellType.STRING){
                        System.out.print(currentCell.getStringCellValue());
                    } else if(currentCell.getCellType() == CellType.NUMERIC){
                        System.out.print(currentCell.getNumericCellValue());
                    } else if(currentCell.getCellType() == CellType.FORMULA){
                        //System.out.print(currentCell.getCellFormula());
                    }

                }
                System.out.println();
            }
        } catch (Exception e) {
            LogUtils.error("读取文件出错", e);
        }

    }
}
