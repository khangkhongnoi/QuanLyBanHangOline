package com.YameShop.imp;

import java.util.List;

import com.YameShop.domain.Product;
import com.YameShop.domain.Product_details;

public interface ProductImp {
	List<Product> findall();
	List<Product> findByProductName(String productName);
	boolean Save(Product product);
	Product findById(String id);
	boolean UpdateProduct(Product product);
	boolean DeleteId(Product product);
	List<Product> findbyProductWhereIdcategory(String idcategory , int limit);
	List<Product> findAllLimitDong(int startingnumber, int endstart);
	List<Product> findAllLimitDongStatus(int startingnumber, int status);
	List<Product> findAllLimitstatus(Integer startingnumber, Integer endstart,Integer status);
	boolean savechitiet(Product_details product_details);
	List<Product> sum();
	List<Product> search(String name , int startingnumbe ,int status);
	List<Product> searchlimit(String name , int startingnumbe);
	List<Product> fidbyCategory(String idcategory, int limit);
	List<Product> fidbyCategorybtkt(String idcategory, int startingnumber, int endstart);
	List<Product> listupdate();
	List<Product> findByViewTop();
	Product loadnhaphang(  String idproduct , Long idctsp );
	List<Product> searchview (String name , int startingnumber);
	List<Product> fidbyCategoryand(String idcategory,String cate, int limit);
	List<Product> sanphamlienquan(String id);
}
