package com.YameShop.Controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("taikhoan")
@RequestMapping("cart")
public class Thanhtoan {
	@GetMapping("ThanhToan")
	public String thanhtoan() {
		
		
		return "/view/DatHang/thanhtoan";
	}
@GetMapping("SuccessThanhToan")
	public String succseethanhtoan() {
		return "/view/DatHang/thanhtoanthanhcong";
	}
}
