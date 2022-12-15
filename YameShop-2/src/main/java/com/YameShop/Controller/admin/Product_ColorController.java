package com.YameShop.Controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Product_Color;
import com.YameShop.domain.Size;
import com.YameShop.service.ProductColorService;
import com.YameShop.service.SizeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin/product_color")
public class Product_ColorController {
	@Autowired
	ProductColorService productColorService;
	@Autowired
	SizeService sizeService;
	@GetMapping("add")
	public String addproductcolor(ModelMap model) {
		List<Product_Color> listproductColors1 = productColorService.findAll();
		List<Product_Color>  listproductColors = productColorService.findAllLimitDong(0, 15);
		int sumproductcolor = listproductColors1.size();
		model.addAttribute("sumproductcolor", sumproductcolor);
		model.addAttribute("listproductColors", listproductColors);
		
		return "admin/produc_color/listproductcolor";
	}
	
	@ModelAttribute("size")
	@Transactional
	public List<Size> getBrand(){
		
		return sizeService.findAll().stream().map(item ->{
			Size dto = new Size();
		
			BeanUtils.copyProperties(item, dto);
			
			return dto;
		}).toList();
	}
	
	// save
	@PostMapping("ajaxsaveproductcolor")
	@ResponseBody
	public List<Product_Color> ajaxsaveproductcolor(@RequestParam String dataJson, @RequestParam int status, @RequestParam int solimit, @RequestParam String image) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.err.println(status);
		JsonNode jsonNode;
		
		jsonNode = objectMapper.readTree(dataJson); 
		Product_Color product_Color = new Product_Color();
		List<Product_Color> list = new ArrayList<>();
		String productcolorname = jsonNode.get("product_color").asText();
		System.err.println(productcolorname);
		list.add(product_Color);
		List<Product_Color> lissProduct_Colors = productColorService.findByProductColorName(productcolorname);
		if(lissProduct_Colors.size() > 0) {
			return list;
		}else {
			product_Color.setColor_Name(productcolorname);
			product_Color.setImages(image);
			product_Color.setStatus(0);
			productColorService.Save(product_Color);
			if(status == 2) {
				return productColorService.findAllLimitDong(0, solimit);
			}
			else {
				List<Product_Color> listproductColors = productColorService.findAllLimitDongStatus(solimit, status);
				return listproductColors;
			}
		}
	}
	
	//
	@PostMapping("ajaxupdateproductcolor")
	@ResponseBody
	public List<Product_Color> ajaxupdateproductcolor(@RequestParam String dataJson, @RequestParam Integer status, @RequestParam Integer solimit ,@RequestParam String image) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		jsonNode = objectMapper.readTree(dataJson);
		Product_Color product_Color = new Product_Color();
		List<Product_Color> list = new ArrayList<>();
		list.add(product_Color);
		String id = jsonNode.get("idproduct_color").asText();
		String productcolorname = jsonNode.get("product_color").asText();
		List<Product_Color> listproProduct_Colors = productColorService.findByProductColorName(productcolorname);
		if(listproProduct_Colors.size() > 0) {
			return list;
		}
		else {
			Product_Color color = productColorService.findById(id);
			color.setColor_Name(productcolorname);
			color.setImages(image);
			productColorService.UpdateProductColor(color);
			if(status == 2) {
				return productColorService.findAllLimitDong(0, solimit);
			}
			else {
				List<Product_Color> listproductColors = productColorService.findAllLimitDongStatus(solimit, status);
				return listproductColors;
			}
		}
	
	}
	// update status
	@PostMapping("updatestatus")
	@ResponseBody
	public List<Product_Color> updatestatus(@RequestParam String id, @RequestParam Integer status, @RequestParam Integer solimit, @RequestParam Integer status1) {
		Product_Color product_Color = productColorService.findById(id);
		
		if(status == 0) {
			product_Color.setStatus(1);
			productColorService.UpdateProductColor(product_Color);
			if(status1 == 2) {
				return productColorService.findAllLimitDong(0, solimit);
			}
			else {
				List<Product_Color> listproductColors = productColorService.findAllLimitDongStatus(solimit, status);
				return listproductColors;
			}
		}
		else {
			product_Color.setStatus(0);
			productColorService.UpdateProductColor(product_Color);
			if(status1 == 2) {
				return productColorService.findAllLimitDong(0, solimit);
			}
			else {
				List<Product_Color> listproductColors = productColorService.findAllLimitDongStatus(solimit, status);
				return listproductColors;
			}
		}
	}
	
	// show status
	@GetMapping("showstatus")
	@ResponseBody
	public List<Product_Color> showstatus(@RequestParam Integer solimit, @RequestParam Integer status) {
		
		if(status == 2) {
			return productColorService.findAllLimitDong(0, solimit);
		}
		else {
			List<Product_Color> listproductColors = productColorService.findAllLimitDongStatus(solimit, status);
			return listproductColors;
		}
		
	}
	
// show limit
	@GetMapping("showlimit")
	@ResponseBody 
	public List<Product_Color> showlimit(@RequestParam Integer status, @RequestParam Integer solimit) {
		
		if(status == 2) {
			return productColorService.findAllLimitDong(0, solimit);
		}
		else {
			List<Product_Color> listproductColors = productColorService.findAllLimitDongStatus(solimit, status);
			return listproductColors;
		}
	}
	
	@GetMapping("pagingproductcolor")
	@ResponseBody
	public List<Product_Color> pagingproductcolor(@RequestParam Integer spbatdau, @RequestParam Integer solimit, @RequestParam Integer status ) {
		System.err.println(spbatdau);
		if(status == 2) {
			List<Product_Color> listproductcolor = productColorService.findAllLimitDong(spbatdau,solimit );
			return listproductcolor;
		}else {

			List<Product_Color> listproductcolor = productColorService.findAllLimitstatus(spbatdau, solimit, status);
			return listproductcolor;
		}
	}
	
	// ajaxdelete
	@PostMapping("ajaxdelete")
	@ResponseBody
	public List<Product_Color> ajaxdelete(@RequestParam String id, @RequestParam Integer solimit, @RequestParam Integer status){
		Product_Color product_Color = productColorService.findById(id);
		productColorService.DeleteId(product_Color);
		if(status == 2) {
			return productColorService.findAllLimitDong(0, solimit);
		}
		else {
			List<Product_Color> listproductColors = productColorService.findAllLimitDongStatus(solimit, status);
			return listproductColors;
		}
		
	}
}
