package com.YameShop.Controller.admin;

import java.sql.Timestamp;
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

import com.YameShop.domain.AppUser;
import com.YameShop.domain.Coupon_Details;
import com.YameShop.domain.Import_coupon;
import com.YameShop.domain.Product;
import com.YameShop.domain.Product_Color;
import com.YameShop.domain.Product_details;
import com.YameShop.domain.Size;
import com.YameShop.domain.Supplier;
import com.YameShop.service.Import_couponService;
import com.YameShop.service.ProductColorService;
import com.YameShop.service.ProductService;
import com.YameShop.service.Product_detailsServices;
import com.YameShop.service.SizeService;
import com.YameShop.service.SupplierService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("admin")
public class TraHangNhap {
	 @Autowired
	    SupplierService supplierService;
	 @Autowired
	 ProductService productService;
	@Autowired
	Product_detailsServices product_detailsServices;
	@Autowired 
	ProductColorService colorService;
	@Autowired
	SizeService sizeService;
	@Autowired
 Import_couponService couponService; 

		@GetMapping("TraHangNhap")
		public String TraHangNhap(ModelMap model) {
			 List<Import_coupon> list = couponService.finallStatus(5);
	    	   model.addAttribute("listimport" , list);
			return "/admin/Tra_Hang/trahang";
		}
		
	@GetMapping("PhiêuTraHang")
	public String PhiêuTraHang( ModelMap model) {
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
			return "/admin/Tra_Hang/phieutrahang";
	}
	
	  @PostMapping("savetraphang")
      @ResponseBody
      public String savephieunhaphang( @RequestParam String dataJson) throws JsonMappingException, JsonProcessingException {
   	   System.err.println(dataJson);
   	   ObjectMapper objectMapper = new ObjectMapper();
  			JsonNode jsonNode;
  			jsonNode = objectMapper.readTree(dataJson);
 			String idtk = jsonNode.get("nguoitao").asText();
 			
  			Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
  			String ncc = jsonNode.get("ncc").asText();
  			Supplier supplier = new Supplier();
  			supplier.setId_Supplier(ncc);
  			
  			Integer giamgia = jsonNode.get("giamgia").asInt();
  			String ghichu = jsonNode.get("ghichu").asText();
  			Import_coupon import_coupon = new Import_coupon();
  			JsonNode jsondetails = jsonNode.get("productdetails");
  			Integer sum = 0;
  			Integer quantity = 0;
  			Set<Coupon_Details> coupon_Details = new HashSet<>();
  			for (JsonNode coupon : jsondetails) {
				Long idproductdetails = coupon.get("idproductdetails").asLong();
				Integer soluong = coupon.get("soluong").asInt();
				Integer gianhap = coupon.get("gianhap").asInt();
				
				Coupon_Details details = new Coupon_Details();
				Product_details product_details = new Product_details();
				product_details.setId_Product_Details(idproductdetails);
				
				
				details.setDon_Gia(gianhap);
				details.setQuanlity(soluong);
				details.setThanhtien(gianhap * soluong);
				details.setProduct_details(product_details);
				details.setImport_coupon(import_coupon);
				sum += gianhap * soluong;
				quantity +=  soluong;
				Product_details details2 = product_detailsServices.findById(idproductdetails);
				product_details.setQuantity(details2.getQuantity() - soluong);
				Product product = new Product();
				product.setId_Poroduct(details2.getProduct().getId_Poroduct());
				Product_Color product_Color = new Product_Color();
				product_Color.setId_Product_Color(details2.getProduct_Color().getId_Product_Color());
				Size size = new Size();
				size.setId_Size(details2.getSize().getId_Size());
				product_details.setProduct(product);
				product_details.setSize(size);
				product_details.setProduct_Color(product_Color);
				product_detailsServices.update(product_details);
				
				
				coupon_Details.add(details);
			}
  		
  			import_coupon.setSupplier(supplier);
  			import_coupon.setStatus(5);
  			import_coupon.setGiamgia((sum * giamgia)/100);
  			import_coupon.setGhichu(ghichu);
  			import_coupon.setData_Founded(timestamp1);
  			import_coupon.setSumquantity(quantity);
  			import_coupon.setTotal_MoneyInteger(sum);
  			import_coupon.setCoupon_Details(coupon_Details);
  			couponService.save(import_coupon);
  			
  			
   	   return "";
      }
	  @GetMapping("CTTNH/{id}")

