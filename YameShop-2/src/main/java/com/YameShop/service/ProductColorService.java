package com.YameShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.YameShop.dao.ProductColorDAO;

import com.YameShop.domain.Product_Color;
import com.YameShop.imp.ProductColorImp;


@Service
public class ProductColorService implements ProductColorImp{
	@Autowired
	ProductColorDAO productColorDAO;

	@Override
	public List<Product_Color> findAll() {
		// TODO Auto-generated method stub
		return productColorDAO.findAll();
	}

	@Override
	public List<Product_Color> findByProductColorName(String productcolorName) {
		// TODO Auto-generated method stub
		return productColorDAO.findByProductColorName(productcolorName);
	}

	@Override
	public boolean Save(Product_Color product_Color) {
		// TODO Auto-generated method stub
		return productColorDAO.Save(product_Color);
	}

	@Override
	public Product_Color findById(String id) {
		// TODO Auto-generated method stub
		return productColorDAO.findById(id);
	}

	@Override
	public boolean UpdateProductColor(Product_Color product_Color) {
		// TODO Auto-generated method stub
		return productColorDAO.UpdateProductColor(product_Color);
	}

	@Override
	public boolean DeleteId(Product_Color product_Color) {
		// TODO Auto-generated method stub
		return productColorDAO.DeleteId(product_Color);
	}

	@Override
	public List<Product_Color> findAllLimitDong(int startingnumber, int endstart) {
		// TODO Auto-generated method stub
		return productColorDAO.findAllLimitDong(startingnumber, endstart);
	}

	@Override
	public List<Product_Color> findAllLimitDongStatus(int startingnumber, int status) {
		// TODO Auto-generated method stub
		return productColorDAO.findAllLimitDongStatus(startingnumber, status);
	}

	@Override
	public List<Product_Color> findAllLimitstatus(Integer startingnumber, Integer endstart, Integer status) {
		// TODO Auto-generated method stub
		return productColorDAO.findAllLimitstatus(startingnumber, endstart, status);
	}
	

}
