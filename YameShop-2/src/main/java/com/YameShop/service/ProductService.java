package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.ProductDAO;
import com.YameShop.domain.Product;
import com.YameShop.domain.Product_details;
import com.YameShop.imp.ProductImp;

@Service
public class ProductService implements ProductImp {

	@Autowired
	ProductDAO productDAO;
	@Override
	public List<Product> findall() {
		// TODO Auto-generated method stub
		return productDAO.findall();
	}
	@Override
	public List<Product> findByProductName(String productName) {
		// TODO Auto-generated method stub
		return productDAO.findByProductName(productName);
	}
	@Override
	public boolean Save(Product product) {
		// TODO Auto-generated method stub
		return productDAO.Save(product);
	}
	@Override
	public Product findById(String id) {
		// TODO Auto-generated method stub
		return productDAO.findById(id);
	}
	@Override
	public boolean UpdateProduct(Product product) {
		// TODO Auto-generated method stub
		return productDAO.UpdateProduct(product);
	}
	@Override
	public boolean DeleteId(Product product) {
		// TODO Auto-generated method stub
		return productDAO.DeleteId(product);
	}
	@Override
	public List<Product> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return  productDAO.findAllLimitDong(startingnumber, endstart);
	}
	@Override
	public List<Product> findAllLimitDongStatus(int startingnumber, int status) {
		// TODO Auto-generated method stub
		return productDAO.findAllLimitDongStatus(startingnumber, status);
	}
	@Override
	public List<Product> findAllLimitstatus(Integer startingnumber, Integer endstart, Integer status) {
		// TODO Auto-generated method stub
		return productDAO.findAllLimitstatus(startingnumber, endstart, status);
	}
	@Override
	public boolean savechitiet(Product_details product_details) {
		// TODO Auto-generated method stub
		return productDAO.savechitiet(product_details);
	}
	@Override
	public List<Product> findbyProductWhereIdcategory(String idcategory, int limit) {
		// TODO Auto-generated method stub
		return productDAO.findbyProductWhereIdcategory(idcategory, limit);
	}
	@Override
	public List<Product> sum() {
		// TODO Auto-generated method stub
		return productDAO.sum();
	}
	@Override
	public List<Product> search(String name ,int startingnumber ,int status) {
		// TODO Auto-generated method stub
		return productDAO.search(name ,startingnumber ,status);
	}
	@Override
	public List<Product> searchlimit(String name, int startingnumbe) {
		// TODO Auto-generated method stub
		return productDAO.searchlimit(name, startingnumbe);
	}
	@Override
	public List<Product> fidbyCategory(String idcategory, int limit) {
		// TODO Auto-generated method stub
		return productDAO.fidbyCategory(idcategory, limit);
	}
	@Override
	public List<Product> listupdate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Product> findByViewTop() {
		
		return productDAO.findByViewTop();
	}
	@Override
	public Product loadnhaphang(String idproduct, Long idctsp) {
		// TODO Auto-generated method stub
		return productDAO.loadnhaphang(idproduct, idctsp);
	}
	@Override
	public List<Product> searchview(String name, int startingnumber) {
		// TODO Auto-generated method stub
		return productDAO.searchview(name, startingnumber);
	}
	@Override
	public List<Product> fidbyCategorybtkt(String idcategory, int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return productDAO.fidbyCategorybtkt(idcategory, startingnumber, endstart);
	}
	@Override
	public List<Product> fidbyCategoryand(String idcategory, String cate, int limit) {
		// TODO Auto-generated method stub
		return productDAO.fidbyCategoryand(idcategory, cate, limit);
	}
	@Override
	public List<Product> sanphamlienquan(String id) {
		// TODO Auto-generated method stub
		return productDAO.sanphamlienquan(id);
	}

}
