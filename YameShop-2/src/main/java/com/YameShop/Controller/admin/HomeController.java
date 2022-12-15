package com.YameShop.Controller.admin;


import java.security.Principal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.YameShop.domain.AppUser;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.MyCounter;
import com.YameShop.domain.Product;
import com.YameShop.domain.Product_details;
import com.YameShop.domain.jsondetails;
import com.YameShop.domain.jsonproduct;
import com.YameShop.service.AccountSerive;
import com.YameShop.service.CustomUserServices;
import com.YameShop.service.InvoiceService;
import com.YameShop.service.LuuLuongTruyCapServices;
import com.YameShop.service.ProductService;
import com.YameShop.service.Product_detailsServices;
import com.YameShop.service.ReportService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.util.LevelToSyslogSeverity;

@Controller
@RequestMapping("admin")
@SessionAttributes("mycounter")
public class HomeController {
@Autowired
ReportService reportService;
@Autowired
LuuLuongTruyCapServices luongTruyCapServices;
@Autowired
ProductService productService;
@Autowired
InvoiceService invoiceService;
@Autowired
AccountSerive accountSerive;
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
		@GetMapping("dashboard")
		public String HomeDashboard(ModelMap model, Principal principal ) {
			Calendar instance = Calendar.getInstance();
	        Integer year = instance.get(Calendar.YEAR);
	        Integer songay = instance.get(Calendar.DAY_OF_MONTH);
	        System.out.println(LocalDate.now());
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
				
				   
			}
			LocalDate timee = LocalDate.now();
			
			List<Invoice> listngay = reportService.finbystartTimea(timee);
			int sum = 0;
			for (Invoice invoice : listngay) {
				sum = sum + invoice.getTotal_money();
				
			}
			double tong = sum;
			Date date = new Date();
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String data = simpleDateFormat.format(new Date());
			Calendar calendar = Calendar.getInstance();
			calendar.add(calendar.DATE, -1);
			Date t = calendar.getTime();
			String d = simpleDateFormat.format(t);
			List<Invoice> listngayhonqua = reportService.finbyYesterday(d);
			int sumngayhonqua = 0;
			for (Invoice invoice1 : listngayhonqua) {
				sumngayhonqua = sumngayhonqua + invoice1.getTotal_money(); 
			}
			int tong1 = sumngayhonqua;
			System.err.println(tong);
			System.err.println(tong1);
			
			double tongphantram = tong - tong1;
			double tongne = (tongphantram / tong1) * 100;
			
			DecimalFormat df= new DecimalFormat("0.00");
			
			String k = df.format(tongne);
			System.err.println(k);
			model.addAttribute("phantram" , k);
			model.addAttribute("list", listinvoi1);
			model.addAttribute("tongtien", tongphantram);
			
			
			
			List<Object> finbytop10 = reportService.finbytop10();
			
			Iterator itr10 = finbytop10.iterator();
			 List<Product_details> liststring = new ArrayList<>();
			 
			while(itr10.hasNext()){
				   Object[] obj = (Object[]) itr10.next();
				   //now you have one array of Object for each row
				   String productname = String.valueOf(obj[1]); // don't know the type of column CLIENT assuming String 
				   Long idproductdetail = Long.parseLong(String.valueOf(obj[0])); //SERVICE assumed as int
				   String Mausac = String.valueOf(obj[2]);
				   String sizename = String.valueOf(obj[3]);
				   Integer tongsoluong = Integer.parseInt(String.valueOf(obj[4]));
				   System.err.println(tongsoluong);
				  
				   Product_details details = new Product_details();
				   details.setId_Product_Details(idproductdetail);
				  // details.set(productname + ", " + Mausac + ", " + sizename);
				  details.setHDSD(productname + ", " + Mausac + ", " + sizename);
				   details.setQuantity(tongsoluong);
				   
				   liststring.add(details);
				   
			}
			
