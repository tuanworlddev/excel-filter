package com.tuandev.excelhandle.controllers;

import com.tuandev.excelhandle.models.Object1;
import com.tuandev.excelhandle.models.Object2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Convert {
    public static List<Object1> convertObject1(File file) throws IOException {
        List<Object1> list = new ArrayList<>();
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 Workbook workbook = new XSSFWorkbook(fis)) {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                if (rowIterator.hasNext()) {
                    rowIterator.next();
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Long orderNumber = getCellValueAsLong(row.getCell(2));
                    Long chrtId = getCellValueAsLong(row.getCell(3));
                    String sellerArticle = getCellValueAsString(row.getCell(4));
                    String wBarcodeArticle = getCellValueAsString(row.getCell(5));
                    String productName = getCellValueAsString(row.getCell(6));
                    String packagingDimension = getCellValueAsString(row.getCell(7));
                    String weight = getCellValueAsString(row.getCell(8));
                    String size = getCellValueAsString(row.getCell(9));
                    String color = getCellValueAsString(row.getCell(10));
                    String brand = getCellValueAsString(row.getCell(11));
                    String productCode = getCellValueAsString(row.getCell(12));
                    String sticker = getCellValueAsString(row.getCell(13));
                    String stickerRead = getCellValueAsString(row.getCell(14));

                    Object1 object1 = new Object1(orderNumber, chrtId, sellerArticle, wBarcodeArticle, productName, packagingDimension, weight, size, color, brand, productCode, sticker, stickerRead);
                    list.add(object1);
                }
            }
        }
        return list;
    }

    public static List<Object2> convertObject2(File file) throws IOException {
        List<Object2> list = new ArrayList<>();
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 Workbook workbook = new XSSFWorkbook(fis)) {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                if (rowIterator.hasNext()) {
                    rowIterator.next();
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    String jobNo = getCellValueAsString(row.getCell(1));
                    String brand = getCellValueAsString(row.getCell(3));
                    String name = getCellValueAsString(row.getCell(4));
                    String size = getCellValueAsString(row.getCell(5));
                    String color = getCellValueAsString(row.getCell(6));
                    String sellerCode = getCellValueAsString(row.getCell(7));
                    String sticker = getCellValueAsString(row.getCell(8));
                    String barcode = getCellValueAsString(row.getCell(9));

                    Object2 object2 = new Object2(jobNo, brand, name, size, color, sellerCode, sticker, barcode);
                    list.add(object2);
                }
            }
        }
        return list;
    }

    private static Long getCellValueAsLong(Cell cell) {
        return cell == null ? null : (long) cell.getNumericCellValue();
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return null;
        }
    }
}
