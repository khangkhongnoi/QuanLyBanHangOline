package com.YameShop.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/DoiTacGH")
public class DoitacgiaohangController {
@GetMapping("add")
public String DoiTacGH() {
	
	return "/admin/DTGH/DTGH";
}
}
