package com.YameShop.Controller.admin;

import java.awt.Image;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Images;
import com.YameShop.domain.Product;
import com.YameShop.service.ImagesServices;
import com.YameShop.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin/images")
@WebServlet(urlPatterns = {"/admin/images"})

public class ImagesController {
	@Autowired
	ProductService productService;
	@Autowired
	ImagesServices imagesServices;
	@GetMapping("add")
	public String images(ModelMap model) {
	
		List<Product> listproduct = productService.findall();
		model.addAttribute("listproduct", listproduct);
		List<Images> listimage =imagesServices.listAll();
		model.addAttribute("listimage" , listimage);
		return "/admin/images/listimages";
	}
	
	
	
	@PostMapping("ajaxsaveimage")
	@ResponseBody
	
	public List<Images> ajaxsave(@RequestParam String Manghinh , @RequestParam String brandName) throws JsonMappingException, JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		System.err.println(Manghinh);
			jsonNode = objectMapper.readTree(Manghinh);
			for (JsonNode jsonNode2 : jsonNode) {
				String hinh = jsonNode2.get("Hinh").asText();
				Images images = new Images();
				Product product = new Product();
				images.setImage_Name(hinh);
				product.setId_Poroduct(brandName);
				images.setProduct(product);
				imagesServices.Save(images);
				
				System.err.println(hinh);
			}
		
			
		return imagesServices.listAll();
	}
	
	@GetMapping("bingdingimage")
	@ResponseBody
	public List<Images>  bingdingimage(@RequestParam String id) {
		
		return imagesServices.images(id);
	}
	
	@GetMapping("deteteimage")
	@ResponseBody 
	public List<Images> deteteiamge(@RequestParam String id){
		
		List<Images> list = imagesServices.images(id);
		 for (Images images : list) {
			imagesServices.Delete(images);
		}
		return imagesServices.listAll();
	}
	
}
