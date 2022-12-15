package com.YameShop.Controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.YameShop.domain.Product_details;
import com.YameShop.service.ProductDetailsService;

@Controller
@RequestMapping("admin/product_details")
public class ProductDetailsController {
	@Autowired
	ProductDetailsService productDetailsService;
	@GetMapping("add")
	public String ProductDetails(ModelMap model) {
		List<Product_details> list = productDetailsService.findAll();
		model.addAttribute("listproductdetails", list);
		return "admin/productdetails/productdetailslist";
	}
}
