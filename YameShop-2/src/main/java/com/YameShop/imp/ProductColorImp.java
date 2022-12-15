package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Product_Color;

public interface ProductColorImp {
	List<Product_Color> findAll();
	List<Product_Color> findByProductColorName(String productcolorName);
	boolean Save(Product_Color product_Color);
	Product_Color findById(String id);
	boolean UpdateProductColor(Product_Color product_Color);
	boolean DeleteId(Product_Color product_Color);
	
	List<Product_Color> findAllLimitDong(int startingnumber, int endstart);
	List<Product_Color> findAllLimitDongStatus(int startingnumber, int status);
	List<Product_Color> findAllLimitstatus(Integer startingnumber, Integer endstart,Integer status);
}
