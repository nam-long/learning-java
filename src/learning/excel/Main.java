package learning.excel;

import learning.json.Employee;
import learning.json.FileIO;
import learning.json.simple.Simple;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        final String filename = "resources/employee.json";

        final String jsonString = FileIO.readText(filename);

        Employee employee = Simple.load(jsonString);

        System.out.println("Employee loaded: " + employee);

        export("employee.xlsx", employee);
    }

    public static void export(String filename, Employee employee) throws IOException {

        final int NAME_COLUMN = 0;
        final int AGE_COLUMN = 1;
        final int MARRIED_COLUMN = 2;

        Workbook wb = new XSSFWorkbook();

        Sheet sheet = wb.createSheet("Employee");
        Cell cell;
        int rowCount = 0;

        /**
         * Định dạng cho Header Cells: chọn màu nền,...
         */
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Đặt chiều rộng của cột 'NAME'
        sheet.setColumnWidth(NAME_COLUMN, 10000);

        /**
         * Tạo header ở dòng đầu tiên
         */
        Row header = sheet.createRow(rowCount);

        cell = header.createCell(NAME_COLUMN);
        cell.setCellValue("NAME");
        cell.setCellStyle(headerStyle);

        cell = header.createCell(AGE_COLUMN);
        cell.setCellValue("AGE");
        cell.setCellStyle(headerStyle);

        cell = header.createCell(MARRIED_COLUMN);
        cell.setCellValue("MARRIED");
        cell.setCellStyle(headerStyle);

        // Tăng giá trị để tạo dòng mới
        rowCount++;

        /**
         * Nội dung chính cần export
         */
        Row row = sheet.createRow(rowCount);

        cell = row.createCell(NAME_COLUMN);
        cell.setCellValue(employee.getName());

        cell = row.createCell(AGE_COLUMN);
        cell.setCellValue(employee.getAge());

        cell = row.createCell(MARRIED_COLUMN);
        cell.setCellValue(employee.isMarried() ? "Yes" : "No");

        // Tăng giá trị để tạo dòng mới
        rowCount++;

        /**
         * Ghi xuống tập tin
         */
        FileOutputStream fis = new FileOutputStream(filename);
        wb.write(fis);
        wb.close();
    }
}
