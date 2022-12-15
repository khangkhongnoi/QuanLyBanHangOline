package com.YameShop.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class DangNhapAdmin {
	@GetMapping("login")
	public String login() {
		System.err.println("oke");
		 return "/admin/DangNhap/longin";
	}
	@GetMapping( "logoutSuccessful")
	    public String logoutSuccessfulPage(ModelMap model) {
	        model.addAttribute("title", "Logout");
	        return "/admin/DangNhap/longin";
	    }
}
