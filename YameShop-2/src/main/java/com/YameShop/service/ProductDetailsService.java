package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.YameShop.dao.ProductDetailsDAO;
import com.YameShop.domain.Product_details;
import com.YameShop.imp.ProductDetails;
@Service
public class ProductDetailsService implements ProductDetails {
@Autowired
ProductDetailsDAO productDetailsDAO;
	@Override
	public List<Product_details> findAll() {
		// TODO Auto-generated method stub
		return productDetailsDAO.findAll();
	}
	@Override
	public List<Product_details> findByAll(String idproductcolor, String idsize
		) {
		// TODO Auto-generated method stub
		return productDetailsDAO.findByAll(idproductcolor, idsize);
	}
	@Override
	public List<Product_details> finbyid(Long id) {
		// TODO Auto-generated method stub
		return productDetailsDAO.finbyid(id);
	}
	@Override
	public boolean Updates(Product_details product_details) {
		// TODO Auto-generated method stub
		return productDetailsDAO.Updates(product_details);
	}
	@Override
	public List<Product_details> finbuMau(String id) {
		// TODO Auto-generated method stub
		return productDetailsDAO.finbuMau(id);
	}
	

}
