package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.Product_detailDAO;
import com.YameShop.domain.Product_details;
import com.YameShop.imp.Product_detailsImp;
@Service
public class Product_detailsServices implements Product_detailsImp {
@Autowired
Product_detailDAO product_detailDAO;
	@Override
	public List<Product_details> findAll() {
		// TODO Auto-generated method stub
		return product_detailDAO.findAll();
	}
	@Override
	public List<Object> finAllTop(String idcategory) {
		// TODO Auto-generated method stub
		return product_detailDAO.finAllTop(idcategory);
	}
	@Override
	public List<Product_details> finByNewProduct(String idcategory , int limit) {
		// TODO Auto-generated method stub
		return product_detailDAO.finByNewProduct(idcategory , limit);
	}
	@Override
	public Product_details findById(Long id) {
		// TODO Auto-generated method stub
		return product_detailDAO.findById(id);
	}
	@Override
	public List<Product_details> findbylist(Long id , String idcolor) {
		// TODO Auto-generated method stub
		return product_detailDAO.findbylist(id ,idcolor);
	}
	@Override
	public List<Product_details> finbylistColor(String id ,Long idproductd) {
		// TODO Auto-generated method stub
		return product_detailDAO.finbylistColor(id ,idproductd);
	}
	@Override
	public List<Object> loandproductdeltais(String idproduct) {
		// TODO Auto-generated method stub
		return product_detailDAO.loandproductdeltais(idproduct);
	}
	@Override
	public Product_details finbyidspissizeidcolor(String idsp , String idcolor , String idsize) {
		// TODO Auto-generated method stub
		return product_detailDAO.finbyidspissizeidcolor(idsp, idcolor ,idsize)
				;
	}
	@Override
	public boolean save(Product_details product_details) {
		// TODO Auto-generated method stub
		return product_detailDAO.save(product_details);
	}
	@Override
	public List<Product_details> loadnhaphang(Long idctsp, String idproduct) {
		// TODO Auto-generated method stub
		return product_detailDAO.loadnhaphang(idctsp, idproduct);
	}
	@Override
	public boolean update(Product_details product_details) {
		// TODO Auto-generated method stub
		return product_detailDAO.update(product_details);
	}
	@Override
	public Product_details FindbyIDProductdetails(Long id) {
		// TODO Auto-generated method stub
		return product_detailDAO.FindbyIDProductdetails(id);
	}

}
