package com.YameShop.Controller.admin;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Staff;
import com.YameShop.service.StaffService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

@Controller
@RequestMapping("admin/nhanvien")
public class StaffController {
	
	@Autowired
	StaffService staffService;
	@RequestMapping("list")
	public String nhanvien(ModelMap model) {
		List<Staff> staffs = staffService.findAll();
		model.addAttribute("staff", new Staff());
		model.addAttribute("liststaff", staffs);
		List<Staff> list = staffService.demsumstaff();
				model.addAttribute("demsum", list);
		return "/admin/Nhanvien/listnhanvien";
	}
	
//	@PostMapping("saveOrUpdate")
//	public ModelAndView saveOrUpdate(ModelMap model, @ModelAttribute("staff") Staff dto , BindingResult result) {
//		
//		Staff entuty = new Staff();
//		BeanUtils.copyProperties(dto, entuty);
//		
//		System.err.println(dto.getIdsaff());
//
//		staffService.Save(entuty);
//		
//		
//		
//		List<Staff> staffs = staffService.findAll();
//		model.addAttribute("staff", staffs);
//		return new ModelAndView("forward:/admin/nhanvien/list", model);
//			}
//	
//	@GetMapping("saveOrUpdate1")
//	public ModelAndView saveOrUpdate1(ModelMap model, @ModelAttribute("staff") Staff dto , BindingResult result) {
//		
//		Staff entuty = new Staff();
//		BeanUtils.copyProperties(dto, entuty);
//		
//		System.err.println(entuty.getIdsaff());
//		
//		
//		
//		List<Staff> staffs = staffService.findAll();
//		model.addAttribute("staff", staffs);
//		return new ModelAndView("forward:/admin/nhanvien/list", model);
//			}
//	
	@PostMapping("ajaxsave")
	@ResponseBody
	public List<Staff> ajaxsave(@RequestParam String dataJson ,@RequestParam String image, @RequestParam Integer status , @RequestParam Integer solimit) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;

			jsonNode = objectMapper.readTree(dataJson);
		
		System.err.println(dataJson);
		
		String name = jsonNode.get("FullName").asText();
		String sdt = jsonNode.get("SDT").asText();
		String data_bith = jsonNode.get("Date_Of_Birth").asText();
		String email = jsonNode.get("Email").asText();
		String cmnd = jsonNode.get("CMND").asText();
		String Address = jsonNode.get("Address").asText();
		String Gender = jsonNode.get("Gender").asText();
			Staff staff1 = new Staff();
			
