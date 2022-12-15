package com.YameShop.Controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Product;
import com.YameShop.domain.Supplier;
import com.YameShop.service.SupplierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin/supplier")
public class SupplierController {
	
	@Autowired
	SupplierService supplierService;
	
	@GetMapping("add")
	public String addSupplier(ModelMap model) {
		List<Supplier> listsupplier = supplierService.findAll();
		List<Supplier> suppliers = supplierService.indAllLimitDong(0, 15);
		int sumsupplier = listsupplier.size();
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("listsupplier", sumsupplier);
		
		
		return "admin/supplier/supplier";
	}
	//dsbre 
	
	//save 
	@PostMapping("ajaxsavesupplier")
	@ResponseBody
	@Transactional
	public List<Supplier> ajaxsavesupplier(@RequestParam String dataJson, @RequestParam Integer status, @RequestParam Integer solimit) throws JsonMappingException, JsonProcessingException{
		System.err.println(dataJson);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		jsonNode = objectMapper.readTree(dataJson); 
		
		Supplier supplier = new Supplier();
		List<Supplier> list = new ArrayList<>();
		String supplierName = jsonNode.get("supplierName").asText();
		String Phonenumnber = jsonNode.get("Phonenumnber").asText();
		String Gamil = jsonNode.get("Gamil").asText();
		String Address = jsonNode.get("Address").asText();
		String descriptions = jsonNode.get("descriptions").asText();
		list.add(supplier);
		List<Supplier> listsupplier = supplierService.findBySupplierName(supplierName);
		if(listsupplier.size() > 0) {
			return list;
		}
		List<Supplier> listphone = supplierService.findBySupplierPhone(Phonenumnber);
		if(listphone.size() > 0) {
			return list;
		}
		else {
			supplier.setSupplier_Name(supplierName);
			supplier.setPhone(Phonenumnber);
			supplier.setEmail(Gamil);
			supplier.setAddress(Address);
			supplier.setDescriptions(descriptions);
			supplier.setStatus(0);
		supplierService.Save(supplier);
		
		if (status == 2) {
			return supplierService.indAllLimitDong(0, solimit);
		}else {
			return supplierService.findAllLimitDongStatus(status, solimit);
		}
		}
	}
	
	// bingding update
	@GetMapping("bingdingupdate")
	@ResponseBody
	public Supplier bingdingupdate(@RequestParam String id) {
		System.err.println(id);
		Supplier supplier = supplierService.findById(id);
		return supplier;
	}
	//save 
		@PostMapping("ajaxupdatesupplier")
		@ResponseBody
		@Transactional
		public List<Supplier> ajaxupdatesupplier(@RequestParam String dataJson, @RequestParam Integer status, @RequestParam Integer solimit) throws JsonMappingException, JsonProcessingException{
			System.err.println(dataJson);
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode;
			jsonNode = objectMapper.readTree(dataJson); 
			String idproduct = jsonNode.get("idproduct").asText();
			Supplier supplier = supplierService.findById(idproduct);
			System.err.println(supplier);
			List<Supplier> list = new ArrayList<>();
			String supplierName = jsonNode.get("supplierName").asText();
			String Phonenumnber = jsonNode.get("Phonenumnber").asText();
			String Gamil = jsonNode.get("Gamil").asText();
			String Address = jsonNode.get("Address").asText();
			String descriptions = jsonNode.get("descriptions").asText();
			Supplier supplier2 = new Supplier();
			list.add(supplier2);
			List<Supplier> listsupplier = supplierService.findBySupplierName(supplierName);
//			if(listsupplier.size() > 0) {
//				return list;
//			}
//			List<Supplier> listphone = supplierService.findBySupplierPhone(Phonenumnber);
//			if(listphone.size() > 0) {
//				return list;
//			}
			
				supplier.setSupplier_Name(supplierName);
				supplier.setPhone(Phonenumnber);
				supplier.setEmail(Gamil);
				supplier.setAddress(Address);
				supplier.setDescriptions(descriptions);
				supplier.setStatus(0);
				supplierService.Update(supplier);
			
			if (status == 2) {
				return supplierService.indAllLimitDong(0, solimit);
			}else {
				return supplierService.findAllLimitDongStatus(status, solimit);
			
			}
			
		}
		// seach ajax 
		@GetMapping("searchByName")
		@ResponseBody
		public List<Supplier> searchByName(@RequestParam String name, @RequestParam Integer solimit, @RequestParam Integer status){
		
			if(status == 2) {
				return supplierService.searchlimit(name,solimit );
			}
			else {
			
			List<Supplier> listsearch = supplierService.search(name ,solimit, status);
			return listsearch;
			}
		}
		
		// updatestatus
		
				@PostMapping("updatestatus")
				@ResponseBody
				public List<Supplier> updatestatus(@RequestParam String id, @RequestParam Integer status, @RequestParam Integer solimit, @RequestParam Integer status1) {
					Supplier product = supplierService.findById(id);
					
					if(status == 0) {
						product.setStatus(1);
						supplierService.Update(product);
						if(status1 == 2) {
							return supplierService.indAllLimitDong(0, solimit);
						}
						else {
							return supplierService.findAllLimitDongStatus(solimit, status);
						}
					}
					else {
						product.setStatus(0);
						supplierService.Update(product);
						if(status1 == 2) {
							return supplierService.indAllLimitDong(0, solimit);
						}
						else {
							return supplierService.findAllLimitDongStatus(solimit, status);
						}
					}
				}
				// show status
				
				@GetMapping("showstatus")
				@ResponseBody
				public List<Supplier> showstatus(@RequestParam Integer solimit, @RequestParam Integer status){
					System.err.println(solimit);
					if(status == 2) {
						return supplierService.indAllLimitDong(0, solimit);
					}
					else {
						return supplierService.findAllLimitDongStatus(solimit, status);
					}
				}
				
				// pagingproduct
				@GetMapping("pagingsupplier")
				@ResponseBody
				public List<Supplier> pagingproduct(@RequestParam Integer spbatdau, @RequestParam Integer solimit, @RequestParam Integer status){
					if(status == 2) {
						return supplierService.indAllLimitDong(spbatdau, solimit);
					}
					else {
						return supplierService.findAllLimitstatus(spbatdau, solimit, status);
					}
				}
}
