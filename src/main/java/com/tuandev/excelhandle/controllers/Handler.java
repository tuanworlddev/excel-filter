package com.tuandev.excelhandle.controllers;

import com.tuandev.excelhandle.models.Object1;
import com.tuandev.excelhandle.models.Object2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler {
    public static void export1Handle(File excelFile, List<Object1> object1List, List<Object2> object2List) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Row headerRow = sheet.createRow(0);
            CellStyle headerStyle = workbook.createCellStyle();

            headerStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Font font = workbook.createFont();
            font.setColor(IndexedColors.BLACK.getIndex());
            headerStyle.setFont(font);

            List<String> headerValue = List.of(
                    "Стикер № 1", "Стикер № 2", "QR-Код", "Артикул-цвет", "Размер", "Баркод", "Бренд", "Продавец", "QR-Ч-Знак", "Предмет"
            );

            for (int i = 0; i < headerValue.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headerValue.get(i));
                cell.setCellStyle(headerStyle);
            }

            Map<String, Object1> object1Map = new HashMap<>();
            for (Object1 obj1 : object1List) {
                object1Map.put(obj1.getSticker(), obj1);
            }
            int cellIndex = 1;

            for (Object2 obj2 : object2List) {
                Object1 obj1 = object1Map.get(obj2.getSticker());
                if (obj1 != null) {
                    Row row = sheet.createRow(cellIndex);
                    String[] stickerSplit = obj1.getSticker().split(" ");
                    row.createCell(0).setCellValue(stickerSplit[0]);
                    row.createCell(1).setCellValue(stickerSplit[1]);
                    row.createCell(2).setCellValue(obj1.getStickerRead());
                    row.createCell(3).setCellValue(obj1.getSellerArticle());
                    row.createCell(4).setCellValue(obj1.getSize());
                    row.createCell(5).setCellValue(obj2.getBarcode());
                    row.createCell(6).setCellValue(obj1.getBrand());
                    row.createCell(7).setCellValue("ИП ДИНЬ ТХИ МАЙ");
                    String[] nameSplit = obj2.getName().split(" ");
                    row.createCell(8).setCellValue("");
                    row.createCell(9).setCellValue(nameSplit[0] + " " + nameSplit[1]);
                    cellIndex++;
                }
            }

            for (int i = 0; i < 10; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
                workbook.write(fileOut);
            }
        }
    }

    public static void export2Handle(File excelFile, List<Object2> object2List) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet();
            Row headerRow = sheet.createRow(0);

            List<String> headerValue = List.of(
                    "№ задания", "Стикер", "КИЗ"
            );

            for (int i = 0; i < headerValue.size(); i++) {
                headerRow.createCell(i).setCellValue(headerValue.get(i));
            }

            int cellIndex = 1;

            for (Object2 obj2 : object2List) {
                Row row = sheet.createRow(cellIndex);
                row.createCell(0).setCellValue(obj2.getJobNo());
                row.createCell(1).setCellValue(obj2.getSticker().replace(" ", ""));
                row.createCell(2).setCellValue("");
                cellIndex++;
            }

            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fileOut = new FileOutputStream(excelFile)) {
                workbook.write(fileOut);
            }

        }
    }
}
