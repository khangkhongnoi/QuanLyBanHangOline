package com.YameShop.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Invoice;
import com.YameShop.domain.Invoice_details;
import com.YameShop.domain.KhacHang;
import com.YameShop.domain.Product;
import com.YameShop.domain.Product_details;
import com.YameShop.domain.Staff;
import com.YameShop.service.InvoiceService;
import com.YameShop.service.KhachHangServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin/khachhang")
public class KhachHangController {
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	KhachHangServices khachHangServices;  
	@GetMapping("add")
	public String add(ModelMap model) {
		List<KhacHang> listkh = khachHangServices.finAll();
		for (KhacHang khacHang : listkh) {
			List<Invoice> invoices = invoiceService.finByidKH(khacHang.getId_KH());
			int sum = 0;
			List<Integer> listsum = new ArrayList<>();
			for (Invoice invoice : invoices) {
				sum = invoice.getTotal_money();
				listsum.add(sum);
			};
			System.err.println(sum);
			
			model.addAttribute("listsum", listsum);
			
		}
		model.addAttribute("listkh",listkh);
		return "/admin/Khachhang/listkhachhang";
	}
	
	@GetMapping("reportKH")
	public String reportKH(ModelMap model) {
		List<Object> list = invoiceService.SumTotalManyCustone();
		Iterator itr = list.iterator();
		List<Invoice> listinvoi1 = new ArrayList<>();
		while(itr.hasNext()){
			   Object[] obj = (Object[]) itr.next();
			   //now you have one array of Object for each row
			   String client = String.valueOf(obj[2]); // don't know the type of column CLIENT assuming String 
			   Integer service = Integer.parseInt(String.valueOf(obj[0]));
			   Integer service1 = Integer.parseInt(String.valueOf(obj[1])); //SERVICE assumed as int
			   //same way for all obj[2], obj[3], obj[4]
			
			   Invoice invoice = new Invoice();
			   invoice.setCustomer_Name(client);
			   invoice.setTotal_money(service);
			   listinvoi1.add(invoice);
			   System.err.println(client);
			   System.err.println(service1);
			   System.err.println(service);
		}
		model.addAttribute("list", listinvoi1);
		return "/admin/Khachhang/reportKH";
	}
	
	@PostMapping("ajaxsave")
	@ResponseBody
	public List<KhacHang> ajaxsave(@RequestParam String dataJson ,@RequestParam String gioitinh, @RequestParam Integer solimit) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;

			jsonNode = objectMapper.readTree(dataJson);
		
		System.err.println(dataJson);
		
		String name = jsonNode.get("FullName").asText();
		String sdt = jsonNode.get("SDT").asText();
		String data_bith = jsonNode.get("Date_Of_Birth").asText();
		String email = jsonNode.get("Email").asText();
		String Address = jsonNode.get("Address").asText();
		
			 
			KhacHang khacHang = new KhacHang();
			List<KhacHang> list = new ArrayList<>();
			
			list.add(khacHang);
		List<KhacHang> phone = khachHangServices.Phone(sdt);
		for (KhacHang khacHang2 : phone) {
			System.err.println(khacHang2.getPhone());
		}
		if(phone.size() > 0) {
			return list;
		}else {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			KhacHang khangsave = new KhacHang();
			khangsave.setTenKH(name);
			khangsave.setPhone(sdt);
			khangsave.setAddress(Address);
			khangsave.setDate_Of_Birth(data_bith);
			khangsave.setGender(gioitinh);
			khangsave.setEmali(email);
			//khachHangServices.save(khangsave);
			return khachHangServices.finAll()
;		}
		
			
	}
	@PostMapping("Update")
	@ResponseBody
	public List<KhacHang> Capnhat(@RequestParam String dataJson ,@RequestParam String gioitinh, @RequestParam Integer solimit) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;

			jsonNode = objectMapper.readTree(dataJson);
		
		System.err.println(dataJson);
		
		String name = jsonNode.get("FullName").asText();
		String sdt = jsonNode.get("SDT").asText();
		String data_bith = jsonNode.get("Date_Of_Birth").asText();
		String email = jsonNode.get("Email").asText();
		String Address = jsonNode.get("Address").asText();
		Integer idkh = jsonNode.get("Idsaff").asInt();
		KhacHang khangsave = khachHangServices.finById(idkh);

			List<KhacHang> list = new ArrayList<>();
			
			list.add(khangsave);
//		List<KhacHang> phone = khachHangServices.Phone(sdt);
//		if(phone.size() > 0) {
//			return list;
//		}else {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			khangsave.setTenKH(name);
			khangsave.setPhone(sdt);
			khangsave.setAddress(Address);
			khangsave.setDate_Of_Birth(data_bith);
			khangsave.setGender(gioitinh);
			khangsave.setEmali(email);
			khachHangServices.update(khangsave);
			return khachHangServices.finAll()
;		}
	
	
}
