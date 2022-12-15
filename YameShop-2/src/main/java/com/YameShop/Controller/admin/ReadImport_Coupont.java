package com.YameShop.Controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Coupon_Details;
import com.YameShop.domain.Import_coupon;
import com.YameShop.domain.NhapHang;
import com.YameShop.domain.Product;
import com.YameShop.domain.Product_Color;
import com.YameShop.domain.Product_details;
import com.YameShop.domain.Size;
import com.YameShop.domain.Supplier;
import com.YameShop.domain.jsonproduct;
import com.YameShop.service.Import_couponService;
import com.YameShop.service.ProductService;
import com.YameShop.service.Product_detailsServices;
import com.YameShop.service.SupplierService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
@RequestMapping("admin/coupon/excel")
public class ReadImport_Coupont {
    @Autowired
    Import_couponService couponService; 
    @Autowired
	ProductService productService;
    @Autowired
    Product_detailsServices detailsServices;
   
    
	public static final int COLUMN_ID = 0;
    public static final int COLUMN_Product_Name = 1;
    public static final int COLUMN_Product_Color = 2;
    public static final int COLUMN_Product_Size = 3;
    public static final int COLUMN_Don_Gia = 4;
    public static final int COLUMN_Quanlity = 5;
  
  
    @PostMapping("savexcel")
    @ResponseBody

    public List<NhapHang> savexcel(@RequestParam String image , ModelMap model) throws IOException {
    	System.err.println(image);
    	final String excelFilePath = "/Users/caovukhang/Downloads/" + image;
    	 final List<NhapHang> books = readExcel(excelFilePath);
    	 Import_coupon import_coupon = new Import_coupon();
    	 List<NhapHang> coupon_Details = new ArrayList<>();
    	

  	   
       for (NhapHang supplier : books) {
    	   NhapHang nhapHang = new NhapHang();
    	   nhapHang.setIdproductl(supplier.getIdproductl());
    	   nhapHang.setName(supplier.getName());
    	   nhapHang.setGia_nHAP(supplier.getGia_nHAP());
    	   nhapHang.setMamau(supplier.getMamau());
    	   nhapHang.setMasize(supplier.getMasize());
    	   nhapHang.setSoluong(supplier.getSoluong());
           coupon_Details.add(nhapHang);
	}
       
  
    	return coupon_Details;
  }
  @PostMapping("savexcelphieunhaphang")
  @ResponseBody
    public String savexcelphieunhaphang( @RequestParam String dataJson, ModelMap model) throws IOException {
    	System.err.println(dataJson);
    	ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
    	jsonNode = objectMapper.readTree(dataJson);

		System.err.println(dataJson);
		Import_coupon import_coupon = new Import_coupon();
		JsonNode jsondetails = jsonNode.get("productdetails");
		Set<Coupon_Details> listDetails = new HashSet<>();
		for (JsonNode productdetails : jsondetails) {
			String idproduct = productdetails.get("idproduct").asText();
			String idcolor = productdetails.get("idproductclor").asText();
			String idsize = productdetails.get("idsize").asText();
			Integer soluong = productdetails.get("quantity").asInt();
			
			Product_details details = detailsServices.finbyidspissizeidcolor(idproduct, idcolor, idsize);
			Product_details product_details = new Product_details();
			product_details.setId_Product_Details(details.getId_Product_Details());
			
			Coupon_Details coupon_Details = new Coupon_Details();
			coupon_Details.setProduct_details(product_details);
			coupon_Details.setQuanlity(soluong);
			coupon_Details.setImport_coupon(import_coupon);
			coupon_Details.setDon_Gia(soluong);
			listDetails.add(coupon_Details);
			
		}
		import_coupon.setStatus(0);
		import_coupon.setCoupon_Details(listDetails);
		couponService.save(import_coupon);
	  return "";
  }
    
//    @PostMapping("savexceltam")
//    @ResponseBody
//
//    public List<Coupon_Details> savexceltam(@RequestParam String image) throws IOException {
//    	System.err.println(image);
//    	final String excelFilePath = "/Users/caovukhang/Downloads/" + image;
//    	 final List<Coupon_Details> books = readExcel(excelFilePath);
//    	 Import_coupon import_coupon = new Import_coupon();
//    	 Set<Coupon_Details> coupon_Details = new HashSet<>();
//  	 
//  	   import_coupon.setStatus(0);
//       for (Coupon_Details supplier : books) {
//    	   System.out.println(supplier.getDVT());
//    	   
//    	Coupon_Details coupon_Details1 = new Coupon_Details();
//    	Product product = new Product();
//    	Product_details product_details = new Product_details();
//    	Size size = new Size();
//    	product.setId_Poroduct(supplier.getProduct_details().getProduct().getId_Poroduct());
//    	product_details.setProduct(product);
//    	coupon_Details1.setProduct_details(product_details);
//    	size
//    	coupon_Details1.setId_Size(supplier.getId_Size());
//    	 coupon_Details1.setId_Product_Color(supplier.getId_Product_Color());
//    	 coupon_Details1.setDVT(supplier.getDVT());
//    	 coupon_Details1.setDon_Gia(supplier.getDon_Gia());
//    	 coupon_Details1.setQuanlity(supplier.getQuanlity());
//    	   coupon_Details.add(coupon_Details1);
//    	 couponService.saves(coupon_Details1);
//    	  import_coupon.setTotal_MoneyInteger(supplier.getQuanlity() * supplier.getDon_Gia());
//    	 
//	}
//    
//       import_coupon.setCoupon_Details(coupon_Details);
//    couponService.save(import_coupon);
//    	return books;
//  }
//    
//    @GetMapping("khang")
//    public static void main(String[] args) throws IOException {
//        final String excelFilePath = "/Users/caovukhang/Downloads/khang10.xlsx";
//        final List<Import_coupon> books = readExcel(excelFilePath);
//       for (Import_coupon supplier : books) {
//    	  System.out.println(supplier.getCoupon_Details());
//    	 
//	}
//       
  //  }
    
    
    public static List<NhapHang> readExcel(String excelFilePath) throws IOException {
        List<NhapHang> listBooks =  new ArrayList<>();
 
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
           
            NhapHang nhapHang = new NhapHang();

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
                case COLUMN_ID:
                	
                	nhapHang.setIdproductl((String) getCellValue(cell));
                    break;
                case COLUMN_Product_Name:
                	nhapHang.setName((String) getCellValue(cell));
                   
                    break;
                 case COLUMN_Product_Color:
                	nhapHang.setMamau((String) getCellValue(cell));
                	 break;
                case COLUMN_Product_Size:
                	nhapHang.setMasize((String) getCellValue(cell));
                    break;
                case COLUMN_Don_Gia:
                	nhapHang.setGia_nHAP(new BigDecimal((double) cellValue).intValue());
                    break;
                case COLUMN_Quanlity:
                   nhapHang.setSoluong(new BigDecimal((double) cellValue).intValue());

                    break;
                    
                default:
                    break;
                }
            }
            listBooks.add(nhapHang);
 
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
    
    
    
    

