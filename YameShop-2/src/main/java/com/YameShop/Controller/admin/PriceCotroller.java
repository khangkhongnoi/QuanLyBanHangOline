package com.YameShop.Controller.admin;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.YameShop.domain.Import_price;
import com.YameShop.domain.Price;
import com.YameShop.domain.Product;
import com.YameShop.domain.Promotional_price;
import com.YameShop.service.PriceServices;
import com.YameShop.service.ProductService;

@Controller
@RequestMapping("admin/price")
public class PriceCotroller {
	@Autowired
	ProductService productService;
	@Autowired
	PriceServices priceServices;
	@GetMapping("add")
	public String add(ModelMap model) {
		 List<Product> listproduct =   productService.findall();
		  List<Product> sum = productService.sum();
		  model.addAttribute("sum", sum);
		model.addAttribute("product", listproduct);
		return "/admin/price/listprice";
	}
	
	@PostMapping("ajaxupdateprice")
	@ResponseBody
	public List<Product> ajaxupdateprice(@RequestParam Integer image,  @RequestParam String idsp , @RequestParam int loaigia  , @RequestParam Integer giamoi ,@RequestParam Integer giatheo ){
		System.err.println(image);

		Product product = productService.findById(idsp);
		System.err.println(loaigia + "="+ giatheo);
	 System.err.println(idsp);
		List<Product> products =productService.sum();
		Price price = new Price();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		if(image == 10) {
			if(loaigia == 3 && giatheo == 1) {
				product.setRetail_Price(giamoi);
				productService.UpdateProduct(product);
			}else {
				List<Product> lisi = productService.sum();
				for (Product product2 : lisi) {
					product2.setRetail_Price(product2.getRetail_Price() - ((product2.getRetail_Price()*giamoi)/100));
					productService.UpdateProduct(product2);
				}
			}
		}
		else {
		
		if(loaigia == 3 && giatheo == 1) {
		product.setRetail_Price(giamoi);
	
		productService.UpdateProduct(product);
		}
		if(loaigia == 3 && giatheo == 2){
			product.setRetail_Price(product.getRetail_Price() - ((product.getRetail_Price() * giamoi)/ 100));
			productService.UpdateProduct(product);
		}
		

		if( loaigia == 1 && giatheo == 1) {
			

			product.setImport_Price(giamoi);

			productService.UpdateProduct(product);
		}
		if( loaigia == 1 && giatheo == 2){
			product.setImport_Price(product.getImport_Price() - ((product.getImport_Price() * giamoi)/100));

			productService.UpdateProduct(product);
		}

		if(loaigia ==  4 && giatheo == 1) {

			product.setPromotional_price(giamoi);

			productService.UpdateProduct(product);
		}
		if(loaigia ==  4 && giatheo == 2) {
			product.setPromotional_price(product.getPromotional_price() - ((product.getPromotional_price() * giamoi)/100));

			productService.UpdateProduct(product);
		}
		}
		return productService.findall();
	}
	
	//hienthisosp
	@GetMapping("showlimit")
	@ResponseBody
	public List<Product> showlimit( @RequestParam int solimt){
		
		return productService.findAllLimitDong(0, solimt);
	}
	// phantrang
	@GetMapping("pagingproduct")
	@ResponseBody
	public List<Product> pagingproduct(@RequestParam Integer spbatdau, @RequestParam Integer solimit){
		return productService.findAllLimitDong(spbatdau, solimit);
	}
	//timkiem
	@GetMapping("searchByName")
	@ResponseBody
	public List<Product> searchByName(@RequestParam String name, @RequestParam Integer solimit){
		return productService.searchlimit(name, solimit);
	}
}
