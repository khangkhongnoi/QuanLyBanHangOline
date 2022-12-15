package com.YameShop.Controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Coupon_Details;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.MyItem;
import com.YameShop.domain.Product;
import com.YameShop.domain.Product_details;
import com.YameShop.service.ReportService;

@Controller
@RequestMapping("report")
public class ReportController {
	@Autowired
	ReportService reportService;
	
	@GetMapping("char")
	@Transactional
	public String Report(ModelMap model) throws ParseException {
		Date date = new Date();
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String data = simpleDateFormat.format(new Date());
		
		
		List<Invoice> listinvoi1 = new ArrayList<>();
//		List<MyItem> listItem = reportService.reportNgay(date, 7);
//		System.err.println(listItem.toString());
//		for (MyItem myItem : listItem) {
//			
//		}
//		model.put("list", listItem);
		
		for(int i = 0 ; i<=6 ; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(calendar.DATE, -i);
			Date t = calendar.getTime();
			
			String d = simpleDateFormat.format(t);
			
			Invoice invoice3 = new Invoice();
			int imvoilist = reportService.finbyngay(d);
			invoice3.setBooking_Date(d);
			invoice3.setTotal_money(imvoilist);
			listinvoi1.add(invoice3);
			
			System.err.println(d);
			System.err.println(imvoilist);
		
		}
		
		model.addAttribute("list", listinvoi1);
	//	return listinvoi1;
		
		return "admin/reportNgay/reportNgay";
	}

	
	
	@GetMapping("ProductReport")
	public String ProductReport(ModelMap model) {
		List<Object> list = reportService.finbytop10();
		//model.addAttribute("list", listtop);
		System.err.println(list);
		Iterator itr = list.iterator();
		List<Invoice_details> listinvoi1 = new ArrayList<>();
		while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   //now you have one array of Object for each row
			   String client = String.valueOf(obj[1]); // don't know the type of column CLIENT assuming String 
			   String service = String.valueOf(obj[0]);
			   Integer service1 = Integer.parseInt(String.valueOf(obj[2])); //SERVICE assumed as int
			   //same way for all obj[2], obj[3], obj[4]
			
			   
			  Product product = new Product();
			  product.setProduct_Name(client);
			  Product_details product_details = new Product_details();
			  product_details.setProduct(product);
			   Invoice_details details = new Invoice_details();
			  
			   listinvoi1.add(details);
			   System.err.println(client);
			   System.err.println(service1);
			   System.err.println(service);
		}
		model.addAttribute("list", listinvoi1);
		return "admin/reportNgay/reportTop";
	}
	
	@GetMapping("ngay")
	@ResponseBody
	public String ngay(@RequestParam String d , @RequestParam String t) {
		System.err.println(d);
//		String pattern = "dd-MM-yyyy";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		String data = simpleDateFormat.format(new Date());
//		String t = simpleDateFormat.format(d);
//		System.err.println(t);
		LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
		System.err.println(previousMonthSameDay);
		return"";
	}
	
	@GetMapping("ngay1")
	@ResponseBody
	public List<Invoice> ngay1() {
		
//		String pattern = "dd-MM-yyyy";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		String data = simpleDateFormat.format(new Date());
//		String t = simpleDateFormat.format(d);
//		System.err.println(t);
//		LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
//		LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12")
//		  .with(TemporalAdjusters.firstDayOfMonth());
//		LocalDate firstDayOfMonth1 = LocalDate.parse("2016-06-12")
//				  .with(TemporalAdjusters.lastDayOfMonth());
//		System.err.println(firstDayOfMonth1);
		
	        Calendar instance = Calendar.getInstance();
	        int year = instance.get(Calendar.YEAR);
	        
		List<Object> list = reportService.finbyMonth(year);
		Iterator itr = list.iterator();
		List<Invoice> listinvoi1 = new ArrayList<>();
		while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   //now you have one array of Object for each row
			   String client = String.valueOf(obj[0]); // don't know the type of column CLIENT assuming String 
			   Integer service = Integer.parseInt(String.valueOf(obj[1])); //SERVICE assumed as int
			   //same way for all obj[2], obj[3], obj[4]
			   Invoice invoice = new Invoice();
			   invoice.setBooking_Date(client);
			   invoice.setTotal_money(service);
			   listinvoi1.add(invoice);
			   System.err.println(client);
			   
			   System.err.println(service);
		}
		return listinvoi1; 
	}
	
@GetMapping("charngay")
	@ResponseBody
	public List<Invoice> ReportNgay(ModelMap model) {

	Date date = new Date();
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	String data = simpleDateFormat.format(new Date());
	
	
	List<Invoice> listinvoi1 = new ArrayList<>();
//	List<MyItem> listItem = reportService.reportNgay(date, 7);
//	System.err.println(listItem.toString());
//	for (MyItem myItem : listItem) {
//		
//	}
//	model.put("list", listItem);
	
	for(int i = 1 ; i<=6 ; i++) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DATE, -i);
		Date t = calendar.getTime();
		String d = simpleDateFormat.format(t);
	
		Invoice invoice3 = new Invoice();
		int imvoilist = reportService.finbyngay(d);
		invoice3.setBooking_Date(d);
		invoice3.setTotal_money(imvoilist);
		listinvoi1.add(invoice3);
		
		System.err.println(d);
		System.err.println(imvoilist);
		
		
	}
	model.addAttribute("listinvoi1",listinvoi1);
	return listinvoi1;
}
//@GetMapping("top10")
//public List<Invoice_details> listtop10(){
//	
//}
@GetMapping("charMonth")
@ResponseBody
public List<Invoice> charMonth(ModelMap model) {

	Calendar instance = Calendar.getInstance();
    Integer year = instance.get(Calendar.YEAR);
    Integer songay = instance.get(Calendar.DAY_OF_MONTH);
    System.out.println(LocalDate.now());
	List<Object> list = reportService.finbyMonth(year);
	Iterator itr = list.iterator();
	List<Invoice> listinvoimoth = new ArrayList<>();
	while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next();
		   //now you have one array of Object for each row
		   String client = String.valueOf(obj[0]); // don't know the type of column CLIENT assuming String 
		//   Integer service = Integer.parseInt(String.valueOf(obj[1])); //SERVICE assumed as int
		   String thang = String.valueOf(obj[1]);
		   //same way for all obj[2], obj[3], obj[4]
		   Invoice invoice = new Invoice();
		   invoice.setCustomer_Name("Tháng " +thang);
		   invoice.setBooking_Date(client);
		  
		   listinvoimoth.add(invoice);
		
		   
	}
	return listinvoimoth;
}

@GetMapping("ngaybtandngaykt")
@ResponseBody
public List<Invoice> ngaybtandngaykt(@RequestParam String ngaybt, @RequestParam String ngaykt){
	
	List<Object> list = reportService.fiadAllTTKT(ngaybt, ngaykt);
	
	Iterator itr = list.iterator();
	List<Invoice> listinvoi1 = new ArrayList<>();
	while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next();
		   //now you have one array of Object for each row
		   String client = String.valueOf(obj[1]); // don't know the type of column CLIENT assuming String 
		   Integer service = Integer.parseInt(String.valueOf(obj[0])); //SERVICE assumed as int
		   //same way for all obj[2], obj[3], obj[4]
		   Invoice invoice = new Invoice();
		   invoice.setBooking_Date(client);
		   invoice.setTotal_money(service);
		   listinvoi1.add(invoice);
		   System.err.println(client);
		   
		   System.err.println(service);
	}
	return listinvoi1; 
}
}
