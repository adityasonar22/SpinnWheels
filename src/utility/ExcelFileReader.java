package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelFileReader {

    private XSSFWorkbook workbook;

    public ExcelFileReader(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> readIntegers(String sheetName, int columnNumber) {
        List<Integer> integers = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell cell = row.getCell(columnNumber);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                integers.add((int) cell.getNumericCellValue());
            }
        }

        return integers;
    }

    public List<String> readXPaths(String sheetName, int columnNumber) {
        List<String> xpaths = new ArrayList<>();
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell cell = row.getCell(columnNumber);
            if (cell != null && cell.getCellType() == CellType.STRING) {
                xpaths.add(cell.getStringCellValue());
            }
        }

        return xpaths;
    }

    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}