//	public static List<Coupon_Details> readExcel(String excelFilePath) throws IOException {
//        List<Coupon_Details> listBooks =  new ArrayList<>();
// 
//        // Get file
//        InputStream inputStream = new FileInputStream(new File(excelFilePath));
//        
//        // Get workbook
//        Workbook workbook = getWorkbook(inputStream, excelFilePath);
//       
//        // Get sheet
//        Sheet sheet = workbook.getSheetAt(0);
//     
//        // Get all rows
//        Iterator<Row> iterator = sheet.iterator();
//        while (iterator.hasNext()) {
//            Row nextRow = iterator.next();
//            if (nextRow.getRowNum() == 0) {
//                // Ignore header
//                continue;
//            }
// 
//            // Get all cells
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
// 
//            // Read cells and set value for book object
//            Coupon_Details book = new Coupon_Details();
//            Coupon_Details coupon_Details = new Coupon_Details();
//            Product product = new Product();
//            Product_details product_details = new Product_details();
//            Product_Color product_Color = new Product_Color();
//            Size size = new Size();
//           
//            while (cellIterator.hasNext()) {
//                //Read cell
//                Cell cell = cellIterator.next();
//                Object cellValue = getCellValue(cell);
//              
//                if (cellValue == null || cellValue.toString().isEmpty()) {
//                    continue;
//                }
//                // Set value for book object
//                int columnIndex = cell.getColumnIndex();
//              
//                switch (columnIndex) {
//                case COLUMN_ID:
//                	
//                	
//                	
//                	product.setId_Poroduct((String) getCellValue(cell));
//                	product_details.setProduct(product);
//                	book.setProduct_details(product_details);
//                    break;
//                case COLUMN_Product_Name:
//                  
//                    product.setProduct_Name((String) getCellValue(cell));
//                    product_details.setProduct(product);
//                    book.setProduct_details(product_details);
//                    break;
//                 case COLUMN_Product_Color:
//                	 product_Color.setId_Product_Color((String) getCellValue(cell));
//                	 product_details.setProduct_Color(product_Color);
//                	 book.setProduct_details(product_details);
//                	 break;
//                case COLUMN_Product_Size:
//                	size.setId_Size((String) getCellValue(cell));
//                	product_details.setSize(size);
//                	book.setProduct_details(product_details);
//                	 break;
//                case COLUMN_DVT:
//                    book.setDVT((String) getCellValue(cell));
//                  
//                    break;
//                case COLUMN_Don_Gia:
//                    book.setDon_Gia(new BigDecimal((double) cellValue).intValue());
//                    break;
//                case COLUMN_Quanlity:
//                    book.setQuanlity(new BigDecimal((double) cellValue).intValue());
//                    ;
//                    break;
//                    
//                default:
//                    break;
//                }
// 
//            }
//            listBooks.add(book);
//           
//        }
//      
//        workbook.close();
//        inputStream.close();
// 
//        return listBooks;
//    }
// 
//    // Get Workbook
//    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
//        Workbook workbook = null;
//        if (excelFilePath.endsWith("xlsx")) {
//            workbook = new XSSFWorkbook(inputStream);
//        } else if (excelFilePath.endsWith("xls")) {
//            workbook = new HSSFWorkbook(inputStream);
//        } else {
//            throw new IllegalArgumentException("The specified file is not Excel file");
//        }
// 
//        return workbook;
//    }
// 
//    // Get cell value
//    private static Object getCellValue(Cell cell) {
//        CellType cellType = cell.getCellTypeEnum();
//        Object cellValue = null;
//        switch (cellType) {
//        case BOOLEAN:
//            cellValue = cell.getBooleanCellValue();
//            break;
//        case FORMULA:
//            Workbook workbook = cell.getSheet().getWorkbook();
//            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//            cellValue = evaluator.evaluate(cell).getNumberValue();
//            break;
//        case NUMERIC:
//            cellValue = cell.getNumericCellValue();
//            break;
//        case STRING:
//            cellValue = cell.getStringCellValue();
//            break;
//        case _NONE:
//        case BLANK:
//        case ERROR:
//            break;
//        default:
//            break;
//        }
// 
//        return cellValue;
//    }

}
