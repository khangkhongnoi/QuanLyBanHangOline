package com.YameShop.Controller.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.Product;
import com.YameShop.domain.Product_Color;
import com.YameShop.domain.Product_details;
import com.YameShop.service.InvoiceService;
import com.YameShop.service.Product_detailsServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import groovyjarjarpicocli.CommandLine.Parameters;
import javassist.bytecode.Descriptor.Iterator;
import javassist.expr.NewArray;

@Controller
@RequestMapping("admin/invoice")
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	Product_detailsServices product_detailsServices;
	
	@GetMapping("all")
	public String allinvoice(ModelMap model) {
		List<Invoice> list = invoiceService.findAll();
		
		List<Invoice> liststatus1 = invoiceService.findAllLimitDongStatus(15, 1);
		model.addAttribute("liststatus1" , liststatus1);
		List<Invoice> liststatus0 = invoiceService.findAllLimitDongStatus(15, 0);
		model.addAttribute("liststatus0",liststatus0);
		List<Invoice> liststatus3 = invoiceService.findAllLimitDongStatus(15, 3);
		model.addAttribute("liststatus3",liststatus3);
		List<Invoice> liststatus4 = invoiceService.findAllLimitDongStatus(15, 4);
		model.addAttribute("liststatus4",liststatus4);
		List<Invoice> liststatus7 = invoiceService.findAllLimitDongStatus(15,7);
		model.addAttribute("liststatus7",liststatus7);
		List<Invoice> liststatus5 = invoiceService.findAllLimitDongStatus(15,5);
		model.addAttribute("liststatus5",liststatus5);
		List<Invoice> liststatus6 = invoiceService.findAllLimitDongStatus(15, 6);
		model.addAttribute("liststatus6",liststatus6);
		model.addAttribute("listinvoice", list);
		
		return "/admin/invoice/listinvoice";
	}
	@GetMapping("taohd")
	public String taohd(ModelMap model) {
		 List<Product_details> details = product_detailsServices.findAll();
	  	   model.addAttribute("details", details);
		return "/admin/invoice/Taohoadon";
	}
	@GetMapping("reloadtbale")
	
	public String reloadtbale(ModelMap model){
		List<Invoice> liststatus1 = invoiceService.findAllLimitDongStatus(15, 1);
		model.addAttribute("liststatus1" , liststatus1);
		return "/admin/invoice/listinvoice";
	}
	
	@GetMapping("showinoivce")
	@ResponseBody
	public List<Invoice> showinoivce(@RequestParam Integer status){
		System.err.println(status);
		
		List<Invoice> list = invoiceService.findAllLimitDongStatus(15,status);
		
		for (Invoice invoice : list) {
			System.err.println(invoice.getStatus());
		}
		return list;
	}
	
	@PostMapping("updatestatus")
	@ResponseBody
	public List<Invoice> updatestatus(@RequestParam Long id, @RequestParam Integer status ,@RequestParam Integer status1, @RequestParam Integer solimit) {
		Invoice invoice = invoiceService.findbyid(id);
		
		if(status == 0) {
			invoice.setStatus(1);
			invoiceService.update(invoice);
			if(status1 == 2) {
				return invoiceService.findAllLimitDong(0, solimit);
			}else {
				return invoiceService.findAllLimitDongStatus(solimit, status);
			}
		}
		else {
			invoice.setStatus(0);
			invoiceService.update(invoice);
			if(status1 == 2) {
				return invoiceService.findAllLimitDong(0, solimit);
			}else {
				return invoiceService.findAllLimitDongStatus(solimit, status);
			}
		}
	}
	
	@GetMapping("laytrangthaihd")
	@ResponseBody
	public List<Invoice> laytrangthaihd(@RequestParam Integer idstatus){
		System.err.println(idstatus);
		
		List<Invoice> list = invoiceService.listallstatus(idstatus);
		for (Invoice invoice : list) {
			System.err.println(invoice.getCustomer_Name());
		}
		
		return invoiceService.listallstatus(idstatus);
	}
	
	
	
	
	
	@PostMapping("updatestatushoadon")
	@ResponseBody
	public List<Invoice> updatestatushoadon(@RequestParam Long id, @RequestParam Integer tthd , @RequestParam Integer status, @RequestParam Integer solimit){
		
		Invoice invoice = invoiceService.findbyid(id);
		invoice.setStatus(tthd);
		invoiceService.update(invoice);
		
		System.err.println(tthd);
		if(tthd == 0) {
			List<Invoice> listbrand = invoiceService.findAllLimitDongStatus(solimit,1 );
			return listbrand;
		}
		if(tthd == 3) {
			List<Invoice> listbrand = invoiceService.findAllLimitDongStatus(solimit,0 );
			return listbrand;
		}
		else{
			List<Invoice> listbrand = invoiceService.findAllLimitDongStatus(solimit,3);
			return listbrand;
		}
	
	}
	
	
	
	
	
	
	
	
	
	@GetMapping("chitiethd/{idhoadon}")
	
	public String invoices(@PathVariable Long idhoadon , ModelMap model){
		Invoice invoice = invoiceService.findbyid(idhoadon);
		Invoice_details invoice_details = new Invoice_details();
		List<Invoice_details>list = invoiceService.listinvoicedetails(idhoadon);
		model.addAttribute("invoice", invoice);
		model.addAttribute("listinoive", list);
		return "/admin/invoice/Chitiethoadon";
		
		
	}
	  
	@GetMapping("capnhathd/{idhd}")
	public String capnhathoadon(@PathVariable Long idhd , ModelMap model) {
		
		Invoice list = invoiceService.findbyid(idhd);
		model.addAttribute("listinoive", list);
//		Invoice invoice = invoiceService.findbyid(idhd);
//		model.addAttribute("invoice", invoice);
//		for (Invoice_details invoice_details : list) {
//			System.err.println(invoice_details.getProduct_details().getProduct().getId_Poroduct());
//			List<Object> product_details = product_detailsServices.loandproductdeltais(invoice_details.getProduct_details().getProduct().getId_Poroduct());
//			
//			java.util.Iterator<Object> itr = product_details.iterator();
//			List<Product_Color> listinvoi1 = new ArrayList<>();
//			while(itr.hasNext()){
//				   Object[] obj = (Object[]) itr.next();
//				   //now you have one array of Object for each row
//				   String client = String.valueOf(obj[1]); // don't know the type of column CLIENT assuming String 
//				   String service = String.valueOf(obj[0]);
//				   
//				   //same way for all obj[2], obj[3], obj[4]
//				Product_Color color = new Product_Color();
//				color.setId_Product_Color(service);
//				color.setColor_Name(client);
//				   
//				 listinvoi1.add(color);
//				   System.err.println(client);
//				  
//				   System.err.println(service);
//			}
//			model.addAttribute("color", listinvoi1);
//		//`	model.addAttribute("product_details",product_details);
//			
//		}
//		
		
		List<Product_details> details = product_detailsServices.findAll();
		   model.addAttribute("details", details);
		return "/admin/invoice/UpdateHD";
	}
	
	
	@PostMapping("saveor")
	public String save(ModelMap model , @ModelAttribute("invoice") Invoice dto ,@ModelAttribute("invoice_details") Invoice_details dtodetail,  BindingResult result) {
		Invoice entity = new Invoice(); 
		BeanUtils.copyProperties(dto, entity);
		System.err.println(entity.getInvoice_details());
		
		Invoice_details details = new Invoice_details();
		BeanUtils.copyProperties(dtodetail, details);
		
		System.err.println(details.getAmount());
		return "";
	}
	
	@GetMapping("deletedetails/{id}")
	public String deletedetails(@PathVariable("id") Long id) {
		Invoice_details details = invoiceService.finbyid(id);
		System.err.println(details);
		invoiceService.delete(details);
		return "/admin/invoice/Capnhathoadon";
	}
	
	@GetMapping("ajaxupdate")
	
	public String ajaxupdate(@RequestParam String dataJson , ModelMap model) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		System.err.println(dataJson);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonNode = objectMapper.readTree(dataJson);
		Invoice invoice = new Invoice();
		Long id = jsonNode.get("id").asLong();
		String ten = jsonNode.get("ten").asText();
		String sdt = jsonNode.get("phone").asText();
		String diachi = jsonNode.get("descriptions").asText();
		invoice.setId_Invoice(id);
		invoice.setCustomer_Name(ten);
		invoice.setPhone(sdt);
		invoice.setDelivery_Address(diachi);
	
		Invoice invoice2 = invoiceService.findbyid(id);
		invoice.setBooking_Date(invoice2.getBooking_Date());
		invoice.setGio(invoice2.getGio());
		invoice.setDelivery_Formality(invoice2.getDelivery_Formality());
		
	//	invoice.setCustomer_Name(ten);
		JsonNode jsoninvoi = jsonNode.get("inoice");
		Set<Invoice_details> listinvocedetails = new HashSet<>();
		Integer sum = 0;
		for (JsonNode invoidetails : jsoninvoi) {
			
			int quantity = invoidetails.get("quantity").asInt();
		//	int price = invoidetails.get("totalprice").asInt();
			Product_details product_details = new Product_details();
			product_details.setId_Product_Details(id);
			Long iddetails = invoidetails.get("iddetails").asLong();
			Integer dongia = invoidetails.get("dongia").asInt();
			Invoice_details details = new Invoice_details();
				details.setProduct_details(product_details);
				details.setInvoice(invoice);
				details.setAmount(quantity);
				details.setDon_gia(dongia);
				details.setToatal_Money(dongia * quantity);
				//details.setToatal_Money(price);
				listinvocedetails.add(details);
				Invoice_details details10 = invoiceService.finbyid(iddetails);
				
				invoiceService.delete(details10);
				sum += dongia * quantity;
			//System.err.println(id + quantity + price);
		}
		
		
		invoice.setTotal_money(sum);
		invoice.setInvoice_details(listinvocedetails);
		invoiceService.update(invoice);
		List<Invoice_details>list = invoiceService.listinvoicedetails(id);
		model.addAttribute("listinoive", list);
		Invoice invoice5 = invoiceService.findbyid(id);
		model.addAttribute("invoice", invoice5);
		return "/admin/invoice/Capnhathoadon";
	}
	
	// pagingproduct
			@GetMapping("pagingproduct")
			@ResponseBody
			public List<Invoice> pagingproduct(@RequestParam Integer spbatdau, @RequestParam Integer solimit, @RequestParam Integer status){
				System.err.println(status);
					return invoiceService.findAllLimitstatus(spbatdau, solimit, status);
				
			}
			// seach ajax 
			@GetMapping("searchByName")
			@ResponseBody
			public List<Invoice> searchByName(@RequestParam String name){
			
				return invoiceService.search(name);
			}
			
			//tao hd
			@PostMapping("taohoadon")
		@ResponseBody
			public String taohd(@RequestParam String dataJson) throws JsonMappingException, JsonProcessingException {
				System.err.println(dataJson);
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode;
				jsonNode = objectMapper.readTree(dataJson);
				String tenhk = jsonNode.get("tenhk").asText();
				String Gamil = jsonNode.get("Gamil").asText();
				String phone = jsonNode.get("phone").asText();
				String Address = jsonNode.get("Address").asText();
				String ghichu = jsonNode.get("ghichu").asText();
				JsonNode jsoninvoi = jsonNode.get("productdetails");
				Invoice invoice = new Invoice();
				invoice.setCustomer_Name(tenhk);
				invoice.setPhone(phone);
				invoice.setDelivery_Address(Address);
				invoice.setGhichu(ghichu);
				invoice.setEmail(Gamil);
				Set<Invoice_details> listinvocedetails = new HashSet<>();
				Integer sum = 0;
				for (JsonNode invoidetails : jsoninvoi) {
					Long idproductdetails = invoidetails.get("idproductdetails").asLong();
					Integer soluong = invoidetails.get("soluong").asInt();
					Integer gianhap = invoidetails.get("gianhap").asInt();
					Invoice_details details = new Invoice_details();
					details.setAmount(soluong);
					details.setDon_gia(gianhap);
					Product_details product_details = new Product_details();
					product_details.setId_Product_Details(idproductdetails);
					details.setProduct_details(product_details);
					details.setInvoice(invoice);
					details.setToatal_Money(soluong * gianhap);
					listinvocedetails.add(details);
					sum += soluong * gianhap;
				}
				invoice.setTotal_money(sum);
				invoice.setInvoice_details(listinvocedetails);
				invoiceService.save(invoice);
				return "";
			}
			
		@PostMapping("capnhattrangthaiyesno")
		@ResponseBody
		public List<Invoice> capnhattrangthaiyesno(@RequestParam Long id, @RequestParam Integer status){
			
			Invoice invoice =  invoiceService.findbyid(id);
			invoice.setStatus(status);
			invoiceService.update(invoice);
			System.err.println(id + "-" +status);
			List<Invoice> liststatus4 = invoiceService.findAllLimitDongStatus(15, 4);
			return liststatus4;
		}
		
		@PostMapping("ajaxdelete")
		public String ajaxdelete(@RequestParam Long id) {
			System.err.println(id);
			Invoice invoice = invoiceService.findbyid(id);
			invoice.setStatus(7);
			invoiceService.update(invoice);
			return "/admin/invoice/listinvoice";
		}
}
