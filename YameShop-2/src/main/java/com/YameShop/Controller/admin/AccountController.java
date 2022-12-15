package com.YameShop.Controller.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.AppRole;
import com.YameShop.domain.AppUser;
import com.YameShop.domain.UserRole;
import com.YameShop.service.AccountSerive;
import com.YameShop.service.AppRoleServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin/account")
public class AccountController {
	@Autowired
	AccountSerive accountSerive;
	@Autowired 
	AppRoleServices appRoleServices;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@GetMapping("add")
	public String add(ModelMap model) {
		
		
		List<AppRole> appRoles = appRoleServices.findAll();
		model.addAttribute("approle", appRoles);
		List<AppUser> appUsers = accountSerive.findAll();
		model.addAttribute("appUsers",appUsers);
		return "/admin/Account/listaccount";
	}
	
	@PostMapping("save")
	@ResponseBody
	public List<AppUser> save(@RequestParam String dataJson) throws JsonMappingException, JsonProcessingException{
		System.err.println(dataJson);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		
		
 		jsonNode = objectMapper.readTree(dataJson);
 		String tennguoidung = jsonNode.get("tennguoidung").asText();
 		String tendangnhap = jsonNode.get("tendangnhap").asText();
 		String matkhau = jsonNode.get("matkhau").asText();
 		Long idrole = jsonNode.get("idrole").asLong();
 		String phone = jsonNode.get("phone").asText();
 		AppUser appUser = new AppUser();
 		List<AppUser> list = new ArrayList<>();
 		list.add(appUser);
 		List<AppUser> findbyname = accountSerive.findByName(tendangnhap);
 		if(findbyname.size() > 0) {
 			return list;
 		}else {
 			Set<UserRole> roles = new HashSet<>();
 			appUser.setUserName(tendangnhap);
 			appUser.setPassword(bCryptPasswordEncoder.encode(matkhau));
 			AppRole appRole = new AppRole();
 			appRole.setRole_Id(idrole);
 			
				UserRole rolem = new UserRole();
				rolem.setAppRole(appRole);
				rolem.setAppUser(appUser);
				roles.add(rolem);
 			appUser.setUserRoles(roles);
 			appUser.setSdt(phone);
 			appUser.setTenngguoidung(tennguoidung);
 			accountSerive.Save(appUser);
 		}
 		
		return accountSerive.findAll();
	}
}
