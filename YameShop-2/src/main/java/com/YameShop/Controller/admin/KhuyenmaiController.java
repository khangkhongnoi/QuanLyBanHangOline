package com.YameShop.Controller.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Brand;
import com.YameShop.domain.Category;
import com.YameShop.domain.Product;
import com.YameShop.domain.Product_Color;
import com.YameShop.domain.Product_details;
import com.YameShop.domain.Promontion;
import com.YameShop.domain.Size;
import com.YameShop.domain.Supplier;
import com.YameShop.domain.jsonproduct;
import com.YameShop.service.ProductColorService;
import com.YameShop.service.ProductService;
import com.YameShop.service.Product_detailsServices;
import com.YameShop.service.PromontionServices;
import com.YameShop.service.SizeService;
import com.YameShop.service.SupplierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin")

public class KhuyenmaiController {
@Autowired
PromontionServices promontionServices;
	@Autowired
	ProductService productService;
	@Autowired
	Product_detailsServices product_detailsServices;
	@Autowired
    SupplierService supplierService;
 
@Autowired 
ProductColorService colorService;
@Autowired
SizeService sizeService;

@GetMapping("KhuyenMai")
public String khuyenmai(ModelMap model) {
	 List<Product> listproduct =   productService.findall();
//	 for (Product product : listproduct) {
//		System.err.println("say la brand" + product.getBrand().getBrand_Name()  );
//	}
	 List<Promontion> promontions = promontionServices.findAll();
	 model.addAttribute("promontions",promontions);
	model.addAttribute("product", listproduct);
	
	List<Product_details> details = product_detailsServices.findAll();
	   model.addAttribute("details", details);
	return "/admin/khuyenmai/bannnerkkhuyenmai";
}
 @GetMapping("TaoKM")
 public String TaoKM(ModelMap model) {
	 List<Supplier> list = supplierService.findAll();
	   model.addAttribute("listsupplier", list);
	   List<Product> listproduct = productService.findall();
	   model.addAttribute("listproduct",listproduct);
	   List<Product_details> details = product_detailsServices.findAll();
	   model.addAttribute("details", details);
	  
	  List<Product_Color> colors = colorService.findAll();
	  model.addAttribute("colors", colors);
	  List<Size> sizes = sizeService.findAll();
	  model.addAttribute("sizes",sizes);
	 return "/admin/khuyenmai/TaoKM";
 }
@PostMapping("ajaxadd")
@ResponseBody
public String ajaxadd( @RequestParam int phantram, @RequestParam String dataJson, @RequestParam String image, @RequestParam Integer cheackvalue,  @RequestParam String thoigianbatdau, @RequestParam String thoigiankethuc , @RequestParam String tenkm) throws JsonMappingException, JsonProcessingException {
		System.err.println(dataJson);
	 Promontion promontion = new Promontion();
	 promontion.setReduced_Price(phantram);
	 promontion.setStart_Time(thoigianbatdau);
	 promontion.setEnd_Time(thoigiankethuc);
	 promontion.setPromontion_Image(image);	
	 promontion.setStatus(0);
	 promontion.setVitri(cheackvalue);
	promontion.setPromontion_Name(tenkm);
	 
	 ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		jsonNode = objectMapper.readTree(dataJson);
		JsonNode jsondetails = jsonNode.get("productdetails");
		Set<Product> listDetails1 = new HashSet<>();
		for(JsonNode promdetails : jsondetails) {
			String id = promdetails.get("idproductdetails").asText();
			
			Product product = new Product();
			product.setId_Poroduct(id);
			Product setkm = productService.findById(id);
			setkm.setPromotional_price(setkm.getRetail_Price() - ((setkm.getRetail_Price() * phantram)/100));
			productService.UpdateProduct(setkm);
			listDetails1.add(product);
			
		}
		
	promontion.setProducts(listDetails1);
		promontionServices.save(promontion);
	return "";
}
@GetMapping("updateKM/{id}")
public String updateKM(@PathVariable("id") Long id ,ModelMap model) {
	Promontion promontion = promontionServices.finbyid(id);
	for (Product_details product : promontion.getProduct_details()) {
		
		model.addAttribute("product",product);
	}
	  List<Product_details> details = product_detailsServices.findAll();
	   model.addAttribute("details", details);
	model.addAttribute("promontion",promontion);
	List<Product> listproduct = productService.findall();
	   model.addAttribute("listproduct",listproduct);
	return "/admin/khuyenmai/UpdateKM";
}
@PostMapping("updatekhuyenmai")
@ResponseBody
public String updatekhuyenmai(@RequestParam int phantram,@RequestParam Long idkm, @RequestParam String dataJson, @RequestParam String image, @RequestParam Integer cheackvalue,  @RequestParam String thoigianbatdau, @RequestParam String thoigiankethuc , @RequestParam String tenkm) throws JsonMappingException, JsonProcessingException {
	System.err.println(dataJson);
	 Promontion promontion = promontionServices.finbyid(idkm);
	 promontion.setReduced_Price(phantram);
	 promontion.setStart_Time(thoigianbatdau);
	 promontion.setEnd_Time(thoigiankethuc);
	 
	 System.err.println(image);
	 promontion.setStatus(promontion.getStatus());
	 promontion.setVitri(cheackvalue);
	promontion.setPromontion_Name(tenkm);
	 
	 ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode;
		jsonNode = objectMapper.readTree(dataJson);
		JsonNode jsondetails = jsonNode.get("productdetails");
		Set<Product> listDetails1 = new HashSet<>();
		for(JsonNode promdetails : jsondetails) {
			String id = promdetails.get("idproductdetails").asText();
			
			Product product = new Product();
			product.setId_Poroduct(id);
			Product setkm = productService.findById(id);
			setkm.setPromotional_price(setkm.getRetail_Price() - ((setkm.getRetail_Price() * phantram)/100));
			productService.UpdateProduct(setkm);
			listDetails1.add(product);
			
		}
		
	promontion.setProducts(listDetails1);
		promontion.setPromontion_Image(image);	
		promontionServices.update(promontion);

	return "";
}
//Updatestatus
		@PostMapping("updatestatuskm")
		@ResponseBody
		public List<Promontion> updatestatus(@RequestParam Long id, @RequestParam Integer status ,@RequestParam Integer status1, @RequestParam Integer solimit) {
			Promontion brand = promontionServices.finbyid(id);
			
			if(status == 0) {
				brand.setStatus(1);
				promontionServices.update(brand);
				if(status1 == 2) {
					return promontionServices.findAllLimitDong(0, solimit);
				}else {
					return promontionServices.findAllLimitDongStatus(solimit, status);
				}
			}
			else {
				brand.setStatus(0);
				promontionServices.update(brand);
				if(status1 == 2) {
					return promontionServices.findAllLimitDong(0, solimit);
				}else {
					return promontionServices.findAllLimitDongStatus(solimit, status);
				}
			}
		}
		// phan trang
				@GetMapping("pagingcategoryss")
				@ResponseBody
				public List<Promontion> pagingcategory( @RequestParam int spbatdau ,@RequestParam int status ,@RequestParam int solimit) {
					if(status == 2) {
						
						List<Promontion> listbrand = promontionServices.findAllLimitDong(spbatdau, solimit);
						return listbrand;
					}
					else {
					List<Promontion> listbrand = promontionServices.findAllLimitstatus(spbatdau, solimit, status);
					
					return listbrand;
					}
				}
				
