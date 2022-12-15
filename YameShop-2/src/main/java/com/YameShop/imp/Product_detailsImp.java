package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Product_details;

public interface Product_detailsImp {
		
	List<Product_details> findAll();
	List<Object> finAllTop(String idcategory);
	List<Product_details> finByNewProduct(String idcategory, int limit);
	Product_details findById(Long id);
	List<Product_details> findbylist(Long id, String idcolor);
	List<Product_details> finbylistColor (String id, Long idproductd);
	
	List<Object> loandproductdeltais(String idproduct);
	Product_details finbyidspissizeidcolor(String idsp , String idcolor ,String idsize);
	boolean save(Product_details product_details);
	
	List<Product_details> loadnhaphang( Long idctsp , String idproduct);
	boolean update(Product_details product_details);
	Product_details FindbyIDProductdetails(Long id);
	
}
