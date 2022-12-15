package com.YameShop.Controller.admin;


import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Category;
import com.YameShop.domain.Product;

import com.YameShop.domain.Product_Color;
import com.YameShop.domain.Product_details;
import com.YameShop.domain.Size;
import com.YameShop.domain.jsonproduct;
import com.YameShop.service.BrandSerive;
import com.YameShop.service.CategoryService;
import com.YameShop.service.ProductColorService;
import com.YameShop.service.ProductDetailsService;
import com.YameShop.service.ProductService;
import com.YameShop.service.Product_detailsServices;
import com.YameShop.service.SizeService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import groovyjarjarantlr4.v4.parse.ANTLRParser.sync_return;




@Controller
@RequestMapping("admin/product")
public class ProductController {
 
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	BrandSerive brandSerive;
	@Autowired
	ProductColorService productColorService;
	@Autowired
	SizeService sizeService;
	@Autowired
	ProductDetailsService productDetailsService;
	@Autowired
	Product_detailsServices detailsServices;
	@GetMapping("add")
	


	public 	String product(ModelMap model) {
		
		  List<Product> listproduct =   productService.findall();
		  List<Product> sum = productService.sum();
		  model.addAttribute("sum", sum);
		model.addAttribute("product", listproduct);
		return "admin/product/productlist";
	}
	
	@ModelAttribute("category")
	@Transactional
	public List<Category> getCategory(){
		
		return categoryService.findall().stream().map(item ->{
			Category dto = new Category();
			BeanUtils.copyProperties(item, dto);
			
			
			return dto;
		}).toList();
		
		
	}
	@ModelAttribute("brand")
	@Transactional
	public List<Brand> getBrand(){
		
		return brandSerive.findAll().stream().map(item ->{
			Brand dto = new Brand();
		
			BeanUtils.copyProperties(item, dto);
			
			return dto;
		}).toList();
	}
	@ModelAttribute("color")
		public List<Product_Color> getproductcolor(){
			return productColorService.findAll().stream().map(item ->{
				Product_Color dto = new Product_Color();
			
				BeanUtils.copyProperties(item, dto);
				
				return dto;
			}).toList();
		}
		@ModelAttribute("size")
		public List<Size> getsize(){
			return sizeService.findAll();
		}
	// save 
	@PostMapping("ajaxsaveproduct")
	@ResponseBody
	public List<Product> ajaxsaveproduct(@RequestParam String dataJson , @RequestParam Integer status, @RequestParam Integer solimit) throws JsonMappingException, JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
	//	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//Product product = objectMapper.readValue(dataJson, Product.class);
		jsonNode = objectMapper.readTree(dataJson);
		System.err.println(dataJson);
		Product product = new Product();
		Product product1 = new Product();
		List<Product> list = new ArrayList<>();
		list.add(product1);
		
		String productname = jsonNode.get("Product_Name").asText();
		List<Product> listname = productService.findByProductName(productname);
		if(listname.size() > 0) {
			return list;
		}
		
		String fabric_material = jsonNode.get("fabric_material").asText();
		Integer gianhap = jsonNode.get("gianhap").asInt();
		Integer giaban = jsonNode.get("giaban").asInt();
		String descriptions = jsonNode.get("descriptions").asText();
		String idcategory = jsonNode.get("idcategory").asText();
		String idbrand = jsonNode.get("idbrand").asText();
		String imagen = jsonNode.get("Imagenen").asText();
		String Image = jsonNode.get("Image").asText();
		JsonNode jsondetails = jsonNode.get("productdetails");
		 Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		 Date date = new Date();
			product.setProduct_Name(productname);
			product.setFabric_Material(fabric_material);
			//product.setPrice(price);
			product.setImport_Price(gianhap);
			product.setRetail_Price(giaban);
			product.setImage1(imagen);
			product.setDescriptions(descriptions);
			product.setImage(Image);
			product.setStatus(0);
			product.setPromotional_price(0);
			product.setWiewtruycap(0);
			Category category = new Category();
			category.setId_Category(idcategory);
			Brand brand = new Brand();
			brand.setId_Brand(idbrand);
			category.setId_Category(idcategory);
			product.setCategory(category);
			product.setBrand(brand);
			List<Product> listrong = new ArrayList<>();
			Product productrong = new Product();
			listrong.add(productrong);
			 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			// Date date = new Date();
			Set<Product_details> listDetails1 = new HashSet<>();
			for (JsonNode productdetails : jsondetails) {
				
				String idsizedetails = productdetails.get("idsize").asText();
				String idcolor = productdetails.get("idproductclor").asText();
				Integer quantity = productdetails.get("quantity").asInt();
				

//					List<Product_details> list2 = productDetailsService.findByAll(idcolor, idsizedetails);
//					System.err.println(list2);
//					if(list2.size() > 0 ) {
//						return listrong;
//					}else {
						
						Size size = new Size();
						size.setId_Size(productdetails.get("idsize").asText());
						System.err.println(size);
						
						Product_Color color = new Product_Color();
						color.setId_Product_Color(idcolor);
						Product_details product_details = new Product_details();
						product_details.setProduct_Color(color);
						product_details.setSize(size);
						product_details.setQuantity(quantity);
						product_details.setProduct(product);
						listDetails1.add(product_details);
					}
					
				
			product.setProduct_details(listDetails1);
		