				@GetMapping("showstatuskm")
				@ResponseBody
				public List<Promontion> showstatus( @RequestParam Integer status , @RequestParam Integer solimit ) {
					
					if(status == 2) {
						
						List<Promontion> listbrand = promontionServices.findAllLimitDong(0, solimit);
						return listbrand;
					}
					else {

						List<Promontion> listbrand = promontionServices.findAllLimitDongStatus(solimit,status );
						return listbrand;
					}
							
				}
				@GetMapping("showCategorykm")
				@ResponseBody
				public List<Promontion> showCategory(@RequestParam Integer sotrang, @RequestParam Integer number) {
					if(number == 2) {
						return promontionServices.findAllLimitDong(0, sotrang);
					}
					List<Promontion> listcategory = promontionServices.findAllLimitDongStatus(sotrang, number);
					
					return listcategory;
				}
				@PostMapping("deleteKhuyenMai")
				@ResponseBody
				public List<Promontion> deletecategory(@RequestParam Long id, @RequestParam int status ,@RequestParam int solimit){
					Promontion promontion = promontionServices.finbyid(id);
					promontionServices.DeleteId(promontion);
					if(status == 2) {
						
						List<Promontion> listbrand = promontionServices.findAllLimitDong(0, solimit);
						return listbrand;
					}
					else {

						List<Promontion> listbrand = promontionServices.findAllLimitDongStatus(solimit,status );
						return listbrand;
					}		
				}
				@GetMapping("laydssanpham")
			       @ResponseBody
			       public jsonproduct laydanhsachtheoid(@RequestParam String id) throws JsonMappingException, JsonProcessingException{
			    	   
			    	   System.err.println(id);
						Product product = productService.findById(id);
						jsonproduct jsonproduct = new jsonproduct();
						
						
						jsonproduct.setId_Poroduct(product.getId_Poroduct());
						jsonproduct.setProduct_Name(product.getProduct_Name());
						jsonproduct.setId_Poroduct(id);
				
						jsonproduct.setFabric_Material(product.getFabric_Material());
						jsonproduct.setDescriptions(product.getDescriptions());
						jsonproduct.setImage(product.getImage());
						jsonproduct.setStatus(product.getStatus());
						jsonproduct.setImage(product.getImage());
						jsonproduct.setImage1(product.getImage1());
						jsonproduct.setPrice(product.getRetail_Price());
						jsonproduct.setGianha(product.getImport_Price());
						
						
						return jsonproduct;
			    	 
			       }
}