      public String CTHN(@PathVariable("id") Integer id, ModelMap model) {
   	   Import_coupon import_coupon = couponService.findbyid(id);
   	   model.addAttribute("import_coupon",import_coupon);
   	   System.err.println(import_coupon.getId_Coupon());
   	   List<Supplier> list = supplierService.findAll();
   	   model.addAttribute("listsupplier", list);
   	   List<Product_details> details = product_detailsServices.findAll();
   	   model.addAttribute("details", details);
   	   for ( Coupon_Details coupon_Details : import_coupon.getCoupon_Details()) {
			model.addAttribute("coupon_Details" , coupon_Details);
			System.err.println(coupon_Details.getProduct_details().getId_Product_Details());
		}
   	return "/admin/Tra_Hang/updateth";
      }
	  @PostMapping("updatephieutrahang")
      @ResponseBody
      public String updatephieunhaphang( @RequestParam String dataJson) throws JsonMappingException, JsonProcessingException {
   	   System.err.println(dataJson);
   	   ObjectMapper objectMapper = new ObjectMapper();
  			JsonNode jsonNode;
  			jsonNode = objectMapper.readTree(dataJson);

  			Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
  			String ncc = jsonNode.get("ncc").asText();
  			Supplier supplier = new Supplier();
  			supplier.setId_Supplier(ncc);
  			Integer id = jsonNode.get("id").asInt();
  			
  			List<Coupon_Details> coupon_Details1 = couponService.finbyIDcoupondetail(id);
			for (Coupon_Details coupon_Details : coupon_Details1) {

				Coupon_Details coupon_Details10 = couponService.findbyidCT(coupon_Details.getId_Coupon_Details());
				couponService.deletelist(coupon_Details10);
			}
  			Integer giamgia = jsonNode.get("giamgia").asInt();
  			String ghichu = jsonNode.get("ghichu").asText();
  			Import_coupon import_coupon = couponService.findbyid(id);
  			JsonNode jsondetails = jsonNode.get("productdetails");
  			Integer sum = 0;
  			Integer quantity = 0;
  			Set<Coupon_Details> coupon_Details = new HashSet<>();
  			for (JsonNode coupon : jsondetails) {
				Long idproductdetails = coupon.get("idproductdetails").asLong();
				Integer soluong = coupon.get("soluong").asInt();
				Integer gianhap = coupon.get("gianhap").asInt();
				
				Coupon_Details details = new Coupon_Details();
				Product_details product_details = new Product_details();
				product_details.setId_Product_Details(idproductdetails);
				
				
				details.setDon_Gia(gianhap);
				details.setQuanlity(soluong);
				details.setThanhtien(gianhap * soluong);
				details.setProduct_details(product_details);
				details.setImport_coupon(import_coupon);
				sum += gianhap * soluong;
				quantity +=  soluong;
				Product_details details2 = product_detailsServices.findById(idproductdetails);
				product_details.setQuantity(details2.getQuantity() + quantity);
				Product product = new Product();
				product.setId_Poroduct(details2.getProduct().getId_Poroduct());
				Product_Color product_Color = new Product_Color();
				product_Color.setId_Product_Color(details2.getProduct_Color().getId_Product_Color());
				Size size = new Size();
				size.setId_Size(details2.getSize().getId_Size());
				product_details.setProduct(product);
				product_details.setSize(size);
				product_details.setProduct_Color(product_Color);
				product_detailsServices.update(product_details);
				
				
				
				coupon_Details.add(details);
			}
  			import_coupon.setSupplier(supplier);
  			import_coupon.setStatus(5);
  			import_coupon.setGiamgia((sum * giamgia)/100);
  			import_coupon.setGhichu(ghichu);
  			import_coupon.setData_Founded(timestamp1);
  			import_coupon.setSumquantity(quantity);
  			import_coupon.setTotal_MoneyInteger(sum);
  			import_coupon.setCoupon_Details(coupon_Details);
  			couponService.update(import_coupon);
  			
  			
   	   return "";
      }
	  @PostMapping("deletetrahang")
		@ResponseBody
		public List<Import_coupon> deleteimport(@RequestParam Integer id){
			Import_coupon import_coupon = couponService.findbyid(id);
			import_coupon.setStatus(2);
			
			couponService.update(import_coupon);
			
		//	Product_details product_details = new Product_details();

			for (Coupon_Details coupon_Details : import_coupon.getCoupon_Details()) {
				Product_details product_details = product_detailsServices.findById(coupon_Details.getProduct_details().getId_Product_Details());
				product_details.setQuantity(product_details.getQuantity() - coupon_Details.getQuanlity());
				product_detailsServices.update(product_details);
			}
			
			return couponService.finallStatus(5);
		}
}
