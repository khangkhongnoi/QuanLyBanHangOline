package com.YameShop.Controller.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.YameShop.domain.Supplier;
@Controller
@RequestMapping("admin/excel")
public class WriteExcelExample {
	public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_Supplier_Name = 1;
    public static final int COLUMN_INDEX_Phone = 2;
    public static final int COLUMN_INDEX_Address = 3;
    public static final int COLUMN_INDEX_EmailL = 4;
    private static CellStyle cellStyleFormatNumber = null;
     
    @GetMapping("exprot")
    public static void main(String[] args) throws IOException {
        final List<Supplier> books = getBooks();
        final String excelFilePath = "/Users/caovukhang/Downloads/khang.xlsx";
        getWorkbook(excelFilePath);
    }
 
//    public static void writeExcel(List<Supplier> books, String excelFilePath) throws IOException {
//        // Create Workbook
//        Workbook workbook = getWorkbook(excelFilePath);
// 
//        // Create sheet
//        Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name
// 
//        int rowIndex = 0;
//         
//        // Write header
//        writeHeader(sheet, rowIndex);
// 
//        // Write data
//        rowIndex++;
//        for (Supplier book : books) {
//            // Create row
//            Row row = sheet.createRow(rowIndex);
//            // Write data on row
//            writeBook(book, row);
//            rowIndex++;
//        }
//         
//        // Write footer
//        //writeFooter(sheet, rowIndex);
// 
//        // Auto resize column witdth
//        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
//        autosizeColumn(sheet, numberOfColumn);
// 
//        // Create file excel
//        createOutputFile(workbook, excelFilePath);
//        System.out.println("Done!!!");
//    }
// 
    // Create dummy data
    private static List<Supplier> getBooks() {
        List<Supplier> listBook = new ArrayList<>();
        Supplier book;
        for (int i = 1; i <= 5; i++) {
            book = new Supplier();
            listBook.add(book);
        }
        return listBook;
    }
 
    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;
 
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
 
        return workbook;
    }
 
    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);
         
        // Create row
        Row row = sheet.createRow(rowIndex);
         
        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");
 
        cell = row.createCell(COLUMN_Supplier_Name);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("supplier_name");
 
        cell = row.createCell(COLUMN_INDEX_Phone);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("phone");
 
        cell = row.createCell(COLUMN_INDEX_Address);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("address");
 
        cell = row.createCell(COLUMN_INDEX_EmailL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Temail");
    }
 
    // Write data
//    private static void writeBook(Supplier book, Row row) {
//        if (cellStyleFormatNumber == null) {
//            // Format number
//            short format = (short)BuiltinFormats.getBuiltinFormat("#,##0");
//            // DataFormat df = workbook.createDataFormat();
//            // short format = df.getFormat("#,##0");
//             
//            //Create CellStyle
//            Workbook workbook = row.getSheet().getWorkbook();
//            cellStyleFormatNumber = workbook.createCellStyle();
//            cellStyleFormatNumber.setDataFormat(format);
//        }
//         
//        Cell cell = row.createCell(COLUMN_INDEX_ID);
//        cell.setCellValue(book.getId_Supplier());
// 
//        cell = row.createCell(COLUMN_Supplier_Name);
//        cell.setCellValue(book.getSupplier_Name());
// 
//        cell = row.createCell(COLUMN_INDEX_Phone);
//        cell.setCellValue(book.getPhone());
//        cell.setCellStyle(cellStyleFormatNumber);
// 
//        cell = row.createCell(COLUMN_INDEX_Address);
//        cell.setCellValue(book.getAddress());
//        // Create cell formula
//        // totalMoney = price * quantity
//        cell = row.createCell(COLUMN_INDEX_EmailL);
//        cell.setCellValue(book.getEmail());
//
//    }
 
    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman"); 
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color
 
        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }
     
    // Write footer
//    private static void writeFooter(Sheet sheet, int rowIndex) {
//        // Create row
//        Row row = sheet.createRow(rowIndex);
//        Cell cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//        cell.setCellFormula("SUM(E2:E6)");
//    }
     
    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }
     
    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
 
}
