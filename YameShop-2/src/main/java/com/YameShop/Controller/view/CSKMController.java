//package com.YameShop.Controller.view;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.YameShop.domain.Promontion;
//import com.YameShop.service.PromontionServices;
//
//@Controller
//@RequestMapping("Home")
//public class CSKMController {
//@Autowired
//PromontionServices promontionServices;
//	@GetMapping("KhuyenMai/{idKM}")
//	public String CTKM(@PathVariable Long idKM , ModelMap model) {
//		Promontion promontion = promontionServices.finbyid(idKM);
//		model.addAttribute("promontion", promontion);
//		
//		return "/view/KhuyenMai/listkhuyenmai";
//	}
//}
