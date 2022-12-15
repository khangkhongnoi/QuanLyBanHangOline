package com.YameShop.Controller.view;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Category;
import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.KhacHang;
import com.YameShop.domain.Product_details;
import com.YameShop.service.CategoryService;
import com.YameShop.service.InvoiceService;
import com.YameShop.service.KhachHangServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("cart")


public class GioHang {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	KhachHangServices hangServices;
	@Autowired
	CategoryService categoryService;
	@GetMapping("review")
	public String Giohang(ModelMap model) {
		List<Category> listcategory = categoryService.findviewlimitstatus();
		model.addAttribute("listcategory",listcategory);
		List<Category> listcategorystatus = categoryService.findByStatus(0);
		model.addAttribute("listcategorystatus",listcategorystatus);
		return "/view/GioHang/ThongTinGioHang";
	}
	@PostMapping("AddVariantToCart")
	public String addgiohang(@RequestParam String id) {
		System.err.println(id);
		return "";
	}
	@PostMapping("ajaxsavecart")
	@ResponseBody
	public String ajaxsavecart(@RequestParam String customename , @RequestParam String sdt , @RequestParam String address , @RequestParam String descript ,@RequestParam String httt,@RequestParam String cart) throws JsonMappingException, JsonProcessingException {
		Time time = new Time(System.currentTimeMillis());
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		System.err.println(httt);
		if( cart != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode;
			jsonNode  = objectMapper.readTree(cart);
			System.err.println(jsonNode);
			Invoice invoice = new Invoice();
			invoice.setCustomer_Name(customename);
			invoice.setReceiptDate(timestamp1);
			invoice.setGio(time);
			invoice.setPhone(sdt);
			invoice.setDelivery_Address(address);
			invoice.setGhichu(descript);
			invoice.setDelivery_Formality(httt);
			invoice.setStatus(0);
			Set<Invoice_details> listinvocedetails = new HashSet<>();
			Integer sum = 0;
			Integer tongsoluong = 0;
			for (JsonNode invoidetails : jsonNode) {
				Long id = invoidetails.get("id").asLong();
				int quantity = invoidetails.get("quantity").asInt();
				int price = invoidetails.get("totalprice").asInt();
				int price1 = invoidetails.get("price").asInt();
				Product_details product_details = new Product_details();
				product_details.setId_Product_Details(id);
				
				Invoice_details details = new Invoice_details();
					details.setProduct_details(product_details);
					details.setInvoice(invoice);
					details.setAmount(quantity);
					details.setToatal_Money(price);
					details.setDon_gia(price1);
					listinvocedetails.add(details);
					sum += price;
					tongsoluong += quantity;
				System.err.println(id);
			}
			invoice.setTotal_money(sum);
			invoice.setTongsoluong(tongsoluong);
			invoice.setInvoice_details(listinvocedetails);
			invoiceService.save(invoice);
		}
		
		return "";
	}
	
	@PostMapping("ajaxmuahangidkh")
	@ResponseBody
public String ajaxmuahangidkh( @RequestParam Integer idkh , @RequestParam String descript ,@RequestParam String httt, @RequestParam String cart) throws JsonMappingException, JsonProcessingException {
		
		Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		Time time = new Time(System.currentTimeMillis());
		if( cart != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode;
			jsonNode  = objectMapper.readTree(cart);
			System.err.println(jsonNode);
			Invoice invoice = new Invoice();
			invoice.setReceiptDate(timestamp1);
			invoice.setGio(time);
			invoice.setGhichu(descript);
			invoice.setStatus(0);
			KhacHang hang = new KhacHang();
			hang.setId_KH(idkh);
			invoice.setKhacHang(hang);
			KhacHang khacHang = hangServices.finById(idkh);
			invoice.setCustomer_Name(khacHang.getTenKH());
			invoice.setPhone(khacHang.getAppUser().getSdt());
			
			invoice.setEmail(khacHang.getAppUser().getEmail());
			invoice.setDelivery_Address(khacHang.getAddress());
			invoice.setDelivery_Formality(httt);
			Set<Invoice_details> listinvocedetails = new HashSet<>();
			Integer sum = 0;
			Integer Tongsoluong = 0;
			for (JsonNode invoidetails : jsonNode) {
				Long id = invoidetails.get("id").asLong();
				int quantity = invoidetails.get("quantity").asInt();
				int price = invoidetails.get("totalprice").asInt();
				Product_details product_details = new Product_details();
				product_details.setId_Product_Details(id);
				
				Invoice_details details = new Invoice_details();
					details.setProduct_details(product_details);
					details.setInvoice(invoice);
					details.setAmount(quantity);
					details.setToatal_Money(price);
					listinvocedetails.add(details);
					sum += price;
					Tongsoluong += quantity;
				System.err.println(id);
			}
			invoice.setTotal_money(sum);
			invoice.setTongsoluong(Tongsoluong);
			invoice.setInvoice_details(listinvocedetails);
			invoiceService.save(invoice);
		}
		
		return "";
	}
	@PostMapping("ajaxmuahangidkhvalue2")
	@ResponseBody
	public String ajaxmuahangidkhvalue2(@RequestParam String diachithaythe,@RequestParam String descript ,@RequestParam String httt, @RequestParam Integer idkh, @RequestParam String cart) throws JsonMappingException, JsonProcessingException {
Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
Time time = new Time(System.currentTimeMillis());
		System.err.println(idkh);
		if( cart != null) {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode;
			jsonNode  = objectMapper.readTree(cart);
			System.err.println(jsonNode);
			Invoice invoice = new Invoice();
			invoice.setReceiptDate(timestamp1);
			invoice.setGio(time);
			invoice.setGhichu(descript);
			invoice.setStatus(0);
			KhacHang hang = new KhacHang();
			hang.setId_KH(idkh);
			invoice.setKhacHang(hang);
			KhacHang khacHang = hangServices.finById(idkh);
			invoice.setCustomer_Name(khacHang.getTenKH());
			invoice.setPhone(khacHang.getAppUser().getSdt());
			
			invoice.setEmail(khacHang.getAppUser().getEmail());
			invoice.setDelivery_Address(diachithaythe);
			invoice.setDelivery_Formality(httt);
			Set<Invoice_details> listinvocedetails = new HashSet<>();
			Integer sum = 0;
			Integer Tongsoluong = 0;
			for (JsonNode invoidetails : jsonNode) {
				Long id = invoidetails.get("id").asLong();
				int quantity = invoidetails.get("quantity").asInt();
				int price = invoidetails.get("totalprice").asInt();
				int pricedongia = invoidetails.get("price").asInt();
				Product_details product_details = new Product_details();
				product_details.setId_Product_Details(id);
				
				Invoice_details details = new Invoice_details();
					details.setProduct_details(product_details);
					details.setInvoice(invoice);
					details.setAmount(quantity);
					details.setDon_gia(pricedongia);
					details.setToatal_Money(price);
					listinvocedetails.add(details);
					sum += price;
					Tongsoluong += quantity;
				System.err.println(id);
			}
			invoice.setTotal_money(sum);
			invoice.setTongsoluong(Tongsoluong);
			invoice.setInvoice_details(listinvocedetails);
			invoiceService.save(invoice);
		}
		return "";
	}
}
