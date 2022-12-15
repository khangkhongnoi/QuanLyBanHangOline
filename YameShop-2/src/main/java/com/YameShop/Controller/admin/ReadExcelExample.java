package com.YameShop.Controller.admin;

import java.util.ArrayList;
import java.util.List;

import com.YameShop.domain.Book;
import com.YameShop.domain.Supplier;
import com.YameShop.service.SupplierService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import java.util.Iterator;

 
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("admin/excel")
public class ReadExcelExample {
		public static final int COLUMN_INDEX_ID = 0;
	    public static final int COLUMN_Supplier_Name = 1;
	    public static final int COLUMN_INDEX_Phone = 2;
	    public static final int COLUMN_INDEX_Address = 3;
	    public static final int COLUMN_INDEX_EmailL = 4;
	    @Autowired
	    SupplierService supplierService;
	    @PostMapping("savexcel")
	    @ResponseBody
	    public List<Supplier> listsupplier( @RequestParam String image, @RequestParam Integer status, @RequestParam Integer solimit) throws IOException{
	    	final String excelFilePath = "/Users/caovukhang/Downloads/" + image;
	        final List<Supplier> books = readExcel(excelFilePath);
	       
	        System.err.println(image);
	       for (Supplier supplier : books) {
	    	   System.out.println(supplier.getSupplier_Name());
	    	   supplier.setStatus(0);
	    	   supplierService.Save(supplier);
		}
	       if(status == 2) {
	    	   return supplierService.indAllLimitDong(0, solimit);
	       }else {
	    	   return supplierService.findAllLimitDongStatus(solimit, status);
	       }
	    	
	    }
	    
	    
	    
//	    public static void main(String[] args) throws IOException {
//	        final String excelFilePath = "/Users/caovukhang/Downloads/danhsachncc.xlsx";
//	        final List<Supplier> books = readExcel(excelFilePath);
//	       for (Supplier supplier : books) {
//	    	   System.out.println(supplier.getSupplier_Name());
//		}
	//    }
	    
	    public static List<Supplier> readExcel(String excelFilePath) throws IOException {
	        List<Supplier> listBooks = new ArrayList<>();
	 
	        // Get file
	        InputStream inputStream = new FileInputStream(new File(excelFilePath));
	 
	        // Get workbook
	        Workbook workbook = getWorkbook(inputStream, excelFilePath);
	 
	        // Get sheet
	        Sheet sheet = workbook.getSheetAt(0);
	 
	        // Get all rows
	        Iterator<Row> iterator = sheet.iterator();
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            if (nextRow.getRowNum() == 0) {
	                // Ignore header
	                continue;
	            }
	 
	            // Get all cells
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	 
	            // Read cells and set value for book object
	            Supplier book = new Supplier();
	            while (cellIterator.hasNext()) {
	                //Read cell
	                Cell cell = cellIterator.next();
	                Object cellValue = getCellValue(cell);
	                if (cellValue == null || cellValue.toString().isEmpty()) {
	                    continue;
	                }
	                // Set value for book object
	                int columnIndex = cell.getColumnIndex();
	                switch (columnIndex) {
	                case COLUMN_INDEX_ID:
	           //         book.setId_Supplier(new BigDecimal((double) cellValue).intValue());
	                    break;
	                case COLUMN_Supplier_Name:
	                    book.setSupplier_Name((String) getCellValue(cell));
	                    break;
	                case COLUMN_INDEX_Phone:
	                    
	            //        book.setPhone(new BigDecimal((double) cellValue).intValue());
	                    break;
	                case COLUMN_INDEX_Address:
	                    book.setAddress((String) getCellValue(cell));
	                    break;
	                case COLUMN_INDEX_EmailL:
	                    book.setEmail((String) getCellValue(cell));
	                    break;
	                    
	                default:
	                    break;
	                }
	 
	            }
	            listBooks.add(book);
	        }
	 
	        workbook.close();
	        inputStream.close();
	 
	        return listBooks;
	    }
	 
	    // Get Workbook
	    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
	        Workbook workbook = null;
	        if (excelFilePath.endsWith("xlsx")) {
	            workbook = new XSSFWorkbook(inputStream);
	        } else if (excelFilePath.endsWith("xls")) {
	            workbook = new HSSFWorkbook(inputStream);
	        } else {
	            throw new IllegalArgumentException("The specified file is not Excel file");
	        }
	 
	        return workbook;
	    }
	 
	    // Get cell value
	    private static Object getCellValue(Cell cell) {
	        CellType cellType = cell.getCellTypeEnum();
	        Object cellValue = null;
	        switch (cellType) {
	        case BOOLEAN:
	            cellValue = cell.getBooleanCellValue();
	            break;
	        case FORMULA:
	            Workbook workbook = cell.getSheet().getWorkbook();
	            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
	            cellValue = evaluator.evaluate(cell).getNumberValue();
	            break;
	        case NUMERIC:
	            cellValue = cell.getNumericCellValue();
	            break;
	        case STRING:
	            cellValue = cell.getStringCellValue();
	            break;
	        case _NONE:
	        case BLANK:
	        case ERROR:
	            break;
	        default:
	            break;
	        }
	 
	        return cellValue;
	    }
}
