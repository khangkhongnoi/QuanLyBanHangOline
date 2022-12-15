package com.YameShop.Controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Member")
public class DangKy {

	@GetMapping("LoginByPhone")
	public String loginbyphone() {
		
		return "/view/DangKy/DangKy";
	}
}