			productService.Save(product);
			List<Product> listproduct =   productService.findall();
			
	//	}
		
			if(status == 2) {
				return productService.findAllLimitDong(0, solimit);
			}
			else {
				return productService.findAllLimitDongStatus(solimit, status);
			}
		
	}
	
	// update
	@PostMapping("ajaxupdate")
	@ResponseBody

	public List<Product> ajaxupdateproduct(@RequestParam String dataJson) throws JsonMappingException, JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
	//	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//Product product = objectMapper.readValue(dataJson, Product.class);
		objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		jsonNode = objectMapper.readTree(dataJson);
		
		System.err.println(dataJson);
		
		List<Product> list = new ArrayList<>();
	//	list.add(product);
		String idproduct = jsonNode.get("idproduct").asText();
		Product product = productService.findById(idproduct);
		
		String productname = jsonNode.get("Product_Name").asText();
		String fabric_material = jsonNode.get("fabric_material").asText();
		
		String descriptions = jsonNode.get("descriptions").asText();
		String idcategory = jsonNode.get("idcategory").asText();
		String idbrand = jsonNode.get("idbrand").asText();
		String imagen = jsonNode.get("Imagenen").asText();
		String Image = jsonNode.get("Image").asText();
		JsonNode jsondetails = jsonNode.get("productdetails");
		 Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
		 Date date = new Date();
			product.setProduct_Name(productname);
			product.setFabric_Material(fabric_material);
			//product.setPrice(price);
			product.setImage1(imagen);
			product.setDescriptions(descriptions);
			product.setImage(Image);
			System.err.println(imagen);
			product.setStatus(product.getStatus());
			product.setPromotional_price(product.getPromotional_price());
			product.setWiewtruycap(product.getWiewtruycap());
			Category category = new Category();
			category.setId_Category(idcategory);
			Brand brand = new Brand();
			brand.setId_Brand(idbrand);
			 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//			// Date date = new Date();
			Set<Product_details> listDetails1 = new HashSet<>();
			for (JsonNode productdetails : jsondetails) {
				
				String idsizedetails = productdetails.get("idsize").asText();
				String idcolor = productdetails.get("idproductclor").asText();
				Integer quantity = productdetails.get("quantity").asInt();
				Long usermanual = productdetails.get("usermanual").asLong();
				Size size = new Size();
				size.setId_Size(productdetails.get("idsize").asText());
				System.err.println(size);
				
				Product_Color color = new Product_Color();
				color.setId_Product_Color(idcolor);
				if(usermanual != 0) {
					Product_details product_detailss = detailsServices.FindbyIDProductdetails(usermanual);
					
					if(product_detailss.getId_Product_Details() == usermanual) {
						product_detailss.setQuantity(quantity);
						product_detailss.setProduct_Color(color);
						product_detailss.setSize(size);
						detailsServices.update(product_detailss);
					}
					
					
				}
				else {
					Product_details product_details = new Product_details();
					product_details.setProduct_Color(color);
					product_details.setSize(size);
					product_details.setQuantity(quantity);
					product_details.setProduct(product);
					listDetails1.add(product_details);
				}
						
//						Size size = new Size();
//						size.setId_Size(productdetails.get("idsize").asText());
//						System.err.println(size);
//						
//						Product_Color color = new Product_Color();
//						color.setId_Product_Color(idcolor);
//						Product_details product_details = new Product_details();
//						product_details.setProduct_Color(color);
//						product_details.setSize(size);
//						product_details.setQuantity(quantity);
//						product_details.setProduct(product);
//						listDetails1.add(product_details);
				product.setProduct_details(listDetails1);
					}
	
			
		
			
			productService.UpdateProduct(product);
			return productService.findall();
			
		
	}
	
	// bingding update
			@GetMapping("bingdingupdate")
			@ResponseBody
			public jsonproduct bingdingupdate(@RequestParam String id){
				System.err.println(id);
				Product product = productService.findById(id);
				jsonproduct jsonproduct = new jsonproduct();
				
				
				//jsonproduct.setId_Poroduct(product.getId_Poroduct());
				jsonproduct.setProduct_Name(product.getProduct_Name());
				jsonproduct.setId_Poroduct(id);
				//jsonproduct.setPrice(product.getPrice());
				jsonproduct.setFabric_Material(product.getFabric_Material());
				jsonproduct.setDescriptions(product.getDescriptions());
				jsonproduct.setImage(product.getImage());
				jsonproduct.setStatus(product.getStatus());
				jsonproduct.setImage(product.getImage());
				jsonproduct.setImage1(product.getImage1());
				jsonproduct.setPrice(product.getRetail_Price());
				jsonproduct.setGianha(product.getImport_Price());
				Category category = new Category();
				category.setId_Category(product.getCategory().getId_Category());
				category.setCategory_Name(product.getCategory().getCategory_Name());
				Brand brand = new Brand();
				brand.setId_Brand(product.getBrand().getId_Brand());
				brand.setBrand_Name(product.getBrand().getBrand_Name());
				Set<Product_details> product_details = new HashSet<>();
				
				for (Product_details value : product.getProduct_details()) {
					Product_details details = new Product_details();
					details.setId_Product_Details(value.getId_Product_Details());
					
					Product_Color product_Color = new Product_Color();
					product_Color.setId_Product_Color(value.getProduct_Color().getId_Product_Color());
					product_Color.setColor_Name(value.getProduct_Color().getColor_Name());
					
					details.setProduct_Color(product_Color);
					
					Size size = new Size();
					size.setId_Size(value.getSize().getId_Size());
					size.setSize_Name(value.getSize().getSize_Name());
					
					details.setSize(size);
					details.setQuantity(value.getQuantity());
				
					
					product_details.add(details);
				}
				
				jsonproduct.setProduct_details(product_details);
				jsonproduct.setBrand(brand);
				jsonproduct.setCategory(category);
				
				
				return jsonproduct;
			}
			
		
		// updatestatus
			
		@PostMapping("updatestatus")
		@ResponseBody
		public List<Product> updatestatus(@RequestParam String id, @RequestParam Integer status, @RequestParam Integer solimit, @RequestParam Integer status1) {
			Product product = productService.findById(id);
			
			if(status == 0) {
				product.setStatus(1);
				productService.UpdateProduct(product);
				if(status1 == 2) {
					return productService.findAllLimitDong(0, solimit);
				}
				else {
					return productService.findAllLimitDongStatus(solimit, status);
				}
			}
			else {
				product.setStatus(0);
				productService.UpdateProduct(product);
				if(status1 == 2) {
					return productService.findAllLimitDong(0, solimit);
				}
				else {
					return productService.findAllLimitDongStatus(solimit, status);
				}
			}
		}
		
		// show status
		
		@GetMapping("showstatus")
		@ResponseBody
		public List<Product> showstatus(@RequestParam Integer solimit, @RequestParam Integer status){
			if(status == 2) {
				return productService.findAllLimitDong(0, solimit);
			}
			else {
				return productService.findAllLimitDongStatus(solimit, status);
			}
		}
		
		// showlimit
		@GetMapping("showlimit")
		@ResponseBody
		public List<Product> showlimit(@RequestParam Integer status, @RequestParam Integer solimit){
			if(status == 2) {
				return productService.findAllLimitDong(0, solimit);
			}
			else {
				return productService.findAllLimitDongStatus(solimit, status);
			}
		}
		
		// pagingproduct
		@GetMapping("pagingproduct")
		@ResponseBody
		public List<Product> pagingproduct(@RequestParam Integer spbatdau, @RequestParam Integer solimit, @RequestParam Integer status){
			if(status == 2) {
				return productService.findAllLimitDong(spbatdau, solimit);
			}
			else {
				return productService.findAllLimitstatus(spbatdau, solimit, status);
			}
		}
		
	// seach ajax 
		@GetMapping("searchByName")
		@ResponseBody
		public List<Product> searchByName(@RequestParam String name, @RequestParam Integer solimit, @RequestParam Integer status){
		
			if(status == 2) {
				return productService.searchlimit(name,solimit );
			}
			else {
			
			List<Product> listsearch = productService.search(name ,solimit, status);
			return listsearch;
			}
		}
		@PostMapping("ajaxdelete")
		@ResponseBody
		public List<Product> ajaxdelete(@RequestParam String id, @RequestParam int status ,@RequestParam int solimit) {
		
		Product product = productService.findById(id);
		product.setStatus(3);
		productService.UpdateProduct(product);
			if(status == 2) {
				return productService.findAllLimitDong(0, solimit);
			}
			else {
				return productService.findAllLimitDongStatus(solimit, status);
			}
		}
		
}
