package com.YameShop.Controller.admin;

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

import com.YameShop.service.BrandSerive;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.YameShop.domain.Brand;
import com.YameShop.domain.Category;

@Controller
@RequestMapping("admin/brand")
public class BrandController {
	
	@Autowired
	BrandSerive brandSerive;
	@GetMapping("add")
	public String Brand(ModelMap model) {
		List<Brand> listbrand = brandSerive.findAllLimitDong(0, 15);
		model.addAttribute("listbrand", listbrand);
		
		return "admin/brand/listbrand";
	}
	@PostMapping("ajaxaddbrand")
	@ResponseBody
	@Transactional
	public List<com.YameShop.domain.Brand> ajaxaddcategory(@RequestParam String dataJson, @RequestParam Integer status, @RequestParam Integer solimit) throws JsonMappingException, JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode;
				Brand brand = new Brand();
				jsonNode = objectMapper.readTree(dataJson);
				String brandName = jsonNode.get("brandName").asText();
				List<Brand> listbrand = new ArrayList<>();
				listbrand.add(brand);
				List<Brand> listBrandName =brandSerive.findByBrandName(brandName);
						if(listBrandName.size() > 0) {
								return listbrand;
						}
						else {
						brand.setBrand_Name(brandName);
						brand.setStatus(0);	
						brandSerive.Save(brand);
						if(status == 2) {
							return brandSerive.findAllLimitDong(0, solimit);
						}else {
							return brandSerive.findAllLimitDongStatus(solimit, status);
						}
						
					}
	}
	@PostMapping("ajaxupdatebrand")
	@ResponseBody
	public List<com.YameShop.domain.Brand> ajaxupdate(@RequestParam String dataJson ,@RequestParam Integer status, @RequestParam Integer solimit ) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;

			jsonNode = objectMapper.readTree(dataJson);
			String id = jsonNode.get("IdBrand").asText();
			Brand brand = new Brand();
			String brandName = jsonNode.get("brandName").asText();
			List< Brand> list = new ArrayList<>();
			list.add(brand);
			List<Brand> listBrandName =brandSerive.findByBrandName(brandName);
			if(listBrandName.size() > 0) {
				return list;
			}
			else {			
				Brand brand1 = brandSerive.findById(id);
				brand1.setBrand_Name(brandName);
				brandSerive.UpdateBrand(brand1);
				if(status == 2) {
					return brandSerive.findAllLimitDong(0, solimit);
				}else {
					return brandSerive.findAllLimitDongStatus(solimit, status);
				}
			
			}

	}
	
	
	// Updatestatus
		@PostMapping("updatestatus")
		@ResponseBody
		public List<Brand> updatestatus(@RequestParam String id, @RequestParam Integer status ,@RequestParam Integer status1, @RequestParam Integer solimit) {
			Brand brand = brandSerive.findById(id);
			
			if(status == 0) {
				brand.setStatus(1);
				brandSerive.UpdateBrand(brand);
				if(status1 == 2) {
					return brandSerive.findAllLimitDong(0, solimit);
				}else {
					return brandSerive.findAllLimitDongStatus(solimit, status);
				}
			}
			else {
				brand.setStatus(0);
				brandSerive.UpdateBrand(brand);
				if(status1 == 2) {
					return brandSerive.findAllLimitDong(0, solimit);
				}else {
					return brandSerive.findAllLimitDongStatus(solimit, status);
				}
			}
		}
		
		@GetMapping("showstatus")
		@ResponseBody
		public List<Brand> showstatus( @RequestParam Integer status , @RequestParam Integer solimit ) {
			
			if(status == 2) {
				
				List<Brand> listbrand = brandSerive.findAllLimitDong(0, solimit);
				return listbrand;
			}
			else {

				List<Brand> listbrand = brandSerive.findAllLimitDongStatus(solimit,status );
				return listbrand;
			}
					
		}
		
		@GetMapping("showCategory")
		@ResponseBody
		public List<Brand> showCategory(@RequestParam Integer sotrang, @RequestParam Integer number) {
			if(number == 2) {
				return brandSerive.findAllLimitDong(0, sotrang);
			}
			List<Brand> listcategory = brandSerive.findAllLimitDongStatus(sotrang, number);
			
			return listcategory;
		}
		
		// phan trang
		@GetMapping("pagingcategory")
		@ResponseBody
		public List<Brand> pagingcategory( @RequestParam int spbatdau ,@RequestParam int status ,@RequestParam int solimit) {
			if(status == 2) {
				
				List<Brand> listbrand = brandSerive.findAllLimitDong(spbatdau, solimit);
				return listbrand;
			}
			else {
			List<Brand> listbrand = brandSerive.findAllLimitstatus(spbatdau, solimit, status);
			
			return listbrand;
			}
		}
		@PostMapping("deletecategory")
		@ResponseBody
		public List<Brand> deletecategory(@RequestParam int status ,@RequestParam int solimit){
			
			if(status == 2) {
				
				List<Brand> listbrand = brandSerive.findAllLimitDong(0, solimit);
				return listbrand;
			}
			else {

				List<Brand> listbrand = brandSerive.findAllLimitDongStatus(solimit,status );
				return listbrand;
			}
}
}