			MyCounter counter = luongTruyCapServices.findbyid(1);
			model.addAttribute("count", counter);
			System.err.println(counter.getCount());
			
			
			
			List<Product> listpoructview = productService.findByViewTop();
			Set<jsondetails> jsondetailsadd = new HashSet<>();
			for (Product product : listpoructview) {
				jsondetails jsondetail = new jsondetails();
				Product product2 = new Product();
				product2.setProduct_Name(product.getProduct_Name());
				product2.setId_Poroduct(product.getId_Poroduct());
				jsondetail.setProduct(product2);
				
			}
			
			
			model.addAttribute("listpoructview",listpoructview);
			
			String name = principal.getName();
		
		System.err.println(name);
			
			//User loginneUser = (User)((Authentication)principal).getPrincipal();
			
			
			model.addAttribute("top10", liststring);
			
		
			String pattern1 = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);
			String data1 = simpleDateFormat1.format(new Date());
			
			
			List<Invoice> listinvoi2 = new ArrayList<>();
			
			for(int i = 0 ; i<=6 ; i++) {
				Calendar calendar1 = Calendar.getInstance();
				calendar1.add(calendar1.DATE, -i);
				Date t1 = calendar1.getTime();
				
				String d1 = simpleDateFormat.format(t1);
				
				Invoice invoice3 = new Invoice();
				int imvoilist = reportService.finbyngay(d1);
				invoice3.setBooking_Date(d1);
				invoice3.setTotal_money(imvoilist);
				listinvoi2.add(invoice3);
				
				System.err.println(d1);
				System.err.println(imvoilist);
			
			}
			List<Invoice> liststatus1 = invoiceService.findAllLimitDongStatus(10, 0);
			model.addAttribute("liststatus1" , liststatus1);
			
			model.addAttribute("listCharngay", listinvoi2);
			return "admin/dashboard/home";
		
}
		@GetMapping("test")
		public String test() {
			
			return "admin/test";
		
}
		@GetMapping("gettaikhoan")
		@ResponseBody
		public AppUser appUser(@RequestParam String tentaikhoan) {
			
			return accountSerive.findByUserName(tentaikhoan);
		}
		
		@PostMapping("capnhattaikhoan")
		@ResponseBody
		public String capnhattaikhoan(@RequestParam String dataJson) throws JsonMappingException, JsonProcessingException {
			System.err.println(dataJson);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode;

				jsonNode = objectMapper.readTree(dataJson);
				String tennguoidung = jsonNode.get("tennguoidung").asText();
				String tendangnhap = jsonNode.get("tendangnhap").asText();
				String phone = jsonNode.get("phone").asText();
				String enmail = jsonNode.get("enmail").asText();
				List<AppUser> listusename = accountSerive.findAllNotIn(tendangnhap);
				for (AppUser appUser : listusename) {
					System.err.println(appUser.getUserName());
				}
				if(listusename.size() < 0) {
					return "trungtendangnhap";
				}else {
					AppUser appUser = accountSerive.findByUserName(tendangnhap);
					appUser.setEmail(enmail);
					appUser.setSdt(phone);
					appUser.setTenngguoidung(tennguoidung);
					accountSerive.Update(appUser);
				}
			return "";
		}
		@PostMapping("capnhatmatkhau")
		@ResponseBody
		public String capnhatmatkhau(@RequestParam Long matk, @RequestParam String mkht, @RequestParam String mkm) {
			List<AppUser> appuser = accountSerive.findById(matk);
			
			for (AppUser appUser2 : appuser) {
				
					if(appUser2.getUserName() != "" && bCryptPasswordEncoder.matches(mkht, appUser2.getPassword())) {
						System.err.println("matkhaudung");
						appUser2.setPassword(bCryptPasswordEncoder.encode(mkm));
						accountSerive.Update(appUser2);
						return "thanhcong";
					}else
					{
						System.err.println("dangngaptn");
						return "thatbai";
				}
			}
			
			return "";
		}
}