			List< Staff> list = new ArrayList<>();
			list.add(staff1);
		List<Staff> phone = staffService.findByBrandName(sdt);
//		if(phone.size() > 0) {
//			return list;
//		}else {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Staff staff = new Staff();
			staff.setFullName(name);
			staff.setSDT(sdt);
			staff.setDate_Of_Birth(data_bith);
			staff.setEmail(email);
			staff.setCMND(cmnd);
			staff.setAddress(Address);
			staff.setGender(Gender);
			staff.setImage(image);
			staff.setStatus(0);
			staffService.Save(staff);
			if(status == 2) {
				
				List<Staff> listbrand = staffService.findAllLimitDong(0, solimit);
				return listbrand;
			}
			else {

				List<Staff> listbrand = staffService.findAllLimitDongStatus(solimit,status );
				return listbrand;
	//		}
			
		}
		
			
	}
	
	
	@GetMapping("update/{id}")
	public String update(ModelMap model, @PathVariable("id") Long id) {
		
		
		return "/admin/Nhanvien/listnhanvien";
	}
	
	@PostMapping("ajaxupdate")
	@ResponseBody
	public List<Staff> ajaxupdate(@RequestParam String dataJson ,@RequestParam Integer status , @RequestParam Integer solimit) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;

			jsonNode = objectMapper.readTree(dataJson);
		
		System.err.println(dataJson);
		Long id = jsonNode.get("Idsaff").asLong();
		String name = jsonNode.get("FullName").asText();
		String sdt = jsonNode.get("SDT").asText();
		String data_bith = jsonNode.get("Date_Of_Birth").asText();
		String email = jsonNode.get("Email").asText();
		String cmnd = jsonNode.get("CMND").asText();
		String Address = jsonNode.get("Address").asText();
		String Gender = jsonNode.get("Gender").asText();
		
			Staff staff = staffService.findById(id);
			staff.setFullName(name);
			staff.setSDT(sdt);
			staff.setDate_Of_Birth(data_bith);
			staff.setEmail(email);
			staff.setCMND(cmnd);
			staff.setAddress(Address);
			staff.setGender(Gender);
			staffService.UpdateBrand(staff);
			
		
		
			if(status == 2) {
				
				List<Staff> listbrand = staffService.findAllLimitDong(0, solimit);
				return listbrand;
			}
			else {

				List<Staff> listbrand = staffService.findAllLimitDongStatus(solimit,status );
				return listbrand;
			}
	}
	
	@GetMapping("showstatus")
	@ResponseBody
	public List<Staff> showstatus( @RequestParam Integer status , @RequestParam Integer solimit ) {
		
		if(status == 2) {
			
			List<Staff> listbrand = staffService.findAllLimitDong(0, solimit);
			return listbrand;
		}
		else {

			List<Staff> listbrand = staffService.findAllLimitDongStatus(solimit,status );
			return listbrand;
		}
				
	}
	
	@GetMapping("showCategory")
	@ResponseBody
	public List<Staff> showCategory(@RequestParam Integer sotrang, @RequestParam Integer number) {
		if(number == 2) {
			return staffService.findAllLimitDong(0, sotrang);
		}
		List<Staff> listcategory = staffService.findAllLimitDongStatus(sotrang, number);
		
		return listcategory;
	}
	
	@PostMapping("updatestatus")
	@ResponseBody
	public List<Staff> updatestatus(@RequestParam Long id, @RequestParam Integer status ,@RequestParam Integer status1, @RequestParam Integer solimit) {
		Staff staff = staffService.findById(id);
		
		if(status == 0) {
			staff.setStatus(1);
			staffService.UpdateBrand(staff);
			if(status1 == 2) {
				return staffService.findAllLimitDong(0, solimit);
			}else {
				return staffService.findAllLimitDongStatus(solimit, status);
			}
		}
		else {
			staff.setStatus(0);
			staffService.UpdateBrand(staff);
			if(status1 == 2) {
				return staffService.findAllLimitDong(0, solimit);
			}else {
				return staffService.findAllLimitDongStatus(solimit, status);
			}
		}
	}
				
	@GetMapping("pagingcategory/{spbatdau}/{status}/{solimit}")
	@ResponseBody
	public List<Staff> pagingcategory( @PathVariable("spbatdau") int spbatdau ,@PathVariable("status") int status ,@PathVariable("solimit") int solimit) {
		if(status == 2) {
			
			List<Staff> listbrand = staffService.findAllLimitDong(spbatdau, solimit);
			return listbrand;
		}
		else {
		List<Staff> listbrand = staffService.findAllLimitstatus(spbatdau, solimit, status);
		
		return listbrand;
		}
	}
	@GetMapping("pagingcategorys/{spbatdau}/{status}/{solimit}")

	public List<Staff> pagingcategoryload( @PathVariable("spbatdau") int spbatdau ,@PathVariable("status") int status ,@PathVariable("solimit") int solimit) {
		if(status == 2) {
			
			List<Staff> listbrand = staffService.findAllLimitDong(spbatdau, solimit);
			return listbrand;
		}
		else {
		List<Staff> listbrand = staffService.findAllLimitstatus(spbatdau, solimit, status);
		
		return listbrand;
		}
	}
	
	
	@GetMapping("search")
	@ResponseBody
	public List<Staff> staffs(@RequestParam String name){
		
		return staffService.seach(name);
				}
	
	@PostMapping("deletenhanvien")
	@ResponseBody
	public List<Staff> staffs(@RequestParam Long id , @RequestParam Integer status, @RequestParam Integer solimit ){
		
		Staff staff = staffService.findById(id);
		staff.setStatus(5);
		staffService.UpdateBrand(staff);
		
		if(status == 2) {
			
			List<Staff> listbrand = staffService.findAllLimitDong(0, solimit);
			return listbrand;
		}
		else {

			List<Staff> listbrand = staffService.findAllLimitDongStatus(solimit,status );
			return listbrand;
		}
	}
}
