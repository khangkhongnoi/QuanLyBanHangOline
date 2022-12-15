package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Product_details;

public interface ProductDetails {
	List<Product_details> findAll();
	List<Product_details> findByAll(String idproductcolor, String idsize);
	List<Product_details> finbyid(Long id);
	boolean Updates(Product_details product_details);
	List<Product_details> finbuMau(String id);

